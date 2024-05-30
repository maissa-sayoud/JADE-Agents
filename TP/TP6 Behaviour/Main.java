


public class Main 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		
		
	
		String [] jadeArg = new String[2];
		StringBuffer SbAgent = new StringBuffer();
		
		
		SbAgent.append("agent:BehaviourComplex;");
		SbAgent.append("agent1:BehaviourComplex;");
		
		jadeArg[0]="-gui";
		
		jadeArg[1]=SbAgent.toString();
		
		jade.Boot.main(jadeArg);
		
		
	}

}
