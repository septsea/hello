# 计算 $\exp At$ 的一种方法

为了简单, 这里只讨论阶数不高于 3 的矩阵。

1 阶没什么好说的, 是吧? 我们先介绍一个引理。

**引理 (Cayley-Hamilton)** 设 $f(\lambda)$ 是 $n$ 阶方阵 $A$ 的特征多项式 $\det(\lambda E-A)$, 那么 $f(A)=O$, 其中 $O$ 是零矩阵, $E$ 是单位矩阵。

>**证** 设 $\lambda E-A$ 的伴随矩阵为 $B(\lambda)$, 从而 $(\lambda E-A)B(\lambda)=f(\lambda)E$。$B(\lambda)$ 的每个元素都是次数低于 $n$ 的多项式, 所以存在 $n$ 个元素全为数字的矩阵 $B_0,B_1,\cdots,B_{n-1}$ 使得
>$$B(\lambda)=B_0{\lambda}^{n-1}+B_1{\lambda}^{n-2}+\cdots+B_{n-1}$$
>设
>$$f(\lambda)={\lambda}^{n}+a_1{\lambda}^{n-1}+\cdots+a_n$$
>那么
>$$(\lambda E-A)(B_0{\lambda}^{n-1}+B_1{\lambda}^{n-2}+\cdots+B_{n-1})={\lambda}^{n}E+a_1{\lambda}^{n-1}E+\cdots+a_nE$$
>展开, 得
>$$\begin{cases}
>EB_0=E\\
>EB_1-AB_0=a_1E\\
>EB_2-AB_1=a_2E\\
>\cdots\\
>EB_{n-2}-AB_{n-2}=a_{n-1}E\\
>-AB_{n-1}=a_nE
>\end{cases}$$
>如果我们给第 $i$ 个 $(i=0,1,\cdots,n)$ 式子 ($EB_0=E$ 是第 0 个, $EB_1-AB_0=a_1E$ 是第 1 个, 等等) 左乘矩阵 $A^{n-i}$, 那么
>$$\begin{cases}
>A^nB_0=A^n\\
>A^{n-1}B_1-A^nB_0=a_1A^{n-1}\\
>A^{n-2}B_2-A^{n-1}B_1=a_2A^{n-2}\\
>\cdots\\
>AB_{n-1}-A^2B_{n-2}=a_{n-1}A\\
>-AB_{n-1}=a_nE
>\end{cases}$$
>左边相加为 $O$, 右边相加恰好为 $f(A)$。证毕。

定义多项式序列
$${\epsilon}_m(\lambda)=\begin{cases}
\frac{{\lambda}^m}{m!},&m>0\\
1,&m=0\\
0,&m<0
\end{cases}$$
不难验证
$${\epsilon}_m'(\lambda)={\epsilon}_{m-1}(\lambda),\qquad {\epsilon}_m(\lambda t)={\epsilon}_m(\lambda)t^m$$
所以 $\exp At$ 就是
$$\exp At=\sum_{m\geq0} {\epsilon}_m(At)=\sum_{m\geq0} {\epsilon}_m(A)t^m$$
我们希望求出 ${\epsilon}_m(A)$。

---

设 $g(\lambda)$ 是多项式。由多项式除法可知, 存在一对多项式 $q(\lambda),r(\lambda)$ 使得
$$g(\lambda)=q(\lambda)f(\lambda)+r(\lambda)$$
且 $r(\lambda)$ 的次数低于 $n$。以 $A$ 代 $\lambda$, 就有
$$g(A)=q(A)f(A)+r(A)=q(A)O+r(A)=r(A)$$
所以, 我们只需定出 $r(\lambda)$ 即可。

如果 ${\lambda}_i$ 是 $f(\lambda)$ 的 $k_i$ 重根, 即 $(\lambda-\lambda_i)^{r_i}$ 整除 $f(\lambda)$, 但 $(\lambda-\lambda_i)^{r_i+1}$ 不整除 $f(\lambda)$, 那么对 $g(\lambda)=q(\lambda)f(\lambda)+r(\lambda)$ 求 $u$ 次导, 有 (Leibniz 公式):
$$g^{(u)}(\lambda)=r^{(u)}(\lambda)+\sum_{0\leq m\leq u}C_u^mq^{(u-m)}(\lambda)f^{(m)}(\lambda)$$
从而
$$g^{(u)}(\lambda_i)=r^{(u)}(\lambda_i),\qquad 0\leq u<r_i$$

---

现在我们先假设 $A$ 是二阶矩阵。

**情形一:** 设 $A$ 有二个相异特征根 $\lambda_1, \lambda_2$, 那么 $r(\lambda)$ 就是一个次数低于 2 的多项式, 且
$$r(\lambda_1)=g(\lambda_1),\qquad r(\lambda_2)=g(\lambda_2)$$
那么满足条件的 $r(\lambda)$ 就是
$$r(\lambda)=\frac{\lambda-\lambda_2}{\lambda_1-\lambda_2}g(\lambda_1)+\frac{\lambda-\lambda_1}{\lambda_2-\lambda_1}g(\lambda_2)$$
这时
$${\epsilon}_m(A)=\frac{A-\lambda_2E}{\lambda_1-\lambda_2}{\epsilon}_m(\lambda_1)+\frac{A-\lambda_1E}{\lambda_2-\lambda_1}{\epsilon}_m(\lambda_2)$$
回想起 Taylor 公式
$$e^{\lambda t}=\sum_{m\geq0} {\epsilon}_m(\lambda t)=\sum_{m\geq0} {\epsilon}_m(\lambda)t^m$$
从而
$$\exp At=\frac{A-\lambda_2E}{\lambda_1-\lambda_2}e^{\lambda_1 t}+\frac{A-\lambda_1E}{\lambda_2-\lambda_1}e^{\lambda_2 t}$$

>注意到 $r(\lambda)$ 也可以写为
>$$r(\lambda)=g(\lambda_1)+\frac{g(\lambda_2)-g(\lambda_1)}{\lambda_2-\lambda_1}(\lambda-\lambda_1)$$
>从而
>$$\exp At=Ee^{\lambda_1 t}+(A-\lambda_1E)\frac{e^{\lambda_2t}-e^{\lambda_1t}}{\lambda_2-\lambda_1}$$

**情形二:** 设 $A$ 有二重特征根 $\lambda_1$, 那么 $r(\lambda)$ 就是一个次数低于 2 的多项式, 且
$$r(\lambda_1)=g(\lambda_1),\qquad r'(\lambda_1)=g'(\lambda_1)$$
那么满足条件的 $r(\lambda)$ 就是
$$r(\lambda)=g(\lambda_1)+g'(\lambda_1)(\lambda-\lambda_1)$$
这时
$${\epsilon}_m(A)=E{\epsilon}_m(\lambda_1)+(A-\lambda_1E){\epsilon}'_m(\lambda_1)=E{\epsilon}_m(\lambda_1)+(A-\lambda_1E){\epsilon}_{m-1}(\lambda_1)$$
从而
$$\exp At=Ee^{\lambda_1 t}+(A-\lambda_1E)te^{\lambda_1 t}$$

---

读者应该抓住了要点。我们只要写出 $r(t)$, 由此计算 ${\epsilon}_m(A)$ (可以表示为诸 ${\epsilon}_i(\lambda_j)$ 的 "线性" 组合), 从而算出 $\exp At$。

当 $A$ 的阶数为 3 时, 特征多项式可分为三类 ($\lambda_1,\lambda_2,\lambda_3$ 互异):

* $f(\lambda)=(\lambda-\lambda_1)(\lambda-\lambda_2)(\lambda-\lambda_3)$
* $f(\lambda)=(\lambda-\lambda_1)^3$
* $f(\lambda)=(\lambda-\lambda_1)^2(\lambda-\lambda_2)$

**第一种:** 分别考虑次数低于 3 且满足如下条件的三个特殊多项式 $L_i(\lambda)$:
$$L_1(\lambda_1)=1,\quad L_1(\lambda_2)=0,\quad L_1(\lambda_3)=0;$$
$$L_2(\lambda_1)=0,\quad L_2(\lambda_2)=1,\quad L_2(\lambda_3)=0;$$
$$L_3(\lambda_1)=0,\quad L_3(\lambda_2)=0,\quad L_3(\lambda_3)=1\,$$
不难看出下面的多项式满足条件:
$$L_1(\lambda)=\frac{(\lambda-\lambda_2)(\lambda-\lambda_3)}{(\lambda_1-\lambda_2)(\lambda_1-\lambda_3)},\quad L_2(\lambda)=\frac{(\lambda-\lambda_1)(\lambda-\lambda_3)}{(\lambda_2-\lambda_1)(\lambda_2-\lambda_3)},\quad L_3(\lambda)=\frac{(\lambda-\lambda_1)(\lambda-\lambda_2)}{(\lambda_3-\lambda_1)(\lambda_3-\lambda_2)}$$
所以
$$r(\lambda)=L_1(\lambda)g(\lambda_1)+L_2(\lambda)g(\lambda_2)+L_3(\lambda)g(\lambda_3)$$
在这种情况下
$$\exp At=L_1(A)e^{\lambda_1t}+L_2(A)e^{\lambda_2t}+L_3(A)e^{\lambda_3t}$$
具体写出, 就是
$$\exp At=\frac{(A-\lambda_2E)(A-\lambda_3E)}{(\lambda_1-\lambda_2)(\lambda_1-\lambda_3)}e^{\lambda_1t}+\frac{(A-\lambda_1E)(A-\lambda_3E)}{(\lambda_2-\lambda_1)(\lambda_2-\lambda_3)}e^{\lambda_2t}+\frac{(A-\lambda_1E)(A-\lambda_2E)}{(\lambda_3-\lambda_1)(\lambda_3-\lambda_2)}e^{\lambda_3t}$$

**第二种:** 分别考虑次数低于 3 且满足如下条件的三个特殊多项式 $M_i(\lambda)$:
$$M_1(\lambda_1)=1,\quad M_1'(\lambda_1)=0,\quad M_1''(\lambda_1)=0;$$
$$M_2(\lambda_1)=0,\quad M_2'(\lambda_1)=1,\quad M_2''(\lambda_1)=0;$$
$$M_3(\lambda_1)=0,\quad M_3'(\lambda_1)=0,\quad M_3''(\lambda_1)=1\,$$
不难看出下面的多项式满足条件:
$$M_1(\lambda)=1,\quad M_2(\lambda)=\lambda-\lambda_1,\quad M_3(\lambda)=\frac{(\lambda-\lambda_1)^2}{2!}$$
所以
$$r(\lambda)=M_1(\lambda)g(\lambda_1)+M_2(\lambda)g'(\lambda_1)+M_3(\lambda)g''(\lambda_1)$$
在这种情况下
$$\exp At=M_1(A)e^{\lambda_1t}+M_2(A)te^{\lambda_1t}+M_3(A)t^2e^{\lambda_1t}$$
具体写出, 就是
$$\exp At=Ee^{\lambda_1t}+(A-\lambda_1E)te^{\lambda_1t}+\frac{(A-\lambda_1E)^2}{2!}t^2e^{\lambda_1t}$$

**第三种:** 分别考虑次数低于 3 且满足如下条件的三个特殊多项式 $N_i(\lambda)$:
$$N_1(\lambda_1)=1,\quad N_1'(\lambda_1)=0,\quad N_1(\lambda_2)=0;$$
$$N_2(\lambda_1)=0,\quad N_2'(\lambda_1)=1,\quad N_2(\lambda_2)=0;$$
$$N_3(\lambda_1)=0,\quad N_3'(\lambda_1)=0,\quad N_3(\lambda_2)=1\,$$
$N_3(\lambda)$ 是比较容易看出来的:
$$N_3(\lambda)=\frac{(\lambda-\lambda_1)^2}{(\lambda_2-\lambda_1)^2}$$
根据 $N_2(\lambda)$ 的特征, 我们可以设
$$N_2(\lambda)=c\cdot(\lambda-\lambda_1)(\lambda-\lambda_2)$$
从而
$$N_2'(\lambda)=c\cdot((\lambda-\lambda_2)+(\lambda-\lambda_1)),\qquad N_2'(\lambda_1)=c(\lambda_1-\lambda_2)=1$$
进而
$$c=\frac{1}{\lambda_1-\lambda_2},\qquad N_2(\lambda)=\frac{(\lambda-\lambda_1)(\lambda-\lambda_2)}{\lambda_1-\lambda_2}$$
根据 $N_1(\lambda)$ 的特征, 我们可以设
$$N_1(\lambda)=(c_1+c_0(\lambda-\lambda_1))\frac{\lambda-\lambda_2}{\lambda_1-\lambda_2}$$
那么
$$N_1(\lambda_1)=c_1=1,\qquad N_1'(\lambda_1)=c_0\frac{\lambda_1-\lambda_2}{\lambda_1-\lambda_2}+c_1\frac{1}{\lambda_1-\lambda_2}=0$$
解出
$$c_1=1,\qquad c_0=\frac{1}{\lambda_2-\lambda_1}$$
从而
$$N_1(\lambda)=\Bigg(1+\frac{\lambda-\lambda_1}{\lambda_2-\lambda_1}\Bigg)\frac{\lambda-\lambda_2}{\lambda_1-\lambda_2}$$
综上
$$r(\lambda)=N_1(\lambda)g(\lambda_1)+N_2(\lambda)g'(\lambda_1)+N_3(\lambda)g(\lambda_2)$$
在这种情况下
$$\exp At=N_1(A)e^{\lambda_1t}+N_2(A)te^{\lambda_1t}+N_3(A)e^{\lambda_2t}$$
具体写出, 就是
$$\exp At=\Bigg(E+\frac{A-\lambda_1E}{\lambda_2-\lambda_1}\Bigg)\frac{A-\lambda_2E}{\lambda_1-\lambda_2}e^{\lambda_1t}+\frac{(A-\lambda_1E)(A-\lambda_2E)}{\lambda_1-\lambda_2}te^{\lambda_1t}+\frac{(A-\lambda_1E)^2}{(\lambda_2-\lambda_1)^2}e^{\lambda_2t}$$

---

用这里的方法计算 $\exp At$ 是方便的, 因为它避开了特征向量的计算, 并且思路简单, 公式清晰。当 $A$ 是二阶时甚为简洁!
