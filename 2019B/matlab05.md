# Assignment 5

## Exercise 1

```matlab
syms x a b;
disp(limit(1 / x, x, 0, 'right'));
disp(limit(x^2 / (1 + x) * (1 + a / x)^x * sin(b / x), x, inf));
f = 2 / (1 + exp(-1 / x)) - sin(a * x) / abs(x);
disp(solve(limit(f, x, 0, 'left') - limit(f, x, 0, 'right')));
```

## Exercise 2

```matlab
syms z;
f = diff((2 * z * z - 3 * z + sqrt(z)) / z, z);
disp(eval(subs(f, z, 1/4)));
```

## Exercise 3

```matlab
syms x;
disp(int(x * sin(sqrt(x))));
disp(int(log(x + sqrt(x * x + 1)), 0, 1));
```

## Exercise 4

```matlab
syms n x;
J = 2 * symsum((2 * x + 1)^(2 * n + 1) / (2 * n + 1), n, 0, inf);
disp(J);
% J = ln(1+x) - ln(-x) if -1<x<0, undefined otherwise.
disp(eval(subs(J, x, 10))); %return NaN
```

## Exercise 5

```matlab
syms t;
ezplot(cos(t)^5, sin(t)^5);
title('星形线', 'fontweight', 'bold', 'fontname', '隶书');
```

## Exercise 6

```matlab
syms x y z theta phi;
a = 3;
b = 4;
c = 5;
x = a * tan(phi) * cos(theta);
y = b * tan(phi) * sin(theta);
z = c * sec(phi);
figure(1);
ezsurf(x, y, z, [-pi / 2, 3 * pi / 2, 0, 2 * pi]);
figure(2);
ezmesh(x, y, z, [-pi / 2, 3 * pi / 2, 0, 2 * pi]);
```

## Exercise 7

```matlab
syms x y z t;
fixtitle = 'XXXXXXXX';
figure(1);
ezsurf(x * x / 16 - y * y / 4);
title(fixtitle);
figure(2);
R = 9;
r = 3;
fimplicit3((sqrt(x * x + y * y) - R)^2 + z * z - r * r, [-15 15]);
title(fixtitle);
figure(3);
ezplot((1 - sin(t)) * cos(t), (1 - sin(t)) * sin(t));
title(fixtitle);
figure(4);
NN = 200;
rx = 0:1/500:1;
ry = 0:1/500:1;

for index = 1:length(rx)
    ry(index) = riemann(rx(index), NN);
end

plot(rx, ry, '.');
title(fixtitle);

% Rational numbers with a big numerator are considered irrational here.
function y = riemann(x, N)

    if (x == 0 || x == 1)
        y = 0;
    else

        if ismember(x * N, 1:N)
            y = gcd(x * N, N) / N;
        else
            y = 0;
        end

    end

end
```

## Exercise 8

### Version 1

```matlab
syms x;
y = sin(x);
fixtitle = 'XXXXXXXX';
% taylor(f(x), x, 0, 'Order', 1 + n) returns a polynomial of degree n.
y1 = taylor(y, x, 0, 'Order', 1 + 1);
y2 = taylor(y, x, 0, 'Order', 1 + 3);
y3 = taylor(y, x, 0, 'Order', 1 + 7);
y4 = taylor(y, x, 0, 'Order', 1 + 11);
ezplot(y, [-pi pi]);
hold on;
ezplot(y1, [-pi pi]);
hold on;
ezplot(y2, [-pi pi]);
hold on;
ezplot(y3, [-pi pi]);
hold on;
ezplot(y4, [-pi pi]);
legend('sin(x)', 'Of order 1', 'Of order 3', 'Of order 7', 'Of order 11');
title(fixtitle);
```

### Version 2

```matlab
syms x;
y = cos(x);
fixtitle = 'XXXXXXXX';
% taylor(f(x), x, 0, 'Order', 1 + n) returns a polynomial of degree n.
y1 = taylor(y, x, 0, 'Order', 1 + 1);
y2 = taylor(y, x, 0, 'Order', 1 + 3);
y3 = taylor(y, x, 0, 'Order', 1 + 7);
y4 = taylor(y, x, 0, 'Order', 1 + 11);
ezplot(y, [-pi pi]);
hold on;
ezplot(y1, [-pi pi]);
hold on;
ezplot(y2, [-pi pi]);
hold on;
ezplot(y3, [-pi pi]);
hold on;
ezplot(y4, [-pi pi]);
legend('cos(x)', 'Of order 1', 'Of order 3', 'Of order 7', 'Of order 11');
title(fixtitle);
```

For your reference.
