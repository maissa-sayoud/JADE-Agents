/*______________________________________________*/  

/*					BISMI ALLAH					*/
/*______________________________________________*/

/************************************************/
/*______________________________________________*/

/*		Projet Techniques des Agents 1			*/
/*______________________________________________*/

/*		SAYOUD Maissa & BOULKABOUL Amira		*/
/*______________________________________________*/


import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Main 
{

    public static void main(String[] args) 
    {
    	
    	
        // Get the JADE runtime instance
        Runtime runtime = Runtime.instance();

        
        
        
        // Set up the main container profile
        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.MAIN_PORT, "1099");
        profile.setParameter(Profile.GUI, "true"); 

        AgentContainer mainContainer = runtime.createMainContainer(profile);

        
        
        
        try 
        {

            AgentController sellerAgent = mainContainer.createNewAgent
            (
                    "SellerAgent",
                    "Seller",
                    new Object[] { 100.0, 110.0 } // Starting and reserve price
            );
            sellerAgent.start();

            
            
            
            // Create buyer agents with different maximum bid limits
            AgentController buyerAgent1 = mainContainer.createNewAgent
            (
                    "BuyerAgent1",
                    "Buyer",
                    new Object[] { 200.0 } // Maximum bid for buyer 1
            );
            buyerAgent1.start();

            AgentController buyerAgent2 = mainContainer.createNewAgent
            (
                    "BuyerAgent2",
                    "Buyer",
                    new Object[] { 250.0 } // Maximum bid for buyer 2
            );
            buyerAgent2.start();

            AgentController buyerAgent3 = mainContainer.createNewAgent
            (
                    "BuyerAgent3",
                    "Buyer",
                    new Object[] { 300.0 } // Maximum bid for buyer 3
            );
            buyerAgent3.start();


        } 
        catch (StaleProxyException e) 
        {
            e.printStackTrace();
        }
    }
}



