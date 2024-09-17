import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String customersFileName = "./src/CustomersData.txt";
        final String tariffTaxFileName = "./src/TariffTaxInfo.txt";
        final String NADRADBFilename = "./src/NADRADB.txt";

        ArrayList<TariffTax> tariffs = TariffTax.readTariffTaxInfo(tariffTaxFileName);
        ArrayList<Customer> customers = Customer.readCustomersInfo(customersFileName);
        ArrayList<NADRARecord> nadraRecords = NADRADBPersistence.readFromFile(NADRADBFilename);

        UserWrapper myUser = new UserWrapper(null);

        Scanner input = new Scanner(System.in);
        while (true) {
            int loginStatus = Main.getLoginStatus(myUser);
            System.out.println(loginStatus);

            if (loginStatus == 1) {
                EmployeeMenu employeeMenu = new EmployeeMenu(myUser.getMyUser());
                employeeMenu.runMenu(input, customers, tariffs, nadraRecords);

            } else if (loginStatus == 2) {
                CustomerMenu customerMenu = new CustomerMenu();
                customerMenu.displayMenu();
            }

            System.out.println("Do you want to continue? (y/n)");
            Character choice = input.next().charAt(0);
            if (choice == 'n' || choice == 'N') {
                System.out.println("Thank you for using the LESCO system!");
                break;
            }
        }

        input.close();

        TariffTax.writeToFile(tariffTaxFileName, tariffs);
        Customer.writeToFile(customersFileName, customers);
        NADRADBPersistence.writeToFile(NADRADBFilename, nadraRecords);
    }

    public static int getLoginStatus(UserWrapper myUser) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        LoginMenu loginMenu = new LoginMenu();
        int loginStatus = loginMenu.login(username, password);
//        System.out.println(loginStatus);
        if(loginStatus == 1) {
            myUser.setMyUser(new Employee(username, password));
        }
        else myUser = null;
        return loginStatus;

    }
}