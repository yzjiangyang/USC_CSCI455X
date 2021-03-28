// Name: Yang Jiang
// USC NetID: yjiang24
// CSCI455 PA2
// Spring 2021


/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
*/
import java.util.ArrayList;

public class Bookshelf {

    /**
      Representation invariant:

      <put rep. invar. comment here>
      Height of each book > 0;

   */

   // <add instance variables here>
   private ArrayList<Integer> pileOfBooks;


   /**
    * Creates an empty Bookshelf object i.e. with no books
    */
   public Bookshelf() {
      pileOfBooks = new ArrayList<Integer>();
      assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
   }

   /**
    * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
    * values: [20, 1, 9].
    *
    * PRE: pileOfBooks contains an array list of 0 or more positive numbers
    * representing the height of each book.
    */
   public Bookshelf(ArrayList<Integer> pileOfBooks) {
      this.pileOfBooks = new ArrayList<Integer>(pileOfBooks);
      assert isValidBookshelf(); // need to make sure the bookshelf is valid
   }

   /**
    * Inserts book with specified height at the start of the Bookshelf, i.e., it
    * will end up at position 0.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addFront(int height) {
      pileOfBooks.add(0, height); // add to the front, (index 0)
      assert isValidBookshelf(); // need to make sure the bookshelf is valid after adding
   }

   /**
    * Inserts book with specified height at the end of the Bookshelf.
    *
    * PRE: height > 0 (height of book is always positive)
    */
   public void addLast(int height) {
      pileOfBooks.add(height); // add a new height
      assert isValidBookshelf(); // need to make sure the bookshelf is valid
   }

   /**
    * Removes book at the start of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeFront() {
      int firstBook = pileOfBooks.remove(0); // get the first book height and remove the first book;
      assert isValidBookshelf(); // need to make sure the bookshelf is valid
      return firstBook;
   }

   /**
    * Removes book at the end of the Bookshelf and returns the height of the
    * removed book.
    *
    * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
    */
   public int removeLast() {
      int lastBook = pileOfBooks.remove(pileOfBooks.size() - 1); // get the last book height and remove the last book.
      assert isValidBookshelf(); // need to make sure the bookshelf is valid
      return lastBook;
   }

   /*
    * Gets the height of the book at the given position.
    *
    * PRE: 0 <= position < this.size()
    */
   public int getHeight(int position) {
      assert isValidBookshelf(); // need to make sure the bookshelf is valid
      return pileOfBooks.get(position);

   }

   /**
    * Returns number of books on the this Bookshelf.
    */
   public int size() {
      assert isValidBookshelf(); // need to make sure the bookshelf is valid
      return pileOfBooks.size();

   }

   /**
    * Returns string representation of this Bookshelf. Returns a string with the height of all
    * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
    * by example here:  “[7, 33, 5, 4, 3]”
    */
   public String toString() {
      if (size() == 0) { // if BookShelf is empty, return "[]"
          assert isValidBookshelf(); // need to make sure the bookshelf is valid
          return "[]";
      }

      String val = "";
      for (int i = 0; i < pileOfBooks.size() - 1; i++) {
         val += pileOfBooks.get(i).toString() + ", ";
      }

      val += pileOfBooks.get(pileOfBooks.size() - 1);
      String result = "[" + val + "]";
      assert isValidBookshelf(); // need to make sure the bookshelf is valid

      return result;

   }

   /**
    * Returns true iff the books on this Bookshelf are in non-decreasing order.
    * (Note: this is an accessor; it does not change the bookshelf.)
    */
   public boolean isSorted() {
      for (int i = 0; i < pileOfBooks.size() - 1; i++) {
         if (pileOfBooks.get(i) > pileOfBooks.get(i + 1)) { // if previous book is higher than the next book, return false
            assert isValidBookshelf(); // need to make sure the bookshelf is valid
            return false;
         }
      }

      assert isValidBookshelf(); // need to make sure the bookshelf is valid
      return true;
   }

   /**
    * Returns true iff the Bookshelf data is in a valid state.
    * (See representation invariant comment for more details.)
    */
   private boolean isValidBookshelf() {
      for (int i = 0; i < pileOfBooks.size(); i++) {
          if (pileOfBooks.get(i) < 0) { // if book height is smaller than 0, return false
             return false;
          }
      }

      return true;
   }

}
