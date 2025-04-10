import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login loginSystem = new Login();

        while (true) {
            System.out.println("1. Admin Login");
            System.out.println("2. Teacher Login");
            System.out.println("3. Student Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    loginSystem.login("Admin");
                    break;
                case 2:
                    loginSystem.login("Teacher");
                    break;
                case 3:
                    loginSystem.login("Student");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}