package com.bank.banking07;

import com.bank.banking08.Customer;

/**
 * �������ж���
 * @author Think Pad
 *
 */
public class Bank {
	
	//��ǰ Bank ���ж�� Cutomer
	private Customer[] customers;
	
	//��ʾ customers �������ж��ٸ������� Customer ����
	private int numberOfCustomer;
	
	private Bank() {
		customers = new Customer[5];
	}
	
	private static Bank instance = new Bank();
	
	public static Bank getBank(){
		return instance;
	}
	
	/**
	 * ���ݴ���Ĳ�������һ���µ� Cusotmer ����, ���Ѹö��󸳸� customers ��ָ����Ԫ��
	 * @param firstName
	 * @param lastName
	 */
	public void addCustomer(String firstName, String lastName){
		//1.���ݴ���Ĳ�������һ���µ� Cusotmer ����
		Customer cust = new Customer(firstName, lastName);
		
		//2.�� 1 �´����� Customer ���󸳸� customers ��ָ����Ԫ��
		customers[numberOfCustomer] = cust;
		
		//3. ʹ��ʾ customers �������ж��ٸ������� Customer ��������� + 1
		numberOfCustomer++;
	}
	
	/**
	 * ���� ��ʾ customers �������ж��ٸ������� Customer ��������� 
	 * @return
	 */
	public int getNumOfCustomers(){
		return numberOfCustomer;
	}
	
	/**
	 * ����ָ��������Ӧ�� Customer ����
	 * @param index
	 * @return
	 */
	public Customer getCustomer(int index){
		return customers[index];
	}
}
