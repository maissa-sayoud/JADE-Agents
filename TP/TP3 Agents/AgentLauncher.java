import jade.core.*;
import jade.core.Runtime;
import jade.core.ProfileImpl;
import jade.core.Profile ;
import jade.wrapper.*;

public class AgentLauncher extends Agent
{
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args)
	{
		try
		{
			Runtime rt = Runtime.instance();
		
			ProfileImpl p = new ProfileImpl("localhost",1099,"JADE");

			//pour afficher l'interface graphique il faudra rajouter les param:
			p.setParameter(Profile.LOCAL_HOST, "localhost");
			p.setParameter(Profile.LOCAL_PORT, "1099");
			p.setParameter(Profile.GUI, "true");
			
			Object [] Arg1 = { "Pc", "500"};
			Object [] Arg2 = {"Mouse", "45"};
			
			ContainerController mc = rt.createMainContainer(p);
			
			//AgentController ag1 = mc.createNewAgent("Buyer1","AgDisplay",null);
			//AgentController ag2 = mc.createNewAgent("Buyer2","AgDisplay",null);
			
			AgentController ag3 = mc.createNewAgent("Buyer3","AgDisplayA",Arg1);
			AgentController ag4 = mc.createNewAgent("Buyer4","AgDisplayA",Arg2);
			
			//ag1.start();
			//ag2.start();
			ag3.start();
			ag4.start();
		}
		catch (Exception e) 
		{
			System.out.println("Une erreur s'est produite : " + e.getMessage());
            //e.printStackTrace();
		}		
	}
}
