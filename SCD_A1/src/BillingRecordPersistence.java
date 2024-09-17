import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class BillingRecordPersistence {

    // Method to write a list of billing records to file
    public static void writeToFile(String fileName, ArrayList<BillingRecord> billingRecords) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (BillingRecord record : billingRecords) {
                bw.write(record.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read billing records from file
    public static ArrayList<BillingRecord> readFromFile(String fileName) {
        ArrayList<BillingRecord> billingRecords = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String customerID = data[0];
                String billingMonth = data[1];
                float currentMeterReadingRegular = Float.parseFloat(data[2]);
                float currentMeterReadingPeak = Float.parseFloat(data[3]);
                String readingEntryDate = data[4];
                float costOfElectricity = Float.parseFloat(data[5]);
                float salesTaxAmount = Float.parseFloat(data[6]);
                float fixedCharges = Float.parseFloat(data[7]);
                float totalBillingAmount = Float.parseFloat(data[8]);
                String dueDate = data[9];
                boolean billPaidStatus = (data[10].equalsIgnoreCase("true"));
                String billPaymentDate;
                try {
                    billPaymentDate = data[11];
                }
                catch (Exception e) {
                    billPaymentDate = "";
                }

                BillingRecord record = new BillingRecord(customerID, billingMonth, currentMeterReadingRegular, currentMeterReadingPeak,
                        readingEntryDate, costOfElectricity, salesTaxAmount, fixedCharges, totalBillingAmount, dueDate, billPaidStatus, billPaymentDate);

                billingRecords.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return billingRecords;
    }
}
