package com.javase.bank.banking;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 客户类
 * @create 2020-04-03 23:06
 */
public class Customer {

    private String firstName;//客户姓名
    private String lastName;
    private Account account;//每个客户的账户

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

    /**
     * 获取当前客户的账户
     * @return 返回客户的账户
     */
    public Account getAccount() {
        return account;
    }

    /**
     * 让客户开独立的账户
     * @param account 放入账户
     */
    public void setAccount(Account account) {
        this.account = account;
    }
}
