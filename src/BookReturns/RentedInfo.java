package BookReturns;

import java.sql.Timestamp;

public class RentedInfo {
    private String bookID;
    private Timestamp RentedDate;
    private String memberID;

    public RentedInfo(String bookID, String memberID, Timestamp rentedDate) {
        this.bookID = bookID;
        this.RentedDate = rentedDate;
        this.memberID = memberID;
    }

    public String getBookID() {
        return bookID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public Timestamp getRentedDate() {
        return RentedDate;
    }

    public void setRentedDate(Timestamp rentedDate) {
        RentedDate = rentedDate;
    }
}
