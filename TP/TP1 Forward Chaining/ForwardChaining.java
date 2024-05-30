
import java.util.ArrayList;
import java.util.Arrays;

public class ForwardChaining 
{

		public static boolean ForwardChain(Rule[] bdr, ArrayList<String> bdf, ArrayList<String> fp) 
		{
			
			boolean resultat = false; 
			
			if (bdf.containsAll(fp))
			{
				resultat = true;  // SUCCESS
				//System.out.println("BF est dans bdf\n");
			}
	        else 
	        {
	        	ArrayList<Rule> reglesNonDeclenchees = new ArrayList<>();
	        	reglesNonDeclenchees.addAll(Arrays.asList(bdr)); 
	        	//System.out.println(reglesNonDeclenchees);
	        	/*for (Rule i : reglesNonDeclenchees)
	        		System.out.println("regles non declenches :\n"+i.name);
	        	*/
	        	
	        	ArrayList<Rule> reglesAConsiderer = new ArrayList<>();
	        	reglesAConsiderer.addAll(Arrays.asList(bdr)); 
	        	//System.out.println(reglesAConsiderer);
	        	/*for (Rule i : reglesAConsiderer)
	        		System.out.println("regles a Considerer :\n"+i.name);
	        	*/
	        	
	        	Rule r;
	        	
	        	
	            while (!reglesNonDeclenchees.isEmpty() && resultat==false) 
	            {
	            	r = ChooseRule.chooseRule(reglesAConsiderer, bdf);
		        	
	            	//System.out.println(r.name);
	            	
	            	reglesAConsiderer.remove(r);
	            	
	            	if (bdf.containsAll(r.P))
	            	{
	            		bdf.addAll(r.C);	
	            
	            		reglesNonDeclenchees.remove(r);
	            		System.out.println("\n\nje remove la regle: "+r.name);
	            		
	            		System.out.println("\nregles non declenchees :\n");
	            		for (Rule i : reglesNonDeclenchees)
	    	        		System.out.println(i.name);
	            		
	            		
	            		
	            		reglesAConsiderer = new ArrayList<>(reglesNonDeclenchees);

	            		/*System.out.println("regles A Considerer :\n");
	            		for (Rule i : reglesAConsiderer)
	    	        		System.out.println(i.name);
	            		*/
	            		
	            		if (bdf.containsAll(fp)) //verififer si le but est atteint ou pas encore : 
	            		{
	            			System.out.println("\n\nSUCCESS ! ");
	            			resultat = true;
	            		}
	            	}
	            }
	        }
			
	        return resultat;
	        
	    }

		
}
