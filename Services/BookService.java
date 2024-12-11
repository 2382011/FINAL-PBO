package Services;

import Entities.Book;

public interface BookService {
    Book[] getBooks();
    void addBook(Book book);
    boolean removeBook(int id);
    boolean updateBook(Book book);

    boolean editBook(Book book);

    void reduceStock(Integer integer, Integer integer1);

    boolean updateStok(Integer integer, Integer integer1);

    Book[] searchBook(Integer integer);

    Book getBookById(Integer integer);

    Book[] searchBook(String keyword);
}
