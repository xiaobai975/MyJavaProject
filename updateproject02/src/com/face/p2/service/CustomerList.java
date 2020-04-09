package com.face.p2.service;

import com.face.p2.bean.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * 
* @Description   为Customer对象的管理模块，内部用数组管理一组Customer对象，
* 并提供相应的添加、修改、删除和遍历方法，供CustomerView调用
* @author JUNSHI  Email:405773808@qq.com  
* @version   V1.0
* @date 2020年2月19日上午12:25:55  
*
 */


public class CustomerList {

//	private Customer[] customers; //用来保存客户对象的数组
	private ArrayList<Customer> customers;
//	private int total;//记录已保存客户对象的数量


	public CustomerList() {
		customers = new ArrayList<>();
	}
	
	/**
	 * 
	* @Description    添加新的客户到数组中最后一个客户对象记录之后
	* @author JUNSHI  
	* @date 2020年2月19日上午12:44:52  
	* @param customer 指定要添加的客户对象 
	* @return 添加成功返回true；false表示数组已满，无法添加
	 */
	public boolean addCustomer(Customer customer) {//存放的是对象

//			customers[total] = customer;
//			total++;
		//或者
		
		//使得再去添加的时候total已经添加了前一个添加的客户客户个数要加1
		customers.add(customer);
			return true;
	}
	
	
	/**
	 * 
	* @Description   替换指定索引客户的账户资料
	* @author JUNSHI  
	* @date 2020年2月19日下午12:51:44  
	* @param index 指定所替换对象在数组中的位置，从0开始
	* @param cust 指定替换的新客户对象 
	* @return 替换成功返回true；false表示索引无效，无法替换
	 */
	public boolean replaceCustomer(int index,Customer cust) { //修改功能可用到


		if(index < 0 || index >= customers.size()) { //如果index指定的对象不在数组范围内
			return false; //表示索引无效
		}
//		//在索引内的情况下，修改对象
//		customers[index] = cust;//修改customers[]指定客户 信息就为cust //cust是个对象

		customers.set(index,cust);

		return true;

		
	}
	
	/**
	 * 
	* @Description   从数组中删除指定索引位置的客户对象记录
	* @author JUNSHI  
	* @date 2020年2月19日下午12:51:52  
	* @param index 指定所删除对象在数组中的索引位置，从0开始
	* @return 删除成功返回true；false表示索引无效，无法删除
	 */
	public boolean deleteCustomer(int index) { //涉及到删除数组   用于删除功能中
		if(index < 0 || index >= customers.size()) {//如果index指定的对象不在数组范围内
			return false;//删除无效
		}
		customers.remove(index);

		//从index开始遍历         数组索引值从0开始，例total长度为10，实际数组里索引最后一位为9
//		for(int i = index;i < total - 1;i++) { // i < 却!= total - 1是因为最后一个数组要设为null
//			customers[i] = customers[i + 1];//已有用户的最后每个客户往前覆盖
//		}
////		customers[total - 1] = null;//覆盖完后将已有客户最后一位设为null
////		total--; // 使得再去删除的时候total已经减去了前一个删除的客户
//		//或者
//		customers[--total] = null;//需要先减1
		return true;//删除成功
	}
	
	/**
	 * 
	* @Description   获取数组中记录的所有客户对象
	* @author JUNSHI  
	* @date 2020年2月19日下午12:51:57  
	* @return	Customer[] 数组中包含了当前所有客户对象，该数组长度与对象个数相同
	 */
	public ArrayList<Customer> getAllCustomers() { //得到所有客户信息
//		//需要返回数组，customers数组内可能有null所以new一个total长度的放已有客户信息
//		Customer[] custs = new Customer[total];
//		for(int i = 0;i < total;i++) { //遍历对象数组信息 可以将cust.length换位total
//			custs[i] = customers[i]; //将customers[]里已有的数组信息的地址值赋给custs
//		}
//		return custs; //返回个对象数组 Customer是个对象 装的都是客户信息
		ArrayList<Customer> list = new ArrayList<>();

		Iterator<Customer> iterator = list.iterator();

		while (iterator.hasNext()){
			Customer next = iterator.next();
			list.add(next);
		}

		return list;
	}
	
	/**
	 * 
	* @Description   获取指定索引位置的客户对象记录
	* @author JUNSHI  
	* @date 2020年2月19日下午12:52:01  
	* @param index 指定所要获取的客户在数组中的索引位置，从0开始
	* @return	如果找到了元素，则返回，如果没有找到则返回null，封装了客户信息的Customer对象
	 */
	public Customer getCustomer(int index) {
		if(index < 0 || index >= customers.size()) { //判断index指定的对象是否在数组范围内
			return null;
		}
//		return customers[index];//返回custmoers[]中需要查找的指定客户对象信息

		return customers.get(index);


	}
	/**
	 * 
	* @Description   获取储存的客户的数量
	* @author JUNSHI  
	* @date 2020年2月19日下午12:52:05  
	* @return
	 */
	public int getTotal() {
		return customers.size();
//		return customers.length;//是错误的
	}
	

	
}
