/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

/**
 *
 * @author Enoch Kim
 */

// This is the list of the interface of the account.
public interface AccountInterface {

    // Get the value of the account.
    double getValue();

    // Deposit/add values in the account.
    double depositValue(double value);

    // Withdraws Value in the account.
    double withdrawValue(double value);

    // Checks the status "open" of the account.
    void openAccount();

    // Checks the status "closed" of the account.
    void closeAccount();
    
    // Gets the account number.
    int getAccountNumber();
    
    // Checks the status of the account.
    boolean isAccountStatus();
}



