# Assignment 4

## Exercise 1

```matlab
x1 = 0:0.1 * pi:2 * pi;
subplot(2, 1, 1);
plot(x1, sin(x1), ':b', x1, cos(x1), '--p');
legend('sin(x)', 'cos(x)');
subplot(2, 1, 2);
x2 = 0:0.1 * pi:2 * pi;
plot(x2, sin(x2), '--pk', x2, cos(x2), '-sr', x2, sin(2 * x2), ':ob');
legend('sin(x)', 'cos(x)', 'sin(2x)');
```

## Exercise 2

```matlab
x = 0:0.1 * pi:4 * pi;
plot(x, sin(x), '-.p', 'linewidth', 2, 'markersize', 7);
hold on;
plot(x, cos(x));
hold off;
grid on;
title('正余弦函数图像');
xlabel('x = 0 : π/10 : 4π');
ylabel('y');
```

## Exercise 3

```matlab
t = 0:0.1:40; plot3(2 * (cos(t) + t .* sin(t)), 2 * (sin(t) - t .* cos(t)), -1.5 * t, ':.r', 'markersize', 10);
```

## Exercise 4

```matlab
[x, y] = meshgrid(-25:0.1:25); mesh(x, y, x.^2/9 - y.^2/4);
```

## Exercise 5

```matlab
[x, y] = meshgrid(-4:0.1:4); surf(x .* exp(-x.^2 - y.^2));
```

For your reference.
