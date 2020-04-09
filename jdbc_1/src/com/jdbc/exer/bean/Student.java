package com.jdbc.exer.bean;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 11:44
 */
public class Student {

    private int flowId;
    private int type;
    private String IDCard;
    private String examCard;
    private String studentName;
    private String location;
    private int grade;

    public Student() {
    }

    public Student(int flowId, int type, String IDCard, String examCard, String studentName, String location, int grade) {
        this.flowId = flowId;//流水号
        this.type = type;//四六级
        this.IDCard = IDCard;//身份证号
        this.examCard = examCard;//准考证号
        this.studentName = studentName;//学生姓名
        this.location = location;//住址城市
        this.grade = grade;//成绩
    }

    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getExamCard() {
        return examCard;
    }

    public void setExamCard(String examCard) {
        this.examCard = examCard;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        System.out.println("=============查询结果============");
        return "流水号：" + flowId + "\n四/六级：" + type + "\n身份证号：" + IDCard +
                "\n准考证号：" + examCard + "\n学生姓名：" + studentName + "\n区域：" +
                location + "\n成绩：" + grade;
    }
}
