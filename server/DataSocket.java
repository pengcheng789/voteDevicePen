/**
 * 
 */
package server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author chance
 *
 */
public class DataSocket {
	private int port = 10000;
	private InetAddress iAdress; 
	private Socket socket;
	
	public DataSocket(InetAddress iAdress){
		this.iAdress = iAdress;
		try {
			this.socket = new Socket(this.iAdress, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void service(Candidate candidate){
		
			ObjectOutputStream os = null;
			try {
				
				os = new ObjectOutputStream(socket.getOutputStream());
				os.writeObject(candidate);
				os.flush();
				os.close();
				System.out.println("传送完成");
				
			} catch (IOException e) {
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
