import jade.core.Agent; 
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Seller extends Agent 
{


    private static final long serialVersionUID = 1L;
    
    
    
    private double startingPrice;
    private double reservePrice;
    private double currentBid;
    private String currentBidder;
    private boolean onAuctionStart = false;

    
    private List<String> buyers = Arrays.asList("BuyerAgent1", "BuyerAgent2", "BuyerAgent3");

    
    @Override
    protected void setup() 
    {
        Object[] args = getArguments();
        
        if (args != null && args.length == 2) 
        {
            startingPrice = (double) args[0];
            reservePrice = (double) args[1];
        } 
        else 
        {
            startingPrice = 100;
            reservePrice = 120;
        }

        
        
        currentBid = startingPrice;

        System.out.println("Seller agent started. Starting price: " + startingPrice);

        
        
        
        // behaviour to start the auction
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

        
        
        // Main auction behaviour
        addBehaviour(new AuctionBehaviour());
        
        
    }

    private class StartAuctionBehaviour extends Behaviour 
    {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        
        
        
        private boolean finished = false;

        
        @Override
        public void action() 
        {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            ACLMessage msg = myAgent.receive(mt);

            if (msg != null) 
            {
                String content = msg.getContent();
                System.out.println("Received message: " + content);

                if (content.equals("Start the auction")) 
                {
                    for (int i = 0; i < 3; i++) 
                    {
                        buyers.add("BuyerAgent" + i);
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
        public int onEnd() {
            return 0;
        }
    }


    private class AuctionBehaviour extends Behaviour 
    {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        
        private boolean finished = false;

        @Override
        public void action() 
        {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
            ACLMessage msg = myAgent.receive(mt);

            if (msg != null) 
            {
                try 
                {
                    double bid = (double) msg.getContentObject();
                    String bidder = msg.getSender().getLocalName();

                    if (bid > currentBid) 
                    {
                        currentBid = bid;
                        currentBidder = bidder;

                        // Notify all buyers of the new highest bid
                        notifyAllBuyers();
                    }
                } 
                catch (UnreadableException e) 
                {
                    e.printStackTrace();
                }
            } 
            else 
            {
                if (!onAuctionStart) 
                {
                    onAuctionStart = true;
                    notifyAllBuyers();
                }
                block();
            }
        }

        private void notifyAllBuyers() 
        {
            for (String buyer : buyers) 
            {
                ACLMessage inform = new ACLMessage(ACLMessage.INFORM);
                inform.addReceiver(getAID(buyer));
                
                inform.setContent("Current highest bid: " + currentBid);
                myAgent.send(inform);
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

    private void endAuction() 
    {
        if (currentBid >= reservePrice && currentBidder != null) 
        {
            System.out.println("Auction ended. Winner: " + currentBidder + " with bid: " + currentBid);
        } 
        else 
        {
            System.out.println("Auction ended. No winner.");
        }
        
        doDelete();
    }
}
