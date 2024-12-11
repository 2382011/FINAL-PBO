package Entities;

public class Book {
    private int id;
    private String title;
    private String author;
    private int stock;
    private double price;
    private String category;
    private String supplier;
    private String expirationDate; // For tracking expired books
    private String condition; // For tracking book's condition (new, damaged, etc.)
    private String isbn;

    public Book() {}

    public Book(int i, String s, String s1) {
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getSupplier() { return supplier; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    public String getExpirationDate() { return expirationDate; }
    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getIsbn() { return isbn ;}
    public void setIsbn(String isbn) { this.isbn = condition; }
}
