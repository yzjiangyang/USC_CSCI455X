// Name: Yang Jiang
// USC NetID: yjiang24
// CS 455 PA4
// Spring 2021

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
/**
   A Rack of Scrabble tiles
 */

public class Rack {
   /**
      Method getAllSubsets get all subsets of the input word
      @param word string to process
   */
   public static ArrayList<String> getAllSubsets(String word) {
      int uniqueLength = getCharCountMap(word).keySet().size(); // get the number of unique characters in the word, called uniqueLength
      String unique = ""; // a string of unique letters
      int[] mult = new int[uniqueLength]; // mult is an array that is used to store the multiplicity of each letter from unique
      int index = 0; // the index of the array mult
      
      for (Map.Entry<Character, Integer> entry : getCharCountMap(word).entrySet()) {
         unique += entry.getKey(); // add the key to unqiue
         mult[index] = entry.getValue(); // update the frequency of the key at location index
         index += 1; // update the index by adding 1
      }

      return allSubsets(unique, mult, 0);
   }

   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   /**
      Method getCharCountMap gives us a HashMap, which has each character of the input word as key, and its corresponding frequency as value.
      @param word string to process
      @return a hashmap which has each character of the input word as key, and its corresponding frequency as value.
   */
   private static Map<Character, Integer> getCharCountMap(String word) {
      char tempArray[] = word.toCharArray(); // convert input word to a char array, called tempArray
      Map<Character, Integer> charToCount = new HashMap<Character, Integer>(); // charToCount is used to store the character and its corresponding frequency
      
      for (char character : tempArray) { // for each character in tempArray
         Integer charCount = charToCount.get(character); // get the frequency of the character, called charCount
         int updateCharCount = 1;
         if (charCount == null) { // if the character is not in charToCount
            charToCount.put(character, updateCharCount); // put the character in charToCount, and its frequency is 1
         }
         else {
            charToCount.put(character, charCount + 1); // if the character is in charToCount, update its frequency by adding 1
         }
      }

      return charToCount; // return the hashmap charToCount
   }


}
