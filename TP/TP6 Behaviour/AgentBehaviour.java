import javax.tools.JavaFileManager.Location;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.wrapper.ControllerException;


public class AgentBehaviour extends Agent
{

	private static final long serialVersionUID = 1L;
	
	
	protected void setup() 
	{
		System.out.println("Starting the agent"+ this.getAID().getName());
	
		//--------------------------OneShotBehaviour----------------------------//
		
		addBehaviour(new OneShotBehaviour() 
		{


			private static final long serialVersionUID = 1L;

			@Override
			public void action() 
			{
				// TODO Auto-generated method stub
				System.out.println("this action runs 1 time");
			}
		});
		
		//----------------------cyclicBehaviour------------------------------
		addBehaviour(new CyclicBehaviour() 
		{

			private static final long serialVersionUID = 1L;

			@Override
			public void action() 
			{
				// TODO Auto-generated method stub
				System.out.println("this action runs an infinity times" );
			}
		});
		
		//----------------------------WakerBehaviour-----------------------------//
		//ce reveille apres certain temps et fait une action
		addBehaviour(new WakerBehaviour(this,6000) 
		{


			private static final long serialVersionUID = 1L;

			public void onWake() {
				System.out.println(getLocalName()+"i'm daying");
				doDelete();
			}
		});
		
		//--------------------------TickerBehaviour-----------------------------/
		addBehaviour(new TickerBehaviour(this,30000)
		{


			private static final long serialVersionUID = 1L;

			@Override
			protected void onTick() {
				// TODO Auto-generated method stub
				System.out.println("this action runs each 30s");
				
			}
		});
		
		//---
		/*System.out.println("Stating agent");
		doDelete();*/
		//-----------------------------------------------------------/
	}
		protected void takeDown() 
		{
			System.out.println("agent terminated");
		}
		public void DoMove(Location L) {
			System.out.println("Agent migrration"+L.getName());
		}
		protected void beforeMove() {
			System.out.println("before miggration"+ this.getAID().getName());
			
			try 
			{
				System.out.println("from container"+this.getContainerController().getContainerName());
			} 
			catch (ControllerException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		protected void afterMove() 
		{
			System.out.println("after miggration"+ this.getAID().getName());
			try 
			{
				System.out.println("to the container "+ this.getContainerController().getContainerName());
			} 
			catch (ControllerException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
}
