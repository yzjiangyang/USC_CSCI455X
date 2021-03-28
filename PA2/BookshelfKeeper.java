// Name: Yang Jiang
// USC NetID: yjiang24
// CSCI455 PA2
// Spring 2021


/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
import java.util.ArrayList;
public class BookshelfKeeper {

    /**
      Representation invariant:

      <put rep. invar. comment here>
      The heights of books on a bookshelf are in non-descreasing order

   */

   // <add instance variables here>
   private Bookshelf bookshelf;
   private int currCalls;
   private int totalCalls;
   private static final int EMPTY = 0;
   /**
   @param bookshelf  BookShelf class
   @param currCalls  The number of calls for current operation
   @param totalCalls  Accumulative number of calls.
   @param EMPTY  Size of empty bookshelf.
   */
   /**
    * Creates a BookShelfKeeper object with an empty bookshelf
    */
   public BookshelfKeeper() {
      bookshelf = new Bookshelf(); //empty bookshelf
      currCalls = 0; // intialize currCalls = 0
      totalCalls = 0; // initialize totalCalls = 0
   }

   /**
    * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
    * Note: method does not make a defensive copy of the bookshelf.
    *
    * PRE: sortedBookshelf.isSorted() is true.
    */
   public BookshelfKeeper(Bookshelf sortedBookshelf) {
      bookshelf = sortedBookshelf; //non-empty bookshelf
      currCalls = 0; // intialize currCalls = 0
      totalCalls = 0; // initialize totalCalls = 0
   }

   /**
    * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
    * after picking up the book.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: position must be in the range [0, getNumBooks()).
    */
   public int pickPos(int position) {
      currCalls = 0; // reset current number of calls = 0
      int bookshelfSize = bookshelf.size(); // get the size of the bookshelf
      int mid = bookshelfSize / 2; // the mid position of the bookshelf
      Bookshelf bufferRemovedBooks = new Bookshelf(); // create a temporary Bookshelf called bufferRemovedBooks to save removed books

      if (bookshelfSize == EMPTY) { // empty bookshelf
         assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
         return 0;
      }

      if (position < mid) { // position < mid means remove from front will have min calls.
         return pickPosSTmid(bufferRemovedBooks, position); // pickPosSTmid means pick position smaller than mid position in bookshelf, it returns the min calls.
      }

      else { // position >= mid means remove from back will have min calls.
         return pickPosGTEmid(bufferRemovedBooks, position, bookshelfSize); //pickPosGTEmid meand pick position greater than equal to mid position of the bookshelf, it returns the min calls.
      }

   }

   /**
    * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
    * after the insertion.
    *
    * Returns the number of calls to mutators on the contained bookshelf used to complete this
    * operation. This must be the minimum number to complete the operation.
    *
    * PRE: height > 0
    */
   public int putHeight(int height) {
      currCalls = 0; // reset current number of calls = 0
      int bookshelfSize = bookshelf.size(); // get the size of the bookshelf
      int mid = bookshelfSize / 2; // the mid position of the bookshef
      Bookshelf bufferRemovedBooks = new Bookshelf(); // create a temporary Bookshelf called bufferRemovedBooks to save removed books
      int insertLoc = 0; // insert postition

      if (bookshelfSize == EMPTY) { // If bookshef is empty
         bookshelf.addLast(height);
         totalCalls += 1; // update total insert calls
         currCalls += 1; // update current insert calls
         assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
         return currCalls; // min number of calls
      }

      // If the input height already exists in bookshelf
      if (hasHeight(bookshelf, height) == true) { // input height already exits in bookshelf
         insertLoc = insertPosNotDistinctHeight(bookshelfSize, insertLoc, height); //gets the optimal insert position
      }

      // If the input height does not exist in bookshelf
      else {
         insertLoc = insertPosDistinctHeight(bookshelfSize, insertLoc, height); //gets the optimal insert position
      }

      // get the min calls
      if (insertLoc <= mid) { // insert position <= mid means removing from front will have min calls.
         return putPosSTEmid(bufferRemovedBooks, insertLoc, height); //putPosSTEmid means insert position smaller than equal to the mid position in the bookshelf, it returns the min calls.
      }

      else { // insert position > mid means remove from front will have min calls.
         return putPosGTmid(bufferRemovedBooks, insertLoc, height, bookshelfSize); //putPosGTmid means insert position is greater than mid position in the bookshelf, it returns the min calls.
      }
   }

   /**
    * Returns the total number of calls made to mutators on the contained bookshelf
    * so far, i.e., all the ones done to perform all of the pick and put operations
    * that have been requested up to now.
    */
   public int getTotalOperations() {
      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return totalCalls;
   }

   /**
    * Returns the number of books on the contained bookshelf.
    */
   public int getNumBooks() {
      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return bookshelf.size();
   }

   /**
    * Returns string representation of this BookshelfKeeper. Returns a String containing height
    * of all books present in the bookshelf in the order they are on the bookshelf, followed
    * by the number of bookshelf mutator calls made to perform the last pick or put operation,
    * followed by the total number of such calls made since we created this BookshelfKeeper.
    *
    * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
    *
    */
   public String toString() {
      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return bookshelf.toString() + " " + currCalls + " " + getTotalOperations();
   }

   /**
    * Returns true iff the BookshelfKeeper data is in a valid state.
    * (See representation invariant comment for details.)
    */
   private boolean isValidBookshelfKeeper() {
      return bookshelf.isSorted();
   }


   // add any other private methods here
   // This method gets the min calls of pick if pick position is smaller than the mid position in bookshelf
   private int pickPosSTmid(Bookshelf bufferRemovedBooks, int position) { // pickPosSTmid means pick position smaller than mid position in bookshelf
      for (int i = 0; i < position; i++){
         bufferRemovedBooks.addLast(bookshelf.removeFront()); // save removed book to bufferRemovedBooks
      }
      bookshelf.removeFront(); // Pick out the the book at posistion
      for (int i = 0; i < position; i++) {
         bookshelf.addFront(bufferRemovedBooks.removeLast()); // get removed books back to the bookshelf
      }
      totalCalls += 2 * position + 1; // update total pick calls
      currCalls += 2 * position + 1;  // update current pick calls

      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return currCalls; // min number of calls
   }


   // This method gets the min calls of pick if pick position is greater than equal to the mid position in bookshelf
   private int pickPosGTEmid(Bookshelf bufferRemovedBooks, int position, int bookshelfSize) {//pickPosGTEmid meand pick position greater than equal to mid position of the bookshelf
      for (int i = bookshelfSize - 1; i > position; i--) {
         bufferRemovedBooks.addLast(bookshelf.removeLast()); // save removed book to bufferRemovedBooks
      }
      bookshelf.removeLast(); // Pick out the the book at posistion
      for (int i = bookshelfSize - 1; i > position; i--) {
         bookshelf.addLast(bufferRemovedBooks.removeLast()); // get removed books back to the bookshelf
      }
      totalCalls += 2 * (bookshelfSize - 1 - position) + 1; // update total pick calls
      currCalls += 2 * (bookshelfSize - 1 - position) + 1; // update current pick calls

      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return currCalls;  // min number of calls

   }

   // This method checks if the input height alreay exists in bookshelf.
   private boolean hasHeight(Bookshelf bookshelf, int height) {
      int bookshelfSize = bookshelf.size(); // get the size of the bookshelf
      for (int i = 0; i < bookshelfSize; i++) {
         if (bookshelf.getHeight(i) == height) { // if there is one value == height, then height exists in bookshelf, return true
            assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
            return true;
           }
       }

      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return false;
   }


   // This method gets the optimal insert position if the input height alreay exists in bookshelf.
   private int insertPosNotDistinctHeight(int bookshelfSize, int insertLoc, int height) {
      int firstLoc = 0; // The fisrt postision that height occurs in bookshelf.
      for (int i = 0; i < bookshelfSize; i++) {
         if (bookshelf.getHeight(i) == height) { // find the first postion that height occurs.
            firstLoc = i;
            break; // already get the first position that height occurs, do not need to excute the rest of the for loop, so break the for loop
         }
      }

      int lastLoc = 0; // The last postision that height occurs in bookshelf.
      for (int i = bookshelfSize - 1; i >= 0; i--) {
         if (bookshelf.getHeight(i) == height) { // find the last postion that height occurs.
            lastLoc = i;
            break; // already get the last position that height occurs, do not need to excute the rest of the for loop, so break the for loop
         }
      }
      // if the numbers of elements that are before firstLoc > the numbers of elements that are after lastLoc
      // inserting at lastLoc + 1 will have min calls. Otherwise, insert at firstLoc will have min calls.
      if (firstLoc > bookshelfSize - 1 - lastLoc) {
         insertLoc = lastLoc + 1;
      }
      else {
         insertLoc = firstLoc;
      }

      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return insertLoc;
   }


   // This method gets the optimal insert position if the input height does not exist in bookshelf.
   private int insertPosDistinctHeight(int bookshelfSize, int insertLoc, int height) {
      if (bookshelf.getHeight(bookshelfSize - 1) < height) { // if the height of the last book on the bookshelf < input height, insert postion = bookshelfSize
         insertLoc = bookshelfSize;
      }
      else {
         for (int i = 0; i < bookshelfSize; i++) {
            if (bookshelf.getHeight(i) > height) {
               insertLoc = i; // find the first postision whose height > input height
               break; // get the location to insert, No need to excute the rest of the for loop, break for loop
            }
         }
      }

      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return insertLoc;
   }


   // This method gets the min calls of putHeight if put position is smaller than equal to the mid position in bookshelf
   private int putPosSTEmid(Bookshelf bufferRemovedBooks, int insertLoc, int height) {//putPosSTEmid means insert position smaller than equal to the mid position in the bookshelf
      for (int i = 0; i < insertLoc; i++) {
         bufferRemovedBooks.addLast(bookshelf.removeFront()); // save removed book to bufferRemovedBooks
      }
      bookshelf.addFront(height); // insert the input height
      for (int i = 0; i < insertLoc; i++) {
         bookshelf.addFront(bufferRemovedBooks.removeLast()); // get removed books back to the bookshelf
      }
      totalCalls += 2 * insertLoc + 1; // update total insert calls
      currCalls += 2 * insertLoc + 1; // update current insert calls

      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return currCalls; // min number of calls
   }


   // This method gets the min calls of putHeight if put position is greater than the mid position in bookshelf
   private int putPosGTmid(Bookshelf bufferRemovedBooks, int insertLoc, int height, int bookshelfSize) { //putPosGTmid means insert position is greater than mid position in the bookshelf
      for (int i = bookshelfSize - 1; i >= insertLoc; i--) {
         bufferRemovedBooks.addLast(bookshelf.removeLast()); // save removed book to bufferRemovedBooks
      }
      bookshelf.addLast(height);
      for (int i = bookshelfSize - 1; i >= insertLoc; i--) {
         bookshelf.addLast(bufferRemovedBooks.removeLast()); // get removed books back to the bookshelf
      }
      totalCalls += 2 * (bookshelfSize - insertLoc) + 1; // update total insert calls
      currCalls += 2 * (bookshelfSize - insertLoc) + 1; // update current insert calls

      assert isValidBookshelfKeeper(); // make sure bookshelf is sorted
      return currCalls; // min number of calls
   }

   //////////////////////////
   public static void main(String[] args) {
       ArrayList<Integer> book = new ArrayList<Integer>();
       book.add(1);
       book.add(2);
       book.add(2);
       book.add(4);
       book.add(9);
       Bookshelf shelf = new Bookshelf(book);
       BookshelfKeeper test = new BookshelfKeeper(shelf);
       System.out.println(test.putHeight(2));
       // System.out.println(test.pickPos(2));
       // System.out.println(test.pickPos(0));
       System.out.println(test.getTotalOperations());
       System.out.println(test.toString());
   }
}
