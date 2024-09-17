import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

class TariffTax {
    private final String meterType;
    private final String customerType;
    private double regularUnitPrice;
    private Double peakHourUnitPrice;  // Can be null for 1-phase meters
    private double taxPercentage;
    private double fixedCharges;

    public TariffTax(String meterType, String customerType, double regularUnitPrice, Double peakHourUnitPrice, double taxPercentage, double fixedCharges) {
        this.meterType = meterType;
        this.customerType = customerType;
        this.regularUnitPrice = regularUnitPrice;
        this.peakHourUnitPrice = peakHourUnitPrice;
        this.taxPercentage = taxPercentage;
        this.fixedCharges = fixedCharges;
    }

    public static void writeToFile(String tariffTaxFileName, List<TariffTax> tariffs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tariffTaxFileName))) {
            for (TariffTax tariffTax : tariffs) {
                bw.write(tariffTax.toFileString());
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public String getMeterType() {
        return meterType;
    }

    public double getRegularUnitPrice() {
        return regularUnitPrice;
    }

    public Double getPeakHourUnitPrice() {
        return peakHourUnitPrice;
    }

    public double getTaxPercentage() {
        return taxPercentage;
    }

    public double getFixedCharges() {
        return fixedCharges;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setRegularUnitPrice(double regularUnitPrice) {
        this.regularUnitPrice = regularUnitPrice;
    }

    public void setPeakHourUnitPrice(Double peakHourUnitPrice) {
        this.peakHourUnitPrice = peakHourUnitPrice;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public void setFixedCharges(double fixedCharges) {
        this.fixedCharges = fixedCharges;
    }

    @Override
    public String toString() {
        return "Meter Type: " + meterType +
                ", Customer Type: " + customerType +
                ", Regular Unit Price: " + regularUnitPrice +
                ", Peak Hour Unit Price: " + (peakHourUnitPrice != null ? peakHourUnitPrice : "N/A") +
                ", Tax Percentage: " + taxPercentage +
                "%, Fixed Charges: " + fixedCharges;
    }

    public String toFileString() {
        return meterType + "," + regularUnitPrice + "," + ((peakHourUnitPrice != null) ? peakHourUnitPrice : "") + "," + taxPercentage + "," + fixedCharges + "\n";
    }

    public static ArrayList<TariffTax> readTariffTaxInfo(String fileName) {
        ArrayList<TariffTax> tariffList = new ArrayList<>();

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
