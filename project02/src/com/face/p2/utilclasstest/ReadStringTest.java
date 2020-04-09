package com.face.p2.utilclasstest;

import com.face.p2.util.CMUtility;

public class ReadStringTest {
	public static void main(String[] args) {
		Name n = new Name();
		n.setName("小朱");
		System.out.println(n.getName());
		System.out.println("请输入需要更改后的姓名：（" + n.getName() + "）：");
		String newName = CMUtility.readString(3, n.getName());
		System.out.println(newName);
		System.out.println(n.getName());
	}

}
class Name{
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}