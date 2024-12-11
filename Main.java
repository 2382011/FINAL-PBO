import Config.Database;
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
        BookService bookServiceImpl= new BookServiceImpl(bookRepository);
        BookView bookView = new BookTerminalView(bookServiceImpl);
        bookView.run();


    }
}
