package task14;

import org.education.Lab1.task12.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Task14Test {

    @Test
    void testA(){
        Book book = new Book("Some title", "Some author", 10);
        Book cloneBook = book.clone();

        // Check that this is not the same object
        Assertions.assertNotSame(cloneBook, book);

        // Check that clone is similar to origin
        Assertions.assertEquals(book, cloneBook);
    }
}
