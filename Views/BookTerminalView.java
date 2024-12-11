package Views;

import Services.BookService;
import Entities.Book;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                System.out.println(book.getId() + ". " + book.getTitle());
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
            System.out.println("10. Author Buku");
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
                    showAuthor();
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

    private void showAuthor() {
        System.out.println("Author BUKU");
        Book[] books = bookService.getBooks();
        for (int i = 0; i < books.length; i++) {
            var book = books[i];
            if (book != null) {
                System.out.println(book.getId() + ". " + book.getTitle() + " - " + book.getAuthor());
            }
        }
    }

    private void showMenuRemoveBook() {
        showBookList();
        String idBook = input("Masukan Buku yang mau di Hapus");
        if (idBook.equals("x")) {
            //batal
        } else {
            boolean success = bookService.removeBook(Integer.valueOf(idBook));
            if (!success) {
                System.out.println("Gagal menghapus Buku : " + idBook);
            }
        }
    }

    public void showMenuAddBook() {
        System.out.println("PENDAFTARAN BUKU BARU");
        String title = input("Judul Buku (x untuk batal)");
        if (title.equals("x")) return;
        String author = input("Penulis Buku (x untuk batal)");
        if (author.equals("x")) return;
        String isbn = input("ISBN (x untuk batal)");
        if (isbn.equals("x")) return;
        String stokInput = input("Stok Buku");
        if (stokInput.equals("x")) return;
        String priceInput = input("Harga Buku");
        if (priceInput.equals("x")) return;
        double price = Double.parseDouble(priceInput);
        int stok = Integer.parseInt(stokInput);
        String category = input("Masukan Catogory: ");
        if (category.equals("x")) return;
        String supplier = input("Masukan Supplier: ");
        if (supplier.equals("x")) return;
        String expirationDate = input("Masukan expiration Date (YYYY-MM-DD): ");
        if (expirationDate.equals("x")) return;
        String book_condition = input("Masukan Kondisi buku: ");
        if (book_condition.equals("x")) return;
        Book book = new Book();
        book.setTitle(title);
        book.setSupplier(supplier);
        book.setPrice(price);
        book.setStock(stok);
        book.setSupplier(supplier);
        book.setExpirationDate(expirationDate);
        book.setCondition(book_condition);
        book.setCategory(category);
        book.setAuthor(author);
        book.setIsbn(isbn);
        bookService.addBook(book);
    }

    public void showMenuReduceStock() {
        showBookList();
        String idBook = input("Masukan Buku yang mau di edit stock");
        String stok = input("Masukan stock");
        if (idBook.equals("x")) {
            //batal
        } else {
            boolean success = bookService.updateStok(Integer.valueOf(idBook),Integer.valueOf(stok));
            if (!success) {
                System.out.println("Gagal menghapus Buku : " + idBook);
            }
        }
    }

    public void showMenuEditBook() {
        System.out.println("PEMBARUAN INFORMASI BUKU");

        String bookNumber = input("Masukkan nomor ID buku yang ingin diperbarui (x untuk batal)");
        if (bookNumber.equals("x")) return;

        Book book = bookService.getBookById(Integer.valueOf(bookNumber));
        if (book == null) {
            System.out.println("Buku dengan ID " + bookNumber + " tidak ditemukan.");
            return;
        }

        System.out.println("Informasi buku saat ini:");
        System.out.println("Judul: " + book.getTitle());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Penulis: " + book.getAuthor());
        System.out.println("Harga: " + book.getPrice());
        System.out.println("Stok: " + book.getStock());

        String newTitle = input("Judul baru (kosong untuk tidak mengubah, x untuk batal)");
        if (newTitle.equals("x")) return;
        if (!newTitle.isBlank()) book.setTitle(newTitle);

        String newIsbn = input("ISBN baru (kosong untuk tidak mengubah, x untuk batal)");
        if (newIsbn.equals("x")) return;
        if (!newIsbn.isBlank()) book.setIsbn(newIsbn);

        String newAuthor = input("Penulis baru (kosong untuk tidak mengubah, x untuk batal)");
        if (newAuthor.equals("x")) return;
        if (!newAuthor.isBlank()) book.setAuthor(newAuthor);

        String newPriceInput = input("Harga baru (kosong untuk tidak mengubah, x untuk batal)");
        if (newPriceInput.equals("x")) return;
        if (!newPriceInput.isBlank()) {
            try {
                double newPrice = Double.parseDouble(newPriceInput);
                book.setPrice(newPrice);
            } catch (NumberFormatException e) {
                System.out.println("Harga tidak valid, perubahan tidak diterapkan.");
            }
        }

        String newStockInput = input("Stok baru (kosong untuk tidak mengubah, x untuk batal)");
        if (newStockInput.equals("x")) return;
        if (!newStockInput.isBlank()) {
            try {
                int newStock = Integer.parseInt(newStockInput);
                book.setStock(newStock);
            } catch (NumberFormatException e) {
                System.out.println("Stok tidak valid, perubahan tidak diterapkan.");
            }
        }

        boolean success = bookService.editBook(book);
        if (success) {
            System.out.println("Buku berhasil diperbarui.");
        } else {
            System.out.println("Gagal memperbarui buku.");
        }
    }


    public void showMenuSearchAndSortBooks() {
        System.out.println("PENCARIAN DAN PENYORTIRAN BUKU");
        String keyword = input("Masukkan kata kunci pencarian (x untuk batal)");
        if (keyword.equals("x")) return;

        Book[] books = bookService.searchBook(keyword);
        if (books.length == 0) {
            System.out.println("Tidak ada buku yang ditemukan. Menampilkan buku default:");
            books = new Book[] {
                    new Book(1, "Default Book 1", "Default Author 1"),
                    new Book(2, "Default Book 2", "Default Author 2")
            };
        }


        for (Book book : books) {
            if (book != null) {
                System.out.println(book.getId() + ". " + book.getTitle() + " - " + book.getAuthor());
            }
        }
    }


    public void showMenuStockReport() {
        System.out.println("LAPORAN STOK BUKU");
        Book[] books = bookService.getBooks();
        for (int i = 0; i < books.length; i++) {
            var book = books[i];
            if (book != null) {
                System.out.println(book.getId() + ". " + book.getTitle() + " - " + book.getStock());
            }
        }
    }

    public void showMenuLowStockNotification() {
        System.out.println("NOTIFIKASI STOK buku yang Habis");
        // Implementasi notifikasi stok habis di sini
        Book[] books = bookService.getBooks();
        for (int i = 0; i < books.length; i++) {
            var book = books[i];
            if (book != null && book.getStock()==0) {
                System.out.println(book.getId() + ". " + book.getTitle() + " - " + book.getStock());
            }
        }
    }

    public void showMenuTransactionHistory() {
        System.out.println("PELACAKAN RIWAYAT TRANSAKSI Harga");
        Book[] books = bookService.getBooks();
        for (int i = 0; i < books.length; i++) {
            var book = books[i];
            if (book != null ) {
                System.out.println(book.getId() + ". " + book.getTitle() + " - " + book.getPrice());
            }
        }
    }

    public void showMenuManageSuppliers() {
        System.out.println("MANAJEMEN PEMASOK BUKU");
        // Implementasi manajemen pemasok di sini
        Book[] books = bookService.getBooks();
        for (int i = 0; i < books.length; i++) {
            var book = books[i];
            if (book != null ) {
                System.out.println(book.getId() + ". " + book.getTitle() + " - " + book.getSupplier());
            }
        }
    }


    public void showMenuExpiredOrDamagedWarning() {
        System.out.println("PERINGATAN BUKU KADALUARSA ATAU RUSAK");
        // Implementasi peringatan kadaluarsa/rusak di sini
        Book[] books = bookService.getBooks();
        for (int i = 0; i < books.length; i++) {
            var book = books[i];

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Sesuaikan format dengan string tanggal

            if (book != null && book.getExpirationDate() != null) {
                try {
                    LocalDate expirationDate = LocalDate.parse(book.getExpirationDate(), formatter);
                    if (expirationDate.isBefore(LocalDate.now())) {
                        System.out.println(book.getId() + ". " + book.getTitle() + " - " + book.getStock());
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Tanggal kedaluwarsa tidak valid untuk buku: " + book.getTitle());
                }
            }

        }
    }

    public void showMenuManageCategories() {
        System.out.println("MANAJEMEN KATEGORI BUKU");
        Book[] books = bookService.getBooks();
        for (int i = 0; i < books.length; i++) {
            var book = books[i];
            if (book != null ) {
                System.out.println(book.getId() + ". " + book.getTitle() + " - " + book.getCategory());
            }
        }
    }

    @Override
    public void run() {
        showMainMenu();
    }
}
