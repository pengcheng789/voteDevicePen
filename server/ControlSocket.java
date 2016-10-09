/**
 * 
 */
package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JPanel;

/**
 * @author chance
 *
 */
public class ControlSocket implements Runnable{
	private int port = 9999;
	private ServerSocket serverSocket;
	private ServerFrame sFrame;
	private Candidate candidate;
	private JPanel jpanel;
	
	public ControlSocket(ServerFrame sFrame, JPanel jpanel, Candidate candidate){
		try {
			serverSocket = new ServerSocket(port);
			this.sFrame = sFrame;
			this.jpanel = jpanel;
			this.candidate = candidate;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void service(){
		while(true){
			Socket socket = null;
			InputStream is = null;
			
			try {
				socket = serverSocket.accept();
				byte[] b = new byte[10];
				is = socket.getInputStream();
				is.read(b);
				if(b[0] == 1){
					//投票
					candidate.setCount(true);
					jpanel.removeAll();
					sFrame.mainPanel(jpanel, candidate);
					jpanel.validate();
					jpanel.repaint();
				}
				DataSocket dSocket = new DataSocket(socket.getInetAddress());
				dSocket.service(candidate);
				is.close();
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

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		service();
	}

}
