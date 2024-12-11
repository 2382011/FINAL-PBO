package Services;

import Repositories.BookRepository;
import Entities.Book;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book[] getBooks() {
        return bookRepository.getAll();
    }

    @Override
    public void addBook(Book book) {
        bookRepository.add(book);
    }

    @Override
    public boolean removeBook(int id) {
        return bookRepository.remove(id);
    }

    @Override
    public boolean updateBook(Book book) {
        return bookRepository.update(book);
    }

    @Override
    public boolean editBook(Book book) {
        return bookRepository.edit(book);
    }

    @Override
    public void reduceStock(Integer bookId, Integer quantity) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.getStock() < quantity) {
            System.out.println("Insufficient stock to reduce.");
            return;
        }
        book.setStock(book.getStock() - quantity);
        bookRepository.update(book);
    }
}

=[\\