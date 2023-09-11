package task16;

import org.education.Lab1.task12.Book;
import org.education.Lab1.task16.BookAuthorComparator;
import org.education.Lab1.task16.BookPriceComparator;
import org.education.Lab1.task16.BookTitleComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;

public class Task16Test{

    static private ArrayList<Book> bookArrayList;
    static private Comparator<Book> titleAuthorComparator;
    static private Comparator<Book> titleComparator;
    static private Comparator<Book> authorTitleComparator;
    static private Comparator<Book> titleAuthorPriceComparator;

    @BeforeAll
    public static void init(){
        titleComparator = new BookTitleComparator();
        titleAuthorComparator = new BookTitleComparator().thenComparing(new BookAuthorComparator());
        authorTitleComparator = new BookAuthorComparator().thenComparing(titleComparator);
        titleAuthorPriceComparator = new BookTitleComparator().thenComparing(new BookAuthorComparator()).thenComparing(new BookPriceComparator());
    }
    @BeforeEach
    public void setUp() {
        bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("title0", "author312", 4));
        bookArrayList.add(new Book("title3", "author6", 9));
        bookArrayList.add(new Book("title2", "author0", 4));
        bookArrayList.add(new Book("title0", "author3", 18));
        bookArrayList.add(new Book("title0", "author3", 14));
        bookArrayList.add(new Book("title1", "author-1", 1));
    }

    @Test
    public void testTitleAuthorSort() {
        bookArrayList.sort(titleAuthorComparator);

        ArrayList<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book("title0", "author3", 18));
        expectedList.add(new Book("title0", "author3", 14));
        expectedList.add(new Book("title0", "author312", 4));
        expectedList.add(new Book("title1", "author-1", 1));
        expectedList.add(new Book("title2", "author0", 4));
        expectedList.add(new Book("title3", "author6", 9));

        Assertions.assertEquals(expectedList, bookArrayList);
    }

    @Test
    public void testTitleSort() {
        bookArrayList.sort(titleComparator);

        ArrayList<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book("title0", "author312", 4));
        expectedList.add(new Book("title0", "author3", 18));
        expectedList.add(new Book("title0", "author3", 14));
        expectedList.add(new Book("title1", "author-1", 1));
        expectedList.add(new Book("title2", "author0", 4));
        expectedList.add(new Book("title3", "author6", 9));

        Assertions.assertEquals(expectedList, bookArrayList);
    }

    @Test
    public void testAuthorTitleSort() {
        bookArrayList.sort(authorTitleComparator);

        ArrayList<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book("title1", "author-1", 1));
        expectedList.add(new Book("title2", "author0", 4));
        expectedList.add(new Book("title0", "author3", 18));
        expectedList.add(new Book("title0", "author3", 14));
        expectedList.add(new Book("title0", "author312", 4));
        expectedList.add(new Book("title3", "author6", 9));

        Assertions.assertEquals(expectedList, bookArrayList);
    }

    @Test
    public void testTitleAuthorPriceSort() {
        bookArrayList.sort(titleAuthorPriceComparator);

        ArrayList<Book> expectedList = new ArrayList<>();
        expectedList.add(new Book("title0", "author3", 14));
        expectedList.add(new Book("title0", "author3", 18));
        expectedList.add(new Book("title0", "author312", 4));
        expectedList.add(new Book("title1", "author-1", 1));
        expectedList.add(new Book("title2", "author0", 4));
        expectedList.add(new Book("title3", "author6", 9));

        Assertions.assertEquals(expectedList, bookArrayList);
    }

}
