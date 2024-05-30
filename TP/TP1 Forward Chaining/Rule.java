import java.util.ArrayList;

public class Rule 
{
	boolean state = true ; //etat : toutes les regles sont activables au preallable
	
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
	
}
