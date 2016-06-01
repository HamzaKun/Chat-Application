package com;

import java.util.Iterator;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
public class Agent2 extends Agent{

	protected void setup(){
		System.out.println("Agent number 2");
		System.out.println("Mon nom est: "+getAID().getName());
		System.out.println("Mon nom local est: "+getAID().getLocalName());
		Iterator it = getAID().getAllAddresses();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Chat-Ensa");
		template.addServices(sd);
		try {
		DFAgentDescription[] result = DFService.search(this,template);
		}
		catch (FIPAException fe) {
		fe.printStackTrace();
		}

		
		addBehaviour(new CyclicBehaviour() {
			@Override
			public void action() {
			System.out.println("Cyclic behavior Receive");
			ACLMessage msg=receive();
			if(msg!=null){
				System.out.println("Réception du message :"+msg.getContent());
			}
			else{
				//block();
			}} });
	}


}
