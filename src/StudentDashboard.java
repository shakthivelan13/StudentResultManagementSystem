import java.sql.*;

public class StudentDashboard {
    public static void dashboard(int studentId) {
        System.out.println("\n--- Student Dashboard ---");
        viewResults(studentId);
    }

    private static void viewResults(int studentId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT s.subject_name, m.marks FROM marks m JOIN subjects s ON m.subject_id = s.subject_id WHERE m.student_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();

            System.out.println("Your Results:");
            while (rs.next()) {
                String subject = rs.getString("subject_name");
                int marks = rs.getInt("marks");
                System.out.println(subject + ": " + marks);
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}