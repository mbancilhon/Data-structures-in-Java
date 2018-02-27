/**
 ** For timing the sorting of a number of cards to be specified 
 ** in the command line
 ** 
 **/
public class TimerDemo {
    /** Starts the program running */
    public static void main(String args[]) {
    	System.out.println(args[0]);
        if (args.length<1) {
            System.err.println("Please specify how many cards to sort!");
        } else {
            Card[] deck = Card.newDeck();
            int cardNum= Integer.parseInt(args[0]);
            Card[] cards = new Card[cardNum] ;
            for (int i = 0; i<Integer.parseInt(args[0]); i++ ) {
                Card c = deck[(int)(52*Math.random())];
                c.flipCard();
                cards[i]= c;
            }
        QuickSort.quicksort(cards, 0,cards.length-1 );

        }

    }
}
