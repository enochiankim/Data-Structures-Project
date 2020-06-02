/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.HashMap;

/**
 * The purpose of the BankAdmin class is that, the Bank Admin has powers that
 * clients cannot do such as closing and opening accounts, modifying values in
 * the account etc by implementing the interface of BankAdmin. I used the close
 * account, open account and creating a cilentList and accountList in order to
 * showcase the power of the BankAdmin.
 *
 * @author Enoch Kim
 */
public class BankAdmin implements BankAdminInterface {

    // Created a client list and accountList.
    static final HashMap<Integer, Client> clientList = new HashMap<Integer, Client>();
    static final HashMap<Integer, Client> accountList = new HashMap<Integer, Client>();

    public BankAdmin() {

    }

    // This method closes an account by getting the account number from the Client Class.
    public void closeAccount(Integer accountNumber, Client client) {
        Account account = client.getAccount(accountNumber);
        account.closeAccount();
    }

    // This method opens an account by getting the account number from the Client Class.
    public void openAccount(Integer accountNumber, Client client) {
        Account account = client.getAccount(accountNumber);
        account.openAccount();
    }

    public void modifyDeposit(Account account, double depositValue) {
        account.depositValue(depositValue);
    }

    public void modifyWithdraw(Account account, double withdrawValue) {
        account.withdrawValue(withdrawValue);
    }

    public double getValue(Account account) {
        return account.getValue();
    }

    public double depositValue(Account account, double depositAmount) {
        return account.depositValue(depositAmount);
    }

    public double withdrawValue(Account account, double withdrawValueAmount) {
        return account.withdrawValue(withdrawValueAmount);
    }

}


