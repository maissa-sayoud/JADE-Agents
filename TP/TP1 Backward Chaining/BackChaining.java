import java.util.ArrayList;
/*import java.util.Arrays;
import java.util.List;*/


public class BackChaining 
{
	// bp = fp
	public static boolean BackChain(Rule[] bdr,ArrayList<String> bdf, ArrayList <String> fp)
	{
		boolean res; //le reste des rules;
		
		if (fp.isEmpty())
		{
			res=true;
		}
		else
		{
			if (TakeGoal.Takegoal(bdr,bdf,fp.get(0)))
			{
				bdf.add(fp.get(0));
				res= BackChain(bdr,bdf,Rest.rest(fp));
			}
			else
			{
				res = false;
			}
		}
		
		return res;
	}
}
