/**
 * 
 */
package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JPanel;

import server.Candidate;

/**
 * @author chance
 *
 */
public class DataSocket implements Runnable{
	private int port = 10000;
	private ServerSocket serverSocket;
	private Candidate candidate;
	private ClientFrame cFrame;
	private JPanel jpanel; 
	
	public DataSocket(ClientFrame cFrame, JPanel jpanel, Candidate candidate){
		try {
			serverSocket = new ServerSocket(port);
			this.candidate = candidate;
			this.cFrame = cFrame;
			this.jpanel = jpanel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void service(){
		
		while(true){
			Socket socket = null;
			ObjectInputStream os = null;
			try {
				socket = serverSocket.accept();
				
				os = new ObjectInputStream(socket.getInputStream());
				candidate = (Candidate)os.readObject();
				
				jpanel.removeAll();
				cFrame.mainPanel(jpanel, candidate);
				jpanel.validate();
				jpanel.repaint();
				
				os.close();
				System.out.println("接收完成");
				System.out.println(candidate.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		service();
	}
}
