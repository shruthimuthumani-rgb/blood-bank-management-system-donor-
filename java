import java.sql.*;
import java.util.Scanner;

public class BloodBankManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to Database
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bloodbankdb",
                "root",
                "password"
            );

            Statement stmt = con.createStatement();

            int choice;

            do {
                System.out.println("\n--- Blood Bank Management System ---");
                System.out.println("1. Add Donor");
                System.out.println("2. View All Donors");
                System.out.println("3. Find Donor by Blood Group");
                System.out.println("4. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();

                switch (choice) {

                    case 1:
                        System.out.print("Enter Donor ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();

                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Blood Group: ");
                        String group = sc.nextLine();

                        System.out.print("Enter Phone: ");
                        String phone = sc.nextLine();

                        String insert =
                            "INSERT INTO donors VALUES (" +
                            id + ", '" + name + "', " +
                            age + ", '" + group + "', '" +
                            phone + "')";

                        stmt.executeUpdate(insert);

                        System.out.println("Donor Added Successfully");
                        break;

                    case 2:
                        ResultSet rs = stmt.executeQuery(
                            "SELECT * FROM donors"
                        );

                        System.out.println("\nDonor List:");

                        while (rs.next()) {
                            System.out.println(
                                rs.getInt("donor_id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getInt("age") + " | " +
                                rs.getString("blood_group") + " | " +
                                rs.getString("phone")
                            );
                        }
                        break;

                    case 3:
                        System.out.print("Enter Blood Group to Search: ");
                        sc.nextLine();
                        String searchGroup = sc.nextLine();

                        ResultSet match = stmt.executeQuery(
                            "SELECT * FROM donors WHERE blood_group = '" +
                            searchGroup + "'"
                        );

                        System.out.println("\nMatching Donors:");

                        while (match.next()) {
                            System.out.println(
                                match.getInt("donor_id") + " | " +
                                match.getString("name") + " | " +
                                match.getString("blood_group") + " | " +
                                match.getString("phone")
                            );
                        }
                        break;

                    case 4:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice");
                }

            } while (choice != 4);

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
