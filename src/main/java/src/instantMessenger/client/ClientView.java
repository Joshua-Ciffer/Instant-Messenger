package src.instantMessenger.client;


/**
 * 
 *
 * @author Joshua
 * @version 04/13/2018
 */
public class ClientView {

	private ClientFrame frame;
	
	public ClientView() {
		frame = new ClientFrame();
	}
	
	ClientFrame getClientFrame() {
		return frame;
	}
	
}
