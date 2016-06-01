package com;

import java.util.Iterator;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class FirstAgent extends GuiAgent{
	private GUI gui;
	private DFAgentDescription dfd;
	private ServiceDescription sd;
	protected void onWake(){

	}
	protected void setup(){
		gui = new GUI();
		System.out.println("In the main class");
		System.out.println("Mon nom est: "+getAID().getName());
		System.out.println("Mon nom local est: "+getAID().getLocalName());
		Iterator it = getAID().getAllAddresses();
		gui.setMyAgent(this);
		while(it.hasNext()){
			System.out.println(it.next());
		}
		dfd = new DFAgentDescription();
		dfd.setName(getAID());
		sd = new ServiceDescription();
		sd.setType("Chat-Ensa");
		sd.setName(getLocalName()+"-Chat-Ensa");
		dfd.addServices(sd);
		try {DFService.register(this, dfd);
		System.out.println("l'agent : "+getAID().getLocalName()+" est enregistré dans DF");
		}   catch (FIPAException fe) {
			fe.printStackTrace();
		}

		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
				System.out.println("Cyclic behavior Receive");
				//gui.showMessage("In the behavior", true);
				ACLMessage msg=receive();
				if(msg!=null){
					gui.showMessage( msg.getSender()+ ": " + msg.getContent(), true);
					System.out.println("Réception du message :"+msg.getContent());
				}
				else{
					System.out.println("No received message");
					block();
				}} });

	}
	@Override
	protected void onGuiEvent(GuiEvent gv) {
		switch(gv.getType()){
		case 1:
			System.out.println("In the event action");
			ACLMessage m = new ACLMessage(ACLMessage.REFUSE);
			DFAgentDescription dfd = new DFAgentDescription();
			ServiceDescription sd  = new ServiceDescription();
			sd.setType( "Chat-Ensa" );
			dfd.addServices(sd);

			DFAgentDescription[] result;
			try {
				result = DFService.search(this, dfd);
				System.out.println(result.length + " results" );
				if (result.length>0){
					for(int i=0; i<result.length; i++){
						System.out.println(" " + result[i].getName() );
						//m.addReceiver(result[i].getName());
						if(result[i].getName().getLocalName().contains(gui.getAgent().getText())){
							System.out.println(result[i].getName().getLocalName());
							m.setContent(gui.getMessage().getText());
							m.addReceiver(result[i].getName());
						}
					}

				}

				/*m.addReceiver(new AID("First", AID.ISLOCALNAME));
				m.setContent("Bonjour monsieur");*/
			} catch (FIPAException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			send(m);
			break;
		}

	}



}
