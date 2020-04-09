package com.java.review2;

import org.junit.Test;

import java.util.*;

/**
 * @author JUNSHI 405773808@qq.com
 * @description:
 * @create 2020-03-26 18:30
 */
public class ArrayListTest {

    @Test
    public void test() {

        ArrayList<Integer> list = new ArrayList<>();

        list.add(123);
        list.add(12);
        list.add(13);
        list.add(1223);

        int size = list.size();
        System.out.println(size);

        list.set(1, 1234);
        list.remove(2);
        list.sort(Integer::compareTo);


        list.forEach(System.out::println);
        System.out.println(list.toString());

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }


    }

    @Test
    public void test1() {

        Map<String, Integer> map = new HashMap<>();

        map.put("XIAOBAI", 89);
        map.put("JUNSHI", 98);
        map.put("Marry", 67);
        map.put("Jerry", 74);
        map.put("Tom", 83);


        map.forEach((key, value) -> {
            System.out.println(key + "---" + value);
        });

        Set<Map.Entry<String, Integer>> set = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            Integer value = entry.getValue();
            String key = entry.getKey();
            System.out.println(key + " --->" + value);

        }

    }



}

