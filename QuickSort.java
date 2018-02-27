import java.util.Arrays;
import java.util.Collections;


public class QuickSort {
	int low;
	int high;
	
	/** Keeps track of moves when sorting */
    public static SortRecorder record = new SortRecorder();
	
	public QuickSort(){
		this.low=0;
		this.high=0;
		
	}
	// TODO 1: quicksort
    public static void quicksort (Card[] deck, int low, int high){

		    if ((low!= high) &&(high>low) &&(high>0)){
		    	int i= partition(deck,low,high);
		    	quicksort(deck, low, i-1);
		    	quicksort(deck, i+1, high);				
    	    }   	
    }
    
    // TODO 2: partition
    //returns index i of the sorted element
    public static int partition (Card[] deck, int low, int high){

    	//take pivot as being low pointer
  	   Card pivot= deck[low];
    	while (low!=high){
	      	//if pivot is smaller than high
    		//already well placed
	      	//decrement high
	    	if (pivot.compareTo(deck[high]) < 0 && pivot.compareTo(deck[high]) != 0 ){
	    		  high=high-1;
	    	}
	    	else if (pivot.compareTo(deck[low]) < 0 && pivot.compareTo(deck[low]) != 0 ){
	    		  swap(deck,low,high);
	    		  high=high-1;
	    	}
	    	//if low is smaller than pivot
	    	//already well placed
	    	//increment low pointer
	    	else if (pivot.compareTo(deck[low])>0){
	    		low=low+1;
	    	}
	    	//if pivot is greater than high
	    	//swap high and low
	    	else if (pivot.compareTo(deck[high]) > 0 ){
	    		  swap(deck,low,high);
	    		  low=low+1;
  
	    	}	    	
    	}
    	return low;
    }

    // TODO 3: swap
    public static void swap (Card[] deck, int low, int high){
    	Card temporary=deck[low];
    	deck[low]=deck[high];
    	deck[high]=temporary;
    	
    	//add record
    	record.next();
    	record.add(deck);   	
    }

	public static void main(String[] args) {
		Card.loadImages(record);
		
		// work with the entire deck eventually, but you can start with
		// a smaller number of cards
		//Card[] deck = Card.smallDeck(10);
		Card[] deck = Card.newDeck();
		for (Card c: deck) {
            c.flipCard();
        }
		
		// mix up the cards
        Collections.shuffle(Arrays.asList(deck));
        record.add(deck);
        
        // TODO call quicksort here
        quicksort(deck,0, deck.length-1);
		
		// make window appear showing the record
        record.display("Card Quicksort");
	}
}
