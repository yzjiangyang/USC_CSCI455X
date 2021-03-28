import java.util.ArrayList;
public class BookshelfTester {
    public static void main(String[] args) {
        // Test empty BookShelf
        Bookshelf emptyBookShelf = new Bookshelf();
        System.out.println(emptyBookShelf.toString());

        // Test non-empty BookShelf
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        pileOfBooks.add(2);
        pileOfBooks.add(3);
        pileOfBooks.add(4);

        Bookshelf BookShelf = new Bookshelf(pileOfBooks);
        System.out.println("Heights of the books are " + BookShelf.toString());
        System.out.println("Removed the last book, and its height is " + BookShelf.removeLast());
        System.out.println("The bookshelf has " + BookShelf.size() + " books");

        BookShelf.addLast(5);
        BookShelf.addFront(1);
        System.out.println("Heights of the books are " + BookShelf.toString());
        System.out.println("Removed the first book, and its height is " + BookShelf.removeFront());
        System.out.println("Heights of the books are " + BookShelf.toString());
    }
}
