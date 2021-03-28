// Name: Yang Jiang
// USC NetID: yjiang24
// CS 455 PA3
// Spring 2021


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield). Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to actions the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // The following are the covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // The following are the uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   private MineField mineField;
   private int numGuess; // the number of guess
   private int [][] mineFieldStatus; // 2D array, used to track the status of the square
   private boolean isWin; // if isWin == true, win the game
   private boolean isLose; // if isLose == true, lose the game. Both of them false mean game is not over
   public static final int MIN_ADJACENT_MINE = 0; // min number of adjacent mines, which is 0
   public static final int[] DIRECTION_ROW = {0, 1, -1, 0, -1, 1, -1, 1}; // 8 directions of row for recursion function
   public static final int[] DIRECTION_COL = {1, 0, 0, -1, -1, 1, 1, -1}; // 8 directions of col for recursion function 
   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      this.mineField = mineField;
      numGuess = 0; // the number of guess = 0 in the beginning
      mineFieldStatus = new int[mineField.numRows()][mineField.numCols()];
      
      for (int i = 0; i < mineField.numRows(); i++) {
         for (int j = 0; j < mineField.numCols(); j++) {
            mineFieldStatus[i][j] = COVERED; // all sqaures are covered in the beginning
         }
      }
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying
      MineField. 
   */     
   public void resetGameDisplay() {
      // mineField.resetEmpty(); // reset mineFiled
      numGuess = 0; // reset number guess = 0
      isWin = false; // set it to be false
      isLose = false; // set it to be false. Both false mean game is not over
      
      for (int i = 0; i < mineField.numRows(); i++) {
         for(int j = 0; j < mineField.numCols(); j++) {
            mineFieldStatus[i][j] = COVERED; // reset mineFiledStatus to be covered
         }
      }
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return mineField;
   }
   
   
   /**
      Returns the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      assert getMineField().inRange(row, col); // ensure the input row and col is valid
      return mineFieldStatus[row][col]; // return the status of the square (row, col)
   }

   
   /**
      Returns the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  This value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      return getMineField().numMines() - numGuess; // return the number of mines left

   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      assert getMineField().inRange(row, col); // ensure the input row and col is valid

      if (mineFieldStatus[row][col] == COVERED) { // if the status is COVERED
         mineFieldStatus[row][col] = MINE_GUESS; // update it to be MINE_GUESS
         numGuess += 1; // increase the number of guess by 1
      }
      else if (mineFieldStatus[row][col] == MINE_GUESS) { // if the status is MINE_GUESS
         mineFieldStatus[row][col] = QUESTION; // update it to be QUESTION
         numGuess -= 1; // decrease the number of gusess by 1
         if (numGuess < 0) { // if the number of guess < 0
            numGuess = 0; // set the number of guess = 0
         }
      }
      else if (mineFieldStatus[row][col] == QUESTION) { // if the status is QUESTION
         mineFieldStatus[row][col] = COVERED; // update it to be COVERED
      }
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      Note: this action may cause the game to end: either in a win (opened all the non-mine squares)
      or a loss (opened a mine).
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      assert getMineField().inRange(row, col); // ensure the input row and col is valid

      if (mineField.hasMine(row, col)) { // if the square has a mine
         mineFieldStatus[row][col] = EXPLODED_MINE; // update the square status to be EXPLODED_MINE
         return false;
      }
      mineFieldStatusUpdate(row, col); // if the square does not have a mine, call mineFieldStatusUpdate
      return true;
   }
 
   
   /**
      Returns whether the game is over.
      (Note: This is not a mutator.)
      @return whether game over
    */
   public boolean isGameOver() {
      int uncoveredCount = 0; // uncoveredCount is the number of unconvered sqaures, it is 0 in the beginning
      int noMineCount = mineField.numRows() * mineField.numCols() - mineField.numMines(); // noMineCount is the number of squares that do not have a mine
      
      for (int i = 0; i < mineField.numRows(); i++) {
         for (int j = 0; j < mineField.numCols(); j++) {
            if (mineFieldStatus[i][j] == EXPLODED_MINE) { // if the status of the sqaure is EXPLODED_MINE, lose the game
               isLose = true;
               break; // alreasy lose the game, so break the for loop
            }
            else if (isUncovered(i, j)) { // if the sqaure has be uncovered
               uncoveredCount += 1; // increase the number of uncoveredCount by 1
               if (uncoveredCount == noMineCount) { // if uncoveredCount == noMineCount, which means uncovered all the squares that do not have mines, win the game
                  isWin = true;
                  break; // already win the game, so break the for loop
               }
            }
         } 
      }
      updateFinalStatus(); // no matter we win the game or lose the game, need to update the status the sqaures finally, call updateFinalStatus to update
      return isWin || isLose; // if win the game or lose the game, the game is over. return true if game is over, otherwise, false.
   }
 
   
   /**
      Returns whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      assert getMineField().inRange(row, col); // ensure the input row and col is valid
      // if the sqaure status it not COVERED and not MINE_GUESS and not QUESTION, which means it is uncovered already
      if (mineFieldStatus[row][col] != COVERED && mineFieldStatus[row][col] != MINE_GUESS && mineFieldStatus[row][col] != QUESTION) {
         return true; // so return true, means it is uncovered
      }
      
      return false; // otherwise, it is not uncovered, resturn false
   }
   
 
   // <put private methods here>

   /**
      mineFieldStatusUpdate method
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
   */
   private void mineFieldStatusUpdate(int row, int col) {
      if (mineField.inRange(row, col) == false) { // if the square is out of range, do not uncover, or keep searching, so return here
         return; // stop searching
      }
      if (mineFieldStatus[row][col] == MINE_GUESS) { // if the sqaure status is MINE_GUESS, do not uncover, or keep searching, so return here
         return; // stop searching
      }
      if (isUncovered(row, col)) { // if the sqaure has been uncovered, do not keep searching, so return here
         return; // stop searching
      }
      if (mineField.numAdjacentMines(row, col) != MIN_ADJACENT_MINE) { // if there exists mines adjacent to the sqaure (row, col)
         mineFieldStatus[row][col] = mineField.numAdjacentMines(row, col); // update the square status = the number of mines adjacent to it
         return; // stop searching
      }
      if (mineField.numAdjacentMines(row, col) == MIN_ADJACENT_MINE) { // if there is NO mines adjacent to the sqaure (row, col)
         mineFieldStatus[row][col] = MIN_ADJACENT_MINE; // update the square status = 0
         for (int direction = 0; direction < DIRECTION_ROW.length; direction++) { // do recursion, keep searching its 8-direction neighbors
            int neighborRow = row + DIRECTION_ROW[direction]; // neighbor's row index
            int neighborCol = col + DIRECTION_COL[direction]; // neighbor's col index
            mineFieldStatusUpdate(neighborRow, neighborCol); // search its neighbor
         }
      }
   }
   
   /**
      updateFinalStatus method
      this method updates the status of the sqaures no matther the player win the game or lose the game finally
      1. Winning game display. When a player successfully opens all of the non-mine locations, the field display       changes to show where the other mines are (it shows them as guesses, in yellow). I.e., these would be any         unopened squares that weren't already yellow.
      2. Losing game display. When a player opens a mine location, that mine explodes, and they lose the game.         The exploded mine is shown by a red square. Any previously made incorrect guesses are shown with an X             though them, the correctly guessed mine locations are still shown as guesses (yellow), and the other             unopened mines are shown as "mines" (a black square, in our implementation). 
   */
   private void updateFinalStatus() {
      if (isWin) { // if win the game
         for (int i = 0; i < mineField.numRows(); i++) {
            for (int j = 0; j < mineField.numCols(); j++) {
               if (mineField.hasMine(i, j) && mineFieldStatus[i][j] != MINE_GUESS) { // if the square has a mine, but we did not mark it as MINE_GUESS (yellow)
                  mineFieldStatus[i][j] = MINE_GUESS; // mark the sqaures as MINE_GUESS (yellow)
               }
            }
         }
      }
      
      if (isLose) { // if lose the game
         for (int i = 0; i < mineField.numRows(); i++) {
            for (int j = 0; j < mineField.numCols(); j++) {
               if (!mineField.hasMine(i, j) && mineFieldStatus[i][j] == MINE_GUESS) { // if the square doesn't have a mine, but we mark it as MINE_GUESS (yellow)
                  mineFieldStatus[i][j] = INCORRECT_GUESS; // change the status of the square to be INCORRECT_GUESS
               }
               if (mineField.hasMine(i, j) && mineFieldStatus[i][j] == EXPLODED_MINE) { // if the square has a mine and the status is EXPLODED_MINE, do not need to do anything, so continue
                  continue;
               }
               if (mineField.hasMine(i, j) && mineFieldStatus[i][j] != MINE_GUESS) { // if the square has a mine, but we did not mark is as MINE_GUESS (yellow)
                  mineFieldStatus[i][j] = MINE; // change the status of the sqaure to be MINE
               }
            }
         }
      }
   }
   
}
