package me.wjy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * 用来存储已经走过的格子
 * 基于 HashMap 实现, 使用 <Index, Integer>, 因为只需要 Key(需要 HashMap 的快速查找), 所以值都为 0
 *
 * @author 王金义
 */
public class IndexContainer {
    /**
     * 用 HashMap 存, 可以做得到查找 O(1)时间复杂度
     */
    private HashMap<Index, Integer> hashMap = new HashMap();

    /**
     * 查找传入的格子是否已经走过
     * 为防止大量创建相同的对象 (因为重写过 Index 的 hashCode 与 equals 所以可能导致大量创建值相同而内存地址不同的相同对象)
     * 使用 x 和 y 进行判断 (基于 Index 的 equals 方法),可以防止在 Maze 类中创建过多的对象
     * @param x 纵坐标
     * @param y 横坐标
     * @return 存在则返回该节点, 不存在返回 null
     */
    public Index containsKey(int x, int y) {
        Set<Index> indices = hashMap.keySet();
        Iterator<Index> iterator = indices.iterator();
        while (iterator.hasNext()) {
            Index temp = iterator.next();
            if (temp.getX() == x && temp.getY() == y) {
                return temp;
            }
        }
        return null;
    }

    public boolean containsKey(Index index) {
        return hashMap.containsKey(index);
    }

    /**
     * 添加一个索引进入该容器
     *
     * @param index 要添加的索引
     */
    public void put(Index index) {
        // 所有的值都传入 0, 因为只需要 Key.
        hashMap.put(index, 0);
    }

    public int size() {
        return hashMap.size();
    }

}
