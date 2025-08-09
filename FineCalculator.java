import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {
    public double calculateFine(LocalDate borrowDate, LocalDate returnDate) {
        long daysLate = ChronoUnit.DAYS.between(borrowDate.plusDays(14), returnDate);
        return daysLate > 0 ? daysLate * 5 : 0; // ₹5 per day late
    }
}
