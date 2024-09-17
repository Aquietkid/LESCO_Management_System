import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeMenu extends Menu {

    private User myEmployee;

    public EmployeeMenu(User employee) {
        this.message = """
                Employee Menu
                1. Create Bill
                2. Modify Bills
                3. Add New Customer
                4. Update a Customer
                5. Record Bill Payment
                6. Update Tariffs and Taxes
                7. View Customer Bills
                8. View Customers with CNICs Expiring Soon
                9. View All Customers
                10. Change Password
                11. Exit
                """;
        this.myEmployee = employee;
    }

    @Override
    public void displayMenu() {
        System.out.println(this.message);
    }

    /**
     * Takes the list of customers and the scanner,
     * asks the user to enter a choice variable,
     * and executes the chosen menu function on the
     * basis of the choice variable in an infinite
     * loop. The menu can be exited using the choice
     * value corresponding to exit.
     */
    public void runMenu(Scanner input, ArrayList<Customer> customers, ArrayList<TariffTax> tariffs, ArrayList<NADRARecord> NADRARecords) {
        int choice;
        do {
            this.displayMenu();
            System.out.println("Enter your choice");
            choice = input.nextInt();
            if (choice > 11 || choice < 0) {
                System.out.println("Invalid choice!");
            }
            if (choice == 11) {
                return;
            } else this.executeMenuTask(choice, customers, tariffs, NADRARecords);
        } while (true);
    }

    public void executeMenuTask(int choice, ArrayList<Customer> customers, ArrayList<TariffTax> tariffs, ArrayList<NADRARecord> NADRARecords) {
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
                updateCustomerInfo(customers);
                break;
            case 5:
                recordBillPayment();
                break;
            case 6:
                updateTariffTaxInfo(tariffs);
                break;
            case 7:
                viewCustomerBills();
                break;
            case 8:
                viewCNICCustomers(customers, NADRARecords);
                break;
            case 9:
                viewAllCustomers(customers);
                break;
            case 10:
                changePassword();
                break;
            default:
                System.out.println("Incorrect choice!");
                break;
        }
    }

    private void viewAllCustomers(ArrayList<Customer> customers) {
        for (Customer customer : customers) {
            System.out.println(customer.toString());
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
            System.out.print("Enter Customer CNIC (13-digit, without dashes):");
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
            return;
        }

        System.out.println("Enter customer name: ");
        String customerName = input.nextLine();

        System.out.println("Enter customer address: ");
        String customerAddress = input.nextLine();

        System.out.println("Enter customer phone number: ");
        String phoneNumber = input.nextLine();

        System.out.println("Is the customer commercial? (Y/N): ");
        boolean isCommercial = getBooleanInput(input);

        System.out.println("Is the customer's meter 3-phase? (Y/N): ");
        boolean isThreePhase = getBooleanInput(input);

        String connectionDate = getConnectionDate(input);
        System.out.println("Enter connection date (DD-MM-YYYY): ");

        Customer newCustomer = new Customer(customerID, customerCNIC, customerName, customerAddress, phoneNumber, isCommercial, isThreePhase, connectionDate);
        customers.add(newCustomer);
        System.out.println("Customer added successfully!");
    }

    private boolean getBooleanInput(Scanner input) {
        boolean boolInput;
        while (true) {
            String commercial = input.nextLine();
            if (commercial.equals("Y") || commercial.equals("y")) {
                boolInput = true;
                return true;
            } else if (commercial.equals("N") || commercial.equals("n")) {
                boolInput = false;
                return false;
            } else {
                System.out.println("Incorrect choice!");
            }
        }
    }

    private String getConnectionDate(Scanner input) {
        System.out.println("Enter the connection date as follows: ");
        while (true) {
            int day;
            int month;
            int year;
            LocalDate now = LocalDate.now();
            System.out.println("Date should not be after " + now.toString());
            try {
                while (true) {
                    System.out.print("Enter the year (YYYY): ");
                    year = Integer.parseInt(input.nextLine());
                    if (year < 1900 || year > 2100) {
                        System.out.println("Invalid year. Please enter a value between 1900 and 2100.");
                        continue;
                    }
                    if (now.getYear() < year) { //Year is in the future
                        System.out.println("Connection date can not be in future!");
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
                    if ((year == now.getYear()) && month > now.getMonthValue()) { //Month is in the future
                        System.out.println("Connection date can not be in future!");
                    }
                    break;
                }

                while (true) {
                    System.out.print("Enter the day (DD): ");
                    day = Integer.parseInt(input.nextLine());
                    if (day < 1 || day > 31) {
                        System.out.println("Invalid day. Please enter a value between 1 and 31.");
                        continue;
                    }
                    if ((year == now.getYear()) && month == now.getMonthValue() && day > now.getDayOfMonth()) { //Day is in the future
                        System.out.println("Connection date can not be in future!");
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

    public void viewCustomerBills() {
    }

    public void viewCNICCustomers(ArrayList<Customer> customers, ArrayList<NADRARecord> nadraRecords) {
        if (customers != null) {
            LocalDate now = LocalDate.now();
            System.out.println("Displaying customers with CNICs expiring soon: ");
            for (Customer c : customers) {
                for (NADRARecord n : nadraRecords) {
                    if (n.getCNIC().equals(c.getCNIC())) {
                        //Check if date is within the next 30 days
                        LocalDate expiry = getExpiryDate(n);
                        if (!expiry.isBefore(now) && !expiry.isAfter(now.plusDays(30))) {
                            System.out.println(c);
                        }

                    }
                }
            }
        } else System.out.println("There are no customers! ");
        System.out.println("--------------------------");
    }

    private LocalDate getExpiryDate(NADRARecord n) {
        String expiryDate = n.getExpiryDate().trim();

        int day = Integer.parseInt(expiryDate.substring(0, 2).trim());
        int month = Integer.parseInt(expiryDate.substring(3, 5).trim());
        int year = Integer.parseInt(expiryDate.substring(6, 10).trim());

        return LocalDate.of(year, month, day);
    }

    public void changePassword() {
        Scanner input = new Scanner(System.in);
        String checkUsername;
        String checkPassword;

        //Enter current username
        System.out.print("Enter your username: ");
        while (true) {
            checkUsername = input.nextLine();
            if (checkUsername.equals(myEmployee.getUsername())) {
                break;
            } else System.out.print("Incorrect username! Enter again: ");
        }

        //Enter current password
        System.out.print("Enter your current password: ");
        while (true) {
            checkPassword = input.nextLine();
            if (checkPassword.equals(myEmployee.getPassword())) {
                break;
            } else System.out.print("Incorrect password! Enter again: ");
        }

        //Enter new password
        String newPassword;
        System.out.print("Enter new password: ");
        while (true) {
            newPassword = input.nextLine();
            if (newPassword.equals(myEmployee.getPassword())) {
                System.out.print("Old password is the same as new password! Please enter a different password :");
            } else if (newPassword.length() < 6) {
                System.out.print("Error! Password is too short! Enter again: ");
            } else break;
        }
        //Repeat new password
        String newPasswordRepeat;
        System.out.print("Enter new password again: ");
        while (true) {
            newPasswordRepeat = input.nextLine();
            if (newPasswordRepeat.equals(newPassword)) break;
            else System.out.print("Passwords do not match! Enter again: ");
        }
        // TODO Update file
        writeToFile(checkUsername, newPassword);

        //Display confirmation message
        System.out.println("Password updated successfully!");
    }

    public static void writeToFile(String _username, String _password) {
        ArrayList<Employee> employees = new ArrayList<>();
        final String fileName = "./src/EmployeesData.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0];
                String password;
                if (username.equals(_username)) {
                    password = _password;
                } else password = data[1];

                Employee employee = new Employee(username, password);
                employees.add(employee);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Employee e : employees) {
                bw.write(e.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void updateTariffTaxInfo(List<TariffTax> tariffs) {
        Scanner input = new Scanner(System.in);

        for (TariffTax tariffTax : tariffs) {
            System.out.println("Updating details for meter type: " + tariffTax.getMeterType() +
                    ", customer type: " + tariffTax.getCustomerType());

            String choice;
            boolean flag;
            while (true) {
                System.out.println("Do you want to update tariffs for this type of customers? (Y/N)");
                choice = input.nextLine();
                if (choice.charAt(0) == 'Y' || choice.charAt(0) == 'y') {
                    flag = true;
                    break;
                } else if (choice.charAt(0) == 'N' || choice.charAt(0) == 'n') {
                    flag = false;
                    break;
                } else System.out.println("Invalid choice! ");
            }
            if (!flag) continue;


            System.out.print("Enter new regular unit price (current: " + tariffTax.getRegularUnitPrice() + "): ");
            double regularUnitPrice = Double.parseDouble(input.nextLine());
            tariffTax.setRegularUnitPrice(regularUnitPrice);

            System.out.print("Enter new peak hour unit price (current: " + ((tariffTax.getPeakHourUnitPrice() == null) ? "NULL for 1-phase meter" : tariffTax.getPeakHourUnitPrice()) + "): ");
            Double peakHourUnitPrice;
            String peakHourPrice = input.nextLine();
            try { //Try catch in case
                peakHourUnitPrice = Double.parseDouble(peakHourPrice);
            } catch (Exception e) {
                peakHourUnitPrice = null;
            }
            tariffTax.setPeakHourUnitPrice(peakHourUnitPrice);

            System.out.print("Enter new tax percentage (current: " + tariffTax.getTaxPercentage() + "): ");
            double taxPercentage = Double.parseDouble(input.nextLine());
            tariffTax.setTaxPercentage(taxPercentage);

            System.out.print("Enter new fixed charges (current: " + tariffTax.getFixedCharges() + "): ");
            double fixedCharges = Double.parseDouble(input.nextLine());
            tariffTax.setFixedCharges(fixedCharges);

        }
        System.out.println("Tariff details updated!\n");
    }

    public void updateCustomerInfo(ArrayList<Customer> customers) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the ID of the customer you want to update (-1 to print all customers): ");
        String customerID = scanner.nextLine();
        if (customerID.equals("-1")) {
            this.viewAllCustomers(customers);
            updateCustomerInfo(customers);
            return;
        }
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                try {
                    System.out.println("Updating details for Customer ID: " + customer.getCustomerID());

                    // Update customer name
                    System.out.print("Enter new customer name (current: " + customer.getCustomerName() + "): ");
                    String customerName = scanner.nextLine();
                    if (!customerName.trim().isEmpty()) {
                        customer.setCustomerName(customerName);
                    }

                    // Update address
                    System.out.print("Enter new address (current: " + customer.getAddress() + "): ");
                    String address = scanner.nextLine();
                    if (!address.trim().isEmpty()) {
                        customer.setAddress(address);
                    }

                    // Update phone number
                    System.out.print("Enter new phone number (current: " + customer.getPhone() + "): ");
                    String phone = scanner.nextLine();
                    if (!phone.trim().isEmpty()) {
                        customer.setPhone(phone);
                    }

                    // Update commercial status
                    System.out.print("Is the customer commercial (true/false)? (current: " + customer.getIsCommercial() + "): ");
                    String isCommercial = scanner.nextLine();
                    if (!isCommercial.trim().isEmpty()) {
                        customer.setIsCommercial(Boolean.parseBoolean(isCommercial));
                    }

                    // Update phase type
                    System.out.print("Is the connection 3-phase (true/false)? (current: " + customer.getThreePhase() + "): ");
                    String isThreePhase = scanner.nextLine();
                    if (!isThreePhase.trim().isEmpty()) {
                        customer.setThreePhase(Boolean.parseBoolean(isThreePhase));
                    }

                    // Update regular units consumed
                    System.out.print("Enter new regular units consumed (current: " + customer.getRegularUnitsConsumed() + "): ");
                    String regularUnits = scanner.nextLine();
                    if (!regularUnits.trim().isEmpty()) {
                        customer.setRegularUnitsConsumed(Float.parseFloat(regularUnits));
                    }

                    // Update peak units consumed
                    System.out.print("Enter new peak units consumed (current: " + customer.getPeakUnitsConsumed() + "): ");
                    String peakUnits = scanner.nextLine();
                    if (!peakUnits.trim().isEmpty()) {
                        customer.setPeakUnitsConsumed(Float.parseFloat(peakUnits));
                    }

                    System.out.println("Customer details updated successfully!\n");

                } catch (NumberFormatException e) {
                    System.err.println("Invalid input. Please enter numbers for numeric fields.");
                } catch (Exception e) {
                    System.err.println("An error occurred while updating customer details: " + e.getMessage());
                } finally {
//                    scanner.close();
                    System.out.println("Customer details updated!\n");
                }
            }
        }
    }
}
