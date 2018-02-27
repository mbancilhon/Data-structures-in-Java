import java.awt.Color;
import java.awt.Point;


/**
 *  Hold information about a node
 *
 *  @author Melanie Bancilhon
 *  @version CSC 112, April 22 2016
 */
public class DisplayNode {
	//color, position, label etc
	public Color color;
	public int x;
	public int y;
	public String label;
		
	public DisplayNode(Color color, int x,int y, String label){
		this.color=color;
		this.x=x;
		this.y=y;
		this.label=label;		
	}

}
