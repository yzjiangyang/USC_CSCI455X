// Name: Yang Jiang
// USC NetID: yjiang24
// CSCI455 PA2
// Spring 2021

/**
 * Class BookshelfKeeperProg
 * This is the main method. This class interacts with the user by using Scanner.
 * The user can input data, and this class will print the result based on the user's input.
 * If the user's input is invalid, it will print error message and exit the main method.
 */
import java.util.Scanner;
import java.util.ArrayList;

public class BookshelfKeeperProg {
   public static void main(String[] args) {
      ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
      System.out.println("Please enter initial arrangement of books followed by newline: ");
      Scanner in = new Scanner(System.in); // create the scanner;
      if (in.hasNextLine()) {
         Scanner lineScanner = new Scanner(in.nextLine()); // read the whole line
         while (lineScanner.hasNext()) {
            int bookHeight = Integer.parseInt(lineScanner.next()); // convert string to int.
            if (bookHeight <= 0) { // if has non positive heights, print error message
               invalidHeightPrint();
               return; // since it has non positive heights, NO need to excute the following codes, so return.
            }
            pileOfBooks.add(bookHeight); // add each book height to the ArrayList pileOfBooks
         }
      }

      Bookshelf bookshelf = new Bookshelf(pileOfBooks); // Create BookShelf
      if (bookshelf.isSorted() == false) { // if bookshelf is not in non-decreasing order, print out the error message
         invalidOrderPrint();
         return; // since bookshelf is not in non-decreasing order, NO need to excute the following codes, so return.
      }

      BookshelfKeeper bookshelfKeeper = new BookshelfKeeper(bookshelf); // create BookshelfKeeper

      System.out.println(bookshelfKeeper.toString());
      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
      operationResultPrint(in, bookshelfKeeper); // print the result of each operation(pick, put, end)
   }


   // If the bookshef is not in non-decreasing order, this method prints error message.
   private static void invalidOrderPrint() {
      System.out.println("ERROR: Heights must be specified in non-decreasing order.");
      System.out.println("Exiting Program.");
   }

   // If the bookshelf height has non-positive height, this method prints error message.
   private static void invalidHeightPrint() {
      System.out.println("ERROR: Height of a book must be positive.");
      System.out.println("Exiting Program.");
   }

   // If the operation is not "pick" or "put", this method prints error message.
   private static void invalidOperationPrint() {
      System.out.println("ERROR: Operation should be either pick or put.");
      System.out.println("Exiting Program.");
   }

   // If the pick position is not valid, this method prints error message.
   private static void invalidPickPositionPrint() {
      System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
      System.out.println("Exiting Program.");
   }

   // If user input "end", this method prints "Exiting Program".
   private static void endPrint() {
      System.out.println("Exiting Program.");
   }

   // This method prints the result of each operation (pick, put, end)
   private static void operationResultPrint(Scanner in, BookshelfKeeper bookshelfKeeper) {
      while (in.hasNextLine()) {
         Scanner lineScanner = new Scanner(in.nextLine()); // read the whole line
         while (lineScanner.hasNext()) {
            String operation = lineScanner.next(); // get the type of current operation
            if (operation.equals("end")) { // if user input "end"
               endPrint(); // print "Exiting Program" message
               return; // since user input "end", NO need to excute the following codes, so return.
            }

            int heightOrIndex = Integer.parseInt(lineScanner.next()); // convert string to int.
            if (!operation.equals("put") && !operation.equals("pick")) { // if operation is not put or pick
               invalidOperationPrint(); // print error message
               return; // since operation is invalid, NO need to excute the following codes, so return.
            }
            if (operation.equals("put") && heightOrIndex <= 0) { // if put non-positive height
               invalidHeightPrint(); // print error message
               return; // since height is invalid, NO need to excute the following codes, so return.
            }
            if (operation.equals("pick") && (heightOrIndex < 0 || heightOrIndex >= bookshelfKeeper.getNumBooks())) { //if pick position is invalid
               invalidPickPositionPrint(); // print error message
               return; // since it is an invalid postion, NO need to excute the following codes, so return.
            }
            if (operation.equals("put")) { // if operation is put
               int currCalls = bookshelfKeeper.putHeight(heightOrIndex); // get current operation calls
               System.out.println(bookshelfKeeper.toString());
            }
            if (operation.equals("pick")) { // if operation is pick
               int currCalls = bookshelfKeeper.pickPos(heightOrIndex); // get current operation calls
               System.out.println(bookshelfKeeper.toString());
            }
         }
      }
   }
}
