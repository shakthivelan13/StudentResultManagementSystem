import java.sql.*;
import java.util.Scanner;

public class AdminDashboard {
    public static void dashboard() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- Admin Dashboard ---");
            System.out.println("1. Add Teacher");
            System.out.println("2. Add Student");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addTeacher();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void addTeacher() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Teacher Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO users (name, email, password, role_id, department) VALUES (?, ?, ?, 2, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, department);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Teacher added successfully!");
            } else {
                System.out.println("Failed to add teacher.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    private static void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Registration Number: ");
        String regNumber = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO users (name, email, password, role_id, registration_number) VALUES (?, ?, ?, 3, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, regNumber);

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Student added successfully!");
            } else {
                System.out.println("Failed to add student.");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}