package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static int promptForInt(String prompt) {
        System.out.print(prompt + ": ");
        int result = scanner.nextInt();
        scanner.nextLine();
        return result;
    }

    public static double promptForDouble(String prompt) {
        System.out.print(prompt + ": ");
        double result = scanner.nextDouble();
        scanner.nextLine();
        return result;
    }

    public static String promptForString(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
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
                //ex.printStackTrace();
            }
        }
    }
    public static LocalTime promptForTime(String prompt){
        while (true) {
            try {
                System.out.print(prompt + ": ");
                String timeAsString = scanner.nextLine();
                return LocalTime.parse(timeAsString); // expects format HH:MM or HH:MM:SS
            } catch (Exception ex) {
                System.out.println("Invalid Entry, please enter a valid time (HH:MM or HH:MM:SS)");
            }
        }
    }
}
