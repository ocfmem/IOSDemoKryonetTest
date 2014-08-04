import net.LocalServer;


public class Server {

	public static void main(String[] args) {
		System.out.println("Starting Server");
		LocalServer localServer = new LocalServer();
		localServer.start();
		System.out.println("Server Started");
	}

}
