import jade.core.Agent; 
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.Random;

public class Buyer extends Agent 
{


	private static final long serialVersionUID = 1L;
	
	private double maxBid;
    private double currentBid;
    
    private Random random;

    
    
    @Override
    protected void setup() 
    {
        Object[] args = getArguments();
        
        if (args != null && args.length == 1) 
        {
            maxBid = (double) args[0];
        } 
        else 
        {
            maxBid = 200; 
        }

        
        System.out.println("Buyer agent " + getLocalName() + " started.");

        
        
        random = new Random();
        
        // Main behaviour to interact with the auction
        addBehaviour(new BiddingBehaviour());
    }

    private class BiddingBehaviour extends Behaviour 
    {


		private static final long serialVersionUID = 1L;
		
		
		
		private boolean finished = false;

        @Override
        public void action() 
        {
            // Receive the latest auction update
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            ACLMessage msg = myAgent.receive(mt);


            if (msg != null) 
            {
                String content = msg.getContent();
                System.out.println(getLocalName() + " received: " + content);

                double highestBid = Double.parseDouble(content.split(": ")[1]);

                // Determine if we should bid higher
                if (highestBid < maxBid && random.nextBoolean()) 
                {
                    double bid = highestBid + random.nextInt(10) + 1;

                    ACLMessage propose = new ACLMessage(ACLMessage.PROPOSE);
                    propose.addReceiver(getAID("SellerAgent"));
                    try 
                    {
                        propose.setContentObject(bid);
                        myAgent.send(propose);

                        currentBid = bid;
                        System.out.println(getLocalName() + " bid: " + bid);
                    } 
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
            } 
            else 
            {
                block();
            }
        }

        @Override
        public boolean done()
        {
            return finished;
        }

        @Override
        public int onEnd() 
        {
            return 0;
        }
    }
}