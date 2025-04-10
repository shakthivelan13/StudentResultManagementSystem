import java.sql.*;
import java.util.Scanner;

public class TeacherDashboard {
    public static void dashboard(int teacherId) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Teacher Dashboard ---");
            System.out.println("1. Enter Marks for Student");
            System.out.println("2. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    enterMarks();
                    break;
                case 2:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void enterMarks() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student Registration Number: ");
        String regNumber = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String studentQuery = "SELECT user_id FROM users WHERE registration_number = ?";
            PreparedStatement studentStmt = conn.prepareStatement(studentQuery);
            studentStmt.setString(1, regNumber);
            ResultSet studentRs = studentStmt.executeQuery();

            if (studentRs.next()) {
                int studentId = studentRs.getInt("user_id");

                String subjectsQuery = "SELECT subject_id, subject_name FROM subjects";
                Statement subjectStmt = conn.createStatement();
                ResultSet subjectsRs = subjectStmt.executeQuery(subjectsQuery);

                while (subjectsRs.next()) {
                    int subjectId = subjectsRs.getInt("subject_id");
                    String subjectName = subjectsRs.getString("subject_name");

                    System.out.print("Enter marks for " + subjectName + ": ");
                    int marks = scanner.nextInt();

                    String insertMarksQuery = "INSERT INTO marks (student_id, subject_id, marks) VALUES (?, ?, ?)";
                    PreparedStatement marksStmt = conn.prepareStatement(insertMarksQuery);
                    marksStmt.setInt(1, studentId);
                    marksStmt.setInt(2, subjectId);
                    marksStmt.setInt(3, marks);
                    marksStmt.executeUpdate();
                }

                System.out.println("Marks entered successfully!");

            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}