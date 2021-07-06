package me.wjy;

import java.util.Scanner;
import java.util.Stack;

/**
 * 逃出迷宫.
 * 给定迷宫, 输出最短路径;
 * 采用广度优先算法 (BFS), 与 LeetCode 中 1293 题类似, 但是 LeetCode 中只要求输出最短路径是多少.
 * 本题目还要求输出路径, 所以需要将路径存储起来.
 * 该程序还自定义了 3 个类:
 * 1. MyQueue 队列, 用于执行 BFS 算法.
 * 2. Index 将二维数组的两个索引包装成一个类.
 * 3. IndexContainer 索引容器, 用于装已经走过的格子的索引.
 *
 * @author 王金义
 */
public class Maze {
    /**
     * 迷宫1
     */
    private static final char[][] MAZE_1 = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 1, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    /**
     * 迷宫2
     */
    private static final char[][] MAZE_2 = {
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, '=', 1, 1, 1, 1, 1, 1, '$', 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 2, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1}
    };

    /**
     * 迷宫的引用, 默认为迷宫 1
     */
    private static char[][] maze = MAZE_1;
    /**
     * 存储已经走过的格子的容器
     */
    private static final IndexContainer INDEX_CONTAINER = new IndexContainer();
    /**
     * 队列类
     */
    private static final MyQueue MY_QUEUE = new MyQueue();
    //    private static int lengthOfShortestPath = 0;

    /**
     * 逃出迷宫,
     * @param index 索引包装类
     * @param len 走过的长度 (并没有输入和参与计算, 只是用于判断是否为入口)
     * @return
     */
    public static Index escape(Index index, int len) {
        INDEX_CONTAINER.put(index);

        int x = index.getX();
        int y = index.getY();

        // 如果是入口
        /* 迷宫的入口一定在边界, 且只能有一个方向可以走 (因为入口一定在边界, 且只有一个入口 (不包含出口)).
            那么迷宫入口则一定只有一个方向可以走.
        * */
        if (len == 0) {
            // 找到第一步要走的位置, 将下一步的位置作为输入递归调用自己
            Index step1 = null;
            if (x == 0 || x == maze.length - 1) {
                x += x == 0 ? 1 : -1;
                step1 = new Index(x, index.getY());
                step1.setFather(index);
            } else if (y == 0 || y == maze[0].length - 1) {
                y += y == 0 ? 1 : -1;
                step1 = new Index(index.getX(), y);
                step1.setFather(index);
            }
            INDEX_CONTAINER.put(index);
            INDEX_CONTAINER.put(step1);
            return escape(step1, ++len);
        }

        // 判断是否到达终点, 如果到终点, 返回终点的 node.
        // 判断标准就是下一步是否会造成数组越界
        if (x == 0 || y == 0 || x == maze.length - 1 || y == maze[1].length - 1) {
            return index;
        }

        // 当前格子的四个方向的引用
        Index right, bottom, left, top;
        // 因为要判断四个方向的格子是否有被走过, 如果走过的话, 则可以从走过的格子的容器里面直接拿
        // 而不用重新 new 对象, 如果没被走过, 再创建新的对象.
        Index tempRight, tempBottom, tempLeft, tempTop;

        // 走过的直接从容器里拿, 没走过的直接拿
        tempRight = INDEX_CONTAINER.containsKey(index.getX(), index.getY() + 1);
        tempBottom = INDEX_CONTAINER.containsKey(index.getX() + 1, index.getY());
        tempLeft = INDEX_CONTAINER.containsKey(index.getX(), index.getY() - 1);
        tempTop = INDEX_CONTAINER.containsKey(index.getX() - 1, index.getY());

        // 再将四个方向的引用指向 tempX 完成赋值
        right = tempRight == null ? index.getRight() : tempRight;
        bottom = tempBottom == null ? index.getBottom() : tempBottom;
        left = tempLeft == null ? index.getLeft() : tempLeft;
        top = tempTop == null ? index.getTop() : tempTop;

        // 判断该方向是否可以走 (是否为1), 如果可以走, 则将其加入队列, 等待递归
        // 并且将该方向的节点父节点的引用指向当前节点
        if (pushOrNot(right, maze)) {
            right.setFather(index);
        }
        if (pushOrNot(bottom, maze)) {
            bottom.setFather(index);
        }
        if (pushOrNot(left, maze)) {
            left.setFather(index);
        }
        if (pushOrNot(top, maze)) {
            top.setFather(index);
        }

        // 将队列首的节点弹出, 将作为下一次递归的输入
        return escape((Index) MY_QUEUE.pop().getElement(), ++len);
    }

    private static boolean pushOrNot(Index index, char[][] maze) {
        if (maze[index.getX()][index.getY()] == 0 && !INDEX_CONTAINER.containsKey(index)) {
            MY_QUEUE.push(index);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // 询问用哪一个迷宫.
        Scanner sc = new Scanner(System.in);
        System.out.println("输入想要走的迷宫 (1 或 2), 输入其他字符都为 1:");
        String flag = sc.next();
        Index escape;
        if ("1".equals(flag)) {
            maze = MAZE_1;
            escape = escape(new Index(0, 18), 0);
        } else if ("2".equals(flag)) {
            maze = MAZE_2;
            escape = escape(new Index(0, 1), 0);
        } else {
            escape = escape(new Index(0, 18), 0);
        }
        // 输入起点执行
        // 因为结果是倒着的路径, 所以用栈存储.
        Stack stack = new Stack();
        stack.push(escape);
        while (true) {
            if (escape.getFather() != null) {
                escape = escape.getFather();
                stack.push(escape);
            } else {
                break;
            }
        }
        // 依次弹出, 则是最短路径
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
