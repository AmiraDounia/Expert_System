package SimpleContainer;

import jade.core.AID;
import jade.core.Agent;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main_agent extends Agent {
    
   
    protected void setup(){
        
        try {
            System.out.println("The main agent's name and id : "+this.getAID().getName());
          
            //Simple container creation with 2 simple agents inside
            Runtime runtime=Runtime.instance();
			ProfileImpl prop_container=new ProfileImpl(false); // the "false" is to indicate that it's a simple container and not a main container
			prop_container.setParameter(ProfileImpl.MAIN_HOST, "localhost");
            AgentContainer ac2=runtime.createAgentContainer(prop_container);
            
            //the 2 simple agents creation
            for(int i=1; i<3; i++){
			AgentController agentSimple=ac2.createNewAgent("Simple Agent" + String.valueOf(i),"SimpleContainer.Simple_agent", new Object[]{String.valueOf(i)});
            agentSimple.start();
          
            }
            
            
            //messages sending
           ACLMessage message = new ACLMessage(ACLMessage.INFORM);
           
             //adding Receivers
            for(int i=1; i<3; i++){
           message.addReceiver(new AID("Simple Agent"+String.valueOf(i), AID.ISLOCALNAME));
          }
            
            //Content 
            message.setContent("Hello, i am the Main Agent :)) ");
           send(message);

           
           //Recieving messages from simple agents 
              addBehaviour(new CyclicBehaviour() 
                {
                     public void action() 
                     {
                        ACLMessage messag= receive();
                        if (messag!=null){
                        	 
                            System.out.println("the simple agent's reply is : " + messag.getContent() + messag.getSender().getLocalName());
                        } 
                       else{
                       block();
                         }
                     }
                });
            }
        



            
        catch (StaleProxyException ex) {
            Logger.getLogger(Main_agent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
   



}

    