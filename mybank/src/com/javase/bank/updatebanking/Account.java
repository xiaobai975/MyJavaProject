package com.javase.bank.updatebanking;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 账户
 * @create 2020-04-03 22:57
 */
public class Account {

    protected double balance;//账户余额


    public Account() {
    }

    public Account(double init_balance) {
        balance = init_balance;
    }

    /**
     * 当前账户的获取余额
     * @return 当前账户余额
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 存取指定的金额
     * @param amt 指定的金额
     * @return true表示成功
     */
    public boolean deposit(double amt){
        if (amt > 0){

            balance += amt;
        }
        return true;

    }
    //取钱
    public boolean withdraw(double amt){
        if (balance > amt){

            balance -= amt;
        }else {
            return false;
        }
        return true;
    }

}
