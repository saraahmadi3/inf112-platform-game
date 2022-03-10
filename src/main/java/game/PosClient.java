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

	public PosClient (GameState game) throws IOException {
		id = 2;
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
					updatePlayer((UpdatePlayer)object);
					return;
				}
				
				if (object instanceof PlayerPos) {
					Player player = game.getPlayer(((PlayerPos) object).id);
					
					// Ignore if not logged in.
					if (player == null) {
						System.out.println("Player is not logged in!");
						return;
					}

					PlayerPos msg = (PlayerPos)object;

					player.setX(msg.x);
					player.setY(msg.y);

					UpdatePlayer update = new UpdatePlayer();
					update.id = player.getIdentity();
					update.x = player.getX();
					update.y = player.getY();
					client.sendTCP(update);
					return;
				}
				
			}

			public void disconnected (Connection connection) {
				System.exit(0);
			}
			
		}));

		ui = new UI();

		String host = ui.inputHost();
		client.connect(2500, host, Network.port);

		this.login = new Login();
		login.id = id;
		client.sendTCP(login);
	}
	
	public void updatePlayer (UpdatePlayer msg) {
		Player player = game.getPlayer(msg.id);
		if (player == null) return;
		player.setX(msg.x);
		player.setY(msg.y);
	}
	
	public void sendMsg() {
		if (login == null) return;
		Player player = game.getPlayer(id);
		PlayerPos msg = new PlayerPos();
		
		msg.id = id;
		msg.x = player.getX();
		msg.y = player.getY();
		
		if (msg != null) {
			client.sendTCP(msg);
		}
	}

	private class UI {

		public String inputHost () {
			String input = (String)JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE,
				null, null, "localhost");
			if (input == null || input.trim().length() == 0) System.exit(1);
			return input.trim(); 
		}
	}
}