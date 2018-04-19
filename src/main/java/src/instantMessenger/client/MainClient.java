package src.instantMessenger.client;

@SuppressWarnings("javadoc")
public class MainClient {

	public static void main(String[] args) {
		Client model = new Client();
		ClientView view = new ClientView();
		ClientController controller = new ClientController(model, view);
	}
	
}