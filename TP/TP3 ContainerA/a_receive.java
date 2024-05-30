
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class a_receive extends Agent
{
	private static final long serialVersionUID = 1L;

	protected void setup()
	{
		addBehaviour(new CyclicBehaviour(this)
		{
			private static final long serialVersionUID = 1L;
			
			@Override
			public void action() 
			{
				// TODO Auto-generated method stub
				ACLMessage msg=receive();
				
				if(msg!=null) 
				{
					System.out.println("I am "+ getLocalName() +" I received a message " + msg.getContent() +" From the agent  "+ msg.getSender().getName());
				
					ACLMessage reply=msg.createReply();
				
					reply.setPerformative(ACLMessage.INFORM);
					reply.setContent("Thank you");
				
					send(reply);
				}
				block();			
			}
		});
	}
}
