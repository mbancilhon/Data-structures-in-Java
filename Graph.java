import java.awt.Color;
import java.awt.Point;
import java.util.LinkedList;

/**
 *  Creating a graph by building nodes and edges and their methods
 *
 *  @author Melanie Bancilhon
 *  @version CSC 112, April 22 2016
 */


public class Graph<V,E> {
	LinkedList<Edge<E>> listEdge= new LinkedList<Edge<E>>();
	LinkedList<Node<V>> listNode= new LinkedList<Node<V>>();
	
	public Graph(){
				
	}
	public class Edge<E>{
				
		public Node<V> head;
		public Node<V> tail;
		public E dataEdge;

		public Edge(E data, Node<V> head, Node<V> tail){
			this.head=head;
			this.tail=tail;
			this.dataEdge= data;
		}
	}
	
	public class Node<V>{
		public V data;
		public boolean visited;
		public int weight;
		
		public Node(V data, boolean visited,int weight){
			this.data=data;	
			this.visited= visited;
			this.weight= weight;
		}
	}
		
	public Edge<E> addEdge(E data, Node<V> head, Node<V> tail){
		Edge<E> edge= new Edge<E>(data,head,tail);
		listEdge.add(edge);
		return edge;
	}
	
	public Node<V> addNode(V data){
		Node<V> node= new Node<V>(data, false, 1000);
		listNode.add(node);	
		return node;
		
	}
	
	public int numEdges(){
		return listEdge.size();		
	}
	public int numNodes(){
		return listNode.size();		
	}

	public void check(Edge<E> edge) {
		//instead of returning false, return error

		//if is the base case
		//First edge added
		if (listEdge.isEmpty()){
			listEdge.add(edge);				
		}
		else{
			boolean equal = true;
				for (int j=0; j<listEdge.size();j++){
					if (getEdge(j).head.equals(edge.head) && getEdge(j).tail.equals(edge.tail)){
						System.out.println("Error: Duplicate Edge "+edge.dataEdge);
						System.out.println();
						break;
						
					}
					else{
						equal=false;					
					}
				}
				//if edge equals no other node
				//add to list of edges
				if (equal==false){
					listEdge.add(edge);				
				}			
			}		
	   }
	
	public Edge<E> getEdge(int i){
		return listEdge.get(i); 
	}
	
	public Edge<E> getEdgeRef(Node< V> head, Node<V> tail ){
		for (int i=0; i<listEdge.size(); i++){
			for (int j=0; j<listEdge.size(); j++){
				if (getEdge(i).head.equals(head) && getEdge(j).tail.equals(tail)){
					return listEdge.get(i);
				}            	 
             }            
		}
		return null;
	}
	
	public Node<V> getNode(int i){
		return listNode.get(i) ; 
	}
	
	public void removeEdge(Edge<E> edge){
		listEdge.remove(edge);

	}
	
	public void removeEdge(Node<V> head, Node<V> tail){
		  listEdge.remove(getEdgeRef(head,tail));
		     
	}
	public void removeNode(Node<V> node){
		listNode.remove(node);		
		for (int i=0; i<listEdge.size(); i++){
			if (getEdge(i).head.equals(node)){
				removeEdge(getEdge(i));
			}
			if (getEdge(i).tail.equals(node)){
				removeEdge(getEdge(i));
			}
		}
	}
	
	public void print(){
		
		for( int j=0; j<listEdge.size();j++){
			System.out.println("Edge= "+getEdge(j).dataEdge+"  Head Node= "+getEdge(j).head.data+"   Tail Node= "+getEdge(j).tail.data);
		}
		for( int i=0; i<listNode.size();i++){
			System.out.println("Node= "+getNode(i).data);
		}

	}
	

}
