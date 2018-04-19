package src.instantMessenger.client;

import java.io.IOException;

/**
 * 
 *
 * @author Joshua
 * @version 04/19/2018
 */
public class ClientListener implements Runnable {

	private Client model;
	
	private ClientController controller;
	
	/**
	 *
	 *
	 * @param client
	 */
	public ClientListener(Client model, ClientController controller) {
		this.model = model;
		this.controller = controller;
	}
	
	@Override
	public void run() {
		while (model.getServerConnection().isConnected()) {
			try {
				controller.getView().getClientFrame().appendToScreen(model.getInboundTraffic().readUTF());
			} catch (IOException e) {}
		}
	}

}