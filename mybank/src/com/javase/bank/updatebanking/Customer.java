package com.javase.bank.updatebanking;


/**
 * @author JUNSHI 405773808@qq.com
 * @description: 修改客户类，进阶02
 * @create 2020-04-03 23:06
 */
public class Customer {

    private String firstName;
    private String lastName;
    private SavingAccount savingAccount;//储蓄账户
    private CheckingAccount checkingAccount;//支票账户


    public Customer() {
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public SavingAccount getSavings() {
        return savingAccount;
    }

    public void setSavings(SavingAccount savingAccount) {
        this.savingAccount = savingAccount;
    }

    public CheckingAccount getChecking() {
        return checkingAccount;
    }

    public void setChecking(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }


}
