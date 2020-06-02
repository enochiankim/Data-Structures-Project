/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Arrays;
import java.util.HashMap;

/**
 * The Client class is the where the client SSN, name is created, The Client constructor also creates a two accounts which is the checking and credit. 
 * During the brainstorming of this project, it logically made sense to have both accounts being made because when a client opens up account, they get both a checking and credit. 
 * This class also has methods that a normal client can do with their bank accounts such as transfer value, deposit value and withdraw value.
 * I also used the a method "addAccount" and stored the account numbers through an HashMap, the reason why I did this is because this allows me to link both the Client 
 * and the account numbers together so when needed to carry out a certain action in the database, such as finding a client based off of the account number, I would have the info. 
 *
 * @author Enoch Kim
 */
public class Client {

    protected Account checkings;
    protected Account credits;
    final String[] name;
    final Integer ssn;

    public Client(Integer ssn, String[] name) {
        this(0, 0, ssn, name);
    }

    public Client(double checkingsBalance, double creditsBalance, Integer ssn, String[] name) {
        checkings = new Account(checkingsBalance);
        credits = new Account(creditsBalance);
        this.name = name;
        this.ssn = ssn;

    }

    // If the checkings and credit match the clients, withdraw from checkings and deposit into credit and returns a string with the amount that was paid from the two accounts.
    public String transferToCredit(Integer accountNumber, Integer accountNumber1, Double money) {
        if ((accountNumber == checkings.getAccountNumber()) && (accountNumber1 == credits.getAccountNumber())) {
            checkings.withdrawValue(money);
            credits.depositValue(money);
        }
        return "Account User paid credit bill for the amount of $" + money + " from account " + accountNumber + " to " + accountNumber1;
    }

    public void depositValueCheckings(double depositValue) {
        checkings.depositValue(depositValue);
    }

    public void depositValueCredits(double depositValue) {
        credits.depositValue(depositValue);
    }

    public void withdrawValueCheckings(double withdrawValue) {
        checkings.withdrawValue(withdrawValue);
    }

    public void withdrawValueCredit(double withdrawValue) {
        credits.withdrawValue(withdrawValue);
    }

    // Prints out the LogFile of the final status of the accounts.
    @Override
    public String toString() {
        String nameString = "Client: " + Arrays.toString(name) + "\n";
        String checkingString = "Checking account number: " + checkings.getAccountNumber() + "\n" + "Balance: $" + checkings.getValue() + ". \n" + "Status of the account:";
        String creditString = "\nCredit account number: " + credits.getAccountNumber() + "\n" + "Balance Paid: $" + credits.getValue() + ". \n" + "Status of the account:";
        if (checkings.isAccountStatus()) {
            checkingString += " Open ";
        } else {
            checkingString += " Closed ";
        }
        if (credits.isAccountStatus()) {
            creditString += " Open ";
        } else {
            creditString += " Closed ";
        }
        return nameString + checkingString + creditString;
    }

    // Adds in the checking account numbers and credit account numbers of the Client into the HashMap.
    public void addAccounts(HashMap<Integer, Client> accountList) {
        accountList.put(checkings.getAccountNumber(), this);
        accountList.put(credits.getAccountNumber(), this);
    }

    // Deposits money into either checking or credit and returns a string showing the deposit
    public String deposit(Integer accountNumber, Double money) {
        if (accountNumber == checkings.getAccountNumber()) {
            depositValueCheckings(money);
            return "Checking Account Number " + accountNumber + " deposited $" + money;
        } else {
            depositValueCredits(money);
            return "Credit Account Number " + accountNumber + " deposited $" + money;
        }
    }

    //Withdraws money from either checking or credit and returns a string showing the withdrawal.
    public String withDrawal(Integer accountNumber, Double money) {
        if (accountNumber == checkings.getAccountNumber()) {
            withdrawValueCheckings(money);
            return "Checking Account Number " + accountNumber + " withdrew $" + money;
        } else {
            withdrawValueCredit(money);
            return "Credit Account Number " + accountNumber + " withdrew $" + money;
        }
    }

    // Returns the account that is connected to an account number.
    public Account getAccount(Integer accountNumber) {
        if (accountNumber == checkings.getAccountNumber()) {
            return checkings;
        } else {
            return credits;
        }
    }

}









