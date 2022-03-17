package game;


import java.io.IOException;
import java.net.InetAddress;


import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;

import game.Network.GameDeltaTime;
import game.Network.KillPlayer;
import game.Network.Login;
import game.Network.PlayerPos;
import game.Network.Register;
import game.Network.RegistrationRequired;
import game.Network.UpdatePlayer;

public class PosClient {
	//UI ui; 
	Client client;
	Login login;
	private GameState game;
	int id;

	public PosClient (GameState game) throws IOException {
		System.setProperty("java.net.preferIPv4Stack" , "true");
		id = 2;
		client = new Client();
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
					
					PlayerPos msg = (PlayerPos)object;
					// Ignore if not logged in.
					if (msg.id == 0) {
						System.out.println("Client: Player is not logged in!");
						return;
					}
					
					Player player = game.getPlayer(msg.id);

					player.setX(msg.x);
					player.setY(msg.y);

					UpdatePlayer update = new UpdatePlayer();
					update.id = player.getIdentity();
					update.x = player.getX();
					update.y = player.getY();
					client.sendTCP(update);
					return;
				}
				
				if (object instanceof GameDeltaTime) {
					GameDeltaTime msg = (GameDeltaTime) object;
					game.syncDelay((game.getTotalDeltaTime()-msg.sumDeltaTime)/2);
					return;
				}
				
				if (object instanceof KillPlayer) {
					KillPlayer msg = (KillPlayer) object;
					Player player = game.getPlayer(msg.id);
					if (player == null) return;
					if (!game.playerIsAlive(player)) return;
					player.killPlayer();	
					return;
				}
				
			}
			

			public void disconnected (Connection connection) {
				System.exit(0);
			}
			
		}));

		//ui = new UI();

		//String host = ui.inputHost();
		InetAddress host = client.discoverHost(Network.port-1, 2500);
		if (host == null) {
			throw new IOException("host cannot be null.");
		} else {
			System.out.println("Host: "+host.toString());
		}
	
		client.connect(2500, host, Network.port);
		client.start();
		this.login = new Login();
		login.id = id;
		client.sendTCP(login);
		game.level(game.getCurrentLevel());
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
	
	public void sync(double totalDeltaTime) {
		if (login == null) return;
		GameDeltaTime msg = new GameDeltaTime();
		msg.sumDeltaTime = totalDeltaTime;
		
		if (msg != null) {
			client.sendTCP(msg);
		}
	}
	
	public void playerDied(Player p) {
		if (login == null) return;
		KillPlayer msg = new KillPlayer();
		
		msg.id = p.getIdentity();
		
		if (msg != null) {
			client.sendTCP(msg);
		}
	}
	
	/*
	private class UI {

		public String inputHost () {
			String input = (String)JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE,
				null, null, "localhost");
			if (input == null || input.trim().length() == 0) System.exit(1);
			return input.trim(); 
		}
	}
	*/
}