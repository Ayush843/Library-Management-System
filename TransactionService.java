import java.sql.*;
import java.time.LocalDate;

public class TransactionService {
    private FineCalculator fineCalculator = new FineCalculator();

    public void borrowBook(int bookId, int memberId) {
        String checkSql = "SELECT available FROM books WHERE book_id = ?";
        String insertSql = "INSERT INTO transactions (book_id, member_id, borrow_date, fine) VALUES (?, ?, ?, 0)";
        String updateBookSql = "UPDATE books SET available = false WHERE book_id = ?";

        try (Connection conn = DatabaseHelper.getConnection()) {
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getBoolean("available")) {
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, bookId);
                insertStmt.setInt(2, memberId);
                insertStmt.setDate(3, Date.valueOf(LocalDate.now()));
                insertStmt.executeUpdate();

                PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql);
                updateBookStmt.setInt(1, bookId);
                updateBookStmt.executeUpdate();

                System.out.println("✅ Book borrowed successfully!");
            } else {
                System.out.println("❌ Book not available!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int transactionId) {
        String getSql = "SELECT * FROM transactions WHERE transaction_id = ?";
        String updateSql = "UPDATE transactions SET return_date = ?, fine = ? WHERE transaction_id = ?";
        String updateBookSql = "UPDATE books SET available = true WHERE book_id = ?";

        try (Connection conn = DatabaseHelper.getConnection()) {
            PreparedStatement getStmt = conn.prepareStatement(getSql);
            getStmt.setInt(1, transactionId);
            ResultSet rs = getStmt.executeQuery();

            if (rs.next()) {
                int bookId = rs.getInt("book_id");
                LocalDate borrowDate = rs.getDate("borrow_date").toLocalDate();
                LocalDate returnDate = LocalDate.now();
                double fine = fineCalculator.calculateFine(borrowDate, returnDate);

                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setDate(1, Date.valueOf(returnDate));
                updateStmt.setDouble(2, fine);
                updateStmt.setInt(3, transactionId);
                updateStmt.executeUpdate();

                PreparedStatement updateBookStmt = conn.prepareStatement(updateBookSql);
                updateBookStmt.setInt(1, bookId);
                updateBookStmt.executeUpdate();

                System.out.println("✅ Book returned. Fine: ₹" + fine);
            } else {
                System.out.println("❌ Transaction not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
