package com;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jade.gui.GuiEvent;

public class GUI extends JFrame{
	private JTextField agent, message;
	private JTextArea conversation;
	private JButton envoyer;
	private JLabel agentLabel, messageLabel;
	private FirstAgent myAgent;
	GUI(){
		envoyer = new JButton("Envoyer");
		agentLabel = new JLabel("Agent: ");
		messageLabel = new JLabel("Message: ");
		agent = new JTextField(10);
		message = new JTextField(10);
		conversation = new JTextArea();
		JPanel pan = new JPanel();
		//pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
		pan.setLayout(new FlowLayout());
		pan.add(agentLabel);
		pan.add(agent);
		pan.add(messageLabel);
		pan.add(message);
		
		pan.add(envoyer);
		pan.add(conversation);
		this.add(pan, BorderLayout.NORTH);
		this.add(new JScrollPane(conversation), BorderLayout.CENTER);
		this.setSize(800, 450);
		this.setVisible(true);
		envoyer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String agent = message.getText();
				GuiEvent gev = new GuiEvent(this, 1);
				gev.addParameter(agent);
				myAgent.onGuiEvent(gev);
				
				
			}
		});
		
	}
	
	public void showMessage(String msg, boolean append){
		if(append == true){
			conversation.append(msg + "\n");
		}else
			conversation.setText(msg);
	}
	
	
	public JTextField getAgent() {
		return agent;
	}
	public void setAgent(JTextField agent) {
		this.agent = agent;
	}
	public JTextField getMessage() {
		return message;
	}
	public void setMessage(JTextField message) {
		this.message = message;
	}
	public JTextArea getConversation() {
		return conversation;
	}
	public void setConversation(JTextArea conversation) {
		this.conversation = conversation;
	}
	public JButton getEnvoyer() {
		return envoyer;
	}
	public void setEnvoyer(JButton envoyer) {
		this.envoyer = envoyer;
	}
	public JLabel getAgentLabel() {
		return agentLabel;
	}
	public void setAgentLabel(JLabel agentLabel) {
		this.agentLabel = agentLabel;
	}
	public JLabel getMessageLabel() {
		return messageLabel;
	}
	public void setMessageLabel(JLabel messageLabel) {
		this.messageLabel = messageLabel;
	}
	public FirstAgent getMyAgent() {
		return myAgent;
	}
	public void setMyAgent(FirstAgent myAgent) {
		this.myAgent = myAgent;
	}
	
}
