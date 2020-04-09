package com.javase.bank.banking08;


import com.javase.bank.banking_2part.Account;

import java.util.ArrayList;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 修改客户类，进阶02
 * @create 2020-04-03 23:06
 */
public class Customer {

    private String firstName;
    private String lastName;

    ArrayList<Account> accList = new ArrayList<>();


    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Account getAccount(int index) {
        return accList.get(index);
    }

    public void addAccount(Account account) {

        accList.add(account);

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumOfAccounts(){
        return accList.size();
    }
}
