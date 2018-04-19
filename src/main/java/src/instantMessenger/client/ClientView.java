package src.instantMessenger.client;


/**
 * 
 *
 * @author Joshua
 * @version 04/13/2018
 */
public class ClientView {

	private ClientFrame frame;
	
	ClientView(ClientFrame frame) {
		this.frame = frame;
	}
	
	ClientFrame getClientFrame() {
		return frame;
	}
	
}
