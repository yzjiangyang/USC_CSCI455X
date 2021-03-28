import java.util.ArrayList;
public class TestAssert {
    public static void main(String[] args) {
        ArrayList<Integer> pileOfBooks = new ArrayList<Integer>();
        // pileOfBooks.add(4);
        pileOfBooks.add(-3);
        Bookshelf BookShelf = new Bookshelf(pileOfBooks);
        // System.out.println(BookShelf.toString());
    }
}
