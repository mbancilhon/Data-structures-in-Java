import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JComponent;


/**
 *  Creating a graph by building nodes and edges and their methods
 *
 *  @author Melanie Bancilhon
 *  @version CSC 112, April 22 2016
 */

public class JGraph extends JComponent {	
	
	public static Graph<DisplayNode, DisplayEdge> myGraph= new Graph<DisplayNode, DisplayEdge>();
    public static int offset=0;
	public static LinkedList<Integer> degrees= new LinkedList<Integer>();
	public static int colorBasis= 255;


    /** For serializing the class */
	private static final long serialVersionUID = 1L;


    /** Constructor is requires a queue object */
    public JGraph() {

    }

    /**
     *  Draws the queue in the graphics window
     *  @param g The graphics object to draw into
     */
  
    /**
     *  The component will look bad if it is sized smaller than this
     *  @returns The minimum dimension
     */
    public Dimension getMinimumSize() {
    		return new Dimension(500,500);
    }

    /**
     *  The component will look best at this size
     *  @returns The preferred dimension
     */
    public Dimension getPreferredSize() {
    		return new Dimension(500,500);
    }

    public static void shortestPath(Graph.Node startingNode){
    	    	
    	startingNode.weight=0;
    	int smallestWeight=1000;
    	Graph.Node smallestNode= startingNode;
    	boolean unvisitedNodes=true; 
    	
    	//As long as some nodes are unvisited, loop
    	
    	
    	while (unvisitedNodes){
	    //	System.out.println("Smallest node is"+smallestNode);

	    	for (int i=0; i<myGraph.listNode.size(); i++){
	    		if (myGraph.listNode.get(i).weight< smallestNode.weight);
	    		   smallestNode= myGraph.listNode.get(i);
	    	}
	    	smallestNode.visited= true;
	    	for (int i=0; i<myGraph.listEdge.size(); i++){
	    		//visiting neighbours of smallest node
	    		Graph.Node neighbour= myGraph.listEdge.get(i).tail;
	    		if (myGraph.listEdge.get(i).head== smallestNode && !neighbour.visited){ //then tail= neighbours
	    			Point startPoint = new Point(myGraph.listEdge.get(i).head.data.x,myGraph.listEdge.get(i).head.data.y) ;
	    			Point endPoint = new Point(myGraph.listEdge.get(i).tail.data.x,myGraph.listEdge.get(i).tail.data.y) ;
	    			//calculate weight of edge
	    			int weight= calculateWeight(startPoint, endPoint );
	    			if (myGraph.listEdge.get(i).tail.weight> weight+ myGraph.listEdge.get(i).head.weight){
	    				//reduce cost
	    				myGraph.listEdge.get(i).tail.weight= weight+ myGraph.listEdge.get(i).head.weight;				
	    			}
	    		}
	    	}
	    	for (int i=0; i<myGraph.listEdge.size(); i++){
	    		Graph.Node neighbour= myGraph.listEdge.get(i).tail;
	    		if (!neighbour.visited){ 
	    			unvisitedNodes=true;
	    			break;
	    		}
	    		else{
	    			unvisitedNodes=false;
	    		}
	    	}
    	}
    }
	    	
	
	public static void Havel(LinkedList<Integer> sequence){
		System.out.println();

		System.out.println("Entered Havel");

		System.out.println();
		//largest is the first element
		Integer largest= sequence.get(0);
	
		while (largest!=0){

			//start for loop at index one
			for (int i=1; i<sequence.size(); i++){

					    //create an edge, which will be a line
					    // starting point at x and y coordinates of first node
					    // ending point at x and y coordinates of second node
					    
 
		                 //decrement degree of connecting node
					    
					    //in listNodes
		                  Integer newSequence= sequence.get(i)-1;
		                  String label= newSequence.toString();
					      myGraph.listNode.get(i+offset).data.label= label;				      
					     //in sequence
		                  sequence.set(i, sequence.get(i)-1);

		                  
		                  //decrement value of largest
		                  
							largest=largest-1;
		                  //in listNodes
					      myGraph.listNode.get(0+offset).data.label= largest.toString();
					      //in sequence
						  sequence.set(0, largest);
						  
						   //create the edge
						   Point startPoint= new Point(myGraph.listNode.get(offset).data.x,myGraph.listNode.get(offset).data.y);
						   Point endPoint= new Point(myGraph.listNode.get(i+offset).data.x,myGraph.listNode.get(i+offset).data.y);
						   int weight= calculateWeight(startPoint, endPoint);
						   DisplayEdge display= new DisplayEdge(startPoint, endPoint, weight);
						   myGraph.addEdge(display,myGraph.listNode.get(offset),myGraph.listNode.get(i+offset) );							        
			}
		}		
		for (int i=0; i<sequence.size(); i++){
			System.out.println("Degree is "+sequence.get(i));
		}	
		for (int i=0; i<sequence.size(); i++){
			if (sequence.get(i).equals(0)){
				offset= offset+1;
				sequence.remove(i);
	
			}
		}	
		if (!sequence.isEmpty()){
			Havel(sequence);
		}

	}	
	private static int calculateWeight(Point startPoint, Point endPoint) {
		
    	int dx= Math.abs((int) (endPoint.getX()-startPoint.getX()));
		int dy= Math.abs((int) (endPoint.getY()-startPoint.getY()));
		
		//use pythagoras' theorem to find path
		int path=  (int) Math.sqrt((dx^2 + dy^2));
		return path;
	}
	public static void DFT(Graph.Node currNode){
		System.out.println("Current node is" + currNode);
		
	//	int colorBasis= 0;
		Graph.Node neighbour = null; 

			for (int i=0; i<myGraph.listEdge.size(); i++){
	    		neighbour= myGraph.listEdge.get(i).tail;
		    	if (myGraph.listEdge.get(i).head== currNode && !neighbour.visited){
		    		neighbour.visited= true;
		    		//each time a neighbour is visited, its color gets lighter
		    		colorBasis= colorBasis-15;
		    		int R = colorBasis;
		    		int G = colorBasis;
		    		int B= 255;
		    		myGraph.listEdge.get(i).head.data.color= Color.BLUE;
		    		myGraph.listEdge.get(i).tail.data.color=new Color(R,G,B);
		    		DFT(neighbour);		    		
		    	}				   	
		}

	}
	public static void BFT(){
		
		int colorBasis= 0;
		Queue<Graph.Node> queue= new LinkedList<Graph.Node>();	


		Graph.Node neighbour;
		Graph.Node startNode= myGraph.listNode.get(0);
		if (startNode==null){
			
		}
		else{
			queue.add(startNode);
			//System.out.println(startNode.data);		
		}		
		while (!queue.isEmpty()){
			Graph.Node currNode= queue.remove();
			System.out.println("Traversed Nodes= "+currNode);
				
			for (int i=0; i<myGraph.listEdge.size(); i++){
	    		neighbour= myGraph.listEdge.get(i).tail;
		    	if (myGraph.listEdge.get(i).head== currNode && !neighbour.visited){					
		    		queue.add(neighbour);
		    		//When the size of the list decreases, that is the more nodes are traversed
		    		//the lighter the color of the node
		    		colorBasis= colorBasis+ (255/myGraph.listNode.size());
		    		int R = 255;
		    		int G = colorBasis;
		    		int B= colorBasis;
		    		myGraph.listEdge.get(i).head.data.color= Color.RED;
		    		myGraph.listEdge.get(i).tail.data.color= new Color(R,G,B);
		    		neighbour.visited= true;  
		    	}				   	
		    }
		}
	}
	  public void paintComponent(Graphics g) {

	    		g.setFont(new Font("Monospace",Font.BOLD,24));
	    		

	    		int diameter = 20;
			
	    		for (int i = 0; i < myGraph.listEdge.size(); i++) {
	    			Point sPoint= myGraph.listEdge.get(i).dataEdge.sPoint;
	    			Point ePoint= myGraph.listEdge.get(i).dataEdge.ePoint;
	    			Graphics2D g2 = (Graphics2D) g;
	    			g.setColor(Color.BLACK);
	    			g.drawLine((int)sPoint.getX()+(diameter/2), (int)sPoint.getY()+(diameter/2), (int)ePoint.getX()+(diameter/2), (int)ePoint.getY()+(diameter/2));
	   
	    		}
	    		//display the nodes
	    		for (int i = 0; i < myGraph.listNode.size(); i++) {
	    			int xpoint=myGraph.listNode.get(i).data.x;
	    			int ypoint=myGraph.listNode.get(i).data.y;
	    			int data= Integer.parseInt(myGraph.listNode.get(i).data.label);
	    			String label= myGraph.listNode.get(i).data.label;
	    			g.setColor(myGraph.listNode.get(i).data.color);
	    			g.fillOval(xpoint, ypoint, diameter, diameter);
	    			g.drawString(label, xpoint, ypoint);
	    		}
	    }

	public static void createNodes(){
			
		degrees.add(3);
		degrees.add(2);
		degrees.add(2);
		degrees.add(3);

		
		//sort list of degrees
		Collections.sort(degrees);
		Collections.reverse(degrees);

		//create new display nodes with element of sequence as label
		//creates nodes with DisplayNode as data
		for (int i=0; i<degrees.size(); i++){
			String label= degrees.get(i).toString();
			int xcoord= (int)(Math.random()*400);
			int ycoord= (int)(Math.random()*400);
			DisplayNode display= new DisplayNode(Color.BLACK,xcoord ,ycoord ,label);
			Graph.Node node= myGraph.addNode(display);
		}
		
	}

}

