import Config.Database;
import Entities.Book;
import Services.BookService;
import Services.BookServiceImpl;
import Views.BookTerminalView;
import Views.BookView;
import Repositories.BookRepository;
import Repositories.BookRepositoryDbImpl;

public class Main {
    public static void main(String[] args) {
        Database database = new Database("manajementokobuku", "root", "", "localhost", "3306");
        database.setup();

        BookRepository bookRepository = new BookRepositoryDbImpl(database);
        BookService bookServiceImpl= new BookServiceImpl(bookRepository) {
            @Override
            public boolean updateStok(Integer integer, Integer integer1) {
                return false;
            }

            @Override
            public Book[] searchBook(Integer integer) {
                return new Book[0];
            }

            @Override
            public Book getBookById(Integer integer) {
                return null;
            }

            @Override
            public Book[] searchBook(String keyword) {
                return new Book[0];
            }
        };
        BookView bookView = new BookTerminalView(bookServiceImpl);
        bookView.run();


    }
}
