package com.jdbc.bean;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-07 22:27
 */
public class Team {

    private int ID;//员工id
    private String name;//姓名
    private int age;//年龄
    private double salary;//工资
    private String position;//职位
    private String status;//状态
    private double bonus;//奖金
    private double stock;//股票
    private String model;//设备
    private String display;//显示器

    public Team() {
    }

    public Team(int ID, String name, int age, double salary, String position, String status, String model, String display) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.model = model;
        this.display = display;
    }

    public Team(int ID, String name, int age, double salary, String position, String status, double bonus, String model, String display) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.bonus = bonus;
        this.model = model;
        this.display = display;
    }

    public Team(int ID, String name, int age, double salary, String position, String status, double bonus, double stock) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.bonus = bonus;
        this.stock = stock;
    }

    public Team(int ID, String name, int age, double salary, String status, double bonus, double stock, String model, String display) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
        this.bonus = bonus;
        this.stock = stock;
        this.model = model;
        this.display = display;
    }

    public Team(String name, int age, double salary, String position, String status, double bonus, double stock, String model, String display) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.bonus = bonus;
        this.stock = stock;
        this.model = model;
        this.display = display;
    }

    public Team(int ID, String name, int age, double salary, String position, String status, double bonus, double stock, String model, String display) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.status = status;
        this.bonus = bonus;
        this.stock = stock;
        this.model = model;
        this.display = display;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Override
    public String toString() {
        return "Team{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", position='" + position + '\'' +
                ", status='" + status + '\'' +
                ", bonus=" + bonus +
                ", stock=" + stock +
                ", model='" + model + '\'' +
                ", display='" + display + '\'' +
                '}';
    }
}
