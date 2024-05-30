import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class Main 
{

    private static JTextField bdfTextField;
    private static JTextField bpTextField;
    private static JTextArea resultTextArea;

    public static void main(String[] args) 
    {
        //-----------------------------------------------------------
    	SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                createAndShowGUI();
            }
        }
    	);
    	//-----------------------------------------------------------
    }

    private static void createAndShowGUI() 
    {
    	//-----------------------------------------------------------
    	JFrame frame = new JFrame("Proof System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 5, 5, 5);

        JLabel bdfLabel = new JLabel("Base des faits (séparés par des espaces):");
        bdfTextField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 0;
        inputPanel.add(bdfLabel, constraints);
        constraints.gridx = 1;
        inputPanel.add(bdfTextField, constraints);

        JLabel bpLabel = new JLabel("Base à prouver:");
        bpTextField = new JTextField(20);
        constraints.gridx = 0;
        constraints.gridy = 1;
        inputPanel.add(bpLabel, constraints);
        constraints.gridx = 1;
        inputPanel.add(bpTextField, constraints);

        JButton startButton = new JButton("Démarrer la preuve");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        inputPanel.add(startButton, constraints);

        frame.add(inputPanel, BorderLayout.NORTH);

        resultTextArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);
        //-----------------------------------------------------------
        
        startButton.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            	
            	String bdfInput = bdfTextField.getText();
            	ArrayList<String> bdf = new ArrayList<>(Arrays.asList(bdfInput.split(" ")));

                String bpInput = bpTextField.getText();
                ArrayList<String> bp = new ArrayList<>(Arrays.asList(bpInput.split(" ")));

                //bdr = !base de Regle
        		Rule[] bdr = new Rule[9];
        		
        		// -- 1
        		ArrayList <String> p = new ArrayList <> (Arrays.asList("A","B"));
        		ArrayList <String> c = new ArrayList <> (Arrays.asList("F"));
        		
        		bdr[0] = new Rule(0,p,c);
        		
        		// -- 2
        		p = new ArrayList <> (Arrays.asList("F","H"));
        		c = new ArrayList <> (Arrays.asList("I"));
        		
        		bdr[1] = new Rule(1,p,c);
        		
        		// -- 3
        		p = new ArrayList <> (Arrays.asList("D","H","G"));
        		c = new ArrayList <> (Arrays.asList("A"));
        		
        		bdr[2] = new Rule(2,p,c);

        		// -- 4
        		p = new ArrayList <> (Arrays.asList("O","G"));
        		c = new ArrayList <> (Arrays.asList("H"));
        		
        		bdr[3] = new Rule(3,p,c);
        		
        		// -- 5
        		p = new ArrayList <> (Arrays.asList("E","H"));
        		c = new ArrayList <> (Arrays.asList("B"));
        		
        		bdr[4] = new Rule(4,p,c);
        		
        		// -- 6
        		p = new ArrayList <> (Arrays.asList("G","A"));
        		c = new ArrayList <> (Arrays.asList("B"));
        		
        		bdr[5] = new Rule(5,p,c);
        		
        		// -- 7
        		p = new ArrayList <> (Arrays.asList("G","H"));
        		c = new ArrayList <> (Arrays.asList("P"));
        		
        		bdr[6] = new Rule(6,p,c);
        		
        		// -- 8
        		p = new ArrayList <> (Arrays.asList("G","H"));
        		c = new ArrayList <> (Arrays.asList("O"));
        		
        		bdr[7] = new Rule(7,p,c);
        		
        		// -- 8
        		p = new ArrayList <> (Arrays.asList("D","O","G"));
        		c = new ArrayList <> (Arrays.asList("J"));
        				
        		bdr[8] = new Rule(8,p,c);

                // Appel de la fonction Backchain
                boolean test = BackChaining.BackChain(bdr, bdf, bp);

                // Mise à jour du JTextArea avec le résultat
                resultTextArea.setText("Résultat de la preuve : " + test);
            }
        });
        
        //-----------------------------------------------------------
        frame.pack();
        frame.setVisible(true);
        //-----------------------------------------------------------
    }
}





