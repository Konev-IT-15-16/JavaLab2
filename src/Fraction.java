public class Fraction {
    private int numerator;   // числитель
    private int denominator; // знаменатель

    // Конструкторы
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        this.numerator = numerator;
        this.denominator = denominator;
        simplify();
    }

    // Конструктор для целого числа
    public Fraction(int number) {
        this(number, 1);
    }

    // Геттеры
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    // Сеттеры
    public void setNumerator(int numerator) {
        this.numerator = numerator;
        simplify();
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        this.denominator = denominator;
        simplify();
    }

    // Нахождение наибольшего общего делителя (НОД)
    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Упрощение дроби
    private void simplify() {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        int gcd = gcd(numerator, denominator);
        if (gcd != 0) {
            numerator /= gcd;
            denominator /= gcd;
        }
    }

    // Сложение с другой дробью
    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Сложение с целым числом
    public Fraction add(int number) {
        return add(new Fraction(number));
    }

    // Вычитание другой дроби
    public Fraction subtract(Fraction other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Вычитание целого числа
    public Fraction subtract(int number) {
        return subtract(new Fraction(number));
    }

    // Умножение на другую дробь
    public Fraction multiply(Fraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Умножение на целое число
    public Fraction multiply(int number) {
        return multiply(new Fraction(number));
    }

    // Деление на другую дробь
    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Деление на целое число
    public Fraction divide(int number) {
        return divide(new Fraction(number));
    }

    // Получение десятичного значения
    public double toDouble() {
        return (double) numerator / denominator;
    }

    // Строковое представление
    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        }
        return numerator + "/" + denominator;
    }


    // Создание дроби из ввода
    public static Fraction createFromInput(java.util.Scanner scanner) {
        System.out.println("\nСОЗДАНИЕ ДРОБИ");

        int numerator = Validation.getInputInt(scanner, "Введите числитель: ", -1000, 1000);

        int denominator;
        while (true) {
            denominator = Validation.getInputInt(scanner, "Введите знаменатель: ", -1000, 1000);
            if (denominator != 0) {
                break;
            }
            System.out.println("Ошибка: знаменатель не может быть равен нулю. Попробуйте снова.");
        }

        return new Fraction(numerator, denominator);
    }

    // Отображение всех дробей
    public static void displayAll(Fraction[] fractions, String title) {
        System.out.println("\n" + title);
        System.out.println("=".repeat(50));
        for (int i = 0; i < fractions.length; i++) {
            System.out.printf("%2d. %s\n", (i + 1), fractions[i]);
        }
        System.out.println("=".repeat(50));
    }
}