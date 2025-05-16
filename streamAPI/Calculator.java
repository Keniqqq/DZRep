import java.util.function.*;

public class Calculator {
    // Статическая переменная для получения экземпляра Calculator
    static Supplier<Calculator> instance = Calculator::new;

    // Математические операции над двумя числами
    BinaryOperator<Integer> plus = Integer::sum;
    BinaryOperator<Integer> minus = (x, y) -> x - y;
    BinaryOperator<Integer> multiply = (x, y) -> x * y;
    BinaryOperator<Integer> devide = (x, y) -> {
        if (y == 0) {
            throw new ArithmeticException("Деление на ноль недопустимо");
        }
        return x / y;
    };

    // Математические операции над одним числом
    UnaryOperator<Integer> pow = x -> x * x;
    UnaryOperator<Integer> abs = x -> Math.abs(x);

    // Проверка, является ли число положительным
    Predicate<Integer> isPositive = x -> x > 0;

    // Вывод числа в консоль
    Consumer<Integer> println = System.out::println;
}