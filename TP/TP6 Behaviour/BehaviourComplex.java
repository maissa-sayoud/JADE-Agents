import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.ParallelBehaviour;
public class BehaviourComplex extends Agent
{
	private static final long serialVersionUID = 1L;
	
	protected void setup() 
	{
		ParallelBehaviour parab=new ParallelBehaviour();
		parab.addSubBehaviour(langhelloBehaviour(" السلام "));
		parab.addSubBehaviour(langhelloBehaviour(" Bonjour "));
		parab.addSubBehaviour(langhelloBehaviour("buenos dias"));
		parab.addSubBehaviour(langhelloBehaviour(" Maraba "));
		System.out.println();
		addBehaviour(parab);
	}
	private Behaviour langhelloBehaviour(String msg) 
	{
		Behaviour b= new Behaviour() 
		{
			private static final long serialVersionUID = 1L;
						
			int i=0;
			@Override
			public void action() 
			{
				// TODO Auto-generated method stub
				System.out.println("affichage du msg"+msg);
			
				i++;	
			}
			
			@Override
			public boolean done() 
			{
				// TODO Auto-generated method stub
				return i>=3;
			}
			
		};
		return b;
	}
}
