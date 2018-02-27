import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *  Program keeps track of sorting activities and then displays them.
 *  Students do not need to read or edit this file.
 *  CardSortDemo.java demonstrates how to use it.
 */
public class SortRecorder extends JComponent implements ChangeListener {
	
	/** For serializing the class */
	private static final long serialVersionUID = 1L;
	
    /** Keeps track of moves */
    private LinkedList<LinkedList<Card[]>> record;

    /** Keeps track of where new piles go */
    private int offset;

    /** Keeps track of how big window needs to be */
    private int maxoffset;

    /** Keeps track of current state to display */
    private int index;

    /** Constructor */
    public SortRecorder() {
        this.record = new LinkedList<LinkedList<Card[]>>();
        this.record.add(new LinkedList<Card[]>());
        this.offset = 6;
        this.index = 0;
    }

    /** Adds a pile to the current record */
    public void add(Card[] pile) {
        LinkedList<Card[]> piles = this.record.removeLast();
        Card[] cpile = new Card[pile.length]; // make a copy
        for (int i=0; i < pile.length; i++) {
        		cpile[i] = pile[i];
        }
        piles.add(cpile);
        this.record.add(piles);
        this.maxoffset = 100+12*pile.length;
        if (this.offset > this.maxoffset) {
            this.maxoffset = this.offset;
        }
    }

    /** Moves on to the next record */
    public void next() {
        this.record.add(new LinkedList<Card[]>());
        this.offset = 6;
    }

    /** Creates a window to put the display into */
    public void display(String title) {
        if (this.record.getLast().size()==0) {
            // remove empty last record
            this.record.remove();
        }
        if (this.record.size() == 0) {
            // need at least one empty record to display
            this.record.add(new LinkedList<Card[]>());
        }

        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        JFrame frame = new JFrame(title);
        try { frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (Exception e) {}

        // Add components
        JSlider slider = new JSlider(JSlider.HORIZONTAL,1,this.record.size(),1);
        slider.setMajorTickSpacing(this.record.size());
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        //slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.addChangeListener(this);
        frame.getContentPane().add(this);
        frame.getContentPane().add(slider,BorderLayout.SOUTH);

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        //if (!source.getValueIsAdjusting()) {
        index = (int)source.getValue()-1;
        repaint();
        //}
    }
    
    /**
     *  Draws the pile at its location on the table.
     *
     *  @param g  Graphics object to draw into
     */
    public void draw(Card[] pile, Graphics g) {
    		int cx = this.offset; //this.x;
    		int cy = 0; // this.y;
    		for (Card card: pile) {
    			if (card.getIsFaceUp()) {
    				g.drawImage(card.getFrontSide(),cx,cy,72,96,null);
            } else {
            	g.drawImage(Card.getBackSide(),cx,cy,72,96,null);
            }
    			cx += 12;
    			//cy += this.offsetY;
    		}
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.GREEN.darker());
        g.fillRect(0,0,this.maxoffset,100);
        LinkedList<Card[]> piles = this.record.get(this.index);
        for (Card[] pile: piles) {
            draw(pile, g);
        }
    }

    public Dimension getMinimumSize() {
        return new Dimension(Math.min(1000,this.maxoffset),100);
    }

    public Dimension getPreferredSize() {
        return new Dimension(Math.min(1000,this.maxoffset),100);
    }
}