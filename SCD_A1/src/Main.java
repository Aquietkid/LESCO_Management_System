import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        List<TariffTax> tariffs = readTariffTaxes();
        System.out.println(tariffs);

        int loginStatus = Main.getLoginStatus();
        System.out.println(loginStatus);
        if (loginStatus == 1) {
            System.out.println("In emp menu");
            EmployeeMenu employeeMenu = new EmployeeMenu();
            employeeMenu.displayMenu();
        } else if (loginStatus == 2) {
            System.out.println("In cust menu");
            CustomerMenu customerMenu = new CustomerMenu();
            customerMenu.displayMenu();
        }

    }

    public static int getLoginStatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        LoginMenu loginMenu = new LoginMenu();
        int loginStatus = loginMenu.login(username, password);
        System.out.println(loginStatus);
        return loginStatus;

    }

    public static List<TariffTax> readTariffTaxes() {
        TariffTaxInfo tariffTaxInfo = new TariffTaxInfo();
        String fileName = "./src/TariffTaxInfo.txt";
        List<TariffTax> tariffTaxes = tariffTaxInfo.readTariffTaxInfo(fileName);
        // Print the loaded tariff tax info
        for (TariffTax tariffTax : tariffTaxes) {
            System.out.println(tariffTax);
        }
        return tariffTaxes;
    }
}