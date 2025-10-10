package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;

public class homeScreen {
    public static ArrayList<Transaction> transactions = getTransactionsFromFile();
    public static void main(String[] args) {
        String mainMenu = """
        What do you want to do?
        D) Add Deposit
        P) Make Payment (Debit)
        L) Ledger
        X) Exit
        """;
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

    private static void addDeposit() {
        System.out.println("Add Deposit");
       String LocalDate  = ConsoleHelper.promptForString("Enter date (YYYY-MM-DD)");
       String LocalTime = ConsoleHelper.promptForString("Enter time (HH:MM:SS)");
       String description = ConsoleHelper.promptForString("Enter description");
       String vendor = ConsoleHelper.promptForString("Enter vendor");
       double amount = ConsoleHelper.promptForDouble("Enter deposit amount");

       Transaction transaction = new Transaction(LocalDate, LocalTime, description, vendor, amount);

    }

    private static void makePayment() {

    }
    private static void displayLedger() {
    }


    public static ArrayList<Transaction> getTransactionsFromFile() {
        return null;
    }
}