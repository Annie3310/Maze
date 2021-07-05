package me.wjy;

import java.util.HashMap;

/**
 * 用来存储已经走过的格子的索引
 * 因为要存储成对的数据, 还不可以用 HashMap, 所以自定义一个用 HashMap 的 Key 存储的容器
 * @author WangJ
 */
public class IndexContainer {
    /**
     * 用 HashMap 存, 可以做得到查找 O(1)时间复杂度
     */
    private HashMap hashMap = new HashMap();

    /**
     * 查找传入的格子是否已经走过
     * @param x 索引的纵坐标
     * @param y 索引的横坐标
     * @return 是否存在
     */
    public boolean containsIndex(int x, int y) {
        String s = this.getString(x, y);
        return hashMap.containsKey(s);
    }

    /**
     * 添加一个索引进入该容器
     * @param x
     * @param y
     */
    public void put(int x, int y) {
        // 所有的值都传入 0, 因为只需要 Key.
        hashMap.put(this.getString(x, y), 0);
    }

    /**
     * 格式化传入的索引, 使其可以被当前类的其他方法读取
     * @param x
     * @param y
     * @return
     */
    private String getString(int x, int y) {
        return x + "|" + y;
    }
}
