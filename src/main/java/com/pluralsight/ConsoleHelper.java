package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleHelper {
    private static Scanner scanner = new Scanner(System.in);

    //This method asks the user to type a whole number (integer) and returns it
    public static int promptForInt(String prompt) {

        //Display a message asking the user for input
        System.out.print(prompt + ": ");

        //Read the number the user types from the keyboard
        //'scanner.nextInt()' waits for the user to type a number and press Enter
        //We assign it to 'result' so we can store and use that number later
        int result = scanner.nextInt();

        //Clear the leftover newline character after the user presses Enter
        //(this prevents the next input from being skipped)
        scanner.nextLine();
        //Return the number the user entered to the main program
        return result;
    }

    public static double promptForDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine();
                return Double.parseDouble(input);  // Convert text to double
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input! Please enter a number");
            }
        }
    }


    public static String promptForString(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String input = scanner.nextLine().trim(); // Read input and remove extra spaces

                if (input.isEmpty()) {  // Check if user entered nothing
                    System.out.println("Invalid input! Please enter text.");
                } else {
                    return input;  // Return valid text
                }

            } catch (Exception e) {
                System.out.println("Something went wrong. Please try again.");
            }
        }
        }

    public static LocalDate promptForDate(String prompt){

        while(true){
            try{
                System.out.print(prompt + ": ");
                String dateAsString = scanner.nextLine();
                return LocalDate.parse(dateAsString);
            }
            catch(Exception ex){
                System.out.println("Invalid Entry, please enter a valid date (YYYY-MM-DD)");
            }
        }
    }
    public static LocalTime promptForTime(String prompt){
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String timeAsString = scanner.nextLine();
                return LocalTime.parse(timeAsString);
            } catch (Exception ex) {
                System.out.println("Invalid Entry, please enter a valid time (HH:MM:SS)");
            }
        }
    }
}
