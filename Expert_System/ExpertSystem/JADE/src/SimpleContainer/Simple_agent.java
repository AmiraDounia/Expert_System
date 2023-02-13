package SimpleContainer;

import jade.core.Agent;
import jade.core.AID;
import jade.core.Location;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Simple_agent extends Agent {
	
	
	//Parameters
	private String y;
	
       protected void setup() {
    	   
    	   Object[] args= getArguments();
    	   
    	   if(args.length==1) {
    	   this.y=(String) args[0];
    	   System.out.println("It is the "+this.getAID().getName()); 
    	   
    	  
    	   
           addBehaviour(new CyclicBehaviour() {
        	  
			public void action() {
        		           		   
        		//Receiving messages from the main agent
                ACLMessage message=receive();
                   if (message!=null) {
                	   System.out.println("the main agent sent : " + message.getContent());
                   
                   
                   //answering the main agent
                   final ACLMessage messag = new ACLMessage(ACLMessage.INFORM);
                            messag.addReceiver(new AID(message.getSender().getLocalName(), AID.ISLOCALNAME));     
                            messag.setContent("Hello, I am ");
                            send(messag);
                         }
                    else {
                        block();
                         }
             }

			private Agent getAID() {
				
				return null;
			}

                
                
    	   }); } }

           
    	   
    	  
       
       
       protected void takeDown(){
    	   System.out.println("Agent destruction");
       }
       public void doMove(Location loc){
    	   System.out.println("Migration towards :"+loc.getName());
       }
       
}
