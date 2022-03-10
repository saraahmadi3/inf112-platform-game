package game;

import java.io.IOException;
import java.util.HashSet;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import game.Network.Login;
import game.Network.PlayerPos;
import game.Network.Register;
import game.Network.UpdatePlayer;

public class PosServer {
	Server server;
	GameState game;
	HashSet<Player> loggedIn = new HashSet<>();

	public PosServer (GameState game) {
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

		server.addListener(new Listener() {
			public void received (Connection c, Object object) {
				// We know all connections for this server are actually CharacterConnections.
				PlayerConnection connection = (PlayerConnection)c;
				Player player = connection.player;
				System.out.println(player);

				if (object instanceof Login) {
					// Ignore if already logged in.
					if (player != null) return;

					// Reject if the name is invalid.
					int id = ((Login)object).id;

					// Reject if already logged in.
					for (Player other : loggedIn) {
						if (other.getIdentity() == id) {
							c.close();
							return;
						}
					}

					player = game.getPlayer(id);
					
					loggedIn(connection, player);
					return;
				}

				if (object instanceof Register) {
					// Ignore if already logged in.
					if (player != null) return;

					Register register = (Register)object;


					loggedIn(connection, player);
					return;
				}

				if (object instanceof PlayerPos) {
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
					server.sendToAllTCP(update);
					return;
				}
			}

			public void disconnected (Connection c) {
				PlayerConnection connection = (PlayerConnection)c;
				if (connection.player != null) {
					loggedIn.remove(connection.player);
					
					//TODO do something when player is disconnected!
				}
			}
		});
		try {
			server.bind(Network.port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		server.start();
	}

	void loggedIn (PlayerConnection c, Player player) {
		c.player = player;

		loggedIn.add(player);
		System.out.print("Player succesfully logged in");
		System.out.print(player);
	}



	// This holds per connection state.
	static class PlayerConnection extends Connection {
		public Player player;
	}
}