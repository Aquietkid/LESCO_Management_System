import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerPersistence {
    public static ArrayList<Customer> readFromFile(String fileName) {
        ArrayList<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String customerID = data[0];
                String CNIC = data[1];
                String customerName = data[2];
                String address = data[3];
                String phone = data[4];
                Boolean isCommercial = data[5].equals("C");
                Boolean isThreePhase = data[6].equals("3");
                String connectionDate = data[7];
                float regularUnits = Float.parseFloat(data[8]);
                float peakUnits = Float.parseFloat(data[9]);

                Customer customer = new Customer(customerID, CNIC, customerName, address, phone, isCommercial, isThreePhase, connectionDate, regularUnits, peakUnits);
                customers.add(customer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public static void writeToFile(String fileName, List<Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Customer customer : customers) {
                bw.write(customer.toFileString());
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
