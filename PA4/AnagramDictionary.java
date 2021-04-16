// Name: Yang Jiang
// USC NetID: yjiang24
// CS 455 PA4
// Spring 2021

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.Map;
import java.util.HashMap;

/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */
public class AnagramDictionary {
   private Map<String, ArrayList<String>> anagramDict; // anagramDict is used to store the word and its corresponding anagrams
   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
      anagramDict = new HashMap<String, ArrayList<String>> ();
      File file = new File(fileName); // read file in

      try (Scanner in = new Scanner(file)) { // use try here, so that Scanner in will be closed automatically
         while (in.hasNext()) {
            String word = in.next(); // get the next word
            String sortedWord = canonicalForm(word); // sort the word in ascending order
            if(!anagramDict.containsKey(sortedWord)) { // if the HashMap anagramDict does not have sortedWord
               ArrayList<String> anagramArray = new ArrayList<String>(); // create an ArrayList called anagramArray to store the anagram
               anagramArray.add(word); // add the word to anagramArray
               anagramDict.put(sortedWord, anagramArray); // put sortedWord and its corresponding anagramArray to HashMap anagramDict
            }
            else{
               if (anagramDict.get(sortedWord).contains(word)) { // if the word occurs more than 1 time
                  // Illegal dictionary
                  throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + word);
               }
               anagramDict.get(sortedWord).add(word); // if the HashMap anagramDict has sortedWord, and the word does not occur more than 1 time, then add the word to its corresponding anagramArray
            }
         }
      }
   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      String sortedWord = canonicalForm(s); // sort the word in ascending order
      if (anagramDict.containsKey(sortedWord)) { // if anagramDict has the sortedWord
         return anagramDict.get(sortedWord); // return its corresponding array of anagrams
      }

      return new ArrayList<String>(); // if anagramDict doesn't have the sortedWord, return empty ArrayList
   }
   /**
      Method canonicalForm gets a sorted version of the characters in the word
      @param word a string that will be sorted
      @return the sorted word
   */
   private String canonicalForm(String word) {
      char tempArray[] = word.toCharArray(); // convert input word to a char array, called tempArray
      Arrays.sort(tempArray); // sort the tempArray in ascending order

      return new String(tempArray); // return the sorted string
   }
}
