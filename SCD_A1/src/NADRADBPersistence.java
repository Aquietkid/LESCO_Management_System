import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NADRADBPersistence {

    public static void writeToFile(String fileName, ArrayList<NADRARecord> nadraRecords) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (NADRARecord record : nadraRecords) {
                bw.write(record.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<NADRARecord> readFromFile(String fileName) {
        ArrayList<NADRARecord> nadraRecords = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length != 3) {
                    continue;
                }

                String CNIC = data[0];
                String issuanceDate = data[1];
                String expiryDate = data[2];

                NADRARecord record = new NADRARecord(CNIC, issuanceDate, expiryDate);
                nadraRecords.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return nadraRecords;
    }
}