import jade.core.Profile; 
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class SecondaryPlatform 
{
    public static void main(String[] args) 
    {
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1098");
        profile.setParameter(Profile.GUI, "true");

        AgentContainer mainContainer = runtime.createMainContainer(profile);

        Profile subContainerProfile = new ProfileImpl(false);
		subContainerProfile.setParameter(Profile.MAIN_HOST, "localhost");
		subContainerProfile.setParameter(Profile.MAIN_PORT, "1098");
		subContainerProfile.setParameter(Profile.CONTAINER_NAME, "SubContainer");
		AgentContainer subContainer = runtime.createAgentContainer(subContainerProfile);
    }
}
