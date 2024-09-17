import java.util.ArrayList;

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

    static TariffTax getTariffTax(ArrayList<TariffTax> tariffTaxes, Customer myCustomer) {
        TariffTax myTariffTax;
        if (!myCustomer.getThreePhase() && !myCustomer.getIsCommercial()) { //1-phase domestic
            myTariffTax = tariffTaxes.get(0);
        } else if (!myCustomer.getThreePhase() && myCustomer.getIsCommercial()) { //1-phase commercial
            myTariffTax = tariffTaxes.get(1);
        } else if (myCustomer.getThreePhase() && !myCustomer.getIsCommercial()) { //3-phase domestic
            myTariffTax = tariffTaxes.get(2);
        } else { //3-phase commercial
            myTariffTax = tariffTaxes.get(3);
        }
        return myTariffTax;
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
}
