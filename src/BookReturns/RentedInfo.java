package BookReturns;

import java.sql.Date;

public class RentedInfo {
    private String bookID;
    private Date RentedDate;
    private String memberID;
    private Date ReturnedDate;

    public RentedInfo(String bookID, String memberID, Date rentedDate, Date returnedDate) {
        this.bookID = bookID;
        this.RentedDate = rentedDate;
        this.memberID = memberID;
        this.ReturnedDate = returnedDate;
    }

    public Date getReturnedDate() {
        return ReturnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        ReturnedDate = returnedDate;
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

    public Date getRentedDate() {
        return RentedDate;
    }

    public void setRentedDate(Date rentedDate) {
        RentedDate = rentedDate;
    }
}
