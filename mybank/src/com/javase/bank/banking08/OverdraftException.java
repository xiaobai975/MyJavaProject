package com.javase.bank.banking08;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-04 12:22
 */
public class OverdraftException extends RuntimeException{

    static final long serialVersionUID = -338793124229948L;

    private double deficit;

    public OverdraftException() {

    }


    public OverdraftException(String message, double deficit) {
        super(message);
        this.deficit = deficit;
    }

    public double getDeficit() {
        return deficit;
    }



}
