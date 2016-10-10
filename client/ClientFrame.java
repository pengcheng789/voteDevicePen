/**
 * 
 */
package client;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import candidate.Candidate;

/**
 * @author chance
 *
 */
@SuppressWarnings("serial")
public class ClientFrame extends JFrame{
	private JPanel jpanel;
	
	public JPanel getPanel(){
		return this.jpanel;
	}
	
	public ClientFrame(ArrayList<Candidate> candidateList){
		super("voteDevice Client");
		
		this.jpanel = new JPanel();
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
		
		add(jpanel);
		
		DataSocket dSocket = new DataSocket(this, jpanel, candidateList);
		Thread dSocketThread = new Thread(dSocket);
		dSocketThread.start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1300, 500);
		setResizable(false);
		setVisible(true);
		setLocation(600,300);
	}
	
	public JPanel mainPanel(final JPanel jpanel,final Candidate candidate){
		jpanel.setLayout(new GridLayout(7, 2));
		
		JLabel headImgLabel = new JLabel("  头像");
		JLabel headImgContent = new JLabel("  ^_^  ");
		JLabel nameLabel = new JLabel("  姓名：");
		JLabel ageLabel = new JLabel("  年龄：");
		JLabel sexLabel = new JLabel("  性别：");
		JLabel detailLabel = new JLabel("  详情：");
		JLabel countLabel = new JLabel("  票数：");
		JLabel nameLabelContent = new JLabel(candidate.getName());
		JLabel ageLabelContent = new JLabel(String.valueOf(candidate.getAge()));
		JLabel sexLabelContent = new JLabel(candidate.getsex());
		JLabel detailLabelContent = new JLabel(candidate.getdetail());
		JLabel countLabelContent = new JLabel(String.valueOf(candidate.getCount()));
		
		JButton voteButton = new JButton("投票");
		voteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new ControlSocket().voteAction(candidate.getId());
			}
		});
		
		jpanel.add(headImgLabel);
		jpanel.add(headImgContent);
		jpanel.add(nameLabel);
		jpanel.add(nameLabelContent);
		jpanel.add(ageLabel);
		jpanel.add(ageLabelContent);
		jpanel.add(sexLabel);
		jpanel.add(sexLabelContent);
		jpanel.add(detailLabel);
		jpanel.add(detailLabelContent);
		jpanel.add(countLabel);
		jpanel.add(countLabelContent);
		jpanel.add(voteButton);
		
		jpanel.setSize(300, 500);
		jpanel.setBorder(new LineBorder(Color.BLUE));
		
		return jpanel;
	}
	
}
