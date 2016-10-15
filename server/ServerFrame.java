/**
 * 
 */
package server;

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
public class ServerFrame extends JFrame {
	private JPanel jpanel;
	
	public JPanel getPanel(){
		return this.jpanel;
	}
	
	public ServerFrame(ArrayList<Candidate> candidateList){
		super("voteDevice Server");
		
		this.jpanel = new JPanel();
		jpanel.setLayout(new GridLayout(1, 4));
		for(Candidate candidate : candidateList){
			jpanel.add(mainPanel(new JPanel(), candidate));
		}
		JButton addButton = new JButton("增加候选人");
		addButton.setEnabled(false);
		jpanel.add(addButton);
		
		add(jpanel);
		
		ControlSocket cSocket = new ControlSocket(this, jpanel, candidateList);
		Thread cSocketThread = new Thread(cSocket);
		cSocketThread.start();
		
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
		
		JButton toZeroButton = new JButton("清零");
		toZeroButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//清零动作
				candidate.setCount(false);
				if(candidate.getId() == 2)
					candidate.setAge(90);
				//System.out.println("面板移除动作开始");
				jpanel.removeAll();
				mainPanel(jpanel, candidate);
				jpanel.validate();
				jpanel.repaint();
				//System.out.println("面板移除动作结束");
			}
		});
		
		JButton editButton = new JButton("编辑");
		editButton.setEnabled(false);
		
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
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(toZeroButton);
		buttonPanel.add(editButton);
		
		jpanel.add(buttonPanel);
		
		//jpanel.setSize(300, 500);
		jpanel.setBorder(new LineBorder(Color.BLUE));
		
		return jpanel;
	}

}
