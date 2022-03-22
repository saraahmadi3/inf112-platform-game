package game;

import java.io.IOException;
import java.net.BindException;
import java.util.HashSet;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import game.Network.GameDeltaTime;
import game.Network.KillPlayer;
import game.Network.Login;
import game.Network.PlayerPos;
import game.Network.Register;
import game.Network.UpdatePlayer;

public class PosServer {
	
	Server server;
	int serverPlayerID;
	GameState game;
	HashSet<Integer> loggedIn = new HashSet<>();

	public PosServer (GameState game) {
		serverPlayerID = 1;
		this.game = game;
		server = new Server() {
			protected Connection newConnection () {
				// By providing our own connection implementation, we can store per
				// connection state without a connection ID to state look up.
				return new PlayerConnection();
			}
		};
		
		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Network.register(server);
		server.start();
		
		try {
			server.bind(Network.port, Network.port-1);
		} catch (Exception e) {
			System.out.println("Catched exception: "+e); 
		}
		

		server.addListener(new Listener() {
			public void received (Connection c, Object object) {
				// We know all connections for this server are actually CharacterConnections.
				PlayerConnection connection = (PlayerConnection)c;
				int playerID = connection.playerID;

				if (object instanceof Login) {
					// Ignore if already logged in.
					if (playerID != 0) return;
					
					playerID = ((Login)object).id;

					// Reject if already logged in.
					for (int other : loggedIn) {
						if (other == playerID) {
							c.close();
							return;
						}
					}
					
					loggedIn(connection, playerID);
					return;
				}

				if (object instanceof Register) {
					// Ignore if already logged in.
					if (playerID != 0) return;

					loggedIn(connection, playerID);
					return;
				}

				if (object instanceof PlayerPos) {
					// Ignore if not logged in.
					if (playerID == 0) {
						System.out.println("Server: Player is not logged in!");
						return;
					}

					PlayerPos msg = (PlayerPos)object;
					
					Player player = game.getPlayer(playerID);
					
					if (player==null) {
						System.out.print("Recived coordinates was ignore because player is null.");
						return;
					}
					
					player.setX(msg.x);
					player.setY(msg.y);

					UpdatePlayer update = new UpdatePlayer();
					update.id = player.getIdentity();
					update.x = player.getX();
					update.y = player.getY();
					server.sendToAllTCP(update);
					return;
				}
				
				if (object instanceof UpdatePlayer) {
					updatePlayer((UpdatePlayer)object);
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
			
			public void updatePlayer (UpdatePlayer msg) {
				Player player = game.getPlayer(msg.id);
				if (player == null) return;
				player.setX(msg.x);
				player.setY(msg.y);
			}


			public void disconnected (Connection c) {
				PlayerConnection connection = (PlayerConnection)c;
				if (connection.playerID != 0) {
					loggedIn.remove(connection.playerID);
					
					//TODO do something when player is disconnected!
				}
			}
		});
	}

	public void sendMsg() {
		if (loggedIn.isEmpty()) return;
		Player player = game.getPlayer(serverPlayerID);
		if (!game.playerIsAlive(player)) return;
		PlayerPos msg = new PlayerPos();
		
		msg.id = serverPlayerID;
		msg.x = player.getX();
		msg.y = player.getY();
		
		if (msg != null) {
			//System.out.println("Sending msg");
			server.sendToAllTCP(msg);
		}
	}
	
	public void sync(double totalDeltaTime) {
		if (loggedIn.isEmpty()) return;
		GameDeltaTime msg = new GameDeltaTime();
		msg.sumDeltaTime = totalDeltaTime;
		
		if (msg != null) {
			server.sendToAllTCP(msg);
		}
	}
	
	public void playerDied(Player p) {
		if (loggedIn.isEmpty()) return;
		KillPlayer msg = new KillPlayer();
		
		msg.id = p.getIdentity();
		
		if (msg != null) {
			server.sendToAllTCP(msg);
		}
	}
	
	void loggedIn (PlayerConnection c, int playerID) {
		c.playerID = playerID;
		loggedIn.add(playerID);
	}



	// This holds per connection state.
	static class PlayerConnection extends Connection {
		public int playerID;
	}
}
