package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

public class HomeScreen {
    //Create an ArrayList to hold all transactions
    public static ArrayList<Transaction> transactions = getTransactionsFromFile();


    //-----Main Menu Methode----//
    //Main menu displayed to the user
    public static void main(String[] args) {
        String mainMenu = """
                
                //------------Main Menu------------//
                    ========================
                    What do you want to do?
                    D) Add Deposit
                    P) Make Payment (Debit)
                    L) Ledger
                    X) Exit
                    =======================
                """;
        // As long as it true it will keep showing the menu until the user chooses to exit
        while (true) {
            System.out.println(mainMenu);
            String command = ConsoleHelper.promptForString("Enter your command (D, P, L, X)").toUpperCase().trim();

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

        //Ask user for transaction details
        LocalDate date = ConsoleHelper.promptForDate("Enter date (YYYY-MM-DD)");
        LocalTime time = ConsoleHelper.promptForTime("Enter time (HH:MM:SS a )");
        String description = ConsoleHelper.promptForString("Enter description");
        String vendor = ConsoleHelper.promptForString("Enter vendor");
        double amount = ConsoleHelper.promptForDouble("Enter deposit amount $ ");

        amount = Math.abs(amount);//Make sure amount is always positive even if the user entered a negative number.

        // This creates and store new transaction
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);
        writeTransactionToFile(newTransaction);
        System.out.println("Funds Successfully Deposited!");
        System.out.printf("New Balance: $%.2f%n", calculateBalance());
    }

    //This method to record a payment transaction
    private static void makePayment() {
        System.out.println("Make your Payment");

        // Ask user for transaction details
        LocalDate date = ConsoleHelper.promptForDate("Enter date (YYYY-MM-DD)");
        LocalTime time = ConsoleHelper.promptForTime("Enter time (HH:MM:SS)");
        String description = ConsoleHelper.promptForString("Enter description");
        String vendor = ConsoleHelper.promptForString("Enter vendor");
        double amount = ConsoleHelper.promptForDouble("Enter payment amount $ ");

        amount = -Math.abs(amount); //Make sure amount is always negative even if the user entered a positive number.

        //This creates and store new transaction
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);
        writeTransactionToFile(newTransaction);
        System.out.println("Your payment recorded successfully!");
        System.out.printf("New Balance: $%.2f%n", calculateBalance());
    }


    //-----Ledger Menu Methode-----///
    //Here it will display the ledger menu for viewing or reporting transactions
    private static void displayLedger() {
        String ledgerMenu = """
                
                //-----------ledger Menu------------// 
                           ================
                           A) All Entries
                           D) Deposits Only
                           P) Payments Only
                           R) Reports
                           H) Home
                           ================
                """;
        // As long as it true it will keep showing the menu until the user chooses to exit
        while (true) {
            System.out.println(ledgerMenu);
            String command = ConsoleHelper.promptForString("Enter your command (A, D, P, R, H)").toUpperCase().trim();

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
        System.out.println(" New Balance " + calculateBalance());
        sortTransactionsByDateTimeNewestFirst(transactions);
        displayTransactions(transactions);
    }

    //Here it will show only deposits
    private static void displayDepositsOnly() {
        System.out.println("User Deposits:");
        //Creates an empty list to store all deposit transactions
        ArrayList<Transaction> depositsList = new ArrayList<>();
        for (Transaction tx : transactions) {
            if (tx.isDeposit()) { //If this transaction is a deposit (amount > 0)
                depositsList.add(tx);//add it to the deposits list
            }
        }
        sortTransactionsByDateTimeNewestFirst(depositsList);
        displayTransactions(depositsList);
    }

    //Here it will show only payments
    private static void displayPaymentsOnly() {
        System.out.println("User Payments:");
        ArrayList<Transaction> paymentsList = new ArrayList<>();
        for (Transaction tx : transactions) {
            if (tx.isPayment()) {//checks if the transaction is a payment (amount < 0)
                paymentsList.add(tx);//puts that transaction into the list of payments
            }
        }
        sortTransactionsByDateTimeNewestFirst(paymentsList);
        displayTransactions(paymentsList);
    }


    //-----Reports Menu Methode------//
    //Here it will show the user reports
    private static void showReports() {
        System.out.println("User Reports");
        String reportsMenu = """
                //-----------Reports Menu------------//
                 ========================
                 1) Month To Date        ]
                 2) Previous Month       ]
                 3) Year To Date         ]
                 4) Previous Year        ]
                 5) Search by Vendor     ]
                 0) Back                 ]
                 =========================
                """;

        // As long as it true it will keep showing the menu until the user chooses to exit
        while (true) {
            System.out.println(reportsMenu);
            String command = ConsoleHelper.promptForString("Enter your command (1, 2, 3, 4, 5, 0)").trim();

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
        ArrayList<Transaction> monthList = new ArrayList<>();
        //Go through each transaction in the list
        for (Transaction tx : transactions) {

            // Check if the transaction happened in the current year AND current month
            if (tx.getDate().getYear() == today.getYear() && tx.getDate().getMonth() == today.getMonth()) {

                // Print the transaction if it matches
                System.out.println(tx);
            }
        }
        sortTransactionsByDateTimeNewestFirst(monthList);
        displayTransactions(monthList);

    }

    //Show all transactions from the previous month
    private static void showPreviousMonth() {
        System.out.println("Previous Month Transactions:");

        //Get the date exactly one month before today
        LocalDate lastMonth = LocalDate.now().minusMonths(1);

        ArrayList<Transaction> previousMonthList = new ArrayList<>();

        //Check each transaction and print it if it happened in the previous month
        for (Transaction tx : transactions) {
            if (tx.getDate().getYear() == lastMonth.getYear() && tx.getDate().getMonth() == lastMonth.getMonth()) {
                System.out.println(tx);
            }
        }
        sortTransactionsByDateTimeNewestFirst(previousMonthList);
        displayTransactions(previousMonthList);
    }

    //Show all transactions from this year
    private static void showYearToDate() {
        System.out.println("Year To Date Transactions:");

        //This line gets the current year so the program can filter transactions that happened this year only.
        int currentYear = LocalDate.now().getYear();

        ArrayList<Transaction> YearToDateList = new ArrayList<>();

        //Go through all transactions and print only the ones from this year
        for (Transaction tx : transactions) {
            if (tx.getDate().getYear() == currentYear) {
                System.out.println(tx);
            }
        }
        sortTransactionsByDateTimeNewestFirst(YearToDateList);
        displayTransactions(YearToDateList);
    }

    //Show all transactions from the previous year
    private static void showPreviousYear() {
        System.out.println("Previous Year Transactions:");

        //Get the previous year
        int lastYear = LocalDate.now().getYear() - 1;

        for (Transaction tx : transactions) {
            if (tx.getDate().getYear() == lastYear) {
                System.out.println(tx);
            }
        }
    }

    //Search by vendor name
    private static void searchByVendor() {
        String vendor = ConsoleHelper.promptForString("Enter vendor name to search");
        System.out.println("Transactions for vendor: " + vendor);

        boolean found = false;//Track if we found any matching transactions

        //In this line it will find and show all transactions matching that vendor
        for (Transaction tx : transactions) {
            if (tx.getVendor()//look inside this box(tx) and tell me the vendor
                    .equalsIgnoreCase(vendor)) {

                System.out.println(tx);
                found = true; //This mark that we found at least one
            }
        }
        if (!found) {
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
                if (lineFromFile.startsWith("date"))
                    continue;//This helps to skip the header row in the transactions.cvs file

                //Split each line by the | symbol
                String[] parts = lineFromFile.split("\\|");

                //Skip lines that don't have all 5 required fields (date, time, description, vendor, amount)
                if (parts.length < 5) continue;

                //Turn the text into the right types (date, time, number)
                LocalDate date = LocalDate.parse(parts[0]);
                LocalTime time = LocalTime.parse(parts[1]);
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                //This line of code added new transaction and add it to the list
                Transaction tx = new Transaction(date, time, description, vendor, amount);
                transactions.add(tx);
            }
        } catch (Exception e) {
            System.out.println("There was an error reading the transactions.cvs file.");
            e.printStackTrace();
        }

        return transactions;
    }


    //Save a single transaction to the CSV file
    private static void writeTransactionToFile(Transaction tx) {

        //Open the file to add new transactions and write lines to it (true = append mode, so existing data is not erased)
        try (FileWriter fw = new FileWriter("transactions.csv", true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.printf("%s|%s|%s|%s|%.2f%n",
                    tx.getDate(), tx.getTime(), tx.getDescription(), tx.getVendor(), tx.getAmount());
        } catch (Exception e) {
            System.out.println("Error saving transaction to file.");
        }
    }


    //Sort Transactions By Newest
    private static void sortTransactionsByDateTimeNewestFirst(ArrayList<Transaction> list) {
        // Sort the transactions so the newest ones come first (by date and time)
        list.sort(Comparator
                // Compare by date in reverse order (newest date first)
                .comparing(Transaction::getDate, Comparator.reverseOrder())
                // If two transactions have the same date, compare by time in reverse order (latest time first)
                .thenComparing(Transaction::getTime, Comparator.reverseOrder()));
    }


    //Display all transactions in a list
    private static void displayTransactions(ArrayList<Transaction> list) {
        for (Transaction tx : list) {
            System.out.println(tx);
        }
    }


    //Calculate the balance
    private static double calculateBalance() {
        //Start with a balance of 0
        double balance = 0;
        //Go through each transaction in the transactions list
        for (Transaction tx : transactions) {
            //Add this transaction's amount to the balance
            balance += tx.getAmount();
        }
        //Return the final balance after adding all transactions
        return balance;
    }
}