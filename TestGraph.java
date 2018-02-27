import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JComponent;

/**
 *  Creating a graph by building nodes and edges and their methods
 *
 *  @author Melanie Bancilhon
 *  @version CSC 112, April 22 2016
 */

public class TestGraph extends JComponent {
	
	public static Graph<Integer, Integer> myGraph= new Graph<Integer, Integer>();
	
	public static LinkedList<Integer> sequence= new LinkedList<Integer>();
    /** For serializing the class */
	private static final long serialVersionUID = 1L;


    /** Constructor is requires a queue object */
    public TestGraph(Graph<Integer,Integer> myGraph) {
        this.myGraph = myGraph;
    }

    /**
     *  Draws the queue in the graphics window
     *  @param g The graphics object to draw into
     */
    public void paintComponent(Graphics g) {
        System.out.println("got to JGraph");

    		g.setFont(new Font("Monospace",Font.BOLD,36));
    		
    		int x=0;
    		int y=0;
    		// cycle through queue & display entries
    		for (int i = 0; i < this.myGraph.listNode.size(); i++) {
    			Integer nodeData= myGraph.getNode(i).data;
    			x=x+2;
    			y=y+2;
    			g.fillOval(x, y, nodeData, nodeData);
    		}
    		repaint();
    }

    /**
     *  The component will look bad if it is sized smaller than this
     *  @returns The minimum dimension
     */
    public Dimension getMinimumSize() {
    		return new Dimension(375,50);
    }

    /**
     *  The component will look best at this size
     *  @returns The preferred dimension
     */
    public Dimension getPreferredSize() {
    		return new Dimension(375,50);
    }

		
	public static void Havel(LinkedList<Integer> sequence){
		
		System.out.println("havel is called");
		//sort the list
		Collections.sort(sequence);
		Collections.reverse(sequence);
		
		System.out.println();

		//largest is the first element
		int largest= sequence.get(0);
		
		//loop from second largest to maximum edges largest can have
		//eg if largest is 3, need to add 3 edges
		int edgeData=0;
		
		while (largest!=0){
			for (int i=1; i<sequence.size(); i++){
					myGraph.addEdge(edgeData+1, myGraph.getNode(0), myGraph.getNode(i));
					sequence.set(i, sequence.get(i)-1);
					largest=largest-1;
					System.out.println("largest is"+largest);
					sequence.set(0, largest);
			}
		}	
		System.out.println("got here");

		for (int i=0; i<sequence.size(); i++){
			System.out.println("end list is "+sequence.get(i));
		}
		
		for (int i=0; i<sequence.size(); i++){
			if (sequence.get(i).equals(0)){
				sequence.remove(i);
			}
		}	
		if (!sequence.isEmpty()){
			Havel(sequence);
		}
	}
     
	public static void main(String[] args){
			
		sequence.add(2);
		sequence.add(3);
		sequence.add(3);
		sequence.add(4);
		sequence.add(4);

		
		//add Nodes

		for (int i=0; i<sequence.size(); i++){
			myGraph.addNode(sequence.get(i));
		}
		
		for (int i=0; i<sequence.size(); i++){
			System.out.println("liiiiist is "+sequence.get(i));
		}
		
		//Call havel to add sequence
        Havel(sequence);
	}

}

