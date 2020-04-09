package com.java.review1;


import sun.plugin2.message.ShowDocumentMessage;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-26 7:59
 */
public class EnumsTest1 {

    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        System.out.println(summer.toString());

        Season1[] values = Season1.values();
        for (Season1 s : values){
            System.out.println(s);
        }
        Season1 winter = Season1.valueOf("WINTER");
        System.out.println(winter);
    }


}
interface info{
    void show();
}
enum Season1 implements info{

    SPRING("春天","村田"){
        @Override
        public void show() {

        }
    },
    SUMMER("夏天","夏天"){
        @Override
        public void show() {

        }
    },
    AUTUMN("秋天","秋天"){
        @Override
        public void show() {

        }
    },
    WINTER("冬天","冬天"){
        @Override
        public void show() {

        }
    };

    private final String SeansonName;
    private final String SeansonDes;

    private Season1(String seansonName,String seansonDes){
        this.SeansonDes = seansonDes;
        this.SeansonName = seansonName;
    }


    public String getSeansonName() {
        return SeansonName;
    }

    public String getSeansonDes() {
        return SeansonDes;
    }

}
