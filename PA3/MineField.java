// Name: Yang Jiang
// USC NetID: yjiang24
// CS 455 PA3
// Spring 2021

/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
import java.util.Random;
public class MineField {
   // <put instance variables here>
   public static final boolean HAS_MINE = true; // If the location has a mine, set it to be true
   private boolean[][] mineData; // 2D array, it will store mine data
   private int numRows; // number of rows this minefield will have
   private int numCols; // number of cols this minefield will have
   private int numMines; // number of mines it has
   private Random generator; // generator will be used to randomly simulate a number
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
      @param mineData  the data for the mines; must have at least one row and one col,
                       and must be rectangular (i.e., every row is the same length)
    */
   public MineField(boolean[][] mineData) {
      generator = new Random();
      numRows = mineData.length; // get mineData length
      numCols = mineData[0].length; // get mineData width
      this.mineData = new boolean[numRows][numCols]; // create mineData
      
      for (int i = 0; i < numRows; i++) {
         for (int j = 0; j < numCols; j++) {
            this.mineData[i][j] = mineData[i][j];
            if (mineData[i][j] == HAS_MINE) { // if the locaion has a mine
               numMines += 1; // increase the number of mines by 1
            }
         }
      }
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      generator = new Random();
      this.numRows = numRows; // assign numRows
      this.numCols = numCols; // assign numCols
      this.numMines = numMines; // assign numMines
      mineData = new boolean[numRows][numCols]; // create mineData
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col) and numMines() < (1/3 * numRows() * numCols())
    */
   public void populateMineField(int row, int col) {
      assert inRange(row, col); // ensure the input row and col is valid
      resetEmpty(); // removes any current mines on the minefield
      int currMines = 0; // currMines is the number of current mines, which is 0 in the beginning

      while (currMines < numMines) {
         int randomRow = generator.nextInt(numRows); // generate a random row number, called randomRow
         int randomCol = generator.nextInt(numCols); // generate a random column number, called randomCol
         if (randomRow == row && randomCol == col) { // no mine is placed at (row, col), so continue
            continue;
         }
         mineData[randomRow][randomCol] = HAS_MINE; // update the square to the status of having a mine
         currMines += 1; // increase the number of current mines by 1
      }
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state a minefield created with the three-arg constructor is in 
         at the beginning of a game.
    */
   public void resetEmpty() {
      mineData = new boolean[numRows][numCols]; // create a new mineData with default value false
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      assert inRange(row, col); // ensure the input row and col is valid
      int numAdjaMine = 0; // numAdjaMine is the numbers of of mines adjacent to the specified mine location
      
      for (int i = row - 1; i <= row + 1; i++) { // start from row - 1 to row + 1
         for (int j = col - 1; j <= col + 1; j++) { // start from column - 1 to column + 1
            if (i == row && j == col || inRange(i, j) == false) { // if get to the square (row, col) or out of range, don't count 
               continue;
            }
            if (hasMine(i, j)) { // if square (i, j) has a mines
               numAdjaMine += 1; // increase the numbers of the mines adjacent to the specified mine location by 1
            }
         }
      }

      return numAdjaMine; // return the numbers of the mines adjacent to the specified mine square
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
      if (row < 0 || row >= numRows || col < 0 || col >= numCols) { // row has to be in [0, numRows - 1], and col has to be in [0, numCols -1]
         return false;
      }
      
      return true;
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return numRows; // return the number of rows in the field
   }
   
   
   /**
      Returns the number of columns in the field.
      @return number of columns in the field
   */    
   public int numCols() {
      return numCols; // return the number of columns in the filed
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      assert inRange(row, col); // ensure the input row and col is valid
      return mineData[row][col]; // return whether there is a mine in mineData
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
      return numMines; // return the number of mines;
   }

   
   // <put private methods here>
   
         
}