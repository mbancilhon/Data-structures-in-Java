import java.awt.Color;
import java.awt.Point;


/**
 *  Hold information about an edge
 *
 *  @author Melanie Bancilhon
 *  @version CSC 112, April 22 2016
 */
public class DisplayEdge {
	//color, position, label etc
	public Point sPoint;
	public Point ePoint;
	public int weight;
		
	public DisplayEdge(Point sPoint, Point ePoint, int weight){
		this.sPoint= sPoint;
		this.ePoint= ePoint;
		this.weight= weight;
	}
}
