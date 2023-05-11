package Database;

import DisplayBook.RenderedBook;
import DisplayMember.RenderedMember;

import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class DatabaseHandler {
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "";
    public static final String MySQL_URL = "jdbc:mysql://localhost:3306/library_management";

    /**
     * Connect to Database.Database;
     */
    public static Connection connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(MySQL_URL, USER_NAME, PASSWORD);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Close connection to MYSQL database.
     *
     * @param connection Connection variable
     */
    private static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Close the PreparedStatement ps.
     *
     * @param ps PreparedStatement needed to close
     */
    private static void close(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean addBook(String bookId, String bookName, String bookAuthor, String bookPublisher, String bookIntcode, String bookAvailable) {
        String query = "INSERT INTO book (id, title, author, publisher, intcode, available) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = connectToDatabase();;
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, bookId);
            statement.setString(2, bookName);
            statement.setString(3, bookAuthor);
            statement.setString(4, bookPublisher);
            statement.setString(5, bookIntcode);
            statement.setInt(6, Integer.parseInt(bookAvailable));

            int rowsAffected = statement.executeUpdate();
            close(statement);
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(conn);
        }
    }

    public static ArrayList searchAllBooks() {
        ArrayList bookList = new ArrayList<>();

        String query = "SELECT * FROM book";

        Connection conn = connectToDatabase();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    String publisher = resultSet.getString("publisher");
                    String intcode = resultSet.getString("intcode");
                    Integer isAvail = resultSet.getInt("available");

                    RenderedBook book = new RenderedBook(id, title, author, publisher, intcode, isAvail);
                    bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
        }

        close(conn);
        return bookList;
    }

        public static ArrayList<RenderedMember> searchAllMembers() {
        ArrayList<RenderedMember> members = new ArrayList<>();
        try {
            Connection conn = connectToDatabase();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Member");

            while (rs.next()) {
                String name = rs.getString("Name");
                int memberId = rs.getInt("MemberId");
                String mobile = rs.getString("Mobile");
                String email = rs.getString("Email");

                RenderedMember member = new RenderedMember(name, memberId, mobile, email);
                members.add(member);
            }
            close(conn);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return members;
    }

    public static boolean deleteMember(String memberId) {
        String deleteQuery = "DELETE FROM member WHERE memberId = ?";
        try {
            Connection conn = connectToDatabase();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            stmt.setString(1, memberId);
            int rowsAffected = stmt.executeUpdate();
            close(stmt);
            close(conn);
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean deleteBook(String bookId) {
        String deleteQuery = "DELETE FROM book WHERE id = ?";
        try {
            Connection conn = connectToDatabase();
            PreparedStatement stmt = conn.prepareStatement(deleteQuery);
            stmt.setString(1, bookId);
            int rowsAffected = stmt.executeUpdate();
            close(conn);
            close(stmt);
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Boolean editMember(RenderedMember member) {
        // Update the member in the database
        try {
            Connection conn = connectToDatabase();
            PreparedStatement stmt = conn.prepareStatement("UPDATE member SET name=?, mobile=?, email=? WHERE memberid=?");
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getMobile());
            stmt.setString(3, member.getEmail());
            stmt.setInt(4, Integer.valueOf(member.getMemberId()));
            int rowsAffected = stmt.executeUpdate();
            close(conn);
            close(stmt);
            if (rowsAffected > 0) {
                // If the update was successful, update the member in the table as well
                return Boolean.TRUE;
            } else {
                // Handle update failure
                return Boolean.FALSE;
            }
        } catch (SQLException ex) {
            // Handle database error
            ex.printStackTrace();
        }
        return Boolean.TRUE;
    }

    public static Boolean editBook(RenderedBook book) {
        // Update the member in the database
        try {
            Connection conn = connectToDatabase();
            PreparedStatement stmt = conn.prepareStatement("UPDATE book SET title=?, author=?, publisher=?, intcode=?, available = ? WHERE id=?");
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getPublisher());
            stmt.setString(4, book.getIntCode());
            stmt.setInt(5, book.getAvailable());
            stmt.setString(6, book.getBookId());
            int rowsAffected = stmt.executeUpdate();
            close(stmt);
            close(conn);
            if (rowsAffected > 0) {
                // If the update was successful, update the member in the table as well
                return Boolean.TRUE;
            } else {
                // Handle update failure
                return Boolean.FALSE;
            }
        } catch (SQLException ex) {
            // Handle database error
            ex.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public static Boolean pushNewMember(String name, String mobile, String email) {
        // create a new member and add it to the database
        try {
            Connection conn = connectToDatabase();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Member (Name, Mobile, Email) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setString(2, mobile);
            stmt.setString(3, email);
            stmt.executeUpdate();

            // retrieve the generated ID
            ResultSet rs = stmt.getGeneratedKeys();
            int id = -1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            close(stmt);
            close(conn);

            // create a new RenderedMember object and add it to the list
            RenderedMember newMember = new RenderedMember(name, id, mobile, email);
            return Boolean.TRUE;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return Boolean.FALSE;
        }
    }

    public static boolean isValidEmail(String email) {
        // Regular expression for validating an email address
        String pattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(pattern);

        return regex.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression for validating a mobile phone number
        String pattern = "^\\+?[1-9]\\d{1,14}$";
        Pattern regex = Pattern.compile(pattern);

        return regex.matcher(phoneNumber).matches();
    }

    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        // Check if the name contains only letters and spaces
        return name.matches("[a-zA-Z ]+");
    }

    public static boolean isValidBookId(String bookId) {
        // Check if the book ID has the format B + 3 digits
        return bookId != null && bookId.matches("^B\\d{3}$");
    }

    public static boolean isValidBookAvailable(String book) {
        // Check if the book is available (represented as a number in string type)
        return book != null && !book.isEmpty() && book.matches("\\d+");
    }
}