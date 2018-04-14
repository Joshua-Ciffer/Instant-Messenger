package src.instantMessenger;

import src.instantMessenger.client.Client;
import src.instantMessenger.client.ClientController;
import src.instantMessenger.client.ClientView;

@SuppressWarnings("javadoc")
public class MainClient {

	public static void main(String[] args) {
		Client model = new Client();
		ClientView view = new ClientView();
		ClientController controller = new ClientController(model, view);
	}
	
}