package com.jdbc.exer;

import com.jdbc.exer.bean.Student;
import com.jdbc.exer.util.JDBCUtils;
import com.jdbc.exer.util.QueryUtil;
import com.jdbc.exer.util.UpdateUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-04-06 0:17
 */
public class ExerTest1 {

    @Test
    public void testInsert() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入你要插入的姓名：");
        String name = scanner.nextLine();

        System.out.print("请输入你要插入的email:");
        String email = scanner.nextLine();

        System.out.print("请输入你要插入的birth:");
        String birth = scanner.nextLine();

        String sql = "insert into customers(id,name,email,birth)values(?,?,?,?)";

//        UpdateUtil.updateSql(sql, name, email, birth);


    }

    public static void main(String[] args) {

//        System.out.println("请选择你要输入的类型：");
//        System.out.println("a:准考证号");
//        System.out.println("b：身份证号");
//        Scanner scan = new Scanner(System.in);
//        String selection = scan.next();
//
//        if ("a".equalsIgnoreCase(selection)) {//忽略大小写
//            System.out.print("请输入你准考证号：");
//            String examCard = scan.next();
//
//            String sql = "select FlowID flowId,Type type,IDCard IDCard,ExamCard examCard,StudentName studentName,Location location," +
//                    "Grade grade from examstudent where examCard = ? ";
//
//            Student oneQuery = QueryUtil.getOneQuery(Student.class, sql, examCard);
//            if (oneQuery != null){
//
//                System.out.println(oneQuery);
//            }else{
//                System.out.println("没有此人");
//            }
//
//        } else if ("b".equalsIgnoreCase(selection)) {
//            System.out.println("请输入你身份证号：");
//            String examCard = scan.next();
//
//            String sql = "select FlowID flowId,Type type,IDCard IDCard,ExamCard examCard,StudentName studentName,Location location," +
//                    "Grade grade from examstudent where IDCard = ? ";
//
//            Student oneQuery = QueryUtil.getOneQuery(Student.class, sql, examCard);
//            if (oneQuery != null){
//
//                System.out.println(oneQuery);
//
//            }else{
//                System.out.println("没有此人");
//            }
//
//
//        } else {
//            System.out.println("你输入的查询有误，请重新输入：");
//        }


//        System.out.println("****************************************************************");

//        System.out.println("请输入学生的考号：");
//        Scanner scan = new Scanner(System.in);
//        String examCard = scan.next();
//        String sql = "select examCard from examstudent where examCard = ?";
//
//        Student oneQuery = QueryUtil.getOneQuery(Student.class, sql, examCard);
//        boolean isFlag = true;
//
//        while (isFlag) {
//            if (oneQuery != null) {
//                System.out.println("请输入需要删除的学生的考号：");
//                String examCard1 = scan.next();
//                String sql1 = "delect from examstudent where examCard = ?";
//                int i = UpdateUtil.updateSql(sql1, examCard1);
//                if (i > 0) {
//                    System.out.println("删除成功！");
//                } else {
//                    System.out.println("没有此人，请重新输入考号：");
//                    return;
//                }
//
//
//            } else {
//                System.out.println("没有此人！请重新输入：");
//                break;
//            }
//
//
//        }
//        char c;
////        for (; ; ) {
////            String str = readKeyBoard(1, false);
////            c = str.charAt(0);
////            if (c != '1' && c != '2' &&
////                    c != '3' && c != '4' && c != '5') {
////                System.out.print("选择错误，请重新输入：");
////            } else break;
////        }
////        return c;


        System.out.println("请输入学生的考号：");
        ExerTest1 e = new ExerTest1();
        while (true){
            Scanner scan = new Scanner(System.in);
            String examCard = scan.next();
            String sql = "delete from examstudent where examCard = ?";
            int count = e.updateSql(sql,examCard);

            if (count > 0 ){
                System.out.println("删除成功！");
                break;
            }else{
                System.out.println("查此人请重新输入：");
            }

        }


    }
    public int updateSql(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            //得到连接
            connection = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = connection.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {

                ps.setObject(i + 1,args[i]);
            }
            //执行

//            ps.execute();

            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(connection,ps);
        }
        return 0;
    }


}
