package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class homeScreen {
    //Create an ArrayList to hold all transactions
    public static ArrayList<Transaction> transactions = getTransactionsFromFile();

    //Main menu displayed to the user
    public static void main(String[] args) {
        String mainMenu = """
                What do you want to do?
                D) Add Deposit
                P) Make Payment (Debit)
                L) Ledger
                X) Exit
                """;
        // As long as it true it will keep showing the menu until the user chooses to exit
        while (true) {
            System.out.println(mainMenu);
            String command = ConsoleHelper.promptForString("Enter your command (D, P, L, X)").toUpperCase();

            switch (command) {
                case "D":
                    addDeposit();
                    break;
                case "P":
                    makePayment();
                    break;
                case "L":
                    displayLedger(); // open the ledger menu
                    break;
                case "X":
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("INVALID COMMAND! Please select a valid option.");
            }
        }
    }


    //Here is the method to add a deposit transaction
    private static void addDeposit() {
        System.out.println("Add Deposit");

        // Ask user for transaction details
        LocalDate date = ConsoleHelper.promptForDate("Enter date (YYYY-MM-DD)");
        LocalTime time = ConsoleHelper.promptForTime("Enter time (HH:MM:SS)");
        String description = ConsoleHelper.promptForString("Enter description");
        String vendor = ConsoleHelper.promptForString("Enter vendor");
        double amount = ConsoleHelper.promptForDouble("Enter deposit amount");

        amount = Math.abs(amount); // This is to make sure the deposit is positive

        // This creates and store new transaction
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);
        saveTransaction(newTransaction);
        System.out.println("Your deposit added successfully!");
    }


    //This method to record a payment transaction
    private static void makePayment() {
        System.out.println("Make your Payment");

        // Ask user for transaction details
        LocalDate date = ConsoleHelper.promptForDate("Enter date (YYYY-MM-DD)");
        LocalTime time = ConsoleHelper.promptForTime("Enter time (HH:MM:SS)");
        String description = ConsoleHelper.promptForString("Enter description");
        String vendor = ConsoleHelper.promptForString("Enter vendor");
        double amount = ConsoleHelper.promptForDouble("Enter payment amount");

        amount = -Math.abs(amount); // This is to make sure the payment is negative

        //This creates and store new transaction
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);
        saveTransaction(newTransaction);
        System.out.println("Your payment recorded successfully!");
    }


    //Here it will display the ledger menu for viewing or reporting transactions
    private static void displayLedger() {
        String ledgerMenu = """
                A) All Entries
                D) Deposits Only
                P) Payments Only
                R) Reports
                H) Home
                """;
        // As long as it true it will keep showing the menu until the user chooses to exit
        while (true) {
            System.out.println(ledgerMenu);
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
                    return;//Go back to home to the main menu
                default:
                    System.out.println("INVALID COMMAND! Please select a valid option.");
            }
        }
    }


    //Here it will show all transactions
    private static void displayAllEntries() {
        System.out.println("User all Entries:");
        displayTransactions(transactions);
    }


    //Here it will show only deposits
    private static void displayDepositsOnly() {
        System.out.println("User Deposits:");
        ArrayList<Transaction> deposits = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() > 0) { //This will only show the positive amounts
                deposits.add(t);
            }
        }
        displayTransactions(deposits);
    }


    //Here it will show only payments
    private static void displayPaymentsOnly() {
        System.out.println("User Payments:");
        ArrayList<Transaction> payments = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getAmount() < 0) {//This will only show the negative amounts
                payments.add(t);
            }
        }
        displayTransactions(payments);
    }


    //Here it will show the user reports
    private static void showReports() {
        System.out.println("User Reports");
        String reportsMenu = """
            Reports Menu:
            1) Month To Date
            2) Previous Month
            3) Year To Date
            4) Previous Year
            5) Search by Vendor
            0) Back
            """;
        // As long as it true it will keep showing the menu until the user chooses to exit
        while (true) {
            System.out.println(reportsMenu);
            String command = ConsoleHelper.promptForString("Enter your command (1, 2, 3, 4, 5, 0)");

            switch (command) {
                case "1":
                    showMonthToDate();
                    break;
                case "2":
                    showPreviousMonth();
                    break;
                case "3":
                    showYearToDate();
                    break;
                case "4":
                    showPreviousYear();
                    break;
                case "5":
                    searchByVendor();
                    break;
                case "0":
                    return; //Go back to Ledger menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }



    //Show all transactions from this month
    private static void showMonthToDate() {
        System.out.println("Month to date Transactions:");
        LocalDate today = LocalDate.now();// Get today's date

        //Go through each transaction in the list
        for (Transaction t : transactions) {

            // Check if the transaction happened in the current year AND current month
            if (t.getDate().getYear() == today.getYear() && t.getDate().getMonth() == today.getMonth()) {

                // Print the transaction if it matches
                System.out.println(t);
            }
        }
    }


    //Show all transactions from the previous month
    private static void showPreviousMonth() {
        System.out.println("Previous Month Transactions:");

        //Get the date exactly one month before today
        LocalDate lastMonth = LocalDate.now().minusMonths(1);

        //Check each transaction and print it if it happened in the previous month
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == lastMonth.getYear() && t.getDate().getMonth() == lastMonth.getMonth()) {
                System.out.println(t);
            }

        }
    }


    //Show all transactions from this year
    private static void showYearToDate() {
        System.out.println("Year-To-Date Transactions:");

        //This line gets the current year so the program can filter transactions that happened this year only.
        int currentYear = LocalDate.now().getYear();

        //Go through all transactions and print only the ones from this year
        for (Transaction t : transactions) {
            if (t.getDate().getYear() == currentYear) {
                System.out.println(t);
            }
        }
    }


    //Show all transactions from the previous year
    private static void showPreviousYear() {
        System.out.println("Previous Year Transactions:");

        //Get the previous year
        int lastYear = LocalDate.now().getYear() - 1;

        for (Transaction t : transactions) {
            if (t.getDate().getYear() == lastYear) {
                System.out.println(t);
            }
        }
    }


    //Search by vendor name
    private static void searchByVendor() {
        String vendor = ConsoleHelper.promptForString("Enter vendor name to search");
        System.out.println("Transactions for vendor: " + vendor);

        boolean found = false;//Track if we found any matching transactions

        //In this line it will find and show all transactions matching that vendor
        for (Transaction t : transactions) {
            if (t.getVendor().equalsIgnoreCase(vendor)) {
                System.out.println(t);
                found = true; //This mark that we found at least one
            }
        }
            if(!found) {
                System.out.println("Nothing found for that vendor");
            }
    }


    //Here where it reads transactions from the CSV file
    public static ArrayList<Transaction> getTransactionsFromFile() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        //Open the transactions.csv file and create a BufferedReader to read it line by line
        try (FileReader fileReader = new FileReader("transactions.csv");
             BufferedReader br = new BufferedReader(fileReader)) {

            String lineFromFile;

            // Read each line from the file until the end is reached
            while ((lineFromFile = br.readLine()) != null) {
                if (lineFromFile.startsWith("date")) continue;//This helps to skip the header row in the transactions.cvs file

                //Split each line by the | symbol
                String[] parts = lineFromFile.split("\\|");
                if (parts.length < 5) continue;

                //Turn the text into the right types (date, time, number)
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                //This line of code added new transaction and add it to the list
                Transaction t = new Transaction(date, time, description, vendor, amount);
                transactions.add(t);
            }
        } catch (Exception e) {
            System.out.println("There was an error reading the transactions.cvs file.");
            e.printStackTrace();
        }

        return transactions;
    }


    //Save a single transaction to the CSV file
    private static void saveTransaction(Transaction t) {

        //Open the file to add new transactions and write lines to it (true = append mode, so existing data is not erased)
        try (FileWriter fw = new FileWriter("transactions.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {

            //This line will write a line to the file with all transaction details
            pw.printf("%s|%s|%s|%s|%.2f%n",
                    t.getDate(), t.getTime(), t.getDescription(), t.getVendor(), t.getAmount());
        }

        catch (Exception e) {
            System.out.println("Error saving transaction to file.");
        }
    }


    //Display all transactions in a list
    private static void displayTransactions(ArrayList<Transaction> list) {
        for (Transaction t : list) {
            System.out.println(t);
        }
    }
}
