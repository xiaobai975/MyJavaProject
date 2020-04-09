package com.java.review;

import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;
import org.junit.Test;
import sun.nio.cs.ext.GBK;

import java.io.*;
import java.nio.Buffer;


/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-25 10:06
 */
public class IOReview {

    @Test
    public void test() throws Exception {

        try (  BufferedReader br = new BufferedReader(new FileReader("hello.txt"));
               BufferedWriter bw = new BufferedWriter(new FileWriter("hello1.txt"));
        ){


            String data;
            while ((data = br.readLine()) != null){
                bw.write(data);
                bw.newLine();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test1() throws IOException {


        try ( FileInputStream fis = new FileInputStream(new File("hello.txt"));
              FileOutputStream fos = new FileOutputStream(new File("hello_gbk.txt"));

              InputStreamReader isr = new InputStreamReader(fis,"UTF8");
              OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos, "gbk");){


            char[] chars = new char[10];
            int len;
            while ((len = isr.read(chars)) != -1){
                outputStreamWriter.write(chars,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test3(){

        try ( DataOutputStream dos = new DataOutputStream(new FileOutputStream("hello.txt"));){
            dos.writeInt(21);
            dos.flush();
            dos.writeBoolean(true);
            dos.flush();
            dos.writeUTF("啊啊啊啊");
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void test2() throws IOException {

        DataInputStream dis = new DataInputStream(new FileInputStream("hello.txt"));

        int i = dis.readInt();
        boolean b = dis.readBoolean();
        String s = dis.readUTF();

        System.out.println(i);
        System.out.println(b);
        System.out.println(s);


    }

    @Test
    public void test4() throws IOException {

        ObjectOutputStream oops = new ObjectOutputStream(new FileOutputStream("hello.txt"));

        oops.writeObject(new String("帅"));
        oops.flush();

    }

    @Test
    public void test5() throws IOException, ClassNotFoundException {

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("hello.txt"));

        Object o = ois.readObject();
        String s = (String) o;
        System.out.println(s);


    }

    @Test
    public void test6() throws Exception {

        RandomAccessFile raf = new RandomAccessFile("hello.txt", "r");

        RandomAccessFile rw = new RandomAccessFile("hello2.txt", "rw");

        byte[] c = new byte[1];
        int len;
        while ((len = raf.read(c)) != -1){
            rw.write(c,0,len);
        }



    }
    
    @Test
    public void test7() {

        try ( RandomAccessFile raf = new RandomAccessFile("hello.txt","rw");

              ByteArrayOutputStream bos = new ByteArrayOutputStream();){

            raf.seek(3);

            byte[] bytes = new byte[12];
            int len;
            while ((len = raf.read(bytes))!= -1){

                bos.write(bytes,0,len);

            }

            raf.seek(3);
            raf.write("xyz".getBytes());

            raf.write(bos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void test8() throws Exception {

        RandomAccessFile rf = new RandomAccessFile(new File("hello.txt"),"rw");

        rf.seek(3);

        StringBuilder sbuilder = new StringBuilder((int) new File("hello.txt").length());
        byte[] bytes = new byte[10];
        int len;
        while ((len = rf.read(bytes)) != -1){
            sbuilder.append(new String(bytes,0,len));
        }

        rf.seek(3);
        rf.write("xyz".getBytes());

        rf.write(sbuilder.toString().getBytes());



    }
}
