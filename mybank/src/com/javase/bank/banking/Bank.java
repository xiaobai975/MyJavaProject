package com.javase.bank.banking;

/**
 * @author JUNSHI 405773808@qq.com
 * @description: 银行，存放客户和客户的账户
 * @create 2020-04-03 23:23
 */
public class Bank {

    private Customer[] customers;//存放Customer的数组
    private int numberOfCustomer;//用来记录当前有多少个客户

    /**
     * 默认可以放10个客户
     */
    public Bank(){
        customers = new Customer[10];
    }




    /**
     * 增加客户 并将客户存入客户数组
     * @param f
     * @param l
     */
    public void addCustomer(String f,String l){

        Customer c = new Customer(f,l);
        customers[numberOfCustomer++] = c;

    }

    /**
     *
     * @return 返回目前有多少个客户
     */
    public int getNumOfCustomers(){
        return numberOfCustomer;
    }

    /**
     * 返回指定索引的客户
     * @param index
     * @return 返回客户
     */
    public Customer getCustomer(int index){

        return customers[index];
    }


}
