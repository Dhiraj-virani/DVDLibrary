/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author Dhiraj
 */
public class UserIOConsoleImpl implements UserIO {

    // Intiallizing Scanner
    Scanner sc = new Scanner(System.in);

    //A simple method that takes in a message to display on the console
    public void print(String message) {
        System.out.println(message);
    }

    //A simple method that takes in a message to display on the console, 
    //and then waits for an answer from the user to return.
    public String readString(String prompt) {
        System.out.println(prompt);
        return sc.nextLine();

    }

    //A simple method that takes in a message to display on the console, 
    //and continually reprompts the user with that message until they enter a  
    //int to be returned as the answer to that message.
    public int readInt(String prompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                System.out.println(prompt);
                num = Integer.parseInt(sc.nextLine());
                invalidInput = false;
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }

        return num;
    }

    //A simple method that takes in a message to display on the console, 
    //and continually reprompts the user with that message until they enter a  
    //int between the min and max to be returned as the answer to that message.
    public int readInt(String prompt, int min, int max) {
        int result;
        do {
            result = readInt(prompt);
        } while (result < min || result > max);

        return result;

    }

    //A simple method that takes in a message to display on the console, 
    //and continually reprompts the user with that message until they enter a  
    //double to be returned as the answer to that message.
    public double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    //A simple method that takes in a message to display on the console, 
    //and continually reprompts the user with that message until they enter a  
    //double between the min and max to be returned as the answer to that message.
    public double readDouble(String prompt, double min, double max) {
        double result;
        do {
            result = readDouble(prompt);
        } while (result < min || result > max);
        return result;
    }

    //A simple method that takes in a message to display on the console, 
    //and continually reprompts the user with that message until they enter a  
    //float to be returned as the answer to that message.
    public float readFloat(String prompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    //A simple method that takes in a message to display on the console, 
    //and continually reprompts the user with that message until they enter a  
    //float between the min and max to be returned as the answer to that message.
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    //A simple method that takes in a message to display on the console, 
    //and continually reprompts the user with that message until they enter a  
    //long to be returned as the answer to that message.
    public long readLong(String prompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(prompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    //A simple method that takes in a message to display on the console, 
    //and continually reprompts the user with that message until they enter a  
    //long between the min and max to be returned as the answer to that message.
    public long readLong(String prompt, long min, long max) {
        long result;
        do {
            result = readLong(prompt);
        } while (result < min || result > max);

        return result;
    }

}
