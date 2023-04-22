package BookReturns;

import Database.DatabaseHandler;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class BookReturnsController implements Initializable {

    @FXML
    private TableColumn<RentedInfo, String> bookIDcolumn;
    @FXML
    private Text totalBookField;
    @FXML
    private TableColumn<RentedInfo, Timestamp> memberRentedDateColumn;
    @FXML
    private Text totalMemberField;
    @FXML
    private TableView<RentedInfo> bookTable;
    @FXML
    private JFXButton clearMemberButton;
    @FXML
    private JFXButton clearBookButton;
    @FXML
    private TableColumn<RentedInfo, Timestamp> bookRentedDateColumn;
    @FXML
    private TableView<RentedInfo> memberTable;
    @FXML
    private TableColumn<RentedInfo, String> memberIDColumn;
    @FXML
    private JFXButton saveButon1;
    @FXML
    private TextField memberIdField;
    @FXML
    private TextField bookIDField;
    @FXML
    private Text warningText;
    @FXML
    private Text warningText1;
    @FXML
    private TextField insertBookField;
    private int bookCount = 0;
    private int memberCount = 0;

    @FXML
    void pressMemberIdKey(KeyEvent event) {
        resetWarningText(warningText);
        if (event.getCode() == KeyCode.ENTER) {
            // Get the member ID entered by the user
            bookCount = 0;
            String memberId = memberIdField.getText();
            if (!DatabaseHandler.isValidBookAvailable(memberId)) {
                setWarningText(warningText, "Invalid Member ID.");
                return;
            }
            queryUserRentedBooks(memberId);
        }
    }

    private void resetWarningText(Text warningText) {
        if (warningText.isVisible()) {
            warningText.setVisible(false);
        }
    }

    private void setWarningText(Text warningText, String text) {
        warningText.setText(text);
        if (!warningText.isVisible()) {
            warningText.setVisible(true);
        }
    }

    void queryUserRentedBooks(String memberId) {
        // Query the rentedbooks table in the database for information about this member
        bookCount = 0;
        try {
            Connection conn = DriverManager.getConnection(DatabaseHandler.MySQL_URL, DatabaseHandler.USER_NAME, DatabaseHandler.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM member where MemberId = ?");
            stmt.setInt(1, Integer.parseInt(memberId));
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                setWarningText(warningText, "Member ID does not exist.");
                return;
            }
            stmt.clearParameters();
            stmt.close();
            stmt = conn.prepareStatement("SELECT BookId, RentedTime FROM rentedbooks WHERE MemberId = ?");
            stmt.setString(1, memberId);
            rs = stmt.executeQuery();

            // Populate the memberTable with the results of the query
            ObservableList<RentedInfo> books = FXCollections.observableArrayList();
            while (rs.next()) {
                String bookId = rs.getString("BookId");
                Timestamp rentedTime = rs.getTimestamp("RentedTime");
                books.add(new RentedInfo(bookId, memberId, rentedTime));
                bookCount++;
            }
            memberTable.setItems(books);

            // Update the totalBookField
            totalBookField.setText("Total books: " + bookCount);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void pressBookID(KeyEvent event) {
        resetWarningText(warningText1);
        if (event.getCode() == KeyCode.ENTER) {
            // Get the book ID entered by the user
            String bookId = bookIDField.getText();
            if (!DatabaseHandler.isValidBookId(bookId)) {
                setWarningText(warningText1, "Invalid Book ID");
                return;
            }
            updateBookTable(bookId);
        }
    }

    private void updateBookTable(String bookId) {
        memberCount = 0;
        // Query the rentedbooks table in the database for information about this book
        try {
            Connection conn = DriverManager.getConnection(DatabaseHandler.MySQL_URL, DatabaseHandler.USER_NAME, DatabaseHandler.PASSWORD);
            PreparedStatement stmt = conn.prepareStatement("SELECT MemberId, RentedTime FROM rentedbooks WHERE BookId = ?");
            stmt.setString(1, bookId);
            ResultSet rs = stmt.executeQuery();
            // Populate the bookTable with the results of the query (bookTable contains information about members have that book)
            ObservableList<RentedInfo> members = FXCollections.observableArrayList();
            while (rs.next()) {
                String memberId = rs.getString("MemberId");
                Timestamp rentedTime = rs.getTimestamp("RentedTime");
                members.add(new RentedInfo("", memberId, rentedTime));
                memberCount++;
            }
            if (memberCount == 0) {
                setWarningText(warningText1, "Book ID was not found in Rented Book.");
                return;
            }
            bookTable.setItems(members);

            // Update the totalMemberField
            totalMemberField.setText("Total members: " + memberCount);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickClearMemberButton(ActionEvent event) {
        totalBookField.setText("0");
        memberTable.getItems().clear();
        memberTable.refresh();
        warningText.setVisible(false);
    }

    @FXML
    void clickClearBookButton(ActionEvent event) {
        totalMemberField.setText("0");
        bookTable.getItems().clear();
        memberTable.refresh();
        warningText.setVisible(false);
    }

    @FXML
    void clickDeleteMemberButton(ActionEvent event) throws IOException, SQLException {
        RentedInfo selectedBook = memberTable.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            // No member selected, show an alert and return
            setWarningText(warningText, "Please choose a row.");
            return;
        }

        // Load the edit member view
        warningText.setVisible(false);

        //delete info
        Connection conn = DriverManager.getConnection(DatabaseHandler.MySQL_URL, DatabaseHandler.USER_NAME, DatabaseHandler.PASSWORD);
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM rentedbooks WHERE BookId = ? AND RentedTime = ? LIMIT 1 ");
        stmt.setString(1, selectedBook.getBookID());
        stmt.setTimestamp(2, selectedBook.getRentedDate());

        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            //update available book in library
            stmt.clearParameters();
            stmt = conn.prepareStatement("SELECT available FROM book WHERE id = ?");
            stmt.setString(1, selectedBook.getBookID());
            ResultSet rs = stmt.executeQuery();
            int available = 0;
            while(rs.next()) {
                available = rs.getInt("available");
            }
            available += 1;
            stmt.clearParameters();
            stmt.close();
            stmt = conn.prepareStatement("UPDATE book SET available=? WHERE id = ?");
            stmt.setInt(1, available);
            stmt.setString(2, selectedBook.getBookID());
            stmt.executeUpdate();

            //update table
            queryUserRentedBooks(selectedBook.getMemberID());
        } else {
            setWarningText(warningText, "Delete failed");
        }

        conn.close();
    }

    @FXML
    void pressInsertBook(KeyEvent event) {
        resetWarningText(warningText);
        if (event.getCode() == KeyCode.ENTER) {
            if (memberIdField.getText() == "") {
                setWarningText(warningText, "Please enter Member ID.");
                return;
            }
            String memberId = memberIdField.getText();
            try {

                // Create a connection to the database
                Connection conn = DriverManager.getConnection(DatabaseHandler.MySQL_URL, DatabaseHandler.USER_NAME, DatabaseHandler.PASSWORD);

                // Prepare a SQL statement to check if the member ID exists
                String sql = "SELECT * FROM member WHERE MemberId=?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(memberId));

                // Execute the SQL statement and get the results
                ResultSet rs = stmt.executeQuery();

                // If the result set has no rows, the member ID does not exist
                if (!rs.next()) {
                    // Display an error message
                    setWarningText(warningText, "Member ID does not exist.");
                    return;
                } else {
                    //check bookID valid
                    String bookID = insertBookField.getText();
                    if ("".equals(bookID)) {
                        setWarningText(warningText, "Please enter Book ID");
                        return;
                    } else if(!DatabaseHandler.isValidBookId(bookID)) {
                        setWarningText(warningText, "Invalid Book ID.");
                        return;
                    }
                    stmt.clearParameters();
                    stmt.close();
                    sql = "SELECT * FROM book WHERE id=?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, bookID);
                    rs = stmt.executeQuery();
                    if (!rs.next()) {
                        setWarningText(warningText, "Book ID does not exist.");
                        return;
                    }

                    //check book numbers
                    int availableBook = rs.getInt("available");
                    if (availableBook <= 0) {
                        setWarningText(warningText, "No book with ID: " + bookID + " is available in library.");
                        return;
                    } else {
                        availableBook--;
                        PreparedStatement changeAvailstmt = conn.prepareStatement("UPDATE book SET available = ? WHERE id = ?");
                        changeAvailstmt.setInt(1, availableBook);
                        changeAvailstmt.setString(2, bookID);
                        changeAvailstmt.executeUpdate();
                    }
                    //put book to rentedbooks table
                    stmt.clearParameters();
                    stmt.close();
                    stmt = conn.prepareStatement("INSERT INTO rentedbooks (MemberId, BookId) VALUES (?, ?)");
                    stmt.setInt(1, Integer.parseInt(memberId));
                    stmt.setString(2, bookID);
                    stmt.executeUpdate();
                    queryUserRentedBooks(memberId);
                }

                // Close the database connection and release resources
                rs.close();
                stmt.close();
                conn.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                setWarningText(warningText, "Member id is invalid.");
            }
        }
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initCol();
    }

    private void initCol() {
        bookIDcolumn.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        memberRentedDateColumn.setCellValueFactory(new PropertyValueFactory<>("RentedDate"));
        memberIDColumn.setCellValueFactory(new PropertyValueFactory<>("memberID"));
        bookRentedDateColumn.setCellValueFactory(new PropertyValueFactory<>("RentedDate"));
    }
}
