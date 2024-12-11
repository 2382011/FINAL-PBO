package Repositories;

import Entities.Book;

public interface BookRepository {
    Book[] getAll();
    Book[] getBook(int id);
    void add(Book book);
    boolean remove(int id);
    boolean update(Book book);
    Book findById(int id);

    Boolean updateStok(int id, int n);

    Boolean edit(Book book);
}
