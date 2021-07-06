package me.wjy;

/**
 * 手动实现的最小队列类, 使用链表形式
 *
 * @author 王金义
 */
public class MyQueue {
    /**
     * 队列头
     */
    private Node first;
    /**
     * 队列大小
     */
    private int size;

    /**
     *
     * @param object
     */
    public MyQueue(Object object) {
        this.first = new Node(object);
        size++;
    }

    public MyQueue() {

    }

    /**
     * 将一个值加入队列尾
     * @param object 值
     * @return 刚加入的节点
     */
    public Node push(Object object) {
        // 使用传入的值新建 Node
        Node node = new Node(object);
        // 如果头引用为空, 则将该节点赋给 first
        if (first == null) {
            first = node;
            size++;
            return node;
        }
        // 如果头不为空,则将值加入队尾
        Node right = first;
        while (true) {
            if (right.right == null) {
                right.right = node;
                node.left = right;
                break;
            }
            right = right.right;
        }
        size++;
        return right;
    }

    /**
     * 弹出队列头, 将 first 指向当前 first 的右节点, 列队头无引用, 等待回收
     * @return pop 之前的头节点
     */
    public Node pop() {
        Node node = first;
        first = first.right;
        size--;
        return node;
    }

    public int size() {
        return this.size;
    }

    /**
     * 输出整个队列
     * @return
     */
    @Override
    public String toString() {
        // 如果队列头为空, 则队列为空
        if (first == null) {
            return null;
        }
        // 使用队列头新建一个 StringBuffer 用于最终输出
        StringBuffer stringBuffer = new StringBuffer(first.toString());
        // 右节点
        Node right = first;
        // 循环将右节点加入 StringBuffer
        while (right.right != null) {
            right = right.right;
            stringBuffer.append(right.toString());
        }
        return String.valueOf(stringBuffer);
    }

    /**
     * 队列的内部类
     */
    class Node{
        /**
         * 左节点
         */
        private Node left;
        /**
         * 节点值
         */
        private Object element;
        /**
         * 右节点
         */
        private Node right;

        /**
         * 设置节点值
         * @param element
         */
        public Node(Object element) {
            this.element = element;
        }

        public Node() {
        }

        /**
         * 只输入节点值
         * @return
         */
        @Override
        public String toString() {
            return element.toString();
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Object getElement() {
            return element;
        }

        public void setElement(Object element) {
            this.element = element;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
