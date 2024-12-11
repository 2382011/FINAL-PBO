package Views;

import Services.BookService;
import Entities.Book;

import java.util.Scanner;

public class BookTerminalView implements BookView {
    public static Scanner scanner = new Scanner(System.in);
    private final BookService bookService;

    public BookTerminalView(final BookService bookService) {
        this.bookService = bookService;
    }

    public void showBookList() {
        System.out.println("BOOK LIST");
        Book[] books = bookService.getBooks();
        for (int i = 0; i < books.length; i++) {
            var book = books[i];
            if (book != null) {
                System.out.println((i + 1) + ". " + book.getTitle() + " - " + book.getAuthor());
            }
        }
    }

    public static String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }

    public void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showBookList();
            System.out.println("MENU:");
            System.out.println("1. Pendaftaran Buku Baru");
            System.out.println("2. Pengurangan Stok Buku");
            System.out.println("3. Pembaruan Informasi Buku");
            System.out.println("4. Pencarian dan Penyortiran Buku");
            System.out.println("5. Laporan Stok Buku");
            System.out.println("6. Penghapusan Buku");
            System.out.println("7. Notifikasi Stok Rendah");
            System.out.println("8. Pelacakan Riwayat Transaksi");
            System.out.println("9. Manajemen Pemasok Buku");
            System.out.println("10. Forecasting Permintaan Buku");
            System.out.println("11. Peringatan Buku Kadaluarsa atau Rusak");
            System.out.println("12. Manajemen Kategori Buku");
            System.out.println("13. Keluar");
            String selectedMenu = input("Pilih Menu");

            switch (selectedMenu) {
                case "1":
                    showMenuAddBook();
                    break;
                case "2":
                    showMenuReduceStock();
                    break;
                case "3":
                    showMenuEditBook();
                    break;
                case "4":
                    showMenuSearchAndSortBooks();
                    break;
                case "5":
                    showMenuStockReport();
                    break;
                case "6":
                    showMenuRemoveBook();
                    break;
                case "7":
                    showMenuLowStockNotification();
                    break;
                case "8":
                    showMenuTransactionHistory();
                    break;
                case "9":
                    showMenuManageSuppliers();
                    break;
                case "10":
                    showMenuDemandForecasting();
                    break;
                case "11":
                    showMenuExpiredOrDamagedWarning();
                    break;
                case "12":
                    showMenuManageCategories();
                    break;
                case "13":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilih menu yang valid.");
            }
        }
    }

    private void showMenuRemoveBook() {
    }

    public void showMenuAddBook() {
        System.out.println("PENDAFTARAN BUKU BARU");
        String title = input("Judul Buku (x untuk batal)");
        if (title.equals("x")) return;
        String author = input("Penulis Buku (x untuk batal)");
        if (author.equals("x")) return;
        String isbn = input("ISBN (x untuk batal)");
        if (isbn.equals("x")) return;

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        bookService.addBook(book);
    }

    public void showMenuReduceStock() {

    }

    public void showMenuEditBook() {
        System.out.println("PEMBARUAN INFORMASI BUKU");
        String bookNumber = input("Masukkan nomor buku untuk diperbarui (x untuk batal)");
        if (bookNumber.equals("x")) return;
        String newTitle = input("Judul baru (x untuk batal)");
        if (newTitle.equals("x")) return;
        String newAuthor = input("Penulis baru (x untuk batal)");
        if (newAuthor.equals("x")) return;

        Book book = new Book();
        book.setId(Integer.valueOf(bookNumber));
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        boolean success = bookService.editBook(book);
        if (success) System.out.println("Buku berhasil diperbarui.");
        else System.out.println("Gagal memperbarui buku.");
    }

    public void showMenuSearchAndSortBooks() {
        System.out.println("PENCARIAN DAN PENYORTIRAN BUKU");
        // Implementasi pencarian dan penyortiran di sini
    }

    public void showMenuStockReport() {
        System.out.println("LAPORAN STOK BUKU");
        // Implementasi laporan stok buku di sini
    }

    public void showMenuLowStockNotification() {
        System.out.println("NOTIFIKASI STOK RENDAH");
        // Implementasi notifikasi stok rendah di sini
    }

    public void showMenuTransactionHistory() {
        System.out.println("PELACAKAN RIWAYAT TRANSAKSI");
        // Implementasi pelacakan riwayat transaksi di sini
    }

    public void showMenuManageSuppliers() {
        System.out.println("MANAJEMEN PEMASOK BUKU");
        // Implementasi manajemen pemasok di sini
    }

    public void showMenuDemandForecasting() {
        System.out.println("FORECASTING PERMINTAAN BUKU");
        // Implementasi forecasting permintaan di sini
    }

    public void showMenuExpiredOrDamagedWarning() {
        System.out.println("PERINGATAN BUKU KADALUARSA ATAU RUSAK");
        // Implementasi peringatan kadaluarsa/rusak di sini
    }

    public void showMenuManageCategories() {
        System.out.println("MANAJEMEN KATEGORI BUKU");
        // Implementasi manajemen kategori buku di sini
    }

    @Override
    public void run() {
        showMainMenu();
    }
}
