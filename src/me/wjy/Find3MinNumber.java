package me.wjy;

import java.util.Iterator;
import java.util.TreeSet;

public class Find3MinNumber {
    public static void main(String[] args) {
        Integer[] integers = {10, 12, 24, 13, 14, 1, 8};
        TreeSet treeSet = new TreeSet();
        for (int i = 0; i < integers.length; i++) {
            treeSet.add(integers[i]);
        }
        Iterator iterator = treeSet.iterator();
        for (int i = 0; i < 3; i++) {
            System.out.println(iterator.next());
        }
    }
}
