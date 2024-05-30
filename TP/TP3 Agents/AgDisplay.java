import jade.core.Agent;

public class AgDisplay extends Agent
{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void setup()
	{
		System.out.println("\n\nMy name is : "+getLocalName());
	}
	
	
}
