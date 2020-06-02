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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Project1 {

    public static void main(String[] args) {



        TextFileInput input = new TextFileInput("Input.txt");
        FileOutput output = new FileOutput("Output.txt");
        FileOutput stateOfFile = new FileOutput("LogFile.txt");

        String line = input.readLine();
        while (line != null) {
            if (line.equals("")) {
                line = input.readLine();
                continue;
            }
            if (line.charAt(0) == '0') {
                // If char equals 0, split the line.
                String[] newClient = line.split("\\s", 3);

                // Assigns the "new cilent" SSN number by taking the integer value in string[] valued at 1. 
                Integer ssn = Integer.valueOf(newClient[1]);

                // Checks if there is a SSN with same number, in the HashMap of the clientList.
                if (!BankAdmin.clientList.containsKey(ssn)) {

                    //Makes a new Client and puts in the SSN and name in the clientList Hashmap.
                    Client client = new Client(ssn, (newClient[2].split("\\s")));
                    BankAdmin.clientList.put(ssn, client);

                    // Add the checking and credit account into the HashMap of accountList.
                    client.addAccounts(BankAdmin.accountList);

                    System.out.println(Arrays.toString(newClient));
                    output.writeLine("Create client name " + newClient[2]);
                } else {
                    output.writeLine("ERROR!!!! SSN already exists!!!!");
                }

            }
            if (line.charAt(0) == '1') {

                // If char equals 1, split the line.
                String[] deposit = line.split("\\s");

                // Takes the string[] valued at 2 and which reads the Integer value of the account number.
                Integer accountNumber = Integer.valueOf(deposit[1]);

                //Takes the string [] valued at 3 and reads the amount of money that will be deposited. 
                Double money = Double.valueOf(deposit[2]);

                // Finding a client, a client based off of an account number
                Client client = BankAdmin.accountList.get(accountNumber);

                //A string assigned to a client deposits the money into the assigned account number, method is from the Client class called deposit.
                String display = client.deposit(accountNumber, money);

                System.out.println(Arrays.toString(deposit));

                // The Output text will show String "display" which is in the Client class of the deposit method. 
                output.writeLine(display);
            }
            if (line.charAt(0) == '2') {
                String[] withDrawal = line.split("\\s");

                Integer accountNumber = Integer.valueOf(withDrawal[1]);
                Double money = Double.valueOf(withDrawal[2]);

                Client client = BankAdmin.accountList.get(accountNumber);
                String display = client.withDrawal(accountNumber, money);

                System.out.println(Arrays.toString(withDrawal));
                output.writeLine(display);

            }
            if (line.charAt(0) == '3') {

                // If the first char equals 3, split the line
                String[] payCredit = line.split("\\s", 4);

                //Takes the String[] valued at 2 and 3 and which reads the Integer value, which are the account number.
                Integer accountNumber = Integer.valueOf(payCredit[1]);
                Integer accountNumber2 = Integer.valueOf(payCredit[2]);

                //String[] value of 4 and reads the Double value of the money. 
                Double money = Double.valueOf(payCredit[3]);

                // Finding a client, a client based off of an account number
                Client client = BankAdmin.accountList.get(accountNumber);

                //A String assigned to a client tranfers the Double money from the first assigned account number to the next assigned account number, method is from the Client class called transferToCredit.
                String display = client.transferToCredit(accountNumber, accountNumber2, money);

                System.out.println(Arrays.toString(payCredit));

                // The Output text will show String "display" which is in the Client class of the transderToCredit method. 
                output.writeLine(display);
            }

            if (line.charAt(0) == '4') {

                // If the first char equals 4, split the line
                String[] closingAccount = line.split("\\s");

                // Declaring a instance of the BankAdmin class.
                BankAdmin admin = new BankAdmin();

                // Takes the string[] valued at 2 and which reads the Integer value of the account number.
                Integer accountNumber = Integer.valueOf(closingAccount[1]);

                // Finding a client, a client based off of an account number
                Client client = BankAdmin.accountList.get(accountNumber);

                // BankAdmin, is using the method closeAccount to close the account number. 
                admin.closeAccount(accountNumber, client);

                System.out.println(Arrays.toString(closingAccount));

                // The Output text will show that which accountNumber was closed.
                output.writeLine("Bank Administration closed account number " + accountNumber + " Successfully. ");

            }
            if (line.charAt(0) == '5') {
                String[] openingAccount = line.split("\\s");

                BankAdmin admin = new BankAdmin();
                Integer accountNumber = Integer.valueOf(openingAccount[1]);
                Client client = BankAdmin.accountList.get(accountNumber);
                admin.openAccount(accountNumber, client);
                System.out.println(Arrays.toString(openingAccount));
                output.writeLine("Bank Administration opened account number " + accountNumber + " Successfully. ");

            }

            line = input.readLine();
        }

        //Print State File, ireratores throught the clientList and gets the final values and prints out the final statements through the toString method in the Client class.
        for (Client statement : BankAdmin.clientList.values()) {
            stateOfFile.writeLine(statement.toString());
        }
        stateOfFile.close();
        output.close();
    }
}



