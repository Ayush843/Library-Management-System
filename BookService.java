import java.sql.*;

public class BookService {
    public void addBook(String title, String author, String category) {
        String sql = "INSERT INTO books (title, author, category, available) VALUES (?, ?, ?, true)";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, category);
            stmt.executeUpdate();
            System.out.println("✅ Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listBooks() {
        String sql = "SELECT * FROM books";
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Books List ---");
            while (rs.next()) {
                System.out.printf("%d | %s | %s | %s | %s\n",
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getBoolean("available") ? "Available" : "Not Available");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
