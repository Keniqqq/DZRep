public class Main {
    public static void main(String[] args) {
        // Создаем экземпляр калькулятора
        Calculator calc = Calculator.instance.get();

        try {
            // Выполняем математические операции
            int a = calc.plus.apply(1, 2); // 3
            int b = calc.minus.apply(1, 1); // 0

            // Обрабатываем ошибку деления на ноль
            if (b == 0) {
                throw new ArithmeticException("Деление на ноль: знаменатель равен нулю");
            }

            int c = calc.devide.apply(a, b);
            calc.println.accept(c);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Пример использования других операций
        int d = calc.pow.apply(5); // 25
        calc.println.accept(d); // 25

        int e = calc.abs.apply(-10);
        calc.println.accept(e); // 10

        System.out.println("Число " + e + " положительное? " + calc.isPositive.test(e)); // true
    }
}