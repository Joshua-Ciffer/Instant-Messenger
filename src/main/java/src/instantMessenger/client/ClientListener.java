package src.instantMessenger.client;

import java.io.IOException;

/**
 * 
 *
 * @author Joshua
 * @version 04/19/2018
 */
public class ClientListener implements Runnable {

	private ClientController controller;
	
	/**
	 *
	 *
	 * @param controller
	 */
	void setController(ClientController controller) {
		this.controller = controller;
	}
	
	@Override
	public void run() {
		while (controller.getModel().getServerConnection().isConnected()) {
			try {
				controller.getView().getClientFrame().appendToScreen(controller.getModel().getInboundTraffic().readUTF());
			} catch (IOException e) {}
		}
	}

}