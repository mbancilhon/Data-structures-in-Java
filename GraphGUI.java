import java.awt.BorderLayout;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;




/**
 *  Class that runs a keyboard handler GUI
 *
 *  @author Nicholas R. Howe, modified by Sara Sheehan, adapted by Melanie Bancilhon
 *  @version February 29, 2016
 */
public class GraphGUI {
	
  //  public static Graph<Integer,Integer> graph;
    public JGraph jGraph;
    
    public GraphGUI(){
    	this.jGraph=  new JGraph();
    	this.jGraph.createNodes();
    	  	
    }

    /** Allocates GUI components */
    public void createComponents(Container pane) {
    		pane.setLayout(new FlowLayout());
    		pane.add(this.jGraph);  
    		
    		JPanel panel= new JPanel();
    		panel.setLayout(new BorderLayout());
    		
    		JButton havelButton= new JButton("Havel-Hakimi");
    		panel.add(havelButton,BorderLayout.NORTH);
     		havelButton.addActionListener(new HavelListener());

    		
    		JButton BFTButton= new JButton("Breadth First Traversal");
    		panel.add(BFTButton,BorderLayout.SOUTH);
     		BFTButton.addActionListener(new BFTListener());

    		
    		JButton DFTButton= new JButton("Depth First Traversal");
    		panel.add(DFTButton,BorderLayout.CENTER);
     		DFTButton.addActionListener(new DFTListener());

    		pane.add(panel);		
    }
    /** Sets up GUI frame */
    public void createAndShowGUI() {
    	
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame = new JFrame("Example with Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add components
        createComponents(frame.getContentPane());

        // Display the window.
        frame.pack();
        frame.setVisible(true);

    }
    /** Runs the program on the event thread */
    public static void main(String[] args) {   	
        	GraphGUI gui = new GraphGUI();
        	gui.createAndShowGUI();

    }
    public class HavelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	System.out.println("HAVEL-HAKIMI");
        	JGraph.Havel(JGraph.degrees);
        	jGraph.repaint();
        }

    }
    public class BFTListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {      
        	jGraph.repaint();
        	System.out.println();
        	System.out.println("BREADTH FIRST TRAVERSAL");

        	JGraph.BFT();
        	jGraph.repaint();
        	jGraph.repaint();
        	for (int i=0; i<JGraph.myGraph.listNode.size(); i++){
        		JGraph.myGraph.listNode.get(i).visited=false;
        	}

        }
    }
    public class DFTListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	System.out.println();
        	System.out.println("DEPTH FIRST TRAVERSAL");
        	jGraph.repaint();
        	JGraph.DFT(JGraph.myGraph.listNode.get(0));
        	jGraph.repaint();
        	jGraph.repaint();
        	for (int i=0; i<JGraph.myGraph.listNode.size(); i++){
        		JGraph.myGraph.listNode.get(i).visited=false;
        	}

        }
    }
 
}

