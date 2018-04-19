package src.instantMessenger.client;

import src.instantMessenger.client.controller.ClientController;
import src.instantMessenger.client.model.Client;
import src.instantMessenger.client.view.ClientView;

@SuppressWarnings("javadoc")
public class Main {

	public static void main(String[] args) {
		Client model = new Client();
		ClientView view = new ClientView();
		ClientController controller = new ClientController(model, view);
	}
	
}