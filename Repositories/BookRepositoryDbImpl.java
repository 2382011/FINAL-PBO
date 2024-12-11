package Repositories;

import Config.Database;
import Entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryDbImpl implements BookRepository {
    private final Database database;

    public BookRepositoryDbImpl(Database database) {
        this.database = database;
    }

    @Override
    public Book[] getAll() {
        Connection connection = database.getConnection();
        String sql = "SELECT * FROM book";
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setStock(resultSet.getInt("stock"));
                book.setPrice(resultSet.getDouble("price"));
                book.setCategory(resultSet.getString("category"));
                book.setSupplier(resultSet.getString("supplier"));
                book.setExpirationDate(resultSet.getString("expirationDate"));
                book.setCondition(resultSet.getString("book_condition"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books.toArray(new Book[0]);
    }
    @Override
    public Book[] getBook(int id) {
        Connection connection = database.getConnection();
        String sql = "SELECT * FROM book where id="+id;
        List<Book> books = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setStock(resultSet.getInt("stock"));
                book.setPrice(resultSet.getDouble("price"));
                book.setCategory(resultSet.getString("category"));
                book.setSupplier(resultSet.getString("supplier"));
                book.setExpirationDate(resultSet.getString("expirationDate"));
                book.setCondition(resultSet.getString("book_condition"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books.toArray(new Book[0]);
    }

    @Override
    public void add(Book book) {
        Connection connection = database.getConnection();
        String sql = "INSERT INTO book (title, author, stock, price, category, supplier, expirationDate, book_condition, isbn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getStock());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getCategory());
            preparedStatement.setString(6, book.getSupplier());
            preparedStatement.setString(7, book.getExpirationDate());
            preparedStatement.setString(8, book.getCondition());
            preparedStatement.setString(9,book.getIsbn());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean remove(int id) {
        Connection connection = database.getConnection();
        String sql = "DELETE FROM book WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id); // Menyisipkan ID buku yang akan dihapus

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Mengembalikan true jika ada data yang terhapus
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Mengembalikan false jika terjadi error
        }
    }


    @Override
    public Boolean updateStok(int id, int newStock) {
        Connection connection = database.getConnection();
        String sql = "UPDATE book SET stock = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, newStock); // Stok baru
            preparedStatement.setInt(2, id);      // ID buku
            return preparedStatement.executeUpdate() > 0; // Berhasil jika baris terpengaruh > 0
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(Book book) {
        Connection connection = database.getConnection();
        String sql = "UPDATE book SET title = ?, author = ?, stock = ?, price = ?, category = ?, supplier = ?, expirationDate = ?, book_condition = ?, isbn=? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getStock());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getCategory());
            preparedStatement.setString(6, book.getSupplier());
            preparedStatement.setString(7, book.getExpirationDate());
            preparedStatement.setString(8, book.getCondition());
            preparedStatement.setString(8, book.getIsbn());
            preparedStatement.setInt(9, book.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Book findById(int id) {
        Connection connection = database.getConnection();
        String sql = "SELECT * FROM book WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setStock(resultSet.getInt("stock"));
                book.setPrice(resultSet.getDouble("price"));
                book.setCategory(resultSet.getString("category"));
                book.setSupplier(resultSet.getString("supplier"));
                book.setExpirationDate(resultSet.getString("expiration_date"));
                book.setCondition(resultSet.getString("condition"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




    @Override
    public Boolean edit(Book book) {
        Connection connection = database.getConnection();
        String sql = "UPDATE book SET title = ?, author = ?, stock = ?, price = ?, " +
                "category = ?, supplier = ?, expiration_date = ?, condition = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getStock());
            preparedStatement.setDouble(4, book.getPrice());
            preparedStatement.setString(5, book.getCategory());
            preparedStatement.setString(6, book.getSupplier());
            preparedStatement.setString(7, book.getExpirationDate());
            preparedStatement.setString(8, book.getCondition());
            preparedStatement.setInt(9, book.getId()); // Asumsi ID adalah integer

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; // Mengembalikan true jika ada data yang terupdate
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Mengembalikan false jika terjadi error
        }
    }

}
