package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;

    }

    @Override
    public String toString() {
        //Convert 24hour LocalTime to 12hour with AM/PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        //Everything lines up evenly in neat columns because of the %-20 spacing.
        return String.format("%-20s | %-20s | %-20s | %-20s | %-20.2f",
                date, time.format(formatter), description, vendor, amount);
    }

}
