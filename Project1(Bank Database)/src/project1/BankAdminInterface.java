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
// This is the list of the interface of the bankadmin interface.
public interface BankAdminInterface {

    // Get the value of the account.
    double getValue(Account account);
    
    // Deposit Value to an account.
    double depositValue(Account account, double depositAmount);

    // Withdraws a value of an account.
    double withdrawValue(Account account, double withdrawValueAmount);

    // Closes an account
    void closeAccount(Integer accountNumber, Client client);

    // Opens an account
    void openAccount(Integer accountNumber, Client client);

    // Modifies a deposit amount.
    void modifyDeposit(Account account, double depositValue);

    //Modifies a withdrawal amount.
    void modifyWithdraw(Account account, double withdrawValue);
}


