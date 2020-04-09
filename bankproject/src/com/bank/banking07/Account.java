package com.bank.banking07;

public class Account {

	protected double balance;
	
	public Account(double init_balance){
		this.balance = init_balance;
	}
	
	public double getBalance() {
		return balance;
	}
	
	/**
	 * ��Ǯ����
	 * @param amt: ����Ǯ��
	 * @return: ���ش���Ƿ�ɹ�
	 */
	public boolean deposit(double amt){
		this.balance += amt;
		return true;
	}
	
	/**
	 * 
	 * @param amt
	 * @throws OverdraftException: ������ʱ���׳����쳣. 
	 */
	public void withdraw(double amt) {

		if(amt > balance){
			throw new OverdraftException("�ʽ���", amt - balance);
		}
		
		this.balance -= amt;
	}
	
}
