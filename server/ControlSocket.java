/**
 * 
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JPanel;

import candidate.Candidate;


/**
 * @author chance
 *
 */
public class ControlSocket implements Runnable{
	private int port = 9999;
	private ServerSocket serverSocket;
	private ServerFrame sFrame;
	private ArrayList<Candidate> candidateList;
	private JPanel jpanel;
	
	public ControlSocket(ServerFrame sFrame, JPanel jpanel, ArrayList<Candidate> candidateList){
		try {
			serverSocket = new ServerSocket(port);
			this.sFrame = sFrame;
			this.jpanel = jpanel;
			this.candidateList = candidateList;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void service(){
		while(true){
			Socket socket = null;
			
			
			try {
				socket = serverSocket.accept();
				
				new ControlSocketThread(socket, candidateList, sFrame, jpanel).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		service();
	}

}
