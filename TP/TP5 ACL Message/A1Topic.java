import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.MessageTemplate;


public class A1Topic extends Agent
{
	
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void setup()
    {
    	try
    	{
    		TopicManagementHelper TopicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
    		
    		final AID topic = TopicHelper.createTopic("JADE");
    		
    		TopicHelper.register(topic);
    		
    		addBehaviour(new CyclicBehaviour(this) 
    		{
    			
    			private static final long serialVersionUID = 1L;
    			
    			public void action ()
    			{
    				ACLMessage msg = receive(MessageTemplate.MatchTopic(topic));
    				
    				if (msg!=null)
    				{
    					System.out.println("\nAgent "+getLocalName()+" : Message about topic "+topic.getLocalName()+" received contentent is : "+msg.getContent());
    				}
    				else
    				{
    					block();
    				}
    			}
    		});
    	}
    	catch (ServiceException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    }
	

}



