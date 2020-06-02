/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.HashMap;

/**
 * The Account class, implements the interface of the Account. Changes to an account can be made in this class such as, depositing, withdrawing,
 * getting the value, checking the account status and getting the of the account number. 
 *
 * @author Enoch Kim
 */
public class Account implements AccountInterface {

    // Declaring variables for value, account, accountNumber.
    
    private double value;
    private boolean accountStatus;
    final int accountNumber;

    
    // The accountNumber starts at the number 10000.
    
    private static int accountCounter = 10000;

    public Account(double value) {
        this.value = value;
        
        // Increases the accountNumber after an account is made, (this allow Clients to have different accounts so they have their own accounts.)
        accountNumber = accountCounter++;
        accountStatus = true;
    }

    public Account() {
        this(0);
        accountStatus = false;
    }

    // These are the methods from the interface, it is stated in the AccountInterface on what each method does.  
    
    public double getValue() {
        return value;
    }

    public double depositValue(double amount) {
        value += amount;
        return value;
    }

    public double withdrawValue(double amount) {
        value -= amount;
        return value;
    }

    public void openAccount() {
        accountStatus = true;
    }

    public void closeAccount() {
        accountStatus = false;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean isAccountStatus() {
        return accountStatus;
    }

}



