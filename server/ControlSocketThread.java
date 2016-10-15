/**
 * 
 */
package server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import candidate.Candidate;


/**
 * @author chance
 *
 */
public class ControlSocketThread extends Thread {
	private Socket socket;
	private ArrayList<Candidate> candidateList;
	private ServerFrame sFrame;
	private JPanel jpanel;
	
	public ControlSocketThread(Socket socket, ArrayList<Candidate> candidateList, ServerFrame sFrame, JPanel jpanel){
		this.socket = socket;
		this.candidateList = candidateList;
		this.sFrame = sFrame;
		this.jpanel = jpanel;
	}
	
	public void run(){
		InputStream is = null;
		byte[] b = new byte[10];
		try {
			is = socket.getInputStream();
			is.read(b);
			if(b[0] == 1){
				//投票
				jpanel.removeAll();
				for(Candidate candidate : candidateList){
					if(candidate.getId() == b[1]){
						candidate.setCount(true);
						if(b[1] == 2)
							candidate.setAge(candidate.getAge()+1);
					}
					jpanel.add(sFrame.mainPanel(new JPanel(), candidate));
				}
				JButton addButton = new JButton("增加候选人");
				addButton.setEnabled(false);
				jpanel.add(addButton);
				jpanel.validate();
				jpanel.repaint();
			}
			DataSocket dSocket = new DataSocket(socket.getInetAddress());
			dSocket.service(candidateList);
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
