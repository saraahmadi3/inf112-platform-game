package game;


import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;

import game.PosServer;
import game.Network;
import game.Network.Login;
import game.Network.PlayerPos;
import game.Network.Register;
import game.Network.RegistrationRequired;
import game.Network.UpdatePlayer;

public class PosClient {
	UI ui;
	Client client;
	Login login;
	private GameState game;
	int id;

	public PosClient (GameState game) {
		client = new Client();
		client.start();
		this.game=game;

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(client);

		// ThreadedListener runs the listener methods on a different thread.
		client.addListener(new ThreadedListener(new Listener() {
			public void connected (Connection connection) {
			}

			public void received (Connection connection, Object object) {
				if (object instanceof RegistrationRequired) {
					Register register = new Register();
					register.id = id;
					client.sendTCP(register);
				}

				if (object instanceof UpdatePlayer) {
					ui.updatePlayer((UpdatePlayer)object);
					return;
				}
			}

			public void disconnected (Connection connection) {
				System.exit(0);
			}
		}));

		ui = new UI(game);

		String host = ui.inputHost();
		try {
			client.connect(4000, host, Network.port);
			// Server communication after connection can go here, or in Listener#connected().
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		id = ui.inputID();
		this.login = new Login();
		login.id = id;
		client.sendTCP(login);
	}
	
	public void sendMsg() {
		if (login == null) return;
		Player player = game.getPlayer(id);
		PlayerPos msg = new PlayerPos();
		
		//TODO Make this movement dependent on key presses
		msg.id = id;
		msg.x = player.getX()+1;
		msg.y = player.getY()+1;
		
		if (msg != null) {
			//System.out.println("Sending msg");
			client.sendTCP(msg);
		}
	}

	private class UI {
		
		private GameState game;

		public UI (GameState game) {
			this.game = game;
		}

		public String inputHost () {
			String input = (String)JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE,
				null, null, "localhost");
			if (input == null || input.trim().length() == 0) System.exit(1);
			return input.trim();
		}

		public int inputID () {
			String input = (String)JOptionPane.showInputDialog(null, "ID:", "Connect to server", JOptionPane.QUESTION_MESSAGE,
				null, null, 1);
			return Integer.parseInt(input);
		}



		public void updatePlayer (UpdatePlayer msg) {
			Player player = game.getPlayer(msg.id);
			if (player == null) return;
			player.setX(msg.x);
			player.setY(msg.y);
			System.out.println(player.getIdentity() + " moved to " + player.getX() + ", " + player.getY());
		}

	}
}