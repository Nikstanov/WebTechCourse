package task15;

import org.education.Lab1.task12.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class Task15Test {

    @Test
    void test() {
        Book[] books = new Book[5];
        books[0] = new Book("War and Peace", "Leo Tolstoy", 1000, 43);
        books[1] = new Book("Crime and Punishment", "Fyodor Dostoyevsky", 800, 21);
        books[2] = new Book("The Catcher in the Rye", "J.D. Salinger", 500, 3);
        books[3] = new Book("The Lord of the Rings", "J.R.R. Tolkien", 1200, 33);
        books[4] = new Book("Nineteen Eighty-Four", "George Orwell", 600, 22);


        Arrays.sort(books);

        Book startedBook = books[4];
        Book expectedBook = new Book("War and Peace", "Leo Tolstoy", 1000);
        Assertions.assertEquals(startedBook, expectedBook);
    }

}
