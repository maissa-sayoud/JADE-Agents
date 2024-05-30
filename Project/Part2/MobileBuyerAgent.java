import jade.core.Agent; 
import jade.core.ContainerID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.HashMap;
import java.util.Map;

public class MobileBuyerAgent extends Agent {

    private static final long serialVersionUID = 1L;
    private Map<String, Double> weights;
    private double bestScore = Double.NEGATIVE_INFINITY;
    private String bestSeller = null;

    @Override
    protected void setup() {
        System.out.println("Mobile Buyer Agent " + getLocalName() + " started.");

        weights = new HashMap<>();
        weights.put("Price", 0.4);
        weights.put("Quality", 0.5);
        weights.put("DeliveryCost", 0.1);

        // Adding migration behaviour with delay to the second platform
        addBehaviour(new MigrateBehaviour("SubContainer"));
        addBehaviour(new DelayedMigrateBehaviour("SubContainer2", 15000)); // Delayed migration to second platform

        // Adding behaviours to handle proposals and end of auction
        addBehaviour(new HandleProposalsBehaviour());
        addBehaviour(new EndAuctionBehaviour());
    }

    private class MigrateBehaviour extends OneShotBehaviour 
    {
        private static final long serialVersionUID = 1L;
        private String targetContainer;

        public MigrateBehaviour(String targetContainer) 
        {
            this.targetContainer = targetContainer;
        }

        @Override
        public void action() 
        {
            try 
            {
                
            	// Add delay before migration
                Thread.sleep(5000);
            
                // Migrate to target container
                ContainerID containerID = new ContainerID(targetContainer, null);
                
                doMove(containerID);
                System.out.println("Agent " + getLocalName() + " moved to container: " + targetContainer);
                
                
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }

    private class DelayedMigrateBehaviour extends OneShotBehaviour 
    {
        private static final long serialVersionUID = 1L;
        private String targetContainer;
        private long delay;

        public DelayedMigrateBehaviour(String targetContainer, long delay) {
            this.targetContainer = targetContainer;
            this.delay = delay;
        }

        @Override
        public void action() 
        {
            try 
            {
                // Delay before migration to the second platform
                Thread.sleep(delay);
                // Migrate to target container on the second platform
                ContainerID containerID = new ContainerID(targetContainer, null);
                doMove(containerID);
                System.out.println("Agent " + getLocalName() + " moved to container: " + targetContainer);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
    }

    private class HandleProposalsBehaviour extends CyclicBehaviour 
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() 
        {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.PROPOSE);
            ACLMessage msg = myAgent.receive(mt);

            if (msg != null) 
            {
                try 
                {
                    Map<String, Double> proposal = (Map<String, Double>) msg.getContentObject();
                    double score = evaluateProposal(proposal);
                    System.out.println("Received proposal from " + msg.getSender().getLocalName() + " with score: " + score);

                    if (score > bestScore) 
                    {
                        bestScore = score;
                        bestSeller = msg.getSender().getLocalName();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } 
            else 
            {
                block();
            }
        }

        private double evaluateProposal(Map<String, Double> proposal) 
        {
            double normalizedPrice = normalize(proposal.get("Price"), 50, 150);
            double normalizedQuality = normalize(proposal.get("Quality"), 1, 10);
            double normalizedDeliveryCost = normalize(proposal.get("DeliveryCost"), 5, 20);

            double score = weights.get("Price") * (1 - normalizedPrice)
                          + weights.get("Quality") * normalizedQuality
                          + weights.get("DeliveryCost") * (1 - normalizedDeliveryCost);

            return score;
        }

        private double normalize(double value, double min, double max)
        {
            return (value - min) / (max - min);
        }
    }

    private class EndAuctionBehaviour extends CyclicBehaviour 
    {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() 
        {
            MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
            ACLMessage msg = myAgent.receive(mt);

            if (msg != null) 
            {
                String content = msg.getContent();
                if (content.equals("Auction ended")) 
                {
                    if (bestSeller != null) 
                    {
                        System.out.println("Auction winner is " + bestSeller + " with score: " + bestScore);
                    } 
                    else 
                    {
                        System.out.println("Auction ended with no valid proposals.");
                    }
                }
            } 
            else 
            {
                block();
            }
        }
    }
}
