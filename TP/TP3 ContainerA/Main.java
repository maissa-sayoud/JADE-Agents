
public class Main 
{

    public static void main(String[] args) 
    {
        String[] jadeArg = new String[2];
        StringBuffer SbAgent = new StringBuffer();

        SbAgent.append("receiver:a_receive;");
        SbAgent.append("sender:a_sender;");
        
        jadeArg[0] = "-gui";
        jadeArg[1] = SbAgent.toString();
        
        jade.Boot.main(jadeArg);

        // Creation of the receiver agent in the main container
        jade.core.Runtime rt = jade.core.Runtime.instance();
        jade.core.ProfileImpl p = new jade.core.ProfileImpl(false);
        
        p.setParameter(jade.core.Profile.MAIN_HOST, "localhost");
        
        jade.wrapper.AgentContainer mainContainer = rt.createMainContainer(p);
        try 
        {
            jade.wrapper.AgentController receiver = mainContainer.createNewAgent("receiver", "a_receive", null);
        
            receiver.start();
        }
        catch (jade.wrapper.ControllerException e) 
        {
            e.printStackTrace();
        }
    }
}
