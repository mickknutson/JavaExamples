package com.baselogic.tutorials.reference.interfaces;

import java.sql.Connection;

/**
 * Created by mickknutson on 1/9/17.
 */
public class MyParent implements InterfaceA, InterfaceB {

    Connection conn;

    @Override
    public void fooBar(){
        return;
    }


    public static String foo(){
        return "parent foo";
    }

    public static void main(String[] args){

        int i = Integer.valueOf(123);
        int j = Integer.valueOf("456");

        long k = Long.valueOf(123);
        long l = Long.valueOf("456");

        System.out.println("MyParent foo: " + MyParent.foo());
        System.out.println("MyChild foo: " + MyChild.foo());
    }
}
