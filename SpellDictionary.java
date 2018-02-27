import java.io.FileReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
/**
 * Has one method that creates a dictionary
 * @author Artemis Metaxa and Melanie Bancilhon
 *
 */
public class SpellDictionary {
	/**
	 * 
	 * From a file of dictionary words, creates a HashSet of dictionary words 
	 * and returns it
	 */
	public static HashSet<String> makeDictionary(){
		try{
		String fileName= "/usr/share/dict/words";
		Scanner input= new Scanner(new FileReader(fileName));
		HashSet<String> dictionary = new HashSet<String>();
			//while more words to add
			while(input.hasNextLine()){
				String word = input.nextLine();
				dictionary.add(word);
			}
			return dictionary;
		}
		
		catch (Exception e){ 
			System.out.println("here");
			return null;
			
		}
		

	}
	/**
	 * Takes in a word (that has been previously not found in dictionary) and 
	 * returns a linked list of all possible words due to misspelling
	 */
	public static LinkedList<String> checkSpelling(String word){
		String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
				"k", "l","m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", 
				"x", "y", "z"};
		
		HashSet<String> dictionary = SpellDictionary.makeDictionary();
		LinkedList<String> possibleWords = new LinkedList<String>();
		//deletions
		for(int i = 0; i<( word).length();i++){
			String deleteWord = word.substring(0, i) + word.substring(i+1, word.length());
			//see if newWord is in dictionary, if so add it
			if(dictionary.contains(deleteWord)&& !possibleWords.contains(deleteWord)){
				possibleWords.add(deleteWord);
			}			
			
			
		}	
		
		//insertions
		for(int j = 0; j < word.length()+1; j++){
			for(String letter : alphabet){
				String insertWord = word.substring(0,j) + letter + word.substring(j, word.length());
				//see if newWord is in dictionary, if so add it
				if(dictionary.contains(insertWord)&& !possibleWords.contains(insertWord)){
					possibleWords.add(insertWord);
				}
			
			}
		}
		for(int j = 0; j < word.length()-1; j++){
			//substitution
				for(String letter : alphabet){					
					String substitutedWord= word.substring(0, j) +letter+ word.substring(j+2, word.length());
					//see if newWord is in dictionary, if so add it
					if(dictionary.contains(substitutedWord) && !possibleWords.contains(substitutedWord)){												
						possibleWords.add(substitutedWord);						
					}
			    }
				//Transpositions
				String transposedWord= word.substring(0, j) +word.substring(j+1,j+2)+word.substring(j,j+1)
			+ word.substring(j+2, word.length());
				//see if newWord is in dictionary, if so add it
				if(dictionary.contains(transposedWord)&& !possibleWords.contains(transposedWord)){
					possibleWords.add(transposedWord);
				}

		}
		//Splits
		for(int j = 1; j < word.length(); j++){
			String splitWord= word.substring(0, j)+ " "+ word.substring(j, word.length());
			//see if newWord is in dictionary, if so add it
			String[] split= splitWord.split(" ");
			if(dictionary.contains(split[0])  && (dictionary.contains(split[1])) && !possibleWords.contains(splitWord)){
				possibleWords.add(splitWord);
			}
		}
		return possibleWords;
	}
	
}


		


