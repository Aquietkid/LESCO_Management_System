/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ali
 */
public class Customer implements User {

    protected String customerID; //4-digit number
    protected String CNIC; //13-digit without dashes
    protected String customerName;
    protected String address;
    protected String phone;
    protected Boolean isCommercial; //true for commercial, false for domestic 
    protected Boolean isThreePhase; //true for 3-phase, false for 1-phase
    private String connectionDate;
    protected float regularUnitsConsumed;
    protected float peakUnitsConsumed;

    public Customer(String customerID, String CNIC, String customerName, String address, String phone, Boolean isCommercial, Boolean isThreePhase, String connectionDate) {
        this.customerID = customerID;
        this.CNIC = CNIC;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.isCommercial = isCommercial;
        this.isThreePhase = isThreePhase;
        this.connectionDate = connectionDate;
        this.regularUnitsConsumed = 0;
        this.peakUnitsConsumed = 0;
    }

    public Customer(String customerID, String CNIC, String customerName, String address, String phone, Boolean isCommercial, Boolean isThreePhase, String connectionDate, float regularUnitsConsumed, float peakUnitsConsumed) {
        this(customerID, CNIC, customerName, address, phone, isCommercial, isThreePhase, connectionDate);
        this.regularUnitsConsumed = regularUnitsConsumed;
        this.peakUnitsConsumed = peakUnitsConsumed;
    }

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

    public Boolean getThreePhase() {
        return isThreePhase;
    }

    public void setThreePhase(Boolean threePhase) {
        isThreePhase = threePhase;
    }

    public String getConnectionDate() {
        return connectionDate;
    }

    public void setConnectionDate(String connectionDate) {
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

    @Override
    public String toString() {
        return this.getCustomerID() + " " + this.getCNIC() + " " + this.getCustomerName() + " " + this.getAddress() + " " + this.getPhone() + " " + ((this.getIsCommercial()) ? "Commercial" : "Domestic") + " " + ((this.getThreePhase()) ? "3-phase" : "1-phase") + " " + this.getConnectionDate() + " " + this.getRegularUnitsConsumed() + " " + this.getPeakUnitsConsumed();
    }

    public String toFileString() {
        return this.getCustomerID() + "," + this.getCNIC() + "," + this.getCustomerName() + "," + this.getAddress() + "," + this.getPhone() + "," + ((this.getIsCommercial()) ? "C" : "D") + "," + ((this.getThreePhase()) ? "3" : "1") + "," + this.getConnectionDate() + "," + this.getRegularUnitsConsumed() + "," + this.getPeakUnitsConsumed() + "\n";
    }

    public static ArrayList<Customer> readCustomersInfo(String fileName) {
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

    @Override
    public String getUsername() {
        return this.getCustomerID();
    }

    @Override
    public String getPassword() {
        return this.getCNIC();
    }
}