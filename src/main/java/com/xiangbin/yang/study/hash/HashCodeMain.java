package com.xiangbin.yang.study.hash;

import static java.lang.System.out;

/**
 * @author xiangbin.yang
 * @since 2017/10/31
 */
public class HashCodeMain {
    public static void main(String[] args) {
        String str = "ab";
        out.print("String a hashcode: ");
        out.println(str.hashCode());

        Integer i = 1;
        out.print("Integer i hashcode: ");
        out.println(i.hashCode());

        Object o = new Object();
        out.print("Object o hashcode: ");
        out.println(o.hashCode());
    }
}
