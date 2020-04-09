package com.javase.bank.banking_2part;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 储蓄账户进阶02
 * @create 2020-04-03 23:35
 */
public class SavingAccount extends Account {

    private double interestRate;//利率

    public SavingAccount() {

    }

    public SavingAccount(double balance) {
        super(balance);
    }

    public SavingAccount(double init_balance, double interestRate) {
        super(init_balance);//对父类的Account中的Balance赋值
        this.interestRate = interestRate;
    }
}
