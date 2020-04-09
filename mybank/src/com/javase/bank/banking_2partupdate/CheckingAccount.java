package com.javase.bank.banking_2partupdate;


import com.javase.bank.banking_2partupdate.domain.OverdraftException;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 透支账户
 * @create 2020-04-03 23:37
 */
public class CheckingAccount extends Account {

    private double overdraftProtection = -1;//可透支余额

    public CheckingAccount() {
    }

    public CheckingAccount(double balance) {
        super(balance);

    }

    /**
     * 初始化可透支用户的初始余额与可透支金额
     *
     * @param init_balance        账户初始余额
     * @param overdraftProtection 可透支金额
     */
    public CheckingAccount(double init_balance, double overdraftProtection) {
        super(init_balance);
        this.overdraftProtection = overdraftProtection;
    }


    @Override
    public void withdraw(double amt) {

        //若余额够用则直接取款
        if (balance >= amt) {

            balance -= amt;
            //余额不够需要透支的情况：

            if (overdraftProtection == -1){
                throw new OverdraftException("no overdraft protection",amt - balance);
            }

            if (overdraftProtection >= (amt - balance)) {
                overdraftProtection -= (amt - balance);
                balance = 0;


            } else {
                throw new OverdraftException("Insufficient funds for overdraft protection",amt - balance);
            }


        }
    }
}
