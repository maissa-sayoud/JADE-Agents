import jade.core.Profile; 
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Main {
    public static void main(String[] args) 
    {
    	
    	
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1099");
        profile.setParameter(Profile.GUI, "true");

        AgentContainer mainContainer = runtime.createMainContainer(profile);

        try 
        {
            Profile subContainerProfile = new ProfileImpl(false);
            subContainerProfile.setParameter(Profile.MAIN_HOST, "localhost");
            subContainerProfile.setParameter(Profile.MAIN_PORT, "1099");
            subContainerProfile.setParameter(Profile.CONTAINER_NAME, "SubContainer");
            AgentContainer subContainer = runtime.createAgentContainer(subContainerProfile);

            AgentController seller1 = mainContainer.createNewAgent("Seller1", "Seller", null);
            AgentController seller2 = subContainer.createNewAgent("Seller2", "Seller", null);

            seller1.start();
            seller2.start();

            AgentController buyer = mainContainer.createNewAgent("Buyer", "MobileBuyerAgent", null);
            buyer.start();
        } 
        catch (StaleProxyException e) 
        {
            e.printStackTrace();
        }
    }
}
