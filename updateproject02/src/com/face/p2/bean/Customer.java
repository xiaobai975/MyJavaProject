package com.face.p2.bean;

/**
 * 
 * @Description Customer为实体对象，用来封装客户信息
 * @author JUNSHI Email:405773808@qq.com
 * @version
 * @date 2020年2月19日上午12:26:38
 *
 */
public class Customer {

//	public static void main(String[] args) {
//
//		
//		Customer c1 = new Customer("明",'男',18,"13438844","asfdfff");
//		
//		System.out.println(c1.getAge() + c1.getEmail() + c1.getGender() + c1.getPhone() + c1.getName());
//
//	}
	
	
	private String name;// 客户姓名
	private char gender;// 性别
	private int age;// 年龄
	private String phone;// 电话号码
	private String email;// 电子邮箱

	//构造器
	public Customer(String name, char gender, int age, String phone, String email) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {// get方法
		return name;
	}

	public char getGender() {// set方法
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

}