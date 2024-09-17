import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String customersFileName = "./src/CustomersData.txt";
        final String tariffTaxFileName = "./src/TariffTaxInfo.txt";
        final String NADRADBFilename = "./src/NADRADB.txt";
        final String billingRecordsFileName = "./src/BillingInfo.txt";

        ArrayList<TariffTax> tariffs = TariffTaxPersistence.readFromFile(tariffTaxFileName);
        ArrayList<Customer> customers = CustomerPersistence.readFromFile(customersFileName);
        ArrayList<NADRARecord> nadraRecords = NADRADBPersistence.readFromFile(NADRADBFilename);
        ArrayList<BillingRecord> billingRecords = BillingRecordPersistence.readFromFile(billingRecordsFileName);

        System.out.println(billingRecords);

        UserWrapper myUser = new UserWrapper(null);

        Scanner input = new Scanner(System.in);
        while (true) {
            int loginStatus = myUser.getLoginStatus();
            System.out.println(loginStatus);

            if (loginStatus == 1) {
                EmployeeMenu employeeMenu = new EmployeeMenu(myUser.getMyUser());
                employeeMenu.runMenu(input, customers, tariffs, nadraRecords, billingRecords);

            } else if (loginStatus == 2) {
                CustomerMenu customerMenu = new CustomerMenu(myUser.getMyUser());
                customerMenu.displayMenu();
            }
            else {
                System.out.println("Invalid login details! Please enter again: ");
                continue;
            }

            while (true) {
                System.out.println("Do you want to continue? (y/n)");
                char choice = input.next().charAt(0);
                if (choice == 'n' || choice == 'N') {
                    input.close();
                    TariffTaxPersistence.writeToFile(tariffTaxFileName, tariffs);
                    CustomerPersistence.writeToFile(customersFileName, customers);
                    NADRADBPersistence.writeToFile(NADRADBFilename, nadraRecords);
                    BillingRecordPersistence.writeToFile(billingRecordsFileName, billingRecords);
                    System.out.println("All files updated. \nThank you for using the LESCO system!");
                    return;
                } else if (choice != 'y' && choice != 'Y') {
                    System.out.println("Invalid choice!");
                }
            }
        }

    }

}