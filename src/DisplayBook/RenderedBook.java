package DisplayBook;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class RenderedBook {

    private final SimpleStringProperty bookId;
    private final SimpleStringProperty title;
    private final SimpleStringProperty author;
    private final SimpleStringProperty publisher;
    private final SimpleStringProperty intCode;
    private int available;

    public RenderedBook(String bookId, String title, String author, String publisher, String intCode, int available) {
        this.bookId = new SimpleStringProperty(bookId);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.publisher = new SimpleStringProperty(publisher);
        this.intCode = new SimpleStringProperty(intCode);
        this.available = available;
    }

    public String getBookId() {
        return bookId.get();
    }

    public SimpleStringProperty bookIdProperty() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public SimpleStringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    public String getIntCode() {
        return intCode.get();
    }

    public SimpleStringProperty intCodeProperty() {
        return intCode;
    }

    public void setIntCode(String intCode) {
        this.intCode.set(intCode);
    }

    public int getAvailable() {
        return this.available;
    }

    public void setAvailable(int available_) {
        this.available = available_;
    }

    @Override
    public String toString() {
        String result = "";
        result += (getBookId() + " ");
        result += (getTitle() + " ");
        result += (getAuthor() + " ");
        result += (getPublisher() + " ");
        result += (getIntCode() + " ");
        result += String.valueOf(this.available);
        return result;
    }
}
