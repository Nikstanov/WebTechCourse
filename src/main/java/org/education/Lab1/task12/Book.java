package org.education.Lab1.task12;

public class Book implements Cloneable, Comparable<Book>{

    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPrice() {
        return price;
    }

    private int price;
    private static int edition;

    public Book(String title, String author, int price, int isbn) {
        edition = 1;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
    }

    private int isbn;

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return price == book.price && title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        int res = title.hashCode();
        res = res * 31 + author.hashCode();
        res = res * 31 + price;
        res = res * 31 + edition;
        return res;
    }

    @Override
    public String toString() {
        return "Title: " + title
                + "\nAuthor: " + author
                + "\nPrice: " + price
                + "\nEdition: " + edition;
    }

    @Override
    public Book clone() {
        try {
            Book clone = (Book) super.clone();
            clone.title = String.copyValueOf(this.title.toCharArray());
            clone.price = price;
            clone.author = String.copyValueOf(this.author.toCharArray());
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public int compareTo(Book o) {
        return isbn - o.isbn;
    }

//    @Override
//    public int BookTitleComparator.compare(Book o1, Book o2) {
//        return 0;
//    }

//    @Override
//    public int compare(Book o1, Book o2) {
//        return o1.compareTo(o2);
//    }

//    @Override
//    public int compareByTitle(Book o1, Book o2) {
//        return o1.title.compareTo(o2.title);
//    }
//
//    @Override
//    public int compareByTitleAndAuthor(Book o1, Book o2) {
//        int temp = compareByTitle(o1,o2);
//        return (temp != 0) ? temp : o1.author.compareTo(o2.author);
//    }
//
//    @Override
//    public int compareByTitleAuthorPrice(Book o1, Book o2) {
//        int temp = compareByTitleAndAuthor(o1,o2);
//        return (temp != 0) ? temp : o1.price - o2.price;
//    }
}
