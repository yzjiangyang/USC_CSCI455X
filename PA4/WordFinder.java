// Name: Yang Jiang
// USC NetID: yjiang24
// CS 455 PA4
// Spring 2021

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
	class WordFinder is the main class for scrabble program
*/
public class WordFinder {
	public static void main (String[] args) {
		String fileName = "./sowpods.txt"; // fileName is used to store the name of the dictionary file
      AnagramDictionary anagramDictionary = null; // initialize anagramDictionary here
		
		try {
			if (args.length > 0) { // If there is a command line argument for a specific dictionary
				fileName = args[0]; // take that as dictionary file
			}
			anagramDictionary = new AnagramDictionary(fileName); // create anagramDictionary
		}
		catch (FileNotFoundException e) { // If file is not found
			System.out.println("ERROR: Dictionary file \"" + fileName + "\" does not exist"); // print file not found message
			System.out.println("Exiting program."); // print program exit message
			return; // exit immediately
		}
		catch (IllegalDictionaryException e) { // If the dictionary file has duplicate words in it
			System.out.println(e.getMessage()); // print the error message
			System.out.println("Exiting program."); // print program exit message
			return; // exit immediately
		}

		System.out.println("Type . to quit."); // print message of "Type . to quit."
      Scanner in = new Scanner(System.in); // create Scanner in
      System.out.print("Rack? "); // print "Rack? "
      while (in.hasNext()) {
			String rack = in.next(); // get the input word as rack
			if (".".equals(rack)) { // if the input is "."
				return; // exit immediately
			}
			Map<String, Integer> anagramsScoreMap = getAnagramsScoreMap(anagramDictionary, rack); // create anagramsScoreMap
      	ArrayList<Map.Entry<String, Integer>> mapEntryArray = getDescendingAnagramsScoreMap(anagramsScoreMap); // create mapEntryArray
      	printSortedValue(mapEntryArray, rack); // print the all words with their scores in decreasing order
      	System.out.print("Rack? "); // ask for a new rack
		}
		in.close(); // close scanner
	}
	
	/**
		Method getAnagramsScoreMap gets the TreeMap which has all anagrams of all the resonable subsets of the word and their scores
		@param anagramDictionary the dictionary
		@param rack input word
		@return TreeMap which has all anagrams of all the resonable subsets of the rack and their scores
	*/	
	private static Map<String, Integer> getAnagramsScoreMap(AnagramDictionary anagramDictionary, String rack) {
		ArrayList<String> subsets = Rack.getAllSubsets(rack); // get all subsets of the input rack
		Map<String, Integer> anagramsScoreMap = new TreeMap<String, Integer>(); // TreeMap anagramsScoreMap is used to store anagrams and their scores
		ScoreTable scoreTable = new ScoreTable(); // create scoreTable
		
		if (subsets.size() == 0) { // if no subsets
			return anagramsScoreMap; // return empty anagramsScoreMap
		}

		for (int i = 0; i < subsets.size(); i++) {
			if (subsets.get(i).equals("")) { // if there is an empty string
				continue; // skip it
			}
			ArrayList<String> anagramsArray = anagramDictionary.getAnagramsOf(subsets.get(i)); // anagramsArray is the arraylist that has all the anagram of a subset
			if (anagramsArray.isEmpty()) { // if no anagram
				continue; // skip it
			}
			for (String anagram : anagramsArray) { // for each word in anagramsArray
				anagramsScoreMap.put(anagram, scoreTable.getScore(anagram)); // store the word and its score to anagramsScoreMap
			}
		}

		return anagramsScoreMap; // return the TreeMap anagramsScoreMap
	}

	/**
		getDescendingAnagramsScoreMap sorts the anagramsScoreMap in descending order by the scores of words
		@param anagramsScoreMap TreeMap which has all anagrams of all the resonable subsets of the rack and their scores
		@param return An ArrayList of entry sets of the anagramsScoreMap in descending order by the scores of words
	*/
	private static ArrayList<Map.Entry<String, Integer>> getDescendingAnagramsScoreMap(Map<String, Integer> anagramsScoreMap) {
		Comparator<Map.Entry<String, Integer>> comparator = new Comparator<Map.Entry<String, Integer>>() { // interface that helps to sort the anagramsScoreMap in descending order by the scores of words
			@Override
			public int compare(Map.Entry<String, Integer> obj1, Map.Entry<String, Integer> obj2) {
				return obj2.getValue() - obj1.getValue(); // since in descending order, obj2.getValue() - obj1.getValue(
			}
		};

		ArrayList<Map.Entry<String, Integer>> mapEntryArray = new ArrayList<Map.Entry<String, Integer>>(); // crete an arraylist mapEntryArray that stores the entry set of anagramsScoreMap
		for (Map.Entry<String, Integer> mapEntry : anagramsScoreMap.entrySet()) {
			mapEntryArray.add(mapEntry); // add the entry set to mapEntryArray
		}
		Collections.sort(mapEntryArray, comparator); // sort the mapEntryArray

		return mapEntryArray; // return the mapEntryArray
	}

	/**
		Method printSortedValue print the sorted results
		@param mapEntryArray An ArrayList of entry sets of the anagramsScoreMap in descending order by the scores of words
		@param rack input word
	*/
	private static void printSortedValue(ArrayList<Map.Entry<String, Integer>> mapEntryArray, String rack) {
		System.out.println("We can make " + mapEntryArray.size() + " words from \"" + rack + "\""); // print message
		
		if (mapEntryArray.size() > 0) { // if the arraylist has elements
			System.out.println("All of the words with their scores (sorted by score):"); // print message
			for (Map.Entry<String, Integer> mapEntry : mapEntryArray) {
				System.out.println(mapEntry.getValue() + ": " + mapEntry.getKey()); // print score and word
			}
		}
	}
}