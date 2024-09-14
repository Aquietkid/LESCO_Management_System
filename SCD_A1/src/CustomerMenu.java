public class CustomerMenu extends Menu {

    public CustomerMenu() {
        this.message = """
                Customer Menu
                1. View Bills
                2. View Current Bill
                3. Estimate Upcoming Bill
                4. Update CNIC Expiry
                """;
    }

    @Override
    public void displayMenu() {
        System.out.println(this.message);
    }

}
