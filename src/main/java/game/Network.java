package game;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

public class Network {
	static public final int port = 8080;

	// This registers objects that are going to be sent over the network.
	static public void register (EndPoint endPoint) {
		Kryo kryo = endPoint.getKryo();
		kryo.register(Login.class);
		kryo.register(RegistrationRequired.class);
		kryo.register(Register.class);
		kryo.register(PlayerPos.class);
		kryo.register(UpdatePlayer.class);

	}

	static public class Login {
		public int id;
	}

	static public class RegistrationRequired {
	}

	static public class Register {
		public int id;
	}
	
	static public class UpdatePlayer {
		public int id; 
		public double x;
		public double y;
	}

	static public class PlayerPos {
		public int id; 
		public double x;
		public double y;
	}
}