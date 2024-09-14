/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Date;

/**
 * @author Ali
 */
public class Customer {

    protected String customerID; //4-digit number
    protected String CNIC; //13-digit without dashes
    protected String customerName;
    protected String address;
    protected String phone;
    protected Boolean isCommercial; //true for commercial, false for domestic 
    protected Boolean isThreePhase; //true for 3-phase, false for 1-phase
    protected Date connectionDate;
    protected float regularUnitsConsumed;
    protected float peakUnitsConsumed;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsCommercial() {
        return isCommercial;
    }

    public void setIsCommercial(Boolean isCommercial) {
        this.isCommercial = isCommercial;
    }

    public Date getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(Date connectionDate) {
        this.connectionDate = connectionDate;
    }

    public float getRegularUnitsConsumed() {
        return regularUnitsConsumed;
    }

    public void setRegularUnitsConsumed(float regularUnitsConsumed) {
        this.regularUnitsConsumed = regularUnitsConsumed;
    }

    public float getPeakUnitsConsumed() {
        return peakUnitsConsumed;
    }

    public void setPeakUnitsConsumed(float peakUnitsConsumed) {
        this.peakUnitsConsumed = peakUnitsConsumed;
    }

}