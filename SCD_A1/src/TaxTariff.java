//import java.io.*;
//
//public class TaxTariff {
//
//
//    public void readFile(String filePath, String[][] values) {
//        FileReader fr = null;
//        BufferedReader br = null;
//        try {
//            fr = new FileReader(filePath);
//            br = new BufferedReader(fr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        } finally {
//            try {
//                if (br != null) {
//                    br.close();
//                }
//                if (fr != null) {
//                    fr.close();
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//}


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Class to represent tariff and tax information for each meter type
class TariffTax {
    private final String meterType;
    private final String customerType;
    private final double regularUnitPrice;
    private final Double peakHourUnitPrice;  // Can be null for 1-phase meters
    private final double taxPercentage;
    private final double fixedCharges;

    public TariffTax(String meterType, String customerType, double regularUnitPrice, Double peakHourUnitPrice, double taxPercentage, double fixedCharges) {
        this.meterType = meterType;
        this.customerType = customerType;
        this.regularUnitPrice = regularUnitPrice;
        this.peakHourUnitPrice = peakHourUnitPrice;
        this.taxPercentage = taxPercentage;
        this.fixedCharges = fixedCharges;
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

    @Override
    public String toString() {
        return "Meter Type: " + meterType +
                ", Customer Type: " + customerType +
                ", Regular Unit Price: " + regularUnitPrice +
                ", Peak Hour Unit Price: " + (peakHourUnitPrice != null ? peakHourUnitPrice : "N/A") +
                ", Tax Percentage: " + taxPercentage +
                "%, Fixed Charges: " + fixedCharges;
    }
}

