/**
 * 
 */
package client;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import candidate.Candidate;

/**
 * @author chance
 *
 */
public class DataSocket implements Runnable{
	private int port = 10000;
	private ServerSocket serverSocket;
	private ArrayList<Candidate> candidateList;
	private ClientFrame cFrame;
	private JPanel jpanel; 
	
	public DataSocket(ClientFrame cFrame, JPanel jpanel, ArrayList<Candidate> candidateList){
		try {
			serverSocket = new ServerSocket(port);
			this.candidateList = candidateList;
			this.cFrame = cFrame;
			this.jpanel = jpanel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void service(){
		
		while(true){
			Socket socket = null;
			ObjectInputStream os = null;
			try {
				socket = serverSocket.accept();
				
				os = new ObjectInputStream(socket.getInputStream());
				candidateList = (ArrayList<Candidate>)os.readObject();
				
				jpanel.removeAll();
				for(Candidate candidate : candidateList){
					jpanel.add(cFrame.mainPanel(new JPanel(), candidate));
				}
				jpanel.setLayout(new GridLayout(1, 4));
				JButton reflushButton = new JButton("刷新");
				reflushButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						new ControlSocket().reflushAction();
					}
				});
				jpanel.add(reflushButton);
				jpanel.validate();
				jpanel.repaint();
				
				os.close();
				System.out.println("接收完成");
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
