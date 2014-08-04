import net.LocalClient;


public class Client {
	public static void main(String[] args) {

			System.out.println("Starting Client");
			LocalClient localClient = new LocalClient();
			localClient.connect();
			System.out.println("Client Started");
	}

}
