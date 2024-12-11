package Repositories;

import Repositories.BookRepository;
import Entities.Book;

public class BookRepositoryImpl implements BookRepository {
    public static Book[] books = new Book[10];

    @Override
    public Book[] getAll() {
        return books;
    }

    @Override
    public Book[] getBook(int id) {
        return new Book[0];
    }

    @Override
    public void add(final Book book) {
        resizeArrayIfFull();

        // Add book to the array at the first null position
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = book;
                break;
            }
        }
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public boolean update(Book book) {
        return false;
    }

    @Override
    public Book findById(int id) {
        return null;
    }

    @Override
    public Boolean updateStok(int id, int n) {
        return null;
    }

    private static void resizeArrayIfFull() {
        // Check if the books array is full
        Boolean isFull = true;
        isFull = isArrayFull(isFull);

        // If full, resize the array to double its size
        if (isFull) {
            resizeArrayToTwoTimesBigger();
        }
    }

    private static void resizeArrayToTwoTimesBigger() {
        Book[] temp = books;
        books = new Book[books.length * 2];
        for (int i = 0; i < temp.length; i++) {
            books[i] = temp[i];
        }
    }

    private static Boolean isArrayFull(Boolean isFull) {
        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                isFull = false;
                break;
            }
        }
        return isFull;
    }


    private static boolean isSelectedBookNotValid(final Integer id) {
        if (id <= 0 || id - 1 > books.length - 1 || books[id - 1] == null) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean edit(Book book) {
        if (isSelectedBookNotValid(book.getId())) {
            return false;
        }
        books[book.getId() - 1] = book;
        return true;
    }
}
