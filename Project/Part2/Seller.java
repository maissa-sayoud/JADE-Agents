import jade.core.Agent; 
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Seller extends Agent 
{

    private static final long serialVersionUID = 1L;
    private Map<String, Double> criteria;

    @Override
    protected void setup() 
    { 
        criteria = new HashMap<>();
        criteria.put("Price", Math.random() * 100 + 50);
        criteria.put("Quality", Math.random() * 5 + 5);
        criteria.put("DeliveryCost", Math.random() * 20 + 5);

        System.out.println("Seller agent started. Criteria: " + criteria);

        addBehaviour(new StartAuctionBehaviour());
        addBehaviour(new WakerBehaviour(this, 10000) 
        {
            private static final long serialVersionUID = 1L;
            
            
            @Override
            protected void onWake() 
            {
                endAuction();
            }
        });
    }

    private class StartAuctionBehaviour extends Behaviour 
    {
        private static final long serialVersionUID = 1L;
        
        
        private boolean finished = false;

        @Override
        public void action() 
        {
            ACLMessage proposal = new ACLMessage(ACLMessage.PROPOSE);
            proposal.addReceiver(getAID("Buyer"));
           
            try 
            {
                proposal.setContentObject((Serializable) criteria);
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
            
            send(proposal);
            
            finished = true;
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

    private void endAuction() 
    {
        System.out.println("Auction ended.");
        ACLMessage endMsg = new ACLMessage(ACLMessage.INFORM);
        endMsg.addReceiver(getAID("Buyer"));
        endMsg.setContent("Auction ended");
        send(endMsg);
    }
}
