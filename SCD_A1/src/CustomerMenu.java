import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMenu extends Menu {

    public CustomerMenu(User customer) {
        this.message = """
                Customer Menu
                1. View Bills
                2. Estimate Upcoming Bill
                3. Update CNIC Expiry
                """;
        this.myCustomer = customer;
    }

    private User myCustomer;

    @Override
    public void displayMenu() {
        System.out.println(this.message);
    }

    public void runMenu(Scanner input, ArrayList<TariffTax> tariffs, ArrayList<NADRARecord> NADRARecords, ArrayList<BillingRecord> billingRecords) {
        int choice;
        do {
            this.displayMenu();
            System.out.println("Enter your choice");
            choice = input.nextInt();
            if (choice > 4 || choice < 0) {
                System.out.println("Invalid choice!");
            } else if (choice == 4) {
                return;
            } else this.executeMenuTask(choice, tariffs, NADRARecords, billingRecords);
        } while (true);
    }

    public void executeMenuTask(int choice, final ArrayList<TariffTax> tariffs, ArrayList<NADRARecord> NADRARecords, ArrayList<BillingRecord> billingRecords) {
        switch (choice) {
            case 1:
                viewBills();
                break;
            case 2:
                estimateUpcomingBills();
                break;
            case 3:
                updateCNICExpiry();
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public void viewBills(ArrayList<BillingRecord> billingRecords) {
        // TODO
        System.out.println("Viewing bills");
        for(BillingRecord br : billingRecords) {
            if(br.getCustomerID().equals(myCustomer.getUsername())) {
                System.out.println(br);
            }
        }
    }

    public void estimateUpcomingBills() {
        // TODO
    }

    public void updateCNICExpiry() {
        // TODO
    }

}
