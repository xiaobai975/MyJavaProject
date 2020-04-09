package com.face.p2.view;

import com.face.p2.bean.Customer;
import com.face.p2.service.CustomerList;
import com.face.p2.util.CMUtility;

/**
 * @Description CustomerView为主模块，负责菜单的显示和处理用户操作
 * @author JUNSHI Email:405773808@qq.com
 * @version
 * @date 2020年2月19日上午12:25:20
 *
 */

public class CustomerView {
	// 创造一个长度为10的对象数组放置客户信息
	CustomerList customerList = new CustomerList(10);

	public CustomerView() {//customerView 的构造器 用来初始化客户信息
		// 新造一个对象 对象里放着了信息
		Customer customer = new Customer("小明", '男', 18, "13500000000", "2@qq.com"); 
		customerList.addCustomer(customer);// 调用存放信息的customerList列表加入customer信息对象

	}

	/**
	 * 
	 * @Description 《客户信息管理系统》
	 * @author JUNSHI
	 * @date 2020年2月19日下午10:02:56
	 * @param args
	 */
	public static void main(String[] args) {
		//main方法进入主程序
		CustomerView c = new CustomerView();//将view类功能实例化
		c.enterMainMenu();//加载程序主页面

//		CustomerList customerList = new CustomerList();

	}

	/**
	 * 
	 * @Description 显示主菜单
	 * @author JUNSHI
	 * @date 2020年2月19日下午2:47:40
	 */
	public void enterMainMenu() {

		boolean isFlag = true;//标识

		while (isFlag) {

			System.out.println("-----------------客户信息管理软件-----------------\n");
			System.out.println("                  1 添 加 客 户");
			System.out.println("                  2 修 改 客 户");
			System.out.println("                  3 删 除 客 户");
			System.out.println("                  4 客 户 列 表");
			System.out.println("                  5 退           出\n");
			System.out.print("请选择(1-5)：");
			//读取键盘1-5
			char into = CMUtility.readMenuSelection();

			switch (into) {
			// 方法中可以直接调用方法
			case '1':
				addNewCustomer();    //添加客户功能
				break;
			case '2':
				modifyCustomer();	//修改客户功能
				break;
			case '3':
				deletCustomer();	//删除客户功能
				break;
			case '4':
				listAllCustomers();	//获取客户列表功能
				break;
			case '5':
				System.out.print("你确定退出系统Y/N：");
				char close = CMUtility.readConfirmSelection();
				if (close == 'Y') {
					isFlag = false;
					System.out.println("该系统已退出！");
				}
				break;

			}

		}
	}

	/**
	 * 
	 * @Description 添加客户功能
	 * @author JUNSHI
	 * @date 2020年2月19日下午10:05:46
	 */
	private void addNewCustomer() {
		System.out.println("---------------------添加客户---------------------\n");
		System.out.print("姓名：");
		String name = CMUtility.readString(10); //读取一个长度不为10的String类型的值
		System.out.print("性别：");
		char gender = CMUtility.readChar();//获取性别
		System.out.print("年龄：");
		int age = CMUtility.readInt();//获取年龄
		System.out.print("电话 ：");
		String phone = CMUtility.readString(13);
		System.out.print("邮箱：");
		String email = CMUtility.readString(30);
		// 将上述数据封装到新客户信息对象之中
		Customer customer = new Customer(name, gender, age, phone, email);

		boolean isSuccess = customerList.addCustomer(customer);//指定要添加的客户对象  返回类型是Boolean型用来提示

		if (isSuccess) {

			System.out.println("---------------------添加完成----------------------\n");

		} else {

			System.out.println("------------------列表已满添加失败-------------------\n");

		}

	}

	/**
	 * 
	 * @Description 修改客户功能
	 * @author JUNSHI
	 * @date 2020年2月19日下午10:06:06
	 */
	private void modifyCustomer() {
		System.out.println("---------------------修改客户---------------------\n");
		Customer cust;// 创造一个Customer类型的对象
		int isChange;// 获取修改的编号

		for (;;) {
			System.out.print("请选择需要修改的账户编号(-1)退出：");
			isChange = CMUtility.readInt();
			if (isChange == -1) {// 若输入
				return; // 结束词此方法 返回主界面
			}

			// 对象赋值为 指定所要获取的客户在数组中的索引位置，寻找到元素则返回该元素的对象赋给cust
			cust = customerList.getCustomer(isChange - 1);// 真实排列序号 - 1 数组索引从0开始
			if (cust == null) {
				System.out.println("无法找到该用户");
			} else {// 找到了相应的客户
				break;
			}
		} // 结束循环

		// 修改信息
		System.out.print("姓名（" + cust.getName() + "）：");
		String newName = CMUtility.readString(10, cust.getName());

		System.out.print("性别（" + cust.getGender() + "）：");
		char newGender = CMUtility.readChar(cust.getGender());

		System.out.print("年龄（" + cust.getAge() + "）：");
		int newAge = CMUtility.readInt(cust.getAge());

		System.out.print("电话（" + cust.getPhone() + "）：");
		String newPhone = CMUtility.readString(13, cust.getPhone());

		System.out.print("邮件（" + cust.getEmail() + "）：");
		String newEmail = CMUtility.readString(30, cust.getEmail());
		//newCust对象用来装改变信息后的对象
		Customer newCust = new Customer(newName, newGender, newAge, newPhone, newEmail);
		boolean isFlag = customerList.replaceCustomer(isChange - 1, newCust);//替换指定索引客户的账户资料 替换后的对象为newCust
		if (isFlag) {

			System.out.println("---------------------修改完成---------------------\n");
		} else {
			System.out.println("---------------------修改失败---------------------\n");

		}
	}

	/**
	 * 
	 * @Description 删除客户功能
	 * @author JUNSHI
	 * @date 2020年2月19日下午10:06:29
	 */
	private void deletCustomer() {
		System.out.println("---------------------删除客户---------------------\n");
		int number;// 用来获取键盘的数字进行操作
		Customer customer;// 创造一个封装了账户信息的对象

		System.out.print("请选择需要删除的账户（-1退出）：");
		for (;;) {// 一直寻找该用户
			number = CMUtility.readInt();
			if (number == -1) { // 如果是负一则退出
				return;// 返回主页面
			} // 不是负一的情况下

			// 获取用户
			customer = customerList.getCustomer(number - 1); // 获取指定索引的客户对象赋给customer
			if (customer == null) {// 如果获取的对象是null
				System.out.println("未找到此用户");
			} else {
				break;// 找到客户后结束循环，表明已经找到对象
			}
		}
		// 找到对象后询问是否删除
		System.out.println("是否确定删除（Y/N）：");
		char delete = CMUtility.readConfirmSelection();// 该方法是获取Y/N
		if (delete == 'Y') {// 如果确定删除则
			// 该方法用来删除指定索引客户的信息 - 1 是因为实际上数组从0开始
			boolean deleteSuccess = customerList.deleteCustomer(number - 1);//返回boolean型
			if (deleteSuccess) {

				System.out.println("---------------------删除完成---------------------\n");
				
			} else {
				System.out.println("---------------------删除失败---------------------\n");
			}
		}

	}

	/**
	 * 
	 * @Description 显示客户列表功能
	 * @author JUNSHI
	 * @date 2020年2月19日下午10:06:44
	 */
	private void listAllCustomers() {
		System.out.println("---------------------客户列表---------------------\n");

		int total = customerList.getTotal();//该方法是记录客户列表里有多少客户信息
		if (total == 0) {//total 为0
			System.out.println("没有客户记录！");
		} else {
			//total 不为0的情况下输出客户列表的信息
			System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
			Customer[] custs = customerList.getAllCustomers();// 返回所有客户对象在一个新的数组中 防止有空值
			for (int i = 0; i < custs.length; i++) { // 遍历客户列表信息 不能加 = 否则角标越界
				Customer cust = custs[i]; // Customer中每个对象的信息都赋给新的数组custs中每个对象
				System.out.println((i + 1) + "\t" + cust.getName() + "\t" + cust.getGender() + "\t" + cust.getAge()
						+ "\t" + cust.getPhone() + "\t" + cust.getEmail());// （i + 1）是编号
			}
		}
		System.out.println("-------------------客户列表完成-------------------\n");

	}

}


