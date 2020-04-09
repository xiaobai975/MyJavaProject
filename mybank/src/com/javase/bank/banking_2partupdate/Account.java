package com.javase.bank.banking_2partupdate;

import com.javase.bank.banking_2partupdate.domain.OverdraftException;

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
    //取钱 需要抛出异常
    public void withdraw(double amt)  {
        if (balance > amt){

            balance -= amt;
        }else {

            throw new OverdraftException("资金不足，还需超支 ＄",amt - balance);

        }
    }

}
