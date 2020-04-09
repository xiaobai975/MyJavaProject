package com.javase.bank.testbanking;/*
 * This class creates the program to test the banking classes.
 * It creates a new Bank, sets the Customer (with an initial balance),
 * and performs a series of transactions with the Account object.
 */


import com.javase.bank.banking.Account;
import com.javase.bank.banking.Customer;

public class TestBanking02 {

  public static void main(String[] args) {
    Customer customer;
    Account account;

    // Create an account that can has a 500.00 balance.
    System.out.println("Creating the customer Jane Smith.");
    customer = new Customer("Jane","Smith");
    //code
    System.out.println("Creating her account with a 500.00 balance.");
    customer.setAccount(new Account(500));
    //code
    System.out.println("Withdraw 150.00");
    customer.getAccount().withdraw(150);
	//code
    System.out.println("Deposit 22.50");
    customer.getAccount().deposit(22.50);
  	//code
    System.out.println("Withdraw 47.62");
    customer.getAccount().withdraw(47.62);
   	//code
    // Print out the final account balance
    System.out.println("Customer [" + customer.getLastName()
		       + ", " + customer.getFirstName()
		       + "] has a balance of " + customer.getAccount().getBalance());
  }
}
