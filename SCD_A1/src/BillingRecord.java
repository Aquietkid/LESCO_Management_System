public class BillingRecord {
    private String customerID;
    private String billingMonth;
    private float currentMeterReadingRegular;
    private float currentMeterReadingPeak;
    private String readingEntryDate;
    private float costOfElectricity;
    private float salesTaxAmount;
    private float fixedCharges;
    private float totalBillingAmount;
    private String dueDate;
    private boolean billPaidStatus;
    private String billPaymentDate;

    public BillingRecord(String customerID, String billingMonth, float currentMeterReadingRegular, float currentMeterReadingPeak, String readingEntryDate, float costOfElectricity, float salesTaxAmount, float fixedCharges, float totalBillingAmount, String dueDate, boolean billPaidStatus, String billPaymentDate) {
        this.customerID = customerID;
        this.billingMonth = billingMonth;
        this.currentMeterReadingRegular = currentMeterReadingRegular;
        this.currentMeterReadingPeak = currentMeterReadingPeak;
        this.readingEntryDate = readingEntryDate;
        this.costOfElectricity = costOfElectricity;
        this.salesTaxAmount = salesTaxAmount;
        this.fixedCharges = fixedCharges;
        this.totalBillingAmount = totalBillingAmount;
        this.dueDate = dueDate;
        this.billPaidStatus = billPaidStatus;
        this.billPaymentDate = billPaymentDate;
    }

    public BillingRecord(String customerID, String billingMonth, float currentMeterReadingRegular, float currentMeterReadingPeak, String readingEntryDate, float costOfElectricity, float salesTaxAmount, float fixedCharges, float totalBillingAmount, String dueDate) {
        this.customerID = customerID;
        this.billingMonth = billingMonth;
        this.currentMeterReadingRegular = currentMeterReadingRegular;
        this.currentMeterReadingPeak = currentMeterReadingPeak;
        this.readingEntryDate = readingEntryDate;
        this.costOfElectricity = costOfElectricity;
        this.salesTaxAmount = salesTaxAmount;
        this.fixedCharges = fixedCharges;
        this.totalBillingAmount = totalBillingAmount;
        this.dueDate = dueDate;
        this.billPaidStatus = false;
        this.billPaymentDate = "";
    }

    public String toFileString() {
        return customerID + "," + billingMonth + "," + currentMeterReadingRegular + "," + currentMeterReadingPeak + "," + readingEntryDate + "," + costOfElectricity + "," + salesTaxAmount + "," + fixedCharges + "," + totalBillingAmount + "," + dueDate + "," + billPaidStatus + "," + billPaymentDate + "\n";
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerID + ", Billing Month: " + billingMonth + ", Regular Reading: " + currentMeterReadingRegular + ", Peak Reading: " + currentMeterReadingPeak + ", Reading Entry Date: " + readingEntryDate + ", Cost of Electricity: " + costOfElectricity + ", Sales Tax: " + salesTaxAmount + ", Fixed Charges: " + fixedCharges + ", Total Billing: " + totalBillingAmount + ", Due Date: " + dueDate + ", Status: " + billPaidStatus + ", Payment Date: " + billPaymentDate;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getBillingMonth() {
        return billingMonth;
    }

    public void setBillingMonth(String billingMonth) {
        this.billingMonth = billingMonth;
    }

    public float getCurrentMeterReadingRegular() {
        return currentMeterReadingRegular;
    }

    public void setCurrentMeterReadingRegular(float currentMeterReadingRegular) {
        this.currentMeterReadingRegular = currentMeterReadingRegular;
    }

    public float getCurrentMeterReadingPeak() {
        return currentMeterReadingPeak;
    }

    public void setCurrentMeterReadingPeak(float currentMeterReadingPeak) {
        this.currentMeterReadingPeak = currentMeterReadingPeak;
    }

    public String getReadingEntryDate() {
        return readingEntryDate;
    }

    public void setReadingEntryDate(String readingEntryDate) {
        this.readingEntryDate = readingEntryDate;
    }

    public float getCostOfElectricity() {
        return costOfElectricity;
    }

    public void setCostOfElectricity(float costOfElectricity) {
        this.costOfElectricity = costOfElectricity;
    }

    public float getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(float salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public float getFixedCharges() {
        return fixedCharges;
    }

    public void setFixedCharges(float fixedCharges) {
        this.fixedCharges = fixedCharges;
    }

    public float getTotalBillingAmount() {
        return totalBillingAmount;
    }

    public void setTotalBillingAmount(float totalBillingAmount) {
        this.totalBillingAmount = totalBillingAmount;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getBillPaidStatus() {
        return billPaidStatus;
    }

    public void setBillPaidStatus(boolean billPaidStatus) {
        this.billPaidStatus = billPaidStatus;
    }

    public String getBillPaymentDate() {
        return billPaymentDate;
    }

    public void setBillPaymentDate(String billPaymentDate) {
        this.billPaymentDate = billPaymentDate;
    }
}
