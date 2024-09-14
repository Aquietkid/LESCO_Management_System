import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<TariffTax> tariffs = readTariffTaxes();
        System.out.println(tariffs);

        int loginStatus = Main.getLoginStatus();
        System.out.println(loginStatus);

        Scanner input = new Scanner(System.in);


        if (loginStatus == 1) {
            System.out.println("In emp menu");
            EmployeeMenu employeeMenu = new EmployeeMenu();
            employeeMenu.displayMenu();
            int choice;
            do {
                System.out.println("Enter your choice");
                choice = input.nextInt();
                if(choice > 9 || choice < 0) {
                    System.out.println("Invalid choice!");
                }
                else break;
            } while (true);
            employeeMenu.executeMenuTask(choice, customers);
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