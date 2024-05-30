
import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;

public class Rule 
{
	boolean state; //etat : activable ou non activable pour le forward chaining
	
	ArrayList <String> P; //premisses
	
	ArrayList <String> C; //conclusion
	
	int name; //nom de la regle
	
	public Rule (int i, ArrayList <String> a1, ArrayList <String> a2)
	{
		name=i;
		state=true;
		P=a1;
		C=a2;
	}
	
	/*public GetRule (int r)
	{
		System.out.println(name+" "+P);
	}*/
	
}
