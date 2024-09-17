import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CustomerMenu extends Menu {

    private DateTimeFormatter dateFormatter;

    public CustomerMenu(User customer) {
        this.message = """
                Customer Menu
                1. View Bills
                2. Estimate Upcoming Bill
                3. Update CNIC Expiry
                4. Exit
                """;
        this.myCustomer = (Customer) customer;
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
                viewBills(billingRecords);
                break;
            case 2:
                estimateUpcomingBills(tariffs);
                break;
            case 3:
                updateCNICExpiry(NADRARecords);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    public void viewBills(ArrayList<BillingRecord> billingRecords) {
        // TODO
        System.out.println("Viewing bills");
        for (BillingRecord br : billingRecords) {
            if (br.getCustomerID().equals(myCustomer.getUsername())) {
                System.out.println(br);
            }
        }
    }

    public void estimateUpcomingBills(ArrayList<TariffTax> tariffTaxes) {
        // TODO
        Scanner input = new Scanner(System.in);
        float currentRegularUnits = 0.0f;
        float currentPeakUnits = 0.0f;
        while (true) {
            try {
                System.out.println("Enter the current regular units reading: ");
                currentRegularUnits = input.nextFloat();
                if (currentRegularUnits > 0.0f) {
                    break;
                } else if (currentRegularUnits < myCustomer.getRegularUnitsConsumed()) {
                    System.out.println("Current reading cannot be less than previous reading!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Value must be a number! ");
            }
        }

        TariffTax myTariffTax = TariffTax.getTariffTax(tariffTaxes, myCustomer);

        if (myTariffTax.getPeakHourUnitPrice() != null) {
            while (true) {
                try {

                    System.out.println("Enter the current peak hour units reading: ");
                    currentPeakUnits = input.nextFloat();
                    if (currentPeakUnits > 0.0f) {
                        break;
                    } else if (currentPeakUnits < myCustomer.getPeakUnitsConsumed()) {
                        System.out.println("Current peak hour reading cannot be less than previous peak hour reading!");
                    } else System.out.println("Incorrect reading! Please enter again: ");
                } catch (NumberFormatException e) {
                    System.out.println("Value must be a number! ");
                }
            }
        }

        float costOfElectricity = (float) (currentRegularUnits * myTariffTax.getRegularUnitPrice()) + (float) ((myTariffTax.getPeakHourUnitPrice() != null) ? (currentPeakUnits * myTariffTax.getPeakHourUnitPrice()) : 0.0);

        float salesTaxAmount = (float) (costOfElectricity * (myTariffTax.getTaxPercentage() / 100));

        float fixedCharges = (float) myTariffTax.getFixedCharges();

        float totalBillingAmount = costOfElectricity + salesTaxAmount + fixedCharges;
        System.out.println("Total Billing Amount: " + totalBillingAmount);
    }

    public void updateCNICExpiry(ArrayList<NADRARecord> nadraRecords) {
        // TODO
        Scanner input = new Scanner(System.in);
        String CNIC = null;
        String currentExpiry = null;
        NADRARecord myNADRARecord = null;

        while (true) {
            System.out.println("Enter your CNIC: ");
            CNIC = input.nextLine();
            if (CNIC.length() != 13) {
                System.out.println("CNIC must be 13-digits long!");
            } else if (CNIC.contains("-")) {
                System.out.println("Please enter CNIC without dashes!");
            } else if (CNIC.equals(myCustomer.getCNIC())) {
                break;
            } else System.out.println("Your CNIC does not match the entered value. ");
        }

        for (NADRARecord nr : nadraRecords) {
            if (nr.getCNIC().equals(CNIC)) {
                currentExpiry = nr.getExpiryDate();
                myNADRARecord = nr;
                break;
            }
        }

        while (true) {
            System.out.println("Enter the new CNIC expiry date (DD/MM/YYYY): ");
            String expiryDate = input.nextLine().trim();

            try {
                // Parse the entered date
                LocalDate newExpiryDate = LocalDate.parse(expiryDate, dateFormatter);
                LocalDate currentExpiryDate = LocalDate.parse(currentExpiry, dateFormatter);

                // Check if the new expiry date is after the current expiry date
                if (newExpiryDate.isBefore(currentExpiryDate) || newExpiryDate.equals(currentExpiryDate)) {
                    System.out.println("New expiry date must be after the current expiry date (" + currentExpiryDate + ").");
                } else if (newExpiryDate.isBefore(LocalDate.now())) {
                    System.out.println("Expiry date cannot be in the past.");
                } else {
                    // Update the expiry date and exit the loop
                    myNADRARecord.setExpiryDate(expiryDate); // Assuming this setter exists
                    System.out.println("CNIC expiry date updated successfully.");
                    break;
                }

            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY.");
            }
        }
    }
}
