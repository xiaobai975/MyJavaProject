package com.bank.banking07;

public class Customer {
	
	private String firstName;
	private String lastName;
	
	//��ʾӵ�������ж���˻�
	private Account[] accounts;
	
	//��ʾ�û����е���Ч�˻�������
	private int numberOfAccounts;
	
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		accounts = new Account[5];
	}

	public Account getAccount(int index) {
		return accounts[index];
	}

	public void addAccount(Account account) {
		accounts[numberOfAccounts++] = account;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public int getNumOfAccounts(){
		return numberOfAccounts;
	}
}
