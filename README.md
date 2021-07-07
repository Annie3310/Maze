# 说明
要求给定一个二维数组组成的迷宫, 0 可以走, 1 为墙壁, 给定入口, 寻找走出迷宫的最短路径并输出.

# 类说明
### GetMaze
从文件中拿到迷宫 (必须是 Windows CRLF 换行符).

### Index
因为需要存储二维数组的路径, 但 Java 中没有能存储一对值的结构, 所以封装了一个类用来存储二维数组的索引, 还因为需要存储路径, 所以每个节点需要记住自己的父节点, 这样就可以通过终点的节点来回溯整个路径.

该类还重写了 hashCode 和 equals 方法, 因为如果该类的两个坐标相同, 则应该认为这两个类是相同的.

### IndexContainer
如果要记录迷宫的最短路径, 那么则一定需要记录已经走过的点, 因记录后需要多次查询, 则应该使用查找效率高的 HashMap 来存储, 所以本类使用 HashMap 的 Key 当作存储容器, 无关乎值.

该类相当于 HashMap 的代理类 (没有实现 Map 接口, 只代理了需要的功能).

使用 containsKey 方法接收横纵坐标来判断容器中是否存在该节点 (是否走过).

### MyQueue
自己实现的一个队列, 基于内部类 `Node`.

### Maze
迷宫的主类.

使用 BFS 算法来实现寻找最短路径问题, 使用递归而不是使用循环.

# 遇到的问题
问题: 
> 因为每个点可以走的路有 4 条, 要判断下一个点是否可以走 (是否已经走过, 是否是墙), 如果每次递归调用时都重新 `new` 一个新的 `Index` 对象, 再去和容器中对比, 则会造成生成过多对象的问题. 

解决:
> 每次在 `new` 下一步的点的对象之前, 先判断该点是否走过, 如果走过, 可以将下一步的引用指向容器中已经存在的对象, 则可以少生成一个对象, 如果容器中没有, 则说明要么是可以走的路, 要么是墙, 再去判断是否需要加入队列, 如果是可以走的路, 则加入队列, 如果是墙, 则将该方向的引用指向空, 使刚 `new` 出来的无用的对象没有引用.

---

问题:
> 入口与出口的特征相同, 所以判断的规则也相同, 如果不加以处理, 则输入入口的第一步就会认为已经到达终点.

解决:
> 传入一个 `int len`, 第一次传入的时候传入0, 则可避免这个问题. 