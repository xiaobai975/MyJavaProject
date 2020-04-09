package com.javase.bank.banking_2part;


/**
 * @author JUNSHI 405773808@qq.com
 * @description: 修改客户类，进阶02
 * @create 2020-04-03 23:06
 */
public class Customer {

    private String firstName;
    private String lastName;

    //表示拥护可以有多个账户
    private Account[] accounts;

    //表示用户所有的有效账户的数量
    private int numberOfAccounts;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

        accounts = new Account[5];
    }

    public Account getAccount(int index) {
        return accounts[index];
    }

    public void addAccount(Account account) {
        accounts[numberOfAccounts++] = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumOfAccounts(){
        return numberOfAccounts;
    }
}
