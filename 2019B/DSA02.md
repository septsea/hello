# Data Structure 02

* [问题 A: 连通图判定](#问题-a-连通图判定)
* [问题 B: 最小生成树](#问题-b-最小生成树)
* [问题 C: 最短路径](#问题-c-最短路径)
* [问题 D: 广度优先搜索](#问题-d-广度优先搜索)
* [问题 E: 有向无环图](#问题-e-有向无环图)
* [问题 F: 顺序查找](#问题-f-顺序查找)
* [问题 G: 折半查找](#问题-g-折半查找)
* [问题 H: 二叉排序树](#问题-h-二叉排序树)
* [问题 I: 冒泡排序](#问题-i-冒泡排序)
* [问题 J: 快速排序](#问题-j-快速排序)

这次的题, 其实不需要打很多代码; 理解了, 改动书上输出部分的代码就是。书上的源代码可以在[此处](https://gitee.com/septsea/hello/raw/master/PythonDataStructureSourceCodes.zip)下载。下面就是卑微小 `τ` 的分析与解。如果您不懂一些代码的含义, 可以问我。如果您发现错误, 那就自己改改。毕竟, 我 "犯了每个男人都会犯的错"。不皮了不皮了, 您接着往下看吧。

卑微小 `τ`

2019 年 11 月 21 日

***
***

## 问题 A: 连通图判定

**题目描述:**
给定一个**无向图**, 利用深度优先搜索判断它是否连通

**输入:**
输入有多组, 每组包含下面数据: 第一行输入两个正整数 `n`, `m` 表示图的顶点数和边数; 接下来输入 `m` 行, 每行两个整数, 分别表示 `m` 条边的两个端点

**输出:**
如果输入的图为连通图, 输出 `Yes`, 否则输出 `No`

**样例输入:**

```python
3 3
1 2
1 3
2 3
```

```python
4 2
1 2
3 4
```

**样例输出:**

```python
Yes
```

```python
No
```

**提示:** 参考程序 `ex060301~02.py`

**分析与解:**

emmmmm... 没什么可说的。直接看代码吧。

```python
#######################################################
#文件名: ex060301.py (6.3.1 深度优先遍历 算法 6-12 ~ 算法 6-13 6.3.2 广度优先遍历 算法 6-14)
#版本号: 0.4
#创建时间: 2017-12-10
#修改时间: 2019-11-21
#######################################################
#类名称: Vertex
#类说明: 定义图中的一个顶点
#类释义: 有数据 data、与该顶点相关联的第一条边 FirstArc
#######################################################
class Vertex(object):
    def __init__(self, data):
        self.data = data
        self.FirstArc = None


##############################################################
#类名称: CircularSequenceQueue
#类说明: 定义一个循环队列
#类释义: 提供循环顺序队列的相关操作
##############################################################
class CircularSequenceQueue:
    ############################
    #默认的初始化循环队列的函数
    ############################
    def __init__(self):
        self.MaxQueueSize = 4
        self.s = [None for x in range(0, self.MaxQueueSize)]
        self.front = 0
        self.rear = 0

    ############################
    #初始化循环队列的函数
    ############################
    def InitQueue(self, Max):
        self.MaxQueueSize = Max
        self.s = [None for x in range(0, self.MaxQueueSize)]
        self.front = 0
        self.rear = 0

    #############################
    #访问某一元素的函数
    #############################
    def QueueVisit(self, element):
        print(element, end=' ')

    #############################
    #判断循环队列是否为空的函数
    #############################
    def IsEmptyQueue(self):
        if self.front == self.rear:
            iQueue = True
        else:
            iQueue = False
        return iQueue

    #############################
    #元素入队的函数
    #############################
    def EnQueue(self, x):
        if (self.rear + 1) % self.MaxQueueSize != self.front:
            self.rear = (self.rear + 1) % self.MaxQueueSize
            self.s[self.rear] = x
        else:
            print("队列已满, 无法入队")
            return

    #############################
    #元素出队的函数
    #############################
    def DeQueue(self):
        if self.IsEmptyQueue():
            print("队列为空, 无法出队")
            return
        else:
            self.front = (self.front + 1) % self.MaxQueueSize
            return self.s[self.front]

    #############################
    #依次访问队列中元素的函数
    #############################
    def QueueTraverse(self):
        if self.IsEmptyQueue():
            print("队列为空, 队列无元素可以访问")
            return
        else:
            if self.front < self.rear:
                i = self.front + 1
                while i < self.rear:
                    self.QueueVisit(self.s[i])
                    i = i + 1
                self.QueueVisit(self.s[self.rear])
            else:
                i = self.front + 1
                while i < self.MaxQueueSize:
                    self.QueueVisit(self.s[i])
                    i = i + 1
                i = 0
                while i <= self.rear:
                    self.QueueVisit(self.s[i])
                    i = i + 1

    #############################
    #获取当前队首元素的函数
    #############################
    def GetHead(self):
        if self.IsEmptyQueue():
            print("队列为空, 无法输出队首元素")
            return
        else:
            return self.s[(self.front + 1) % self.MaxQueueSize]

    #############################
    #输出当前队列中元素个数的函数
    #############################
    def GetQueueLength(self):
        if self.IsEmptyQueue():
            print("队列为空, 队列长度为零")
            return
        else:
            return (self.rear - self.front +
                    self.MaxQueueSize) % self.MaxQueueSize


##########################################################
#类名称: Acr
#类说明: 定义图中的一条边
#类释义: 包含该边的一个顶点 adjacent、与该边相关的信息 info 和
#        与该边依附于相同顶点的另一条边 NextArc
##########################################################
class Arc(object):
    def __init__(self, adjacent):
        self.adjacent = adjacent
        self.info = None
        self.NextArc = None


##################################################################
#类名称: Graph
#类说明: 定义一个图
#类释义: 包含该图的类型 kind (0 无向图, 1 无向网, 2 有向图, 3 有向网)、
#        图中的顶点数 VertexNum 、边或弧的数目 ArcNum 和邻接表 Vertices
##################################################################
class Graph(object):
    def __init__(self, kind):
        self.kind = kind
        self.VertexNum = 0
        self.ArcNum = 0
        self.Vertices = []

    #############################################
    #以邻接表作为存储结构创建有向图或无向图的方法
    #############################################
    def CreateGraph(self):
        #依次输入顶点的值, 创建顺序存储结构
        # print('请依次输入图中各顶点的值, 每个顶点值以回车间隔, 并以#作为输入结束符: ')
        (n, m) = map(int, input().split())
        # data = input('->')
        # while data is not '#':
        for i in range(n):
            vertex = Vertex(str(i + 1))
            self.Vertices.append(vertex)
            self.VertexNum = self.VertexNum + 1
            # data = input('->')
        #依次输入边或弧的两个顶点, 并进行定位
        # print('请依次输入图中每条边的两个顶点值, 两个顶点值以空格作为间隔, 每输入一组后进行换行, 最终以#结束输入: ')
        # arc = input('->')
        # while arc is not '#':
        for i in range(m):
            arc = input()
            TailVertex = arc.split()[0]
            HeadVertex = arc.split()[1]
            TailIndex = self.LocateVertex(TailVertex)
            HeadIndex = self.LocateVertex(HeadVertex)
            #将与输入的两个顶点相关联的边插入到顶点的链表当中
            self.InsertArc(TailIndex, HeadIndex)
            self.ArcNum = self.ArcNum + 1
            # arc = input('->')
        #创建成功
        # print('创建成功!')

    ##################################
    #定位顶点在邻接表中的位置的方法
    ##################################
    def LocateVertex(self, Vertex):
        index = 0
        while self.Vertices[index].data != Vertex and index < len(
                self.Vertices):
            index = index + 1
        return index

    ##################################
    #将图中的边或弧插入邻接表的方法
    ##################################
    def InsertArc(self, TailIndex, HeadIndex):
        if self.kind is 0:  #无向图
            TailArc = Arc(TailIndex)
            HeadArc = Arc(HeadIndex)
            #对 TailVertex, 插入 HeadVertex
            HeadArc.NextArc = self.Vertices[TailIndex].FirstArc
            self.Vertices[TailIndex].FirstArc = HeadArc
            #对 HeadVertex, 插入 TailVertex
            TailArc.NextArc = self.Vertices[HeadIndex].FirstArc
            self.Vertices[HeadIndex].FirstArc = TailArc
        elif self.kind is 2:  #有向图
            #对 TailVertex, 插入 HeadVertex
            HeadArc = Arc(HeadIndex)
            HeadArc.NextArc = self.Vertices[TailIndex].FirstArc
            self.Vertices[TailIndex].FirstArc = HeadArc

    ##################################
    #得到某一顶点的第一个邻接点的方法
    ##################################
    def GetFirstAdjacentVertex(self, Vertex):
        FirstArc = self.Vertices[Vertex].FirstArc
        if FirstArc is not None:
            return FirstArc.adjacent

    #################################################
    #得到某一顶点相对于 Adjacent 的下一个邻接点的方法
    #################################################
    def GetNextAdjacentVertex(self, Vertex, Adjacent):
        ArcLink = self.Vertices[Vertex].FirstArc
        while ArcLink is not None:
            if ArcLink.adjacent is Adjacent:
                if ArcLink.NextArc is not None:
                    return ArcLink.NextArc.adjacent
                else:
                    return None
            else:
                ArcLink = ArcLink.NextArc

    ################################
    #算法 6-12 深度优先遍历图的算法
    ################################
    def DFSTraverse(self):
        visited = []
        index = 0
        while index < self.VertexNum:
            visited.append('False')
            index = index + 1
        # 只访问一个顶点, 判断是否能够全部访问完毕
        self.DFS(visited, 0)
        # index = 0
        # while index < self.VertexNum:
        #     if visited[index] is 'False':
        #         self.DFS(visited, index)
        #     index = index + 1
        for x in visited:
            if x == 'False':
                return 'No'
        return 'Yes'

    ###################################
    #算法 6-13 深度优先遍历图的递归算法
    ###################################
    def DFS(self, visited, Vertex):
        visited[Vertex] = 'True'
        # 抑制此处的输出
        # self.VisitVertex(Vertex)
        NextAdjacent = self.GetFirstAdjacentVertex(Vertex)
        while NextAdjacent is not None:
            if visited[NextAdjacent] is 'False':
                self.DFS(visited, NextAdjacent)
            NextAdjacent = self.GetNextAdjacentVertex(Vertex, NextAdjacent)

    ########################
    #访问图中某一顶点的方法
    ########################
    def VisitVertex(self, Vertex):
        print(self.Vertices[Vertex].data, end='')

    ###############################
    #算法 6-14 广度优先遍历图的算法
    ###############################
    def BFSTraverse(self):
        tmpdata = []
        visited = []
        index = 0
        Queue = CircularSequenceQueue()
        Queue.InitQueue(10)
        while index < self.VertexNum:
            visited.append('False')
            index = index + 1
        index = 0
        while index < self.VertexNum:
            if visited[index] is 'False':
                visited[index] = 'True'
                # self.VisitVertex(index)
                tmpdata.append(self.Vertices[index].data)
                Queue.EnQueue(index)
                while Queue.IsEmptyQueue() is False:
                    tVertex = Queue.DeQueue()
                    NextAdjacent = self.GetFirstAdjacentVertex(tVertex)
                    while NextAdjacent is not None:
                        if visited[NextAdjacent] is 'False':
                            visited[NextAdjacent] = 'True'
                            # self.VisitVertex(NextAdjacent)
                            tmpdata.append(self.Vertices[NextAdjacent].data)
                            Queue.EnQueue(NextAdjacent)
                        NextAdjacent = self.GetNextAdjacentVertex(
                            tVertex, NextAdjacent)
            index = index + 1
        return ' '.join(tmpdata)

###############
#主程序
###############
if __name__ == '__main__':
    #创建无向图
    while 1:
        graph = Graph(0)
        graph.CreateGraph()
        #深度优先递归遍历图
        # print('深度优先递归遍历图的结果如下:')
        print(graph.DFSTraverse())
        # print()
        #广度优先遍历图
        # print('广度优先遍历图的结果如下:')
        # print(graph.BFSTraverse())
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 B: 最小生成树

**题目描述:**
对于给定的**无向图**, 输出它的最小生成树

**输入:**
输入有多组, 每组数据如下: 第一行输入两个正整数 `n`, `m` 分别表示图的顶点数和边数; 接下来 `m` 行, 每行输入三个正整数, 表示一条边的两个端点和边的权值

**输出:**
每组输出一行, 先输出组成最小生成树所有边, 然后输出最小生成树的权值

**样例输入:**

```python
3 3
1 2 1
1 3 2
2 3 3
```

**样例输出:**

```python
12,13,3
```

**提示:**
参考 `ex060402.py`

**分析与解:**

emmmmm... 没什么可说的。直接看代码吧。

```python
##########################################################
#文件名: ex060402.py (6.4.2 Prim算法 )
#版本号: 0.3
#创建时间: 2017-12-10
#修改时间: 2019-11-21
##########################################################
#算法 6-15 Prim 算法
##########################################################
#类名称: Vertex
#类说明: 定义图的一个顶点
#类释义: 包含顶点值 data 和顶点的相关信息 info
##########################################################
class Vertex(object):
    def __init__(self, data):
        self.data = data
        self.info = None


##################################################################
#类名称: Graph
#类说明: 定义一个图
#类释义: 包含该图的类型 kind (0 无向图, 1无向网, 2有向图, 3有向网)、
#        存储图中所有顶点的顶点集 Vertices、邻接矩阵 Arcs、图中的
#        顶点数 VertexNum 和边或弧的数目 ArcNum
##################################################################
class Graph(object):
    def __init__(self, kind):
        self.kind = kind
        self.Vertices = []
        self.Arcs = []
        self.ArcNum = 0
        self.VertexNum = 0

    #####################################
    #以邻接矩阵为存储结构创建无向网的方法
    #####################################
    def CreateGraph(self):
        # print('请依次输入图中各顶点的值, 每个顶点值以回车间隔, 并以#作为输入结束符: ')
        (n, m) = map(int, input().split())
        # data = input('->')
        # while data is not '#':
        for i in range(n):
            vertex = Vertex(str(i + 1))
            self.Vertices.append(vertex)
            self.VertexNum = self.VertexNum + 1
            # data = input('->')
        self.Arcs = [[0 for i in range(self.VertexNum)]
                     for i in range(self.VertexNum)]
        Horizontal = 0
        while Horizontal < self.VertexNum:
            Vertical = 0
            while Vertical < self.VertexNum:
                if Vertical is Horizontal:
                    self.Arcs[Horizontal][Vertical] = 0
                else:
                    self.Arcs[Horizontal][Vertical] = float("inf")
                Vertical = Vertical + 1
            Horizontal = Horizontal + 1
        #依次输入边或弧的两个顶点, 并进行定位
        # print('请依次输入图中每条边的两个顶点值和权值, 以空格作为间隔, 每输入一组后进行换行, 最终以#结束输入: ')
        # arc = input('->')
        # while arc is not '#':
        for i in range(m):
            arc = input()
            VertexOne = arc.split()[0]
            VertexTwo = arc.split()[1]
            VertexOneIndex = self.LocateVertex(VertexOne)
            VertexTwoIndex = self.LocateVertex(VertexTwo)
            weight = float(arc.split()[2])
            if self.kind is 1:
                self.Arcs[VertexOneIndex][VertexTwoIndex] = weight
                self.Arcs[VertexTwoIndex][VertexOneIndex] = weight
            self.ArcNum = self.ArcNum + 1
            # arc = input('->')
        # print('创建成功')

    ##################################
    #定位顶点在邻接表中的位置的方法
    ##################################
    def LocateVertex(self, Vertex):
        index = 0
        while self.Vertices[index].data != Vertex and index < len(
                self.Vertices):
            index = index + 1
        return index

    #################################
    #算法 6-15 Prim 算法
    #################################
    def MiniSpanTreePrim(self, Vertex):
        #arc 存储最小生成树的边, 以顶点值对的形式存储
        arc = []
        closedge = [[] for i in range(self.VertexNum)]
        #以 self.Vertices 中的第 0 个顶点作为根结点, 创建最小生成树
        MinEdge = 0
        #closedge[i] 包含两个部分, 第二部分是与下标 i 表示的顶点相关联的边的最小权值, 第二部分是该边依附于的另一个顶点
        #0 表示该顶点已经包含在最小生成树内
        index = 0
        #初始化 closedge
        while index < self.VertexNum:
            closedge[index] = [Vertex, self.Arcs[Vertex][index]]
            index = index + 1
        #寻找最小生成树的 n - 1 条边
        index = 1
        while index < self.VertexNum:
            #获取符合条件下权值最小的边, 并将其存入 arc
            MinEdge = self.GetMin(closedge)
            arc.append([
                self.Vertices[closedge[MinEdge][0]].data,
                self.Vertices[MinEdge].data
            ])
            closedge[MinEdge][1] = 0
            i = 0
            #更新 closedge
            while i < self.VertexNum:
                if self.Arcs[MinEdge][i] < closedge[i][1]:
                    closedge[i] = [MinEdge, self.Arcs[MinEdge][i]]
                i = i + 1
            index = index + 1
        # print('组成最小生成树的边如下: ')
        # weightsum 用于存储最小生成树权值
        weightsum = 0
        for item in arc:
            # 使用邻接矩阵获取边的权值。注意到顶点是用 1, 2, ... 标记的, 而 Python 的可枚举对象用 0, 1, ... 标记, 因而索引值减 1
            weightsum += self.Arcs[int(item[0]) - 1][int(item[1]) - 1]
            # 与样例输出保持一致
            print(''.join(item), end=',')
        # 有限权值均假设为整数, 而存储权值时保存为 float 型, 因而最后取整输出
        print(int(weightsum))

    ##########################
    #获取权值最小的边的方法
    ##########################
    def GetMin(self, closedge):
        index = 0
        MinWeight = float("inf")
        vertex = 0
        while index < self.VertexNum:
            if closedge[index][1] is not 0 and closedge[index][
                    1] < MinWeight:  #当该边（index）存在时, 比较其权值是否更小
                MinWeight = closedge[index][1]
                vertex = index
            index = index + 1
        return vertex


###############
#主程序
###############
if __name__ == '__main__':
    while 1:
        #创建一个连通的无向网
        graph = Graph(1)
        graph.CreateGraph()
        #以下标为 0 的顶点构造最小生成树
        graph.MiniSpanTreePrim(0)
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 C: 最短路径

**题目描述:**
对一个给定的**无向图**, 给出一条指定起点和终点的最短路径

**输入:**
输入有多组, 每组数据如下: 第一行输入两个正整数 `n`, `m` 分别表示图的顶点数和边数; 接下来 `m` 行, 每行输入 `3` 个正整数, 分别代表一条边的两个端点和权值; 最后一行输入两个正整数, 表示最短路径的起点和终点

**输出:**
每组数据输出一行, 第一个数是最短路径的权值, 从第二个数组开始是指定起点到终点的最短路径顶点序列

**样例输入:**

```python
4 4
1 2 1
2 3 2
3 4 3
4 1 4
1 3
```

**样例输出:**

```python
3 1 2 3
```

**提示:**
参考程序 `ex060502~03.py`

**分析与解:**

您注意到我在题目描述中给 "无向图" 三字加粗了吗? 这次, 您不但要改动输入输出, 还要记得把有向图改为无向图。好了, 代码就放在这里。

```python
##################################################################
#文件名: ex060502~03.py (6.5.2 从某源点到其余各顶点的最短路径 6.5.3 每一对顶点之间的最短路径)
#版本号: 0.5
#创建时间: 2017-12-12
#修改时间: 2019-11-21
##################################################################
#算法 6-17~6-18
##################################################################
#类名称: Vertex
#类说明: 定义图的一个顶点
#类释义: 包含顶点值 data 和顶点的相关信息 info
##################################################################
class Vertex(object):
    def __init__(self, data):
        self.data = data
        self.info = None


##################################################################
#类名称: Graph
#类说明: 定义一个图
#类释义: 包含该图的类型 kind (0 无向图, 1 无向网, 2 有向图, 3 有向网)、
#        顶点集 Vertices、邻接矩阵 Arcs、图中的顶点数 VertexNum 和边或弧的数目 ArcNum
##################################################################
class Graph(object):
    def __init__(self, kind):
        self.kind = kind
        self.Vertices = []
        self.Arcs = []
        self.ArcNum = 0
        self.VertexNum = 0

    ######################################
    #以邻接矩阵为存储结构创建有向网的方法
    # 这里魔改为无向网
    ######################################
    def CreateGraph(self):
        # print('请依次输入图中各顶点的值, 每个顶点值以回车间隔, 并以#作为输入结束符: ')
        (n, m) = map(int, input().split())
        # data = input('->')
        # while data is not '#':
        for i in range(n):
            vertex = Vertex(str(i + 1))
            self.Vertices.append(vertex)
            self.VertexNum = self.VertexNum + 1
            # data = input('->')
        self.Arcs = [[0 for i in range(self.VertexNum)]
                     for i in range(self.VertexNum)]
        Horizontal = 0
        while Horizontal < self.VertexNum:
            Vertical = 0
            while Vertical < self.VertexNum:
                if Vertical is Horizontal:
                    self.Arcs[Horizontal][Vertical] = 0
                else:
                    self.Arcs[Horizontal][Vertical] = float("inf")
                Vertical = Vertical + 1
            Horizontal = Horizontal + 1
        #依次输入边或弧的两个顶点, 并进行定位
        # print('请依次输入图中每条边的两个顶点值和权值, 以空格作为间隔, 每输入一组后进行换行, 最终以#结束输入: ')
        # arc = input('->')
        # while arc is not '#':
        for i in range(m):
            arc = input()
            VertexOne = arc.split()[0]
            VertexTwo = arc.split()[1]
            VertexOneIndex = self.LocateVertex(VertexOne)
            VertexTwoIndex = self.LocateVertex(VertexTwo)
            #权值不一定都是整数
            weight = float(arc.split()[2])
            # if self.kind is 3:
            # 注意! 题干给出的是无向图!
            if self.kind is 1:
                self.Arcs[VertexOneIndex][VertexTwoIndex] = weight
                self.Arcs[VertexTwoIndex][VertexOneIndex] = weight
            self.ArcNum = self.ArcNum + 1
            # arc = input('->')
        # print('创建成功')

    ##################################
    #定位顶点在顶点集中的位置的方法
    ##################################
    def LocateVertex(self, Vertex):
        index = 0
        ######self.Vertices[index].data != Vertex 用于判断字符串和数字都行得通
        ######如果用 is not 只能判断数字, 而不能判断字符串
        while self.Vertices[index].data != Vertex and index < len(
                self.Vertices):
            index = index + 1
        return index

    ###############################
    #算法 6-17 Dijkstra 算法
    ###############################
    def Dijkstra(self, Vertex):
        Dist = [[] for i in range(self.VertexNum)]  #最短路径长度
        Path = [[] for i in range(self.VertexNum)]  #最短路径
        flag = [[] for i in range(self.VertexNum)]  #记录顶点是否已求得最短路径
        #初始化三个列表
        index = 0
        while index < self.VertexNum:
            Dist[index] = self.Arcs[Vertex][index]
            flag[index] = 0
            if self.Arcs[Vertex][index] < float("inf"):
                Path[index] = Vertex
            else:
                Path[index] = -1
            index = index + 1
        flag[Vertex] = 1
        Path[Vertex] = 0
        Dist[Vertex] = 0
        index = 1
        while index < self.VertexNum:
            MinDist = float("inf")
            j = 0  #被考察的路径
            #不断选取未被访问的最短的路径
            while j < self.VertexNum:
                if flag[j] is 0 and Dist[j] < MinDist:
                    tVertex = j
                    MinDist = Dist[j]
                j = j + 1
            flag[tVertex] = 1
            EndVertex = 0
            #将 MinDist 重新置为无穷大
            MinDist = float("inf")
            #更新最短路径长度
            while EndVertex < self.VertexNum:
                if flag[EndVertex] is 0:
                    if self.Arcs[tVertex][EndVertex] < MinDist and Dist[
                            tVertex] + self.Arcs[tVertex][EndVertex] < Dist[
                                EndVertex]:
                        Dist[EndVertex] = Dist[tVertex] + self.Arcs[tVertex][
                            EndVertex]
                        Path[EndVertex] = tVertex
                EndVertex = EndVertex + 1
            index = index + 1
        self.ShortestPath(Dist, Path, flag, Vertex)

    #################################################
    #输出从顶点 Vertex 到其他顶点的最短路径 (Dijkstra)
    #################################################
    def ShortestPath(self, Dist, Path, flag, Vertex):
        tPath = []
        index = 0
        while index < self.VertexNum:
            if flag[index] is 1 and index is not Vertex:
                print('到达顶点' + self.Vertices[index].data + '的路径为: ')
                tPath.append(index)  #添加路径终点
                former = Path[index]  #获取前一个顶点的下标
                while former is not Vertex:
                    tPath.append(former)
                    former = Path[former]
                tPath.append(Vertex)
                while len(tPath) > 0:
                    print(self.Vertices[tPath.pop()].data, end='')
                print()
            index = index + 1

    #############################
    #算法 6-18 Floyd 算法
    #############################
    # def Floyd(self):
    # 添加形式参数 Start 与 End, 表示最短路径的起点和终点
    def Floyd(self, Start, End):
        Dist = [[0 for i in range(self.VertexNum)]
                for i in range(self.VertexNum)]  #最短路径长度
        Path = [[0 for i in range(self.VertexNum)]
                for i in range(self.VertexNum)]  #最短路径
        Horizontal = 0
        while Horizontal < self.VertexNum:
            Vertical = 0
            while Vertical < self.VertexNum:
                Dist[Horizontal][Vertical] = self.Arcs[Horizontal][Vertical]
                if self.Arcs[Horizontal][Vertical] < float(
                        "inf") and Horizontal is not Vertical:
                    Path[Horizontal][Vertical] = Horizontal
                else:
                    Path[Horizontal][Vertical] = -1
                Vertical = Vertical + 1
            Horizontal = Horizontal + 1
        #判断顶点 tVertex 是否是顶点 ArcTail 和顶点 ArcHead 最短路径中的一个顶点
        tVertex = 0
        while tVertex < self.VertexNum:
            ArcTail = 0
            while ArcTail < self.VertexNum:
                ArcHead = 0
                while ArcHead < self.VertexNum:
                    if ArcTail is not ArcHead and (
                            Dist[ArcTail][tVertex] + Dist[tVertex][ArcHead] <
                            Dist[ArcTail][ArcHead]):
                        Dist[ArcTail][ArcHead] = Dist[ArcTail][tVertex] + Dist[
                            tVertex][ArcHead]
                        Path[ArcTail][ArcHead] = Path[tVertex][ArcHead]
                    ArcHead = ArcHead + 1
                ArcTail = ArcTail + 1
            tVertex = tVertex + 1
        # self.AllShortestPath(Dist, Path)
        self.OneOfTheShortestPath(Dist, Path, Start, End)

    ####################################
    #输出每一组顶点间的最短路径 (Floyd)
    ####################################
    def AllShortestPath(self, Dist, Path):
        tPath = []
        Start = 0
        while Start < self.VertexNum:
            End = 0
            while End < self.VertexNum:
                if Dist[Start][End] < float("inf") and Start is not End:
                    print('从顶点' + self.Vertices[Start].data + '到顶点' +
                          self.Vertices[End].data + '的路径为: ',
                          end='')
                    tVertex = Path[Start][End]
                    tPath.append(End)
                    while (tVertex is not -1) and tVertex is not Start:
                        tPath.append(tVertex)
                        tVertex = Path[Start][tVertex]
                    tPath.append(Start)
                    while len(tPath) > 0:
                        print(self.Vertices[tPath.pop()].data, end='')
                    print()
                    print('其路径长度为: ' + str(Dist[Start][End]))
                End = End + 1
            Start = Start + 1

    # 在 AllShortestPath(self, Dist, Path) 的基础上写出方法
    # OneOfTheShortestPath(self, Dist, Path, Start, End)
    def OneOfTheShortestPath(self, Dist, Path, Start, End):
        tPath = []
        if Dist[Start][End] < float("inf") and Start is not End:
            print(int(Dist[Start][End]), end=' ')
            # print('从顶点' + self.Vertices[Start].data + '到顶点' +
            #       self.Vertices[End].data + '的路径为: ',
            #       end='')
            tVertex = Path[Start][End]
            tPath.append(End)
            while (tVertex is not -1) and tVertex is not Start:
                tPath.append(tVertex)
                tVertex = Path[Start][tVertex]
            tPath.append(Start)
            while len(tPath) > 1:
                # print(self.Vertices[tPath.pop()].data, end='')
                print(self.Vertices[tPath.pop()].data, end=' ')
            print(self.Vertices[tPath.pop()].data)
            # print('其路径长度为: ' + str(Dist[Start][End]))


#########
#主程序
#########
if __name__ == '__main__':
    while 1:
        # 注意! 题干给出的是无向图!
        #创建一个无向网
        graph = Graph(1)
        graph.CreateGraph()
        # print('构造以下标为 0 的顶点为起点, 网中其余各顶点为终点的最短路径')
        # graph.Dijkstra(0)
        # print('构造网中各顶点间的最短路径')
        (Start, End) = map(lambda x: int(x) - 1, input().split())
        graph.Floyd(Start, End)
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 D: 广度优先搜索

**题目描述:**
给定一个**无向图**, 从标号为 `1` 的顶点开始给出它的广度优先遍历序列

**输入:**
输入有多组, 每组格式如下: 第一行输入两个正整数 `n`, `m`, 分别表示图的顶点数和边数; 接下来 `m` 行, 每行输入两个正整数, 表示一条边的两个端点

**输出:**
每组数据输出为一行, 为该图的从序号为 `1` 的顶点开始的广度优先遍历序列

**样例输入:**

```python
3 3
1 2
2 3
3 1
```

**样例输出:**

```python
1 3 2
```

**提示:**
参考程序 `ex060301~02.py`

**分析与解:**

emmmmm... 没什么可说的。直接看代码吧。还有, 这代码其实 90%+ 跟[问题 A](#问题-a-连通图判定) 一致, 只有后面的 `if __name__ == '__main__':` 里面的东西不一样。

```python
#######################################################
#文件名: ex060301.py (6.3.1 深度优先遍历 算法 6-12 ~ 算法 6-13 6.3.2 广度优先遍历 算法 6-14)
#版本号: 0.4
#创建时间: 2017-12-10
#修改时间: 2019-11-21
#######################################################
#类名称: Vertex
#类说明: 定义图中的一个顶点
#类释义: 有数据 data、与该顶点相关联的第一条边 FirstArc
#######################################################
class Vertex(object):
    def __init__(self, data):
        self.data = data
        self.FirstArc = None


##############################################################
#类名称: CircularSequenceQueue
#类说明: 定义一个循环队列
#类释义: 提供循环顺序队列的相关操作
##############################################################
class CircularSequenceQueue:
    ############################
    #默认的初始化循环队列的函数
    ############################
    def __init__(self):
        self.MaxQueueSize = 4
        self.s = [None for x in range(0, self.MaxQueueSize)]
        self.front = 0
        self.rear = 0

    ############################
    #初始化循环队列的函数
    ############################
    def InitQueue(self, Max):
        self.MaxQueueSize = Max
        self.s = [None for x in range(0, self.MaxQueueSize)]
        self.front = 0
        self.rear = 0

    #############################
    #访问某一元素的函数
    #############################
    def QueueVisit(self, element):
        print(element, end=' ')

    #############################
    #判断循环队列是否为空的函数
    #############################
    def IsEmptyQueue(self):
        if self.front == self.rear:
            iQueue = True
        else:
            iQueue = False
        return iQueue

    #############################
    #元素入队的函数
    #############################
    def EnQueue(self, x):
        if (self.rear + 1) % self.MaxQueueSize != self.front:
            self.rear = (self.rear + 1) % self.MaxQueueSize
            self.s[self.rear] = x
        else:
            print("队列已满, 无法入队")
            return

    #############################
    #元素出队的函数
    #############################
    def DeQueue(self):
        if self.IsEmptyQueue():
            print("队列为空, 无法出队")
            return
        else:
            self.front = (self.front + 1) % self.MaxQueueSize
            return self.s[self.front]

    #############################
    #依次访问队列中元素的函数
    #############################
    def QueueTraverse(self):
        if self.IsEmptyQueue():
            print("队列为空, 队列无元素可以访问")
            return
        else:
            if self.front < self.rear:
                i = self.front + 1
                while i < self.rear:
                    self.QueueVisit(self.s[i])
                    i = i + 1
                self.QueueVisit(self.s[self.rear])
            else:
                i = self.front + 1
                while i < self.MaxQueueSize:
                    self.QueueVisit(self.s[i])
                    i = i + 1
                i = 0
                while i <= self.rear:
                    self.QueueVisit(self.s[i])
                    i = i + 1

    #############################
    #获取当前队首元素的函数
    #############################
    def GetHead(self):
        if self.IsEmptyQueue():
            print("队列为空, 无法输出队首元素")
            return
        else:
            return self.s[(self.front + 1) % self.MaxQueueSize]

    #############################
    #输出当前队列中元素个数的函数
    #############################
    def GetQueueLength(self):
        if self.IsEmptyQueue():
            print("队列为空, 队列长度为零")
            return
        else:
            return (self.rear - self.front +
                    self.MaxQueueSize) % self.MaxQueueSize


##########################################################
#类名称: Acr
#类说明: 定义图中的一条边
#类释义: 包含该边的一个顶点 adjacent、与该边相关的信息 info 和
#        与该边依附于相同顶点的另一条边 NextArc
##########################################################
class Arc(object):
    def __init__(self, adjacent):
        self.adjacent = adjacent
        self.info = None
        self.NextArc = None


##################################################################
#类名称: Graph
#类说明: 定义一个图
#类释义: 包含该图的类型 kind (0 无向图, 1 无向网, 2 有向图, 3 有向网)、
#        图中的顶点数 VertexNum 、边或弧的数目 ArcNum 和邻接表 Vertices
##################################################################
class Graph(object):
    def __init__(self, kind):
        self.kind = kind
        self.VertexNum = 0
        self.ArcNum = 0
        self.Vertices = []

    #############################################
    #以邻接表作为存储结构创建有向图或无向图的方法
    #############################################
    def CreateGraph(self):
        #依次输入顶点的值, 创建顺序存储结构
        # print('请依次输入图中各顶点的值, 每个顶点值以回车间隔, 并以#作为输入结束符: ')
        (n, m) = map(int, input().split())
        # data = input('->')
        # while data is not '#':
        for i in range(n):
            vertex = Vertex(str(i + 1))
            self.Vertices.append(vertex)
            self.VertexNum = self.VertexNum + 1
            # data = input('->')
        #依次输入边或弧的两个顶点, 并进行定位
        # print('请依次输入图中每条边的两个顶点值, 两个顶点值以空格作为间隔, 每输入一组后进行换行, 最终以#结束输入: ')
        # arc = input('->')
        # while arc is not '#':
        for i in range(m):
            arc = input()
            TailVertex = arc.split()[0]
            HeadVertex = arc.split()[1]
            TailIndex = self.LocateVertex(TailVertex)
            HeadIndex = self.LocateVertex(HeadVertex)
            #将与输入的两个顶点相关联的边插入到顶点的链表当中
            self.InsertArc(TailIndex, HeadIndex)
            self.ArcNum = self.ArcNum + 1
            # arc = input('->')
        #创建成功
        # print('创建成功!')

    ##################################
    #定位顶点在邻接表中的位置的方法
    ##################################
    def LocateVertex(self, Vertex):
        index = 0
        while self.Vertices[index].data != Vertex and index < len(
                self.Vertices):
            index = index + 1
        return index

    ##################################
    #将图中的边或弧插入邻接表的方法
    ##################################
    def InsertArc(self, TailIndex, HeadIndex):
        if self.kind is 0:  #无向图
            TailArc = Arc(TailIndex)
            HeadArc = Arc(HeadIndex)
            #对 TailVertex, 插入 HeadVertex
            HeadArc.NextArc = self.Vertices[TailIndex].FirstArc
            self.Vertices[TailIndex].FirstArc = HeadArc
            #对 HeadVertex, 插入 TailVertex
            TailArc.NextArc = self.Vertices[HeadIndex].FirstArc
            self.Vertices[HeadIndex].FirstArc = TailArc
        elif self.kind is 2:  #有向图
            #对 TailVertex, 插入 HeadVertex
            HeadArc = Arc(HeadIndex)
            HeadArc.NextArc = self.Vertices[TailIndex].FirstArc
            self.Vertices[TailIndex].FirstArc = HeadArc

    ##################################
    #得到某一顶点的第一个邻接点的方法
    ##################################
    def GetFirstAdjacentVertex(self, Vertex):
        FirstArc = self.Vertices[Vertex].FirstArc
        if FirstArc is not None:
            return FirstArc.adjacent

    #################################################
    #得到某一顶点相对于 Adjacent 的下一个邻接点的方法
    #################################################
    def GetNextAdjacentVertex(self, Vertex, Adjacent):
        ArcLink = self.Vertices[Vertex].FirstArc
        while ArcLink is not None:
            if ArcLink.adjacent is Adjacent:
                if ArcLink.NextArc is not None:
                    return ArcLink.NextArc.adjacent
                else:
                    return None
            else:
                ArcLink = ArcLink.NextArc

    ################################
    #算法 6-12 深度优先遍历图的算法
    ################################
    def DFSTraverse(self):
        visited = []
        index = 0
        while index < self.VertexNum:
            visited.append('False')
            index = index + 1
        # 只访问一个顶点, 判断是否能够全部访问完毕
        self.DFS(visited, 0)
        # index = 0
        # while index < self.VertexNum:
        #     if visited[index] is 'False':
        #         self.DFS(visited, index)
        #     index = index + 1
        for x in visited:
            if x == 'False':
                return 'No'
        return 'Yes'

    ###################################
    #算法 6-13 深度优先遍历图的递归算法
    ###################################
    def DFS(self, visited, Vertex):
        visited[Vertex] = 'True'
        # 抑制此处的输出
        # self.VisitVertex(Vertex)
        NextAdjacent = self.GetFirstAdjacentVertex(Vertex)
        while NextAdjacent is not None:
            if visited[NextAdjacent] is 'False':
                self.DFS(visited, NextAdjacent)
            NextAdjacent = self.GetNextAdjacentVertex(Vertex, NextAdjacent)

    ########################
    #访问图中某一顶点的方法
    ########################
    def VisitVertex(self, Vertex):
        print(self.Vertices[Vertex].data, end='')

    ###############################
    #算法 6-14 广度优先遍历图的算法
    ###############################
    def BFSTraverse(self):
        tmpdata = []
        visited = []
        index = 0
        Queue = CircularSequenceQueue()
        Queue.InitQueue(10)
        while index < self.VertexNum:
            visited.append('False')
            index = index + 1
        index = 0
        while index < self.VertexNum:
            if visited[index] is 'False':
                visited[index] = 'True'
                # self.VisitVertex(index)
                tmpdata.append(self.Vertices[index].data)
                Queue.EnQueue(index)
                while Queue.IsEmptyQueue() is False:
                    tVertex = Queue.DeQueue()
                    NextAdjacent = self.GetFirstAdjacentVertex(tVertex)
                    while NextAdjacent is not None:
                        if visited[NextAdjacent] is 'False':
                            visited[NextAdjacent] = 'True'
                            # self.VisitVertex(NextAdjacent)
                            tmpdata.append(self.Vertices[NextAdjacent].data)
                            Queue.EnQueue(NextAdjacent)
                        NextAdjacent = self.GetNextAdjacentVertex(
                            tVertex, NextAdjacent)
            index = index + 1
        return ' '.join(tmpdata)


###############
#主程序
###############
if __name__ == '__main__':
    #创建无向图
    while 1:
        graph = Graph(0)
        graph.CreateGraph()
        #深度优先递归遍历图
        # print('深度优先递归遍历图的结果如下:')
        # graph.DFSTraverse()
        # print()
        #广度优先遍历图
        # print('广度优先遍历图的结果如下:')
        print(graph.BFSTraverse())
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 E: 有向无环图

**题目描述:**
一个有向图如果不存在有向回路, 就被称为有向无环图 (*Directed Acyclic Graph*)。一个有向无环图一定可以拓扑排序, 同样利用拓扑排序可以判断一个图是否为有向无环图。给定一个有向图, 判断它是否为有向无环图。

**输入:**
输入有多组, 每组数据如下: 第一行为两个正整数 `n`, `m` 表示图的顶点数和弧数; 接下来 `m` 行每行两个正整数, 表示弧的起点和终点

**输出:**
如果有向图为有向无环图, 输出 `Yes`, 否则输出 `No`

**样例输入:**

```python
3 3
1 2
2 3
3 1
```

```python
3 3
1 2
1 3
2 3
```

**样例输出:**

```python
No
```

```python
Yes
```

**提示:**
参考程序 `ex060602.py`

**分析与解:**

这里我使用了如下的赋值语句:

```python
(n, m) = map(int, input().split())
```

我也魔改了 `TopologicalSort(self)` 方法, 原方法会输出拓扑排序后的结果; 我把结果添到列表 `result` 中, 并使该方法返回 `len(result) != 0`。

不多说, 直接上 (正如赵先生所言, 只需改动输入输出语句即可):

```python
##################################
#文件名: ex060602.py (6.6.2 拓扑排序的实现 算法 6-19~6-21)
#版本号: 0.3
#创建时间: 2017-12-11
#修改时间: 2019-11-21
##################################
##############################################################################
#类名称: VertexIndegree
#类说明: 定义图中的一个顶点
#类释义: 包含顶点值 data、与该顶点相关联的第一条边 FirstArc 以及该顶点的入度
##############################################################################
#算法 6-19 顶点的结构
####################################
class VertexIndegree(object):
    def __init__(self, data):
        self.data = data
        self.indegree = 0
        self.FirstArc = None


##########################################################
#类名称: Acr
#类说明: 定义图中的一条边
#类释义: 包含该边的一个顶点 adjacent、与该边相关的信息 info 和
#       与该边依附于相同顶点的另一条边 NextArc
##########################################################
class Arc(object):
    def __init__(self, adjacent):
        self.adjacent = adjacent
        self.info = None
        self.NextArc = None


##################################################################
#类名称: Graph
#类说明: 定义一个图
#类释义: 包含该图的类型 kind (0 无向图, 1 无向网, 2 有向图, 3 有向网)、
#       图中的顶点数 VertexNum、边或弧的数目 ArcNum 和邻接表 Vertices
##################################################################
class Graph(object):
    def __init__(self, kind):
        self.kind = kind
        self.VertexNum = 0
        self.ArcNum = 0
        self.Vertices = []

    ######################################
    #以邻接表作为存储结构创建有向图的方法
    ######################################
    def CreateGraph(self):
        #依次输入顶点的值, 创建顺序存储结构
        # print('请依次输入图中各顶点的值, 每个顶点值以回车间隔, 并以#作为输入结束符: ')
        (n, m) = map(int, input().split())
        # data = input('->')
        # while data is not '#':
        for i in range(n):
            vertex = VertexIndegree(str(i + 1))
            self.Vertices.append(vertex)
            self.VertexNum = self.VertexNum + 1
            # data = input('->')
        #依次输入边或弧的两个顶点, 并进行定位
        # print('请依次输入图中每条边的两个顶点值, 两个顶点值以空格作为间隔, 每输入一组后进行换行, 最终以#结束输入: ')
        # arc = input('->')
        # while arc is not '#':
        for i in range(m):
            arc = input()
            TailVertex = arc.split()[0]
            HeadVertex = arc.split()[1]
            TailIndex = self.LocateVertex(TailVertex)
            HeadIndex = self.LocateVertex(HeadVertex)
            #将与输入的两个顶点相关联的边插入到顶点的链表当中
            self.InsertArc(TailIndex, HeadIndex)
            self.ArcNum = self.ArcNum + 1

    ##################################
    #定位顶点在邻接表中的位置的方法
    ##################################
    def LocateVertex(self, Vertex):
        index = 0
        while self.Vertices[index].data != Vertex and index < len(
                self.Vertices):
            index = index + 1
        return index

    ##################################
    #将图中的边或弧插入邻接表的方法
    ##################################
    def InsertArc(self, TailIndex, HeadIndex):
        if self.kind is 2:
            #对TailVertex, 插入HeadVertex
            HeadArc = Arc(HeadIndex)
            HeadArc.NextArc = self.Vertices[TailIndex].FirstArc
            self.Vertices[TailIndex].FirstArc = HeadArc

    ##################################
    #得到某一顶点的第一个邻接点的方法
    ##################################
    def GetFirstAdjacentVertex(self, Vertex):
        FirstArc = self.Vertices[Vertex].FirstArc
        if FirstArc is not None:
            return FirstArc.adjacent
        else:
            return None

    #################################################
    #得到某一顶点相对于 Adjacent 的下一个邻接点的方法
    #################################################
    def GetNextAdjacentVertex(self, Vertex, Adjacent):
        ArcLink = self.Vertices[Vertex].FirstArc
        while ArcLink is not None:
            if ArcLink.adjacent is Adjacent:
                if ArcLink.NextArc is not None:
                    return ArcLink.NextArc.adjacent
                else:
                    return None
            else:
                ArcLink = ArcLink.NextArc

    ########################
    #访问图中某一顶点的方法
    ########################
    def VisitVertex(self, Vertex):
        print(self.Vertices[Vertex].data)

    #############################
    #算法 6-21 计算顶点入度的算法
    #############################
    def FindIndegree(self):
        index = 0
        while index < self.VertexNum:
            tArc = self.Vertices[index].FirstArc
            while tArc is not None:
                self.Vertices[tArc.adjacent].indegree = self.Vertices[
                    tArc.adjacent].indegree + 1
                tArc = tArc.NextArc
            index = index + 1

    #######################
    #算法 6-20 拓扑排序算法
    #######################
    def TopologicalSort(self):
        self.FindIndegree()
        StackVertex = []
        index = 0
        while index < self.VertexNum:
            if self.Vertices[index].indegree is 0:
                StackVertex.append(index)
            index = index + 1
        result = []
        while len(StackVertex) > 0:
            tVertex = StackVertex.pop()
            # print(self.Vertices[tVertex].data)
            result.append(self.Vertices[tVertex].data)
            tAdjacent = self.GetFirstAdjacentVertex(tVertex)
            while tAdjacent is not None:
                self.Vertices[
                    tAdjacent].indegree = self.Vertices[tAdjacent].indegree - 1
                if self.Vertices[tAdjacent].indegree is 0:
                    StackVertex.append(tAdjacent)
                tAdjacent = self.GetNextAdjacentVertex(tVertex, tAdjacent)
        return len(result) != 0


##############
#主程序
##############
if __name__ == '__main__':
    #创建一个有向图
    while 1:
        graph = Graph(2)
        graph.CreateGraph()
        print("Yes" if graph.TopologicalSort() else "No")
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 F: 顺序查找

**题目描述:**
给定一个序列 `L` 和待查数据 `a`, 利用顺序查找算法判断 `a` 是否在序列 `L` 中

**输入:**
输入为多组, 每组一行, 每组的最后一个数据为待查数据, 前面所有数据为序列 `L`

**输出:**
如果待查数据在队列中找到, 给出其第一次出现在序列中的序号; 否则输出 `-1`

**样例输入:**

```python
1 2 3 4 5 3
```

```python
1 2 3 4 5 6
```

**样例输出:**

```python
3
```

```python
-1
```

**提示:**
参考程序 `ex070201_02.py`

**分析与解:**

首先, 注意到 `3` 在 `1 2 3 4 5` 的位置不是 `2`, 而是 `3` (这与我习惯的从 `0` 开始有一点不一样)。其次, 我给出一个有点 Python 风格的解法:

```python
while 1:
    l = list(map(int, input().split()))
    element = l[-1]
    l = l[:-1]
    if (element in l):
        print(l.index(element) + 1)
    else:
        print(-1)
```

注意到我在查找元素时, 使用了现成的 `index` 方法与 `in` 运算符 (后 4 行)。现在我的任务是把这一部分用自己的代码实现。怎么实现呢? 看看程序 `ex070201_02.py`, 我就知道, 它利用了 `for` 循环, 并且使用 `==` 这个更为基本的运算符判断待查数据 `a` 是否在序列 `L` 中。所以, 不难写出这样的代码 (无妨使用 `while` 循环, 并且我顺着查找):

```python
def look(numlist, element):
    i = 0
    length = len(numlist)
    while i < length:
        if numlist[i] == element:
            return i + 1
        else:
            i = i + 1
    return -1

while 1:
    l = list(map(int, input().split()))
    element = l[-1]
    l = l[:-1]
    print(look(l, element))
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 G: 折半查找

**题目描述:**
给出有序序列 `L` 和数据 `a`, 利用折半查找判断 `a` 是否在 `L` 中

**输入:**
数据有多组, 每组输入一行, 最后一个为待查数据, 前面所有数据为有序序列

**输出:**
每组数据输出一行, 如果 `a` 在 `L` 中, 给出 `a` 在 `L` 中第一次出现的序号; 否则输出 `-1`

**样例输入:**

```python
1 2 3 4 5 3
```

```python
4 5 6 8
```

**样例输出:**

```python
2
```

```python
-1
```

**提示:**
参考程序 `ex070202.py`

**分析与解:**

首先, 我给出一个有点 Python 风格的解法:

```python
while 1:
    l = list(map(int, input().split()))
    element = l[-1]
    l = l[:-1]
    if (element in l):
        print(l.index(element))
    else:
        print(-1)
```

与[问题 F](#问题-f-顺序查找) 类似, 我只要替换后 4 行即可。根据程序 `ex070202.py` 的想法, 我不难写出下面的程序:

```python
def look(numlist, element):
    low = 0
    high = len(numlist) - 1
    while low <= high:
        mid = int((low + high) / 2)
        if element == numlist[mid]:
            return mid
        elif element < numlist[mid]:
            high = mid - 1
        else:
            low = mid + 1
    return -1

while 1:
    l = list(map(int, input().split()))
    element = l[-1]
    l = l[:-1]
    print(look(l, element))
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 H: 二叉排序树

**题目描述:**
根据输入的数据序列创建二叉排序树, 输入数据为正整数

**输入:**
数据有多组, 每组一行, 输入一个正整数序列

**输出:**
每组输出一行, 给出对应正整数序列创建的二叉排序树的中序遍历序列

**样例输入:**

```python
10 5 2 15 7 13 18
```

**样例输出:**

```python
2 5 7 10 13 15 18
```

**提示:**
参考程序 `ex070301_02.py`

**分析与解:**

还是先给出有点 Python 风格的解法:

```python
while 1: print(' '.join(map(str, sorted(map(int, input().split())))))
```

您说您看不懂? 没关系, 我给个不这么 Python 的解答 (其实, 也没打算让您那么写)。也没什么好说的, 下面直接给基于程序 `ex070301_02.py` 的解答。想法很简单: 利用动态查找的思想, 找不到元素, 就把元素插入到二叉树内, 然后中序输出。这些模块, 程序 `ex070301_02.py` 都有。改改输出就好了 (正如赵先生所言, 只需改动输入输出语句即可)。

```python
#################################################################
# 文件名: ex070301_02.py
# 版本号: 0.2
# 创建时间: 2017-09-14
# 修改时间: 2019-11-21
#################################################################
#################################################################
# 类名称: DElemType
# 类说明: 数据元素类型
# 类释义: 该类拥有带排序的关键字 key 和值 value
#################################################################
class DElemType:
    def __init__(self, key):
        self.key = key


#################################################################
# 类名称: BSTNode
# 类说明: 定义一个二叉排序树的结点
# 类释义: 分别有数据元素 data, 左孩子 Left 和右孩子 Right
#################################################################
class BSTNode:
    def __init__(self, data):
        self.data = data
        self.Left = None
        self.Right = None


#################################################################
# 类名称: BSTree
# 类说明: 定义一个二叉排序树
# 类释义:
#################################################################
class BSTree:
    def __init__(self):
        self.root = None
        for i in range(len(node_list)):
            self.InsertNode(node_list[i])

    ##################################
    # 算法 7-7 二叉排序树的插入结点函数
    ##################################
    def InsertNode(self, key):
        bt = self.root
        if not bt:
            self.root = BSTNode(DElemType(key))
            return
        while True:
            if (key < bt.data.key):
                if not bt.Left:
                    bt.Left = BSTNode(DElemType(key))
                    return
                bt = bt.Left
            elif key > bt.data.key:
                if not bt.Right:
                    bt.Right = BSTNode(DElemType(key))
                    return
                bt = bt.Right
            else:
                bt.data.key = key
                return

    ############################
    # 创建二叉排序树函数
    ############################
    def CreateTree(self, node_list):
        for i in range(len(node_list)):
            self.InsertNode(node_list[i])

    ############################
    # 查找函数
    ############################
    def SearchBST(self, root, key):
        if not root:
            self.InsertNode(key)
            return
        elif root.data.key == key:
            return root
        elif root.data.key < key:
            return self.SearchBST(root.Right, key)
        else:
            return self.SearchBST(root.Left, key)

    ############################
    # 中序遍历
    ############################
    def Visit(self, root):
        if not root:
            return
        self.Visit(root.Left)
        # print(root.data.key)
        print(root.data.key, end=' ')
        self.Visit(root.Right)


#################################################################
# 主程序
#################################################################

while 1:
    node_list = list(map(int, input().split()))
    BST = BSTree()
    BST.CreateTree(node_list)
    BST.Visit(BST.root)
    for BSTKey in node_list:
        BST.SearchBST(BST.root, BSTKey)
    print()
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 I: 冒泡排序

**题目描述:**
利用冒泡排序对给定序列排序, 排序数据为正整数

**输入:**
输入只有一行, 为待排序的正整数序列

**输出:**
第一行输出没排序前的原始序列, 以后每一行输出冒泡排序每一轮排好序的序列

**样例输入:**

```python
51 39 78 56 64 20 69 28
```

**样例输出:**

```python
51 39 78 56 64 20 69 28
39 51 56 64 20 69 28 78
39 51 56 20 64 28 69 78
39 51 20 56 28 64 69 78
39 20 51 28 56 64 69 78
20 39 28 51 56 64 69 78
20 28 39 51 56 64 69 78
```

**提示:**
参考程序 `ex080301_Update.py`

**分析与解:**

首先改一改输入部分 `CreateSequenceListByInput(self)`。注意到这个算法利用了 `flag` 标记是否已经排好序, 所以这暗示我在 `if flag == 0: break` 后添加输出语句。使用 `' '.join(iterable)` 将存着字符串的 `iterable` 用空格拼接。在排序前也输出一遍待排序的原始序列。代码如下:

```python
#################################################################
# 文件名: ex080301_Update.py
# 版本号: 0.2
# 创建时间: 2017-09-14
# 修改时间: 2019-11-21
# 算法 8-8 改进的冒泡排序算法
#################################################################
class ListItem(object):
    def __init__(self, key, value):
        self.key = key
        self.value = value


class SortSequenceList(object):
    def __init__(self):
        self.SeqList = []

    def CreateSequenceListByInput(self):
        self.SeqList.append(ListItem(int(0), 0))
        # print("请输入数据: ")
        tmplist = list(map(int, input().split()))
        for i in range(len(tmplist)):
            self.SeqList.append(ListItem(tmplist[i], i + 1))

    def TraverseElementSet(self):
        for i in range(1, len(self.SeqList)):
            print(self.SeqList[i].key)

    ##############################
    #算法 8-8 改进的冒泡排序算法
    ##############################
    def BubbleSort(self):
        SeqListLen = len(self.SeqList)
        for i in range(1, SeqListLen - 1):
            flag = 0
            for j in range(1, SeqListLen - i):
                # print("j=", j)
                if self.SeqList[j + 1].key < self.SeqList[j].key:
                    self.SeqList[0].key = self.SeqList[j].key
                    self.SeqList[j].key = self.SeqList[j + 1].key
                    self.SeqList[j + 1].key = self.SeqList[0].key
                    flag = 1
            if flag == 0:
                break
            print(' '.join(map(str, [x.key for x in self.SeqList][1:])))


if __name__ == '__main__':
    SL = SortSequenceList()
    SL.CreateSequenceListByInput()
    print(' '.join(map(str, [x.key for x in SL.SeqList][1:])))
    SL.BubbleSort()
    # print('排序算法结果为:')
    # SL.TraverseElementSet()
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***

## 问题 J: 快速排序

**题目描述:**
利用快速排序为给定正整数序列排序, 默认序列中第一个元素为枢轴记录

**输入:**
输入只有一行, 给定一个正整数序列

**输出:**
输出第一行为待排序的原始序列, 以后每一行输出每一次调用 `AdjustPartition` 后的序列结果

**样例输入:**

```python
51 39 78 56 64 20 69 28
```

**样例输出:**

```python
51 39 78 56 64 20 69 28
28 39 20 51 64 56 69 78
20 28 39 51 64 56 69 78
20 28 39 51 56 64 69 78
20 28 39 51 56 64 69 78
```

**提示:**
参考程序 `ex080302.py`

**分析与解:**

跟[问题 I](#问题-i-冒泡排序) 类似, 改一改输入输出部分。这一次, 我在 `QuickSort(self, low, high)` 处添加输出语句。代码如下:

```python
#################################################################
# 文件名: ex080302.py
# 版本号: 0.2
# 创建时间: 2017-09-14
# 修改时间: 2019-11-21
#################################################################
class ListItem(object):
    def __init__(self, key, value):
        self.key = key
        self.value = value


class SortSequenceList(object):
    def __init__(self):
        self.SeqList = []

    def CreateSequenceListByInput(self):
        self.SeqList.append(ListItem(int(0), 0))
        tmplist = list(map(int, input().split()))
        for i in range(len(tmplist)):
            self.SeqList.append(ListItem(tmplist[i], i + 1))

    def TraverseElementSet(self):
        for i in range(1, len(self.SeqList)):
            print(self.SeqList[i].key)

    ############################
    #算法 8-9 快速排序的一趟分区
    ############################
    def AdjustPartition(self, low, high):
        left = low
        right = high
        self.SeqList[0].key = self.SeqList[left].key
        while left < right:
            while left < right and self.SeqList[right].key >= self.SeqList[
                    0].key:
                right = right - 1
            self.SeqList[left].key = self.SeqList[right].key
            while left < right and self.SeqList[left].key <= self.SeqList[
                    0].key:
                left = left + 1
            self.SeqList[right].key = self.SeqList[left].key
        self.SeqList[left].key = self.SeqList[0].key
        return left

############################
#算法 8-10 快速排序
############################

    def QuickSort(self, low, high):
        if low < high:
            pivot = self.AdjustPartition(low, high)
            print(' '.join(map(str, [x.key for x in self.SeqList][1:])))
            self.QuickSort(low, pivot - 1)
            self.QuickSort(pivot + 1, high)

if __name__ == '__main__':
    SL = SortSequenceList()
    # SL.CreateSequenceListByInput(10)
    SL.CreateSequenceListByInput()
    print(' '.join(map(str, [x.key for x in SL.SeqList][1:])))
    length = len(SL.SeqList) - 1
    SL.QuickSort(1, length)
    # print('排序算法结果为:')
    # SL.TraverseElementSet()
```

这道题就这么结束了。点击[这里](#data-structure-02)返回到开头处。

***
***

卑微小 `τ` 在此非常感谢您的阅读。Farewell.
