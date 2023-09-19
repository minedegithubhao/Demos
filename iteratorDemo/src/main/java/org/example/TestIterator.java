package org.example;

import java.util.*;

public class TestIterator {

    public static void main(String[] args) {
        /*
         *  Arrays.asList 这个方式获取的集合,该集合底层没有重写一些方法,所以报错
         *  Exception in thread "main" java.lang.UnsupportedOperationException
         */
        List<String> strings = Arrays.asList("1", "2", "3", "4");

        //新建个集合来进行测试
        List<String> list = new ArrayList<>();
        list.addAll(strings);

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            if ("2".equals(iterator.next())){
                iterator.remove();
            }
        }
        //[1, 3, 4]
        System.out.println(list);

        for (String string : list) {
            if ("3".equals(string)){
                list.remove(string);
            }
        }
        System.out.println(list);
    }
}
