package org.education.Lab1.task16;

import org.education.Lab1.task12.Book;

import java.util.Comparator;

public interface BookComparator extends Comparator<Book> {

    int compareByTitle(Book o1, Book o2);
    int compareByTitleAndAuthor(Book o1, Book o2);
    int compareByTitleAuthorPrice(Book o1, Book o2);
}
