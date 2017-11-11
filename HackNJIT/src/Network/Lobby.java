package Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Lobby {
	private final String LOBBY = "34.210.113.172";
	private final int PORT = 5000;
	
	/**
	 * Input: none
	 * Output: none
	 * 
	 * Description: Advertises that the user wants to establish a game.
	 */
	public void advertise() {
		Socket myClient = null;
		DataOutputStream output = null;
		DataInputStream input = null;
		
		// get the socket, input, and output
		try {
			myClient = new Socket(LOBBY, PORT);
			input = new DataInputStream(myClient.getInputStream());
			output = new DataOutputStream(myClient.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    // advertise an open game to the lobby
	    if (myClient != null && output != null && input != null) {

	    	// just send hello
	    	String response = "";
    		try {
    			output.writeUTF("hello");
                response = input.readUTF();
    		} catch (IOException e) {
    			System.err.println(e);
    		}
    		
    		System.out.println(response);
	    }
	    
	    // close the connections
	    try {
	    	myClient.close();
	    	input.close();
	    	output.close();
	    } catch (IOException e) {
	    	System.err.println(e);
	    }
	}
}
