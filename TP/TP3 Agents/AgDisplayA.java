import jade.core.Agent;

public class AgDisplayA extends Agent
{
	private static final long serialVersionUID = 1L;
	
	float price;
	
	String name;
	
	@Override
	protected void setup()
	{
		Object[] args = getArguments();
		
		if (args!=null)
		{
			name = (String)args[0];
			
			price = Float.valueOf((String)(args[1])).floatValue();
			
			System.out.println("\nAgent : "+getLocalName()+" sells the product "+name+" at price "+price+"$.\n\n");
			
			doDelete(); //KILL agent ! dans ce cas "AP_DELETED" est à TRUE !
		}
			
	}
}
