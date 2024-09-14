import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class EmployeeMenu extends Menu {

    public EmployeeMenu() {
        this.message = """
                Employee Menu
                1. Create Bill
                2. Modify Bills
                3. Add New Customer
                4. Record Bill Payment
                5. Update Tariffs and Taxes
                6. View Customer Bills
                7. View Customers with CNICs Expiring Soon
                8. Change Password
                9. Exit
                """;
    }

    @Override
    public void displayMenu() {
        System.out.println(this.message);
    }

    public void executeMenuTask(int choice, ArrayList<Customer> customers) {
        switch (choice) {
            case 1:
                createBill();
                break;
            case 2:
                modifyBill();
                break;
            case 3:
                addNewCustomer(customers);
                break;
            case 4:
                recordBillPayment();
                break;
            case 5:
                updateTariffPayment();
                break;
            case 6:
                viewCustomerBills();
                break;
            case 7:
                viewCustomers();
                break;
            case 8:
                changePassword();
                break;
            case 9:
                exitMenu();
                break;
            default:
                System.out.println("Incorrect choice!");
                break;
        }
    }

    public void createBill() {
    }

    public void modifyBill() {
    }

    public void addNewCustomer(ArrayList<Customer> customers) {
        Scanner input = new Scanner(System.in);
        String customerID = "1000"; //Initial minimum value
        for (Customer c : customers) {
            if (Integer.parseInt(c.getCustomerID()) > Integer.parseInt(customerID)) customerID = c.getCustomerID();
            customerID = c.getCustomerID();
        }
        customerID = String.valueOf(Integer.parseInt(customerID) + 1);

        String customerCNIC;
        while (true) {
            System.out.println("Enter Customer CNIC (13-digit, without dashes):\n");
            customerCNIC = input.nextLine();
            if (customerCNIC.length() != 13) {
                System.out.println("Enter CNIC again!");
            } else {
                break;
            }
        }

        int CNICcount = 0;
        for (Customer customer : customers) {
            if (customer.getCNIC().equals(customerCNIC)) {
                CNICcount++;
            }
            if (CNICcount >= 3) {
                break;
            }
        }
        if (CNICcount == 3) {
            System.out.println("More than 3 meters can not be added for one CNIC! Aborting addition...");
        }

        System.out.println("Enter customer name: ");
        String customerName = input.nextLine();

        System.out.println("Enter customer address: ");
        String customerAddress = input.nextLine();

        System.out.println("Enter customer phone number: ");
        String phoneNumber = input.nextLine();

        System.out.println("Is the customer commercial? (Y/N): ");
        boolean isCommercial = getStringInput(input);

        System.out.println("Is the customer's meter 3-phase? (Y/N): ");
        boolean isThreePhase = getStringInput(input);

        String connectionDate = getConnectionDate(input);
        System.out.println("Enter connection date (DD-MM-YYYY): ");

        Customer newCustomer = new Customer(customerID, customerCNIC, customerName, customerAddress, phoneNumber, isCommercial, isThreePhase, connectionDate);
    }

    private boolean getStringInput(Scanner input) {
        boolean isCommercial;
        String commercial = input.nextLine();
        if (commercial.equals("Y") || commercial.equals("y")) {
            isCommercial = true;
            return true;
        } else if (commercial.equals("N") || commercial.equals("n")) {
            isCommercial = false;
            return true;
        } else {
            System.out.println("Incorrect choice!");
        }
        return false;
    }

    private String getConnectionDate(Scanner input) {
        while (true) {
            int day;
            int month;
            int year;
            try {
                while (true) {
                    System.out.print("Enter the day (DD): ");
                    day = Integer.parseInt(input.nextLine());
                    if (day < 1 || day > 31) {
                        System.out.println("Invalid day. Please enter a value between 1 and 31.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.print("Enter the month (MM): ");
                    month = Integer.parseInt(input.nextLine());
                    if (month < 1 || month > 12) {
                        System.out.println("Invalid month. Please enter a value between 1 and 12.");
                        continue;
                    }
                    break;
                }

                while (true) {
                    System.out.print("Enter the year (YYYY): ");
                    year = Integer.parseInt(input.nextLine());
                    if (year < 1900 || year > 2100) {
                        System.out.println("Invalid year. Please enter a value between 1900 and 2100.");
                        continue;
                    }
                    break;
                }

                return String.format("%02d-%02d-%04d", day, month, year);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numeric values.");
            }
        }
    }

    public void recordBillPayment() {
    }

    public void updateTariffPayment() {
    }

    public void viewCustomerBills() {
    }

    public void viewCustomers() {
    }

    public void changePassword() {
    }

    public void exitMenu() {
    }


}
