import jade.core.Agent;
import jade.lang.acl.ACLMessage;



public class Buyer extends Agent
{

	private static final long serialVersionUID = 1L ;
	
	@Override
	protected void setup()
	{
		ACLMessage m;
		
		Product P;
		
		m = receive();
		
		if(m!=null)
		{
			try 
			{
				P= (Product)m.getContentObject();
				
				System.out.println("I am "+getLocalName()+" I received the product "+P.name+" with the price : "+P.price);
			}
			catch (Exception e)
			{
				
			}
		}
	}
	
	
}
