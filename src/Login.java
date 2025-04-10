import java.sql.*;
import java.util.Scanner;

public class Login {
    Scanner scanner = new Scanner(System.in);

    public void login(String role) {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM users WHERE email = ? AND password = ? AND role_id = (SELECT role_id FROM roles WHERE role_name = ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, role);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println(role + " login successful!");
                int userId = rs.getInt("user_id");
                switch (role) {
                    case "Admin":
                        AdminDashboard.dashboard();
                        break;
                    case "Teacher":
                        TeacherDashboard.dashboard(userId);
                        break;
                    case "Student":
                        StudentDashboard.dashboard(userId);
                        break;
                }
            } else {
                System.out.println("Invalid credentials for " + role + ".");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}