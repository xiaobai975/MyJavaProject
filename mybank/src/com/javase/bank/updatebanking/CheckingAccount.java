package com.javase.bank.updatebanking;

/**
 * 该类必须包括一个关联属性，称作 protectedBy ，表示透支保护。该属性的
 * 类型为 SavingAccount ，缺省值必须是 null 。除此之外该类没有其他的数据属
 * 性。
 * 4.该类必须包括一个带有参数（ balance ）的公有构造器，该构造器必须通过调
 * 用 super(balance) 将 balance 参数传递到父类构造器。
 * 5.修改构造器为 CheckingAccount(double balance,SavingAccount protect) 构造器。
 * 该构造器必须通过调用 (super ba lance ）将 balance 参数传递给父类构造器。
 * 6. CheckingAccount类必须覆盖 withdraw 方法。该类必须执行下面的检查：
 * 如果当前余额足够弥补取款 amount ，则正常进行。如果不够弥补但是存在透支
 * 保护则尝试用储蓄账户来弥补这个差值（ balance -  amount ）。如果后一个交易
 * 失败，则整个交易一定失败，但余额未受影响。
 * @author JUNSHI 405773808@qq.com
 * @description: 透支账户进阶02
 * @create 2020-04-03 23:37
 */
public class CheckingAccount extends Account {

    private SavingAccount protectedBy;//关联的透支账户


    public CheckingAccount() {
    }

    public CheckingAccount(double balance) {
        super(balance);

    }

    /**
     * 透支账户赋值
     * @param init_balance
     * @param protectedBy
     */
    public CheckingAccount(double init_balance, SavingAccount protectedBy) {
        super(init_balance);
        this.protectedBy = protectedBy;
    }

    @Override
    public boolean withdraw(double amt) {

        //若余额够用则直接取款
        if (balance >= amt){

            balance -= amt;

            //余额不够需要透支的情况： protestedBy不能为空！
        }else if (protectedBy != null && protectedBy.getBalance() >= (amt - balance)){
            protectedBy.withdraw(amt - balance);
            balance = 0;
        }else {
            return false;
        }

        return true;
    }
}