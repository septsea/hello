class Rational:
    """
    This is a library for performing calculations involving rational numbers.
    """
    def __init__(self, numerator: int, denominator: int = 1):
        if (type(numerator) != type(1) or type(denominator) != type(1)):
            raise Exception("Both arguments must be integers")
        if (denominator == 0):
            raise Exception("The denominator must not be 0")
        gcd = lambda a, b: abs(a) if (b == 0) else gcd(b, a % b)
        t: int = gcd(numerator, denominator)
        self.__numerator = numerator // t
        self.__denominator = denominator // t
        if (self.__denominator < 0):
            self.__numerator *= -1
            self.__denominator *= -1

    def getNumerator(self) -> int:
        return self.__numerator

    def getDenominator(self) -> int:
        return self.__denominator

    def __str__(self):
        return str(self.getNumerator()) if self.getDenominator(
        ) == 1 else f"{self.getNumerator()}/{self.getDenominator()}"

    def __neg__(self):
        n: int = -self.getNumerator()
        d: int = self.getDenominator()
        return Rational(n, d)

    def __pos__(self):
        return self

    def __abs__(self):
        return self if (self.getNumerator() >= 0) else -self

    def __add__(self, other):
        if (not isinstance(other, (Rational, int))):
            return NotImplemented
        n1: int = self.getNumerator()
        d1: int = self.getDenominator()
        if (isinstance(other, Rational)):
            n2: int = other.getNumerator()
            d2: int = other.getDenominator()
        else:
            n2: int = other
            d2: int = 1
        n: int = n1 * d2 + n2 * d1
        d: int = d1 * d2
        return Rational(n, d)

    def __radd__(self, other):
        if (isinstance(other, (Rational, int))):
            return self + other
        else:
            return NotImplemented

    def __sub__(self, other):
        if (not isinstance(other, (Rational, int))):
            return NotImplemented
        n1: int = self.getNumerator()
        d1: int = self.getDenominator()
        if (isinstance(other, Rational)):
            n2: int = other.getNumerator()
            d2: int = other.getDenominator()
        else:
            n2: int = other
            d2: int = 1
        n: int = n1 * d2 - n2 * d1
        d: int = d1 * d2
        return Rational(n, d)

    def __rsub__(self, other):
        if (isinstance(other, (Rational, int))):
            return -self + other
        else:
            return NotImplemented

    def __mul__(self, other):
        if (not isinstance(other, (Rational, int))):
            return NotImplemented
        n1: int = self.getNumerator()
        d1: int = self.getDenominator()
        if (isinstance(other, Rational)):
            n2: int = other.getNumerator()
            d2: int = other.getDenominator()
        else:
            n2: int = other
            d2: int = 1
        n: int = n1 * n2
        d: int = d1 * d2
        return Rational(n, d)

    def __rmul__(self, other):
        if (isinstance(other, (Rational, int))):
            return self * other
        else:
            return NotImplemented

    def __eq__(self, other):
        if (not isinstance(other, (Rational, int))):
            return NotImplemented
        n1: int = self.getNumerator()
        d1: int = self.getDenominator()
        if (isinstance(other, Rational)):
            n2: int = other.getNumerator()
            d2: int = other.getDenominator()
        else:
            n2: int = other
            d2: int = 1
        return n1 == n2 and d1 == d2

    def __gt__(self, other):
        if (not isinstance(other, (Rational, int))):
            return NotImplemented
        return (self - other).getNumerator() > 0

    def __lt__(self, other):
        if (not isinstance(other, (Rational, int))):
            return NotImplemented
        return (self - other).getNumerator() < 0

    def __ge__(self, other):
        return not (other > self)

    def __le__(self, other):
        return not (other < self)

    def __truediv__(self, other):
        if (not isinstance(other, (Rational, int))):
            return NotImplemented
        if (other == Rational(0) or other == 0):
            raise ZeroDivisionError()
        n1: int = self.getNumerator()
        d1: int = self.getDenominator()
        if (isinstance(other, Rational)):
            n2: int = other.getNumerator()
            d2: int = other.getDenominator()
        else:
            n2: int = other
            d2: int = 1
        n: int = n1 * d2
        d: int = d1 * n2
        return Rational(n, d)

    def __rtruediv__(self, other):
        if (isinstance(other, (Rational, int))):
            return (Rational(1) / self) * other
        else:
            return NotImplemented

    def __pow__(self, other):
        if (not isinstance(other, int)):
            return NotImplemented
        if (other == 0):
            return Rational(1)
        elif (other > 0):
            result = Rational(1)
            for i in range(other):
                result *= self
            i += 1  # this line is to avoid the complaint of the complier
            return result
        else:
            k = Rational(1) / self
            return k**(-other)

    def __repr__(self):
        return f"Rational({self.getNumerator()}, {self.getDenominator()})" if self.getDenominator(
        ) != 1 else str(self.getNumerator())

    def __float__(self):
        return self.getNumerator() / self.getDenominator()

    def __trunc__(self):
        sign: int = 1 if self > Rational(0) else -1 if self < Rational(
            0) else 0
        return sign * ((sign * self.getNumerator()) // self.getDenominator())

    def __floor__(self):
        if (self >= Rational(0)):
            return int(self)
        else:
            if (self.getDenominator() == 1):
                return self.getNumerator()
            else:
                return int(self) - 1

    def __ceil__(self):
        return -(-self).__floor__()

    def __bool__(self):
        return self != 0


class Matrix:
    """
    This is a hastily written matrix library.
    """
    def __init__(self, row: int, column: int):
        """
        Initialization.
        """
        self.__row: int = row
        self.__column: int = column
        self.__mat = [[0 for j in range(self.__column)]
                      for i in range(self.__row)]

    def __repr__(self):
        """
        Gives the (canonical) string representation the matrix.
        """
        return repr(self.__mat)

    def __str__(self):
        """
        Gives the string representation the matrix (for display).
        """
        text: str = ""
        entrySeparator: str = ", "
        rowSeparator: str = "\n"
        for i in range(self.__row):
            for j in range(self.__column):
                text = text + str(self.__mat[i][j])
                if (j < self.__column - 1):
                    text = text + entrySeparator
            if (i < self.__row - 1):
                text = text + rowSeparator
        return text

    def gets(self, rowIndex: int, columnIndex: int):
        """
        Gets the (`rowIndex`, `columnIndex`)-entry.
        """
        return self.__mat[rowIndex][columnIndex]

    def sets(self, element, rowIndex: int, columnIndex: int):
        """
        Assigns the (`rowIndex`, `columnIndex`)-entry to be `element`.
        """
        self.__mat[rowIndex][columnIndex] = element
        return self

    def rowNum(self) -> int:
        """
        Gives the number of rows.
        """
        return self.__row

    def columnNum(self) -> int:
        """
        Gives the number of columns.
        """
        return self.__column

    def transpose(self):
        """
        Gives the transpose of the matrix.
        """
        cn: int = self.rowNum()
        rn: int = self.columnNum()
        result = Matrix(rn, cn)
        for i in range(rn):
            for j in range(cn):
                result.sets(self.gets(j, i), i, j)
        return result

    def subMatrix(self, rowIndex: int, columnIndex: int):
        """
        Returns the matrix obtained by removing entries row `rowIndex` and
        column `columnIndex`.
        """
        rn: int = self.rowNum()
        cn: int = self.columnNum()
        if (rowIndex >= rn or columnIndex >= cn):
            raise Exception("at least one of the indices is out of bound")
        result = Matrix(rn - 1, cn - 1)
        for i in range(rn - 1):
            for j in range(cn - 1):
                ti: int = i if (i < rowIndex) else i + 1
                tj: int = j if (j < columnIndex) else j + 1
                result.sets(self.gets(ti, tj), i, j)
        return result

    def det(self):
        """
        Gives the determinant: the (1, 1)-entry if the order of the square matrix is 1,
        `sum((-1) ^ j * a(0, j) * self.subMatrix(0, j).det(), 0 <= j < self.columnNum())`
        otherwise.
        """
        rn: int = self.rowNum()
        cn: int = self.columnNum()
        if (rn != cn):
            raise Exception("Not a square matrix")
        if (cn == 1):
            return self.gets(0, 0)
        else:
            return sum([(-1)**j * self.gets(0, j) * self.subMatrix(0, j).det()
                        for j in range(cn)])

    def __adjoint(self):
        """
        Gives the the classical adjoint: `[(-1) ^ (i + j) * self.subMatrix(j, i).det()]`.
        """
        rn: int = self.rowNum()
        cn: int = self.columnNum()
        if (rn != cn):
            raise Exception("Not a square matrix")
        result = Matrix(rn, rn)
        if (rn == 1):
            result.sets(1, 0, 0)
        else:
            for i in range(rn):
                for j in range(rn):
                    result.sets((-1)**(i + j) * self.subMatrix(j, i).det(), i,
                                j)
        return result

    def inverse(self):
        """
        Gives the inverse matrix: self.adjoint() / self.det().
        """
        rn: int = self.rowNum()
        cn: int = self.columnNum()
        if (rn != cn):
            raise Exception("Not a square matrix")
        d = self.det()
        if (10**5 * abs(d) < 1):
            raise Exception("Not invertible")
        result = Matrix(rn, rn)
        adj = self.__adjoint()
        for i in range(rn):
            for j in range(rn):
                result.sets(adj.gets(i, j) / d, i, j)
        return result

    def toList(self) -> list:
        """
        Convert the matrix to a 2D array.
        """
        return self.__mat

    def __matmul__(self, other):
        """
        Gives the product of `self` and `other`.
        """
        rn: int = self.rowNum()
        cn: int = other.columnNum()
        if (self.columnNum() != other.rowNum()):
            raise Exception("Undefined")
        s: int = self.columnNum()
        result = Matrix(rn, cn)
        for i in range(rn):
            for j in range(cn):
                result.sets(
                    sum([self.gets(i, k) * other.gets(k, j)
                         for k in range(s)]), i, j)
        return result

    @staticmethod
    def convertLists2Matrix(*ls: list):
        """
        Converts lists to a `Matrix` instance.

        ls: lists to be converted, one or more.
        """
        rn: int = len(ls)
        cn: int = len(ls[0])
        result = Matrix(rn, cn)
        for i in range(rn):
            for j in range(cn):
                result.sets(ls[i][j], i, j)
        return result

    @staticmethod
    def identity(n: int):
        """
        Generates an n by n matrix, whose (i,j)-entry is 1 if i=j or 0 if i!=j.
        """
        result = Matrix(n, n)
        for i in range(n):
            result.sets(1, i, i)
        return result


def leastsquares(y, *x):
    """
    Gives the least-squares linear model of `y` and `x`:

    `y = beta_0 * x0 + beta_1 * x1 + ... beta_k * xk`

    y: a list containing n numbers (the dependent variable).

    x: k+1 lists, each containing n numbers (the k+1 independent variables).

    A dictionary(map) containing [beta_i] (`coefficients`), `R_squared`
    and `delta_squared_sum` will be returned.
    """
    n: int = len(y)
    for l in x:
        if len(l) != n:
            raise Exception("!!!")
    a = Matrix.convertLists2Matrix(*x).transpose()
    a_t = a.transpose()
    b = a_t @ a  # @ stands for matrix multiplication
    rhs = Matrix.convertLists2Matrix(y).transpose()
    # Solves a_t @ a @ x = a_t @ rhs
    # x = inv(a_t @ a) @ (a_t @ RHS)
    x = (b.inverse() @ a_t @ rhs)
    y_hat_m = a @ x
    beta = x.transpose().toList()[0]  # x is a column vector
    y_hat = y_hat_m.transpose().toList()[0]
    delta = [y_hat[i] - y[i] for i in range(n)]
    square = lambda t: t * t
    delta_squared_sum = sum(map(square, delta))
    R_squared = 1 - delta_squared_sum / sum(map(square, y))
    return {
        "coefficients": beta,
        "R_squared": R_squared,
        "delta_squared_sum": delta_squared_sum
    }


def poly_leastsquares(x, y, degree: int = 1):
    """
    Gives the least-squares polynomial model of `x` and `y`:

    `y = beta_0 * 1 + beta_1 * x + beta_2 * x**2 + ... + beta_i * x**i`

    x: a list containing n numbers (the independent variable).

    y: a list containing n numbers (the dependent variable).

    degree: (optional.) the desired degree of the polynomial model.
    If not specified, it is 1.

    A dictionary(map) containing [beta_i] (`coefficients`), `R_squared`
    and `delta_squared_sum` will be returned.
    """
    if (len(x) != len(y)):
        raise Exception("!!!")
    n: int = len(x)
    xx = [ones(n)]
    for i in range(1, degree + 1):
        xx.append([element**i for element in x])
    return leastsquares(y, *xx)


def ones(n: int):
    """
    Generates a list consisting of `n` ones.
    """
    return [1 for i in range(n)]


def comp(t0, cs, pts):
    """
    Computes f(t0), where

    f(t) = cs[0] * 1 + cs[1]   * (t - pts[0])
                     + cs[2]   * (t - pts[0]) * (t - pts[1])
                     + ...
                     + cs[n-1] * (t - pts[0]) * (t - pts[1]) * ... * (t - pts[n-2])

    t0: the number at which the value of f(t) is computed

    cs: coefficients in question

    pts: points in question    
    """
    result = 0
    p = 1
    if (len(cs) != len(pts)):
        raise Exception("!!!")
    r = len(cs)
    for i in range(r):
        result += cs[i] * p
        p *= t0 - pts[i]
    return result


def newton(x, y, *ts):
    """
    The method finds a polynomial f(t) such that
    
    `f(x[i]) == y[i] for i in range(len(x))`

    using the Newton interpolation method.

    The list 
    `[f(t) for t in ts]` 
    will be returned.

    x, y: lists in question.
    
    ts: (optional) numbers at which f(t) is calculated at.
    If a list `ts` is needed to pass, use `*ts`.
    """
    if (len(x) != len(y)):
        raise Exception("!!!")
    n: int = len(x)
    dft = []  # dft: dividedDifferenceTable
    dft.append(x)
    dft.append(y)
    Y = [y[i] for i in range(n)]
    for k in range(1, n):
        Y = [(Y[i + 1] - Y[i]) / (x[i + k] - x[i]) for i in range(n - k)]
        dft.append(Y)
    # Now dft is a complete divided difference table.
    coefficients = [dft[i][0] for i in range(1, n + 1)]
    return [comp(t, coefficients, x) for t in ts]
