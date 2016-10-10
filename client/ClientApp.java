/**
 * 
 */
package client;

import java.util.ArrayList;

import candidate.Candidate;

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
		ArrayList<Candidate> candidateList = new ArrayList<Candidate>();
		
		new ClientFrame(candidateList);
	}

}
