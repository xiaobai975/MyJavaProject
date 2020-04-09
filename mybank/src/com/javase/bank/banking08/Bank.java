package com.javase.bank.banking08;


import java.util.ArrayList;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 银行，存放客户和客户的账户
 * @create 2020-04-03 23:23
 */
public class Bank {

    ArrayList<Customer> list = new ArrayList<>();
    private int numberOfCustomer;//用来记录当前有多少个客户


    private Bank(){

    }

    //饿汉式
    private static Bank bank = new Bank();

    public static Bank getBank(){
        return bank;
    }

    /**
     * 增加客户 并将客户存入客户数组
     * @param f
     * @param l
     */
    public void addCustomer(String f,String l){

        Customer c = new Customer(f,l);
        list.add(c);
    }

    /**
     *
     * @return 返回目前有多少个客户
     */
    public int getNumOfCustomers(){
        return list.size();
    }

    /**
     * 返回指定索引的客户
     * @param index
     * @return 返回客户
     */
    public Customer getCustomer(int index){

        return list.get(index);
    }


}
