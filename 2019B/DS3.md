# Solutions to Data Structure (III)

持续更新中。有问题直接问。

---
---

## 2019 年 12 月 30 日

### 问题 A: 整数排序

**题目描述:**
给出一组正整数, 将其按升序排序

**输入:**
一组正整数

**输出:**
按升序排序后的序列

**样例输入:**
`3 2 1 4 5`

**样例输出:**
`1 2 3 4 5`

**参考解答:**

赵先生也不说清楚, 我没写 `while 1:` 就挂了! 哼!

对了, 请各位熟悉 `sorted()`, `map()`, `split()`, `join()` 这些高频方法。

```python
while 1:
    print(" ".join(map(str, sorted(map(int, input().split())))))

```

---

### 问题 B: 合并数字

**题目描述:**
给出 `n` 个数, 将 `n` 个数合并为一个数, 每次只能选择两个数 `a, b` 合并, 需要能量 `a + b`, 输出将 `n` 个数合并成一个数后消耗的最小能量

**输入:**
输入有多行, 每行输入若干个整数

**输出:**
输出多行, 每行为该行数据合并消耗的最小能量

**样例输入:**
`1 2 3 4`

**样例输出:**
`19`

**提示:**

```l
对序列 1 2 3 4, 消耗最小能量的合并过程如下:
1 和 2 合并得到 3, 消耗能量 3
3 和 3 合并得到 6, 消耗能量 6
6 和 4 合并得到 10, 消耗能量 10
总消耗能量 19
考虑一下 huffman
```

**参考解答:**

```python
def energy(data: list) -> int:
    temp: list = []
    s: int = 0
    if len(data) <= 2:
        return sum(data)
    else:
        temp = sorted(data)
        s = temp[0] + temp[1]
        return s + energy(temp[2:] + [s])


while 2:
    print(energy(list(map(int, input().split()))))

```

---

### 问题 C: 滑动窗口的最大值

**题目描述:**
给出一个包含若干个正整数的数组, 和一个正整数 `k`, 对数组从左到右滑动一个大小为 `k` 的窗口, 依次给出滑动过程中各个窗口里的最大值

**输入:**
输入有多组, 每组第一行为若干个正整数, 第二行输入一个正整数 `k`

**输出:**
输出有多组, 每组输出由窗口中最大值构成的序列

**样例输入:**

```python
1 2 7 7 8
3
```

**样例输出:**
`7 7 8`

**提示:**

```l
1 2 7 7 8 窗口长度为 3, 从左到右滑动窗口, 得到下面的序列片段
[1 2 7] [2 7 7] [7 7 8] 取各个窗口中的最大值, 得到
7 7 8
```

**参考解答:**

```python
def maximum(data: list, k: int) -> list:
    result: list = []
    for i in range(len(data) - k + 1):
        result.append(max(data[i:i + k]))
    return result


while 3:
    print(" ".join(
        map(str, maximum(list(map(int,
                                  input().split())), int(input())))))

```

---

### 问题 D: 统计前面比自己小的数

**题目描述:**
给定一个正整数组成的数组, 对数组中的每个元素, 计算其前面比它小的元素个数

**输入:**
输入有多组, 每组一行, 每一行包含组成数组的若干个正整数

**输出:**
每一行输出统计结果

**样例输入:**
`1 2 7 8 5`

**样例输出:**
`0 1 2 3 2`

**参考解答:**

```python
def smaller(data: list) -> list:
    result: list = [0] * len(data)
    for i in range(len(data)):
        for j in range(i):
            if data[j] < data[i]:
                result[i] += 1
    return result


while 4:
    print(" ".join(map(str, smaller(list(map(int, input().split()))))))

```

---
---

## 2019 年 12 月 31 日

### 问题 E: 寻找丢失的数

**题目描述:**
给出一个由 1 到 n 的正整数组成的一个字符串, 其中数字顺序是无序的, 且数字中间没有空格。如果所给序列丢失了一个整数, 请找到它

**输入:**
输入有多组, 每组有两行, 第一行输入正整数 n, 表示一个有多少正整数, 第二行输出除了缺失的正整数之外的所有正整数的一个无序序列组成的字符串 

**输出:**
每个输入有一行输出, 输出丢失的数 

**样例输入:**

```python
20
19201234567891011121314151618
```

**样例输出:**
`17`

**参考解答:**

在这里, 最笨的方法就是最好的方法! 注意, `while "False":` 跟 `while False:` 有别。

```python
def num_sequence(n: int, k: int) -> str:
    # n: the number of positive integers
    # k: the number to be skipped
    # For example, num_sequence(20, 3) gives
    # 124567891011121314151617181920
    result: str = ""
    for i in range(n):
        if i + 1 == k:
            continue
        else:
            result += str(i + 1)
    return result


def comparison(n: int, num_str: str) -> int:
    data1: list = sorted(num_str)
    for i in range(n):
        if data1 == sorted(num_sequence(n, i + 1)):
            return i + 1
    return -1


while "False":
    print(comparison(int(input()), input()))

```

---

### 问题 F: 稀疏矩阵乘法

**题目描述:**
给出两个稀疏矩阵 A 和 B 的三元组表示, 返回矩阵 A 和 B 乘积 AB 的三元组表示 

**输入:**
输入有多组, 每组为四行, 第一行输入两个数, 为矩阵 A 的行、列值, 第二行为矩阵 A 的三元组表示, 3 个数一组, 有若干组, 数字中间由空格间隔, 第三行输入两个数, 为矩阵 B 的行、列值, 第二行为矩阵 B 的三元组表示, 3 个数一组, 有若干组, 数字中间由空格间隔

**输出:**
每组输出为两行, 第一行两数, 表示矩阵 AB 的行、列值; 第二行表示矩阵 AB 的三元组表示, 3个一组, 有若干组, 数字中间由空格间隔

**样例输入:**

```python
2 3
0 0 1 1 2 3
3 3
0 0 7 2 2 1
```

**样例输出:**

```python
2 3
0 0 7 1 2 3
```

**参考解答:**

```python
def matrix_multiplication(a: list, b: list) -> list:
    return [[
        sum([a[i][k] * b[k][j] for k in range(len(b))])
        for j in range(len(b[0]))
    ] for i in range(len(a))]


def generate_matrix(size: list, elements: list) -> list:
    # size[0]: number of rows
    # size[1]: number of columns
    # elements: consists of elements like [i j element]
    result: list = [[0 for j in range(size[1])] for i in range(size[0])]
    for element in elements:
        result[element[0]][element[1]] = element[2]
    return result


def generate_3_tuple(matrix: list) -> list:
    result: list = []
    for i in range(len(matrix)):
        for j in range(len(matrix[0])):
            if matrix[i][j] != 0:
                result.append([i, j, matrix[i][j]])
    return result


def string_to_3_tuple(data: str) -> list:
    temp: list = list(map(int, data.split()))
    result: list = []
    three_tuple: list = [0, 0, 0]
    counter: int = 0
    while 3 * counter < len(temp):
        three_tuple = [
            temp[3 * counter], temp[3 * counter + 1], temp[3 * counter + 2]
        ]
        result.append(three_tuple)
        counter += 1
    return result


def three_tuple_to_string(data: list) -> str:
    temp: list = []
    for x in data:
        for y in x:
            temp.append(y)
    return " ".join(map(str, temp))


while "Hello World":
    ans: list = matrix_multiplication(
        generate_matrix(list(map(int,
                                 input().split())),
                        string_to_3_tuple(input())),
        generate_matrix(list(map(int,
                                 input().split())),
                        string_to_3_tuple(input())))
    print("{0} {1}".format(len(ans), len(ans[0])))
    print(three_tuple_to_string(generate_3_tuple(ans)))

```

---

### 问题 G: 链表划分

**题目描述:**
给定一个单链表和数值 x, 划分链表, 使得所有小于 x 的结点排在大于等于 x 的结点之前, 排好后的新链表数据除了 x 之外, 保持原有的相对次序

**输入:**
每组输入两行, 第一行为数值 x; 第二行为单链表数据
输入有多组 

**输出:**
每组输出一行, 为划分后的单链表数据输出 

**样例输入:**

```python
3
1 4 3 2 5 2
```

**样例输出:**
`1 2 2 4 3 5`

**参考解答:**

```python
def division(x: int, data: list) -> list:
    lower: list = []
    not_lower: list = []
    for element in data:
        if element < x:
            lower.append(element)
        else:
            not_lower.append(element)
    return lower + not_lower


while "Easy":
    print(" ".join(
        map(str, division(int(input()), list(map(int,
                                                 input().split()))))))

```

---
---

## 2020 年 1 月 2 日

### 问题 H: 多关键字排序

**题目描述:**
给出 n 个学生的学号和成绩, 将学生按成绩排降序, 若成绩相同, 按学号升序排列

**输入:**
输入多组, 每组两行, 第一行为正整数 n, 表示学生数目, 第二行为 2n 个数, 分别表示 n 个学生的学号, 成绩对 

**输出:**
每组输出 n 行, 每行两个数, 第一个为学号, 第二个为成绩 

**样例输入:**
```python
3
1 50 2 50 3 100
```

**样例输出:**
```python
3 100
1 50
2 50
```

**参考解答:**

```python
def grade_sort(data: list) -> list:
    # data is a list. Example: [[2, 50], [3, 60], [1, 50]]
    # The first is the student's number, and the second is the grade
    result: list = data[:]
    for i in range(len(result)):
        for j in range(i):
            if result[j][1] < result[i][1]:
                result[i], result[j] = result[j][:], result[i][:]
    for i in range(len(result)):
        for j in range(i):
            if result[j][1] == result[i][1]:
                if result[j][0] > result[i][0]:
                    result[i], result[j] = result[j][:], result[i][:]
    return result


def string_to_2_tuple(data_str: str) -> list:
    # data_str is a string. Example: "2 50 3 60 1 50"
    temp: list = list(map(int, data_str.split()))
    result: list = []
    two_tuple: list = [0, 0]
    counter: int = 0
    while 2 * counter < len(temp):
        two_tuple = [temp[2 * counter], temp[2 * counter + 1]]
        result.append(two_tuple)
        counter += 1
    return result


def two_tuple_to_string(data: list) -> str:
    # data is a list. Example: [[2, 50], [3, 60], [1, 50]]
    return "\n".join([" ".join(map(str, two_tuple)) for two_tuple in data])


while "Happy New Year!":
    # Actually I made no use of the variable "num"
    num: int = int(input())
    print(two_tuple_to_string(grade_sort(string_to_2_tuple(input()))))

```

---

### 问题 I: 逆波兰表达式

**题目描述:**
给定一个算术表达式 (字符串形式, 符号包括 + - * / 和小括号 ( ) )。返回它的逆波兰表达式

**输入:**
输入多行, 每行为一个表达式

**输出:**
输出每一行表达式对应的逆波兰表达式

**样例输入:**
```python
3 - 4 * 5
( 3 - 4 ) * 5
```

**样例输出:**
```python
3 4 5 * -
3 4 - 5 *
```

**参考解答:**

```python
def grade(operator: str) -> int:
    # Of course, the higher the number is, the greater the grade is
    return 1 if operator in ["+", "-"] else 2


def in_fix_to_post_fix(in_fix_expression: list) -> list:
    operators: list = []
    post_fix_expression: list = []
    temp: str = ""
    for element in in_fix_expression:
        if element in ["+", "-", "*", "/"]:
            # "while 9:" means the same as "while True:"
            while 9:
                if len(operators) == 0:
                    operators.append(element)
                    break
                temp = operators.pop()
                if temp == "(" or grade(element) > grade(temp):
                    operators.append(temp)
                    operators.append(element)
                    break
                else:
                    post_fix_expression.append(temp)
        elif element == "(":
            operators.append(element)
        elif element == ")":
            while len(operators) > 0:
                temp = operators.pop()
                if temp != "(":
                    post_fix_expression.append(temp)
                else:
                    break
        else:
            post_fix_expression.append(element)
    while len(operators) > 0:
        post_fix_expression.append(operators.pop())
    return post_fix_expression


while "Happy New Year!":
    print(" ".join(in_fix_to_post_fix(input().split())))

```

---
---

## 2020 年 1 月 3 日

### 问题 J: 无向图的连通分支问题

**题目描述:**
对给定无向图, 计算它的连通分支

**输入:**

第 1 行输入三个正整数 n, m, k; 分别表示图有 n 个顶点, m 条边, k 是查询顶点对个数。接下来第 2 行-第 m+1 行, 每行两个正整数表示一条边。接下来第 m+2 行到第 m+k+1 行, 每行两个正整数表示查询顶点对 

**输出:**
输出 k 行, 如果对应顶点对属于同一个连通分支, 输出 Yes; 否则, 输出 No

**样例输入:**

```python
10 3 3
1 2
3 4
1 3
2 3
1 4
5 6
```

**样例输出:**

```python
Yes
Yes
No
```

**参考解答:**

我把[上一次的题](https://gitee.com/septsea/hello/blob/master/DSA02.md#问题-c-最短路径)用上来了, 仅小改了两下。我可以把无向图当作权值只取 0, 1, infinity 的无向网。不得不说, 轮子思维真香, 无论是自己造还是用别人的。

```python
##################################################################
# 文件名: ex060503.py (6.5.3 每一对顶点之间的最短路径)
# 版本号: 0.5.1
# 创建时间: 2017-12-12
# 修改时间: 2020-1-3
##################################################################
# 算法 6-18
##################################################################
# 类名称: Vertex
# 类说明: 定义图的一个顶点
# 类释义: 包含顶点值 data 和顶点的相关信息 info
##################################################################
class Vertex(object):
    def __init__(self, data):
        self.data = data
        self.info = None


##################################################################
# 类名称: Graph
# 类说明: 定义一个图
# 类释义: 包含该图的类型 kind (0 无向图, 1 无向网, 2 有向图, 3 有向网)、
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
    # 以邻接矩阵为存储结构创建有向网的方法
    # 这里魔改为无向网
    ######################################
    def CreateGraph(self, n, m):
        # print('请依次输入图中各顶点的值, 每个顶点值以回车间隔, 并以#作为输入结束符: ')
        # (n, m) = map(int, input().split())
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
        # 依次输入边或弧的两个顶点, 并进行定位
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
            # weight = float(arc.split()[2])
            weight = 1
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
        # self.OneOfTheShortestPath(Dist, Path, Start, End)
        return self.OneOfTheShortestPath(Dist, Path, Start, End)

    #####################################
    ##输出每一组顶点间的最短路径 (Floyd)
    #####################################
    # def AllShortestPath(self, Dist, Path):
    #     tPath = []
    #     Start = 0
    #     while Start < self.VertexNum:
    #         End = 0
    #         while End < self.VertexNum:
    #             if Dist[Start][End] < float("inf") and Start is not End:
    #                 print('从顶点' + self.Vertices[Start].data + '到顶点' +
    #                       self.Vertices[End].data + '的路径为: ',
    #                       end='')
    #                 tVertex = Path[Start][End]
    #                 tPath.append(End)
    #                 while (tVertex is not -1) and tVertex is not Start:
    #                     tPath.append(tVertex)
    #                     tVertex = Path[Start][tVertex]
    #                 tPath.append(Start)
    #                 while len(tPath) > 0:
    #                     print(self.Vertices[tPath.pop()].data, end='')
    #                 print()
    #                 print('其路径长度为: ' + str(Dist[Start][End]))
    #             End = End + 1
    #         Start = Start + 1

    # 在 AllShortestPath(self, Dist, Path) 的基础上写出方法
    # OneOfTheShortestPath(self, Dist, Path, Start, End)
    def OneOfTheShortestPath(self, Dist, Path, Start, End):
        tPath = []
        if Dist[Start][End] < float("inf") and Start is not End:
            tVertex = Path[Start][End]
            tPath.append(End)
            while (tVertex is not -1) and tVertex is not Start:
                tPath.append(tVertex)
                tVertex = Path[Start][tVertex]
            tPath.append(Start)
            if len(tPath) > 1:
                return 'Yes'
        return 'No'


while 10:
    # 注意! 题干给出的是无向图!
    # 创建一个无向网
    graph = Graph(1)
    (n, m, k) = map(lambda x: int(x), input().split())
    graph.CreateGraph(n, m)
    temp = []
    for i in range(k):
        temp.append(list(map(lambda x: int(x) - 1, input().split())))
    for pair in temp:
        (Start, End) = pair
        print(graph.Floyd(Start, End))

```

---
---

我知道, 您可能将我视为**工具**, 不过, 我仍要感谢您的关注与支持! 没有您的需求, 这个网页不可能出现在这里。再次表示感谢!

菜鸡的卑微小 `τ` 祝您新年快乐且万事顺利。

是时候得预习物理了。

有缘再会! Farewell.

2020 年 1 月 3 日 6 时 45 分
