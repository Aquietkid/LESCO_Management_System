import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TariffTaxInfo {

    public List<TariffTax> readTariffTaxInfo(String fileName) {
        List<TariffTax> tariffList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                String meterType = data[0];
                String customerType = (lineNum % 2 == 0) ? "Domestic" : "Commercial";
                double regularUnitPrice = Double.parseDouble(data[1]);
                Double peakHourUnitPrice = (data[2].isEmpty()) ? null : Double.parseDouble(data[2]);
                double taxPercentage = Double.parseDouble(data[3]);
                double fixedCharges = Double.parseDouble(data[4]);

                TariffTax tariffTax = new TariffTax(meterType, customerType, regularUnitPrice, peakHourUnitPrice, taxPercentage, fixedCharges);
                tariffList.add(tariffTax);
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tariffList;
    }
}
