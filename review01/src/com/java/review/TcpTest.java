package com.java.review;

import com.sun.org.apache.bcel.internal.generic.LNEG;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;
import org.junit.Test;

import java.io.*;
import java.net.*;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-24 23:33
 */
public class TcpTest {

    @Test
    public void test() {


        Socket socket = null;
        OutputStream outputStream = null;
        try {
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8899);

            outputStream = socket.getOutputStream();
            outputStream.write("你好我是靓仔".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {

                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {

                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Test
    public void test1()  {

        try (
                ServerSocket serverSocket = new ServerSocket(8899);

                Socket accept = serverSocket.accept();

                InputStream inputStream = accept.getInputStream();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ){


            byte[] buffer = new byte[20];
            int len;
            while ((len = inputStream.read(buffer)) != -1){
                byteArrayOutputStream.write(buffer,0,len);
            }

            System.out.println(byteArrayOutputStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() throws Exception {

        DatagramSocket socket = new DatagramSocket();

        String str = "UDP方式发送的信息";
        byte[] data = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket dp = new DatagramPacket(data, data.length, inet, 9090);
        socket.send(dp);
        socket.close();

    }

    @Test
    public void test3() throws Exception {

        DatagramSocket socket = new DatagramSocket(9090);

        byte[] buffer = new byte[100];

        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);

        socket.receive(packet);
        System.out.println(new String(packet.getData(),0,packet.getLength()));

        socket.close();

    }
    @Test
    public void test9() throws UnknownHostException {

       String hostName = String.valueOf(InetAddress.getLocalHost());
        System.out.println(hostName);
    }
}
