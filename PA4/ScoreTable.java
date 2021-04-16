// Name: Yang Jiang
// USC NetID: yjiang24
// CS 455 PA4
// Spring 2021

/**
	class ScoreTable gives the value of every word (no matter lowercase or uppercase)
*/
public class ScoreTable {
	/**
      Representation invariant:
      1. size of scoreArray = 26
      2. element in scoreArray is interger and greater than 0
   */

	/**
		@param ONE_POINT value of 1 point
		@param TWO_POINTS value of 2 points
		@param THREE_POINTS value of 3 points
		@param FOUR_POINTS value of 4 points
		@param FIVE_POINTS value of 5 points
		@param EIGHT_POINTS value of 8 points
		@param TEN_POINTS value of 10 points
		@param LETTER_NUMBER the number of letters, 26 total;
		@param ASCII_A ASCII code for 'A' = 65;
		@param scoreArray an array used to store the value for each letter;
	*/
	public static final int ONE_POINT = 1;
	public static final int TWO_POINTS = 2;
	public static final int THREE_POINTS = 3;
	public static final int FOUR_POINTS = 4;
	public static final int FIVE_POINTS = 5;
	public static final int EIGHT_POINTS = 8;
	public static final int TEN_POINTS = 10;
	public static final int LETTER_NUMBER = 26;
	public static final int ASCII_A = 65;
	private int[] scoreArray;

	/**
		store each letter's weight to the Array called scoreArray
	*/
	public ScoreTable() {
		scoreArray = new int[LETTER_NUMBER];
		for (int i = ASCII_A; i < LETTER_NUMBER + ASCII_A; i++) {
			if (i == 'A' || i == 'E' || i == 'I' || i == 'O' || i == 'U' || i == 'L' || i == 'N' || i == 'S' || i == 'T' || i == 'R') {
				scoreArray[i - ASCII_A] = ONE_POINT; // 1 point for A, E, I, O, U, L, N, S, T, R
			}
			if (i == 'D' || i == 'G') {
				scoreArray[i - ASCII_A] = TWO_POINTS; // 2 points for D, G
			}
			if (i == 'B' || i == 'C' || i == 'M' || i == 'P') {
				scoreArray[i - ASCII_A] = THREE_POINTS; // 3 points for B, C, M, P
			}
			if (i == 'F' || i == 'H' || i == 'V' || i == 'W' || i == 'Y') {
				scoreArray[i - ASCII_A] = FOUR_POINTS; // 4 points for F, H, V, W, Y
			}
			if (i == 'K') {
				scoreArray[i - ASCII_A] = FIVE_POINTS; // 5 points for K
			}
			if (i == 'J' || i == 'X') {
				scoreArray[i - ASCII_A] = EIGHT_POINTS; // 8 points for J, X
			}
			if (i == 'Q' || i == 'Z') {
				scoreArray[i - ASCII_A] = TEN_POINTS; // 10 points for Q, Z
			}
		} 
	}

	/**
		Method getScore gets the value of the input word (no matter it is lowecase or uppercase)
		@param word string to process
		@return the score of the word
	*/
	public int getScore(String word) {
		int score = 0; // the score of the input word
		String newWord = word.toUpperCase(); // convert the word to uppercase
		for (int i = 0; i < newWord.length(); i++) {
			score += scoreArray[newWord.charAt(i) - 'A']; // get the value of the word.
		}

		return score; // return the value
	}
}