package Database;
public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private String intCode;
    private boolean isAvailable;

    public Book(int id, String title, String author, String publisher, String intCode, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.intCode = intCode;
        this.isAvailable = isAvailable;
    }

    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIntCode() {
        return intCode;
    }

    public void setIntCode(String intCode) {
        this.intCode = intCode;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}