//Seller Agent S2

import jade.core.AID;
import jade.core.Agent;

import jade.lang.acl.ACLMessage;



public class Seller extends Agent
{
	private static final long serialVersionUID = 1L ;
	
	
	Product P = new Product() ;
	
	ACLMessage m;
		
	@Override
	protected void setup()
	{
		P.price = 2500 ;
		P.name = "Keyboard" ;
		
		m = new ACLMessage(ACLMessage.INFORM);
		
		m.addReceiver ( new AID("Buyer",AID.ISLOCALNAME));
		
	
	
		try
		{
			m.setContentObject(P);
			
			m.setLanguage("JavaSerialization"); 
			//le msg s'envoie comme une chaine de caractere puis il se reconstitue lors de son arrivé en forme d'objet Produit !
		
			send(m);
		}
		catch (Exception e)
		{
			
		}
	
}}