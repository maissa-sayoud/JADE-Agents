
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class a_sender extends Agent
{

	private static final long serialVersionUID = 1L;

	protected void setup() 
	{
		ACLMessage msg=new ACLMessage(ACLMessage.INFORM);
		
		msg.setContent("Hello");
		msg.addReceiver(new AID("receiver",AID.ISLOCALNAME));
		
		send(msg);
		
		// TODO Auto-generated method stub
		ACLMessage msg1=new ACLMessage(ACLMessage.INFORM);
		
		msg1=receive();
		
		if(msg1!=null) 
		{
			System.out.println("Iam "+ getLocalName() +" I received a reply " + msg.getContent() +" From a reply "+ msg.getSender().getName());	
		}
	}
}
