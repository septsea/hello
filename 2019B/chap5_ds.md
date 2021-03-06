# Five

1\. A。参见页 213 性质 2。

2\. C。设 $S_n=2^0+2^1+\cdots+2^{n-1}$, 那么 $S_{10}\leq1028<S_{11}$, 从而当二叉树尽可能满的时候有 11 层。极端情形自然是 1028 层, 每层 1 个。

3\. B。对于 A, C, D, 考虑每层只有一个元素的树就行了。

4\. B。这棵树自然是

```l
      A
  B       C
D   E   F   G
```

从而, `A(B(D,E),C(F,G))` 的先序序列是 `A` + `B(D,E)` 的先序序列 + `C(F,G)` 的先序序列。`B(D,E)` 的先序序列是 `BDE`, `C(F,G)` 的先序序列是 `CFG`, 所以 `A(B(D,E),C(F,G))` 的先序序列是 `ABDECFG`。

5\. C。参见页 224 性质 3。

6\. `4`; `3`; `3`。前二个参考页 210; 第三个参考页 254。

7\. `ABCDEF`; `CBDAEF`; `CDBFEA`。

```l
A(B(C,D),E(,F)) 的先序序列 = A + B(C,D) 的先序序列 + E(,F) 的先序序列
                          = A + BCD + EF
                          = ABCDEF
A(B(C,D),E(,F)) 的中序序列 = B(C,D) 的中序序列 + A + E(,F) 的中序序列
                          = CBD + A + EF
                          = CBDAEF
A(B(C,D),E(,F)) 的后序序列 = B(C,D) 的后序序列 + E(,F) 的后序序列 + A
                          = CDB + FE + A
                          = CDBFEA
```

8\. `12`。画出来:

```l
1
2                       3
4           5           6     7
8     9     10    11    12 13 14 15
16 17 18 19 20 21 22 23
```

可以看出, 从 `12` 到 `23` 都是叶子结点, 从而有 `23 - (12 - 1) = 12` 个叶子结点。

9\. `G`。 一个基本原则是 "左子右兄"。首先, 写出这样的结构:

```l
A
B(1)      E(2)      F(3)
C D                 G H
                      I
```

于是有 (`[ ]` 表示空)

```l
A
(1)         [ ]
[ ] (2)
    [ ] (3)
```

现在我们看 `(3)` 怎么变为二叉树。根据 "左子右兄", 有

```l
F
G         [ ]
[ ] H
    I [ ]
```

从而 `F` 的左孩子是 `G`, 右孩子是空。

10\. `1110`。为方便, 我们考虑频率的 100 倍 (无妨称为 `w value`), 并依 `w value` 升序排列:

```l
D: 9, A: 10, E: 18, B: 20, C: 43
```

挑选出 `w value` 最小的二个 (`D: 9, A: 10`), 移除它们, 添加 `[ ](D,A): 9+10` (`[ ]` 表示空), 并依 `w value` 升序排列:

```l
E: 18, [ ](D,A): 19, B: 20, C: 43
```

挑选出 `w value` 最小的二个 (`E: 18, [ ](D,A): 19`), 移除它们, 添加 `[ ](E,[ ](D,A)): 18+19`, 并依 `w value` 升序排列:

```l
B: 20, [ ](E,[ ](D,A)): 37, C: 43
```

挑选出 `w value` 最小的二个 (`B: 20, [ ](E,[ ](D,A)): 37`), 移除它们, 添加 `[ ](B,[ ](E,[ ](D,A))): 20+37`, 并依 `w value` 升序排列:

```l
C: 43, [ ](B,[ ](E,[ ](D,A))): 57
```

挑选出 `w value` 最小的二个 (`C: 43, [ ](B,[ ](E,[ ](D,A))): 57`), 移除它们, 添加 `[ ](C,[ ](B,[ ](E,[ ](D,A)))): 43+57`, 并依 `w value` 升序排列:

```l
[ ](C,[ ](B,[ ](E,[ ](D,A)))): 100
```

只剩一个了。我们将它写为

```l
[ ]
C   [ ]
    B   [ ]
        E   [ ]
            D   A
```

从顶部的 `[ ]` 往下, 左 0 右 1, 从而 `D` 就是右右右左, 也就是 `1110`。
