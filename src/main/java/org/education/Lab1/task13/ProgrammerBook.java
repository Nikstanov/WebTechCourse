package org.education.Lab1.task13;

import org.education.Lab1.task12.Book;

public class ProgrammerBook extends Book {
    private final String language;
    private final int level;

    public ProgrammerBook(String title, String author, int price, String language, int level) {
        super(title, author, price);
        this.language = language;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgrammerBook book = (ProgrammerBook) o;
        return super.equals(book) && language.equals(book.language) && level == book.level;
    }

    @Override
    public int hashCode() {
        int res = super.hashCode();
        res = res * 31 + language.hashCode();
        res = res * 31 + level;
        return res;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nLanguage: " + language
                + "\nLevel: " + level;
    }
}
