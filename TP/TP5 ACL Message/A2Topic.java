import jade.core.AID;
import jade.core.Agent;
import jade.core.messaging.TopicManagementHelper;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.TickerBehaviour;


public class A2Topic extends Agent
{
	
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void setup()
    {
    	try
    	{
    		TopicManagementHelper TopicHelper = (TopicManagementHelper) getHelper(TopicManagementHelper.SERVICE_NAME);
    		
    		final AID topic = TopicHelper.createTopic("JADE");
    		
    		//TopicHelper.register(topic);
    		
    		addBehaviour(new TickerBehaviour(this, 5000) 
    		{
    			
    			private static final long serialVersionUID = 1L;
    			
    			public void onTick ()
    			{
    				System.out.println("\nAgent "+getLocalName()+" : Sending message about topic "+topic.getLocalName());
    				
    				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
    				
    				msg.addReceiver(topic);
    				
    				msg.setContent(String.valueOf(getTickCount()));
    				
    				send(msg);
    			}
    		});
    	}
    	catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
    }
	

}



