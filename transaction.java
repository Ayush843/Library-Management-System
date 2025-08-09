import java.util.Date;

public class Transaction {
    private int transactionId;
    private int bookId;
    private int memberId;
    private Date borrowDate;
    private Date returnDate;
    private double fine;

    public Transaction(int transactionId, int bookId, int memberId, Date borrowDate, Date returnDate, double fine) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.fine = fine;
    }

    public int getTransactionId() { return transactionId; }
    public int getBookId() { return bookId; }
    public int getMemberId() { return memberId; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getReturnDate() { return returnDate; }
    public double getFine() { return fine; }
}
