
//import java.util.Arrays;
//import java.util.List;
import java.util.ArrayList;

public class Rest 
{
	public static ArrayList<String> rest (ArrayList <String> a)
	{
		ArrayList<String> r = new ArrayList<String> ();
		
		for (int i=1; i<a.size(); i++)
		{
			r.add(a.get(i));
		}
		
		return r;
	}
}
