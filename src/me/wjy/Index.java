package me.wjy;

import java.util.Objects;

/**
 * 原来保存二维数组的索引及所经过的路线.
 *
 * @author 王金义
 */
public class Index {
    /**
     * 前驱节点, 也就是路径的上一步
     */
    private Index father;
    /**
     * 二维数组的纵坐标
     */
    private int x;
    /**
     * 二维数组的横坐标
     */
    private int y;

    public Index(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 查找其父节点, 也就是上一步的位置
     * @return
     */
    public Index getFather() {
        return father;
    }

    public void setFather(Index father) {
        this.father = father;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    //生成各方向的下一步

    public Index getRight() {
        return new Index(this.x, this.y + 1);
    }

    public Index getBottom() {
        return new Index(this.x + 1, this.y);
    }

    public Index getLeft() {
        return new Index(this.x, this.y - 1);
    }

    public Index getTop() {
        return new Index(this.x - 1, this.y);
    }

    /**
     * 格式化输出
     * @return
     */
    @Override
    public String toString() {
        return ("(" + x + "," + y + ")");
    }

    /**
     * 重写 equals 和 hashCode, 如果两个对象的 x 和 y 都相等, 则认为两个对象相等.
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        Index index = (Index) o;
        return this.x == index.getX() && this.y == index.getY();
    }

    /**
     * 只用 x 和 y 的值参与 hash 运算
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
