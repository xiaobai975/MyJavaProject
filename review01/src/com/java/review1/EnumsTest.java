package com.java.review1;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-25 15:58
 */
public class EnumsTest {

    public static void main(String[] args) {

        Season spring = Season.SPRING;
        System.out.println(spring);

    }

}
class Season{

    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName,String seasonDesc){

        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;

    }

    public static final Season SPRING = new Season("春天","春天！");
    public static final Season SUMMER = new Season("夏天","夏天!");
    public static final Season AUTUMN = new Season("秋天","秋天!");
    public static final Season WINTER = new Season("冬天","冬天!");

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}