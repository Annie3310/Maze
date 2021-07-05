package me.wjy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Exam1 {
    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 2, 3, 1, 3};
        Map map1 = new HashMap();
        Map map2 = new HashMap();
        for (int i = 0; i < integers.length; i++) {
            if (!map1.containsKey(integers[i]) && !map2.containsKey(integers[i])) {
                map1.put(integers[i],i);
                continue;
            }
            if (map1.containsKey(integers[i]) && !map2.containsKey(integers[i])) {
                map2.put(integers[i],i);
                continue;
            }
            if (map1.containsKey(integers[i]) && map2.containsKey(integers[i])) {
                map1.remove(integers[i]);
                continue;
            }
        }
        Set set1 = map1.keySet();
        Set set2 = map2.keySet();
        Iterator iterator = set1.iterator();
        Iterator iterator2 = set2.iterator();

        while (iterator.hasNext()) {
            set2.remove(iterator.next());
        }

        Iterator iterator1 = set2.iterator();
        System.out.println(iterator1.next());

    }
}
