package com.pluralsight;

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
        String command = ConsoleHelper.promptForString("Enter your command");



    
 


    }
    public static ArrayList<Transaction> getTransactionsFromFile() {
        return null;
    }
}