public class NADRARecord {
    private String CNIC;
    private String issuanceDate;  // Format: DD/MM/YYYY
    private String expiryDate;    // Format: DD/MM/YYYY

    public NADRARecord(String CNIC, String issuanceDate, String expiryDate) {
        this.CNIC = CNIC;
        this.issuanceDate = issuanceDate;
        this.expiryDate = expiryDate;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(String issuanceDate) {
        this.issuanceDate = issuanceDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return CNIC + "," + issuanceDate + "," + expiryDate;
    }
}
