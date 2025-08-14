import java.sql.*;
import java.util.Scanner;

public class EmployeeTaskManagement {
    private static final String URL = "jdbc:mysql://localhost:3306/ems";
    private static final String USER = "root";
    private static final String PASS = "yourpassword";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to DB");

            while (true) {
                System.out.println("\n1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Assign Task");
                System.out.println("4. View Tasks");
                System.out.println("5. Exit");
                System.out.print("Choose: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter email: ");
                        String email = sc.nextLine();
                        PreparedStatement ps = con.prepareStatement(
                                "INSERT INTO employees (name, email) VALUES (?, ?)");
                        ps.setString(1, name);
                        ps.setString(2, email);
                        ps.executeUpdate();
                        System.out.println("Employee Added!");
                        break;
                    case 2:
                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM employees");
                        while (rs.next()) {
                            System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " - " + rs.getString(3));
                        }
                        break;
                    case 3:
                        System.out.print("Enter employee ID: ");
                        int empId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter task description: ");
                        String task = sc.nextLine();
                        PreparedStatement ps2 = con.prepareStatement(
                                "INSERT INTO tasks (emp_id, task_desc, status) VALUES (?, ?, 'Pending')");
                        ps2.setInt(1, empId);
                        ps2.setString(2, task);
                        ps2.executeUpdate();
                        System.out.println("Task Assigned!");
                        break;
                    case 4:
                        ResultSet rs2 = con.createStatement().executeQuery(
                                "SELECT * FROM tasks");
                        while (rs2.next()) {
                            System.out.println(rs2.getInt(1) + " - EmpID: " + rs2.getInt(2) +
                                    " - " + rs2.getString(3) + " - " + rs2.getString(4));
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
