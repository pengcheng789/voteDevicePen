/**
 * 
 */
package client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author chance
 *
 */
public class ControlSocket{
	private int port = 9999;
	private String host = "localhost";
	private Socket socket;
	
	public ControlSocket(){
		try {
			socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void reflushAction(){
		byte[] packet = {0};
		OutputStream os = null;
		try {
			os = socket.getOutputStream();
			os.write(packet);
			os.flush();
			os.close();
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
	
	public void voteAction(int id){
		byte[] packet = {1, (byte)id};
		OutputStream os = null;
		try {
			os = socket.getOutputStream();
			os.write(packet);
			os.flush();
			os.close();
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
