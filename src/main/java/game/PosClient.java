package game;


import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Listener.ThreadedListener;
import com.esotericsoftware.minlog.Log;

import game.Network.GameDeltaTime;
import game.Network.GameOver;
import game.Network.LevelComplete;
import game.Network.Login;
import game.Network.PlayerPos;
import game.Network.Register;
import game.Network.RegistrationRequired;

public class PosClient { 
	Client client;
	Login login;
	private GameState game;
	int id;
	int serverID;

	public PosClient (GameState game, InetAddress hostIP) throws IOException {
		InetAddress host = hostIP;
		//System.setProperty("java.net.preferIPv4Stack" , "true");
		id = 2;
		serverID = 1;
		client = new Client();
		this.game=game;
		Log.set(1);
		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(client);
		client.start();
		// ThreadedListener runs the listener methods on a different thread.
		client.addListener(new ThreadedListener(new Listener() {
			public void connected (Connection connection) {
			}

			public void received (Connection connection, Object object) {
				if (object instanceof RegistrationRequired) {
					Register register = new Register();
					client.sendTCP(register);
				}
				
				if (object instanceof PlayerPos) {
					
					PlayerPos msg = (PlayerPos)object;
					Player player = game.getPlayer(serverID);
					
					if (player==null) return;
					
					player.setX(msg.x);
					player.setY(msg.y);


					return;
				}
				
				if (object instanceof GameDeltaTime) {
					GameDeltaTime msg = (GameDeltaTime) object;
					game.syncDelay((game.getTotalDeltaTime()-msg.sumDeltaTime)/2);
					return;
				}
				
				if (object instanceof GameOver) {
					GameOver msg = (GameOver) object;
					Player player = game.getPlayer(serverID);
					if (player == null) return;
					player.setScore(msg.score);
					game.setGameOver(true);
					return;
				}
				
				if (object instanceof LevelComplete) {
					game.startNextLevel();
					return;
				}
				
			}
			

			public void disconnected (Connection connection) {
				System.exit(0);
			}
			
		}));

		if (host == null) {
			host = client.discoverHost(Network.port-1, 2500);
			if (host == null) {
				throw new IOException("host cannot be null.");
			} else {
				System.out.println("Host: "+host.toString());
			}
		}
	
		client.connect(2500, host, Network.port, Network.port-1);
		this.login = new Login();
		client.sendTCP(login);
		game.level(game.getCurrentLevel());
	}
	
	public PosClient(GameState gameState) throws IOException {
		this(gameState, null);
	}
	
	public void sendMsg() {
		if (login == null) return;
		Player player = game.getPlayer(id);
		PlayerPos msg = new PlayerPos();
		
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
		GameOver msg = new GameOver();
		msg.score = game.getPlayer(id).getScore();
		if (msg != null) {
			client.sendTCP(msg);
		}
		game.setGameOver(true);
	}
	
	public void levelComplete() {
		if (login == null) return;
		LevelComplete msg = new LevelComplete();
		if (msg != null) {
			client.sendTCP(msg);
		}
		game.startNextLevel();
	}
}