import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.leap.Properties;
import jade.util.ExtendedProperties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;


public class Main_Container {

	public static void main(String[] args) {
		try {
			
			Runtime runtime=Runtime.instance();
			Properties properties=new ExtendedProperties(); 
			properties.setProperty("gui", "true"); //graphic interface
			ProfileImpl prop_container=new ProfileImpl(properties);
			
			//Main container creation 
			AgentContainer container=runtime.createMainContainer(prop_container);
			
			//Main agent creation
			AgentController main=container.createNewAgent("MainAgent", "SimpleContainer.Main_agent", new Object[]{});
			main.start();
			
		} catch (ControllerException e) {
			
			e.printStackTrace();
		}

	}

}
