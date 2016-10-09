/**
 * 
 */
package client;

import server.Candidate;

/**
 * @author chance
 *
 */
public class ClientApp {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Candidate candidate = new Candidate(" ", 0, "", "");
		
		new ClientFrame(candidate);
	}

}
