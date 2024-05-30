import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class simplecontainer 
{

    public static void main(String[] args) 
    {
        // Get a hold of the JADE runtime
        Runtime rt = Runtime.instance();
        
        // Create a default profile for the main container
        Profile pMain = new ProfileImpl(true); // true means it's the main container
        pMain.setParameter(Profile.MAIN_HOST, "localhost");
        
        // Create and start the main container
        AgentContainer mainContainer = rt.createMainContainer(pMain);
        
        try 
        {
            // Start the main container
            mainContainer.start();
            
            // Create a profile for the additional container
            ProfileImpl pContainer = new ProfileImpl(false); // false means it's not the main container
            pContainer.setParameter(Profile.MAIN_HOST, "localhost");
            
            // Create the additional container
            AgentContainer container = rt.createAgentContainer(pContainer);

            // Create and start agents in the additional container
            AgentController agent1 = container.createNewAgent("agent1", "BehaviourComplex", null);
            AgentController agent2 = container.createNewAgent("agent2", "BehaviourComplex", null);

            agent1.start();
            agent2.start();
        } 
        catch (ControllerException e) 
        {
            e.printStackTrace();
        }
    }
}
