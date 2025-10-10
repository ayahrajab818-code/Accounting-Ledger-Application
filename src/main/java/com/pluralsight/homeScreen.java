package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class homeScreen {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    public static void main(String[] args) {
        String mainMenu = """
                What do you want to do?
                D) Add Deposit
                P) Make Payment (Debit)
                L) Ledger
                X) Exit
                """;
        while (true) {
            String command = ConsoleHelper.promptForString("Enter your command (D, P, L, X)").toUpperCase();
            switch (command) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("INVALID COMMAND! Please select a valid option.");
                    break;
            }
        }
    }
    private static void addDeposit() {
        System.out.println("Add Deposit");
        LocalDate date = ConsoleHelper.promptForDate("Enter date (YYYY-MM-DD)");
        LocalTime time = ConsoleHelper.promptForTime("Enter time (HH:MM:SS)");
       String description = ConsoleHelper.promptForString("Enter description");
       String vendor = ConsoleHelper.promptForString("Enter vendor");
       double amount = ConsoleHelper.promptForDouble("Enter deposit amount");

        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);

    }
    private static void makePayment() {
        LocalDate date = ConsoleHelper.promptForDate("Enter date (YYYY-MM-DD)");
        LocalTime time = ConsoleHelper.promptForTime("Enter time (HH:MM:SS)");
        String vendor = ConsoleHelper.promptForString("Enter vendor");
        String description = ConsoleHelper.promptForString("Enter description");
        double amount = ConsoleHelper.promptForDouble("Enter payment amount");
        Transaction tx = new Transaction(date, time, vendor, Math.abs(amount), description);
    }

    private static void displayLedger() {
        String ledgerMenu = """
        A) All Entries
        D) Deposits Only
        P) Payments Only
        R) Reports
        H) Home
        """;
        while (true) {
            String command = ConsoleHelper.promptForString("Enter your command (A, D, P, R, H)").toUpperCase();
            switch (command) {
                case "A":
                    displayAllEntries();
                    break;
                case "D":
                    displayDepositsOnly();
                    break;
                case "P":
                    displayPaymentsOnly();
                    break;
                case "R":
                    showReports();
                    break;
                case "H":
                    return;
                default:
                    System.out.println("INVALID COMMAND! Please select a valid option");
                    break;
            }
        }
    }

    private static void displayAllEntries() {
    }
    private static void displayDepositsOnly() {
    }
    private static void displayPaymentsOnly() {
    }
    private static void showReports() {
    }
}
