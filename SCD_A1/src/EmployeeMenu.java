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

}
