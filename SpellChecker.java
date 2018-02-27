import java.io.FileReader;
import java.io.StringReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * 
 * @author Artemis Metaxa and Melanie Bancilhon
 *
 */
public class SpellChecker {
	public static void main (String[] args){
		//if configuration
		if(args.length == 1){
			try{
				//user input
				Scanner input= new Scanner(new StringReader(args[0]));
				//dictionary to check words against
				HashSet<String> dictionary = SpellDictionary.makeDictionary();
				//possible word changes
				LinkedList<LinkedList<String>> suggestions = new LinkedList<LinkedList<String>>();
				while (input.hasNext()){
					String word = input.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
					//check if word is in dictionary (correct)
					if(! dictionary.contains(word)){
						LinkedList<String> possibilities = SpellDictionary.checkSpelling(word);
						if(! suggestions.contains(possibilities)){
							suggestions.add(possibilities);
						}
					}
				}
				for(LinkedList<String> list: suggestions){
					for(String word: list){
						System.out.println(word);
					}
				}
			}
			catch(Exception e){System.out.println("caught");}
		}
		//else, run from input
		else{
			Scanner input = new Scanner(System.in);
			
			//dictionary to check words against
			HashSet<String> dictionary = SpellDictionary.makeDictionary();
			//possible word changes
			LinkedList<LinkedList<String>> suggestions = new LinkedList<LinkedList<String>>();
			while (input.hasNext()){
				String word = input.next().replaceAll("[^a-zA-Z ]", "").toLowerCase();
				System.out.println(word);
				if(word.equals("quit")){
					break;
				}
				//check if word is in dictionary (correct)
				if(! dictionary.contains(word)){
					LinkedList<String> possibilities = SpellDictionary.checkSpelling(word);

					if(! suggestions.contains(possibilities)){
						suggestions.add(possibilities);
						System.out.println("added");
					}
				}
			}
			for(LinkedList<String> list: suggestions){
				for(String word: list){
					System.out.println(word);
				}
			}
		}
	}
}



