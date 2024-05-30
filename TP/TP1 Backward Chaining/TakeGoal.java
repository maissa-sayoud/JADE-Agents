
//import java.util.Arrays;
//import java.util.List;
import java.util.ArrayList;


//construire l'ensemble des conflits 
//les regles activables

public class TakeGoal 
{
	
	public static Boolean Takegoal (Rule[] bdr, ArrayList <String> bdf, String goal)
	{
		Boolean res;
	
		if (bdf.contains(goal))
		{
			res = true;
		}
		else
		{
			res = false;
			//RA : l'ensemble des conflits: toutes les regles activables
			ArrayList <Rule> RA = new ArrayList <Rule> (); //initialement Vide
			
			for(int i=0; i<bdr.length; i++)
			{
				if (bdr[i].C.contains(goal))
				{
					RA.add(bdr[i]);		
				}
			}
			
			while (!RA.isEmpty() && res!=true)
			{
				Rule r = RA.get(0);
				RA.remove(0);
				res = BackChaining.BackChain(bdr, bdf, r.P);
			}
			
		}
		
		return res;
	}
	
}
