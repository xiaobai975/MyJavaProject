//package com.javase.bank.banking08.report;
//
//import com.javase.bank.banking_2part.*;
//import com.javase.bank.banking_2part.report.CustomerReport;
//
///**
// * @author JUNSHI 405773808@qq.com
// * @description:
// * @create 2020-04-04 0:58
// */
//public class CustomerReport01 {
//
//
//    public void generateReport() {
//        Bank bank = Bank.getBank();
//        Customer customer = null;
//
//        // Generate a report
//        System.out.println("\t\t\tCUSTOMERS REPORT");
//        System.out.println("\t\t\t================");
//
//        for (int cust_idx = 0; cust_idx < bank.getNumOfCustomers(); cust_idx++) {
//            customer = bank.getCustomer(cust_idx);
//
//            System.out.println();
//            System.out.println("Customer: "
//                    + customer.getLastName() + ", "
//                    + customer.getFirstName());
//
//            for (int acct_idx = 0; acct_idx < customer.getNumOfAccounts(); acct_idx++) {
//                Account account = customer.getAccount(acct_idx);
//                String account_type = "";
//
//                // Determine the account type
//                /*** Step 1:
//                 **** Use the instanceof operator to test what type of account
//                 **** we have and set account_type to an appropriate value, such
//                 **** as "Savings Account" or "Checking Account".
//                 ***/
//                if (account instanceof CheckingAccount){
//                    account_type = "Checking Account";
//                }else if(account instanceof SavingAccount){
//                    account_type = "Savings Account";
//                }
//
//
//                // Print the current balance of the account
//                /*** Step 2:
//                 **** Print out the type of account and the balance.
//                 **** Feel free to use the currency_format formatter
//                 **** to generate a "currency string" for the balance.
//                 ***/
//                System.out.println(account_type + ": current balance is ￥ " + account.getBalance());
//
//            }
//        }
//
//    }
//}