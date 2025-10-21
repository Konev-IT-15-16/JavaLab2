import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<Person> people = new ArrayList<>();
    private static List<Name> names = new ArrayList<>();
    private static List<City> cities = new ArrayList<>();
    private static List<Fraction> fractions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeDefaultData();

        while (true) {
            showMainMenu();
            int choice = getMenuChoice(1, 5);

            switch (choice) {
                case 1:
                    workWithPeople();
                    break;
                case 2:
                    workWithNames();
                    break;
                case 3:
                    workWithCities();
                    break;
                case 4:
                    workWithFractions();
                    break;
                case 5:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

            System.out.println();
        }
    }

    private static void initializeDefaultData() {
        // Инициализация людей
        people.add(new Person(new Name("Клеопатра"), 152));
        people.add(new Person(new Name("Пушкин"), 167));
        people.add(new Person(new Name("Владимир"), 189));

        //  Инициализация городов (схема из рисунка)
        cities.addAll(City.createFigure2Scheme());

        // Инициализация дробей
        fractions.add(new Fraction(1, 3));
        fractions.add(new Fraction(2, 3));
        fractions.add(new Fraction(3, 4));
        fractions.add(new Fraction(5, 6));
        fractions.add(new Fraction(7, 8));

        // Инициализация имен
        names.add(new Name("Клеопатра"));
        names.add(new Name("Пушкин", "Александр", "Сергеевич"));
        names.add(new Name("Маяковский", "Владимир"));

        System.out.println("Созданы люди:");
        System.out.println("Человек с Именем Клеопатра и ростом 152");
        System.out.println("Человек с Именем Пушкин Александр Сергеевич и ростом 167");
        System.out.println("Человек с Именем Маяковский Владимир и ростом 189");

        System.out.println("\nСозданы дроби:");
        for (Fraction fraction : fractions) {
            System.out.println(fraction);
        }

        System.out.println("\nСоздана схема городов из рисунка 2:");
        for (City city : cities) {
            System.out.println(city.toShortString());
        }

    }


    private static void showMainMenu() {
        System.out.println("ГЛАВНОЕ МЕНЮ");
        System.out.println("1. Работа с людьми");
        System.out.println("2. Работа с именами");
        System.out.println("3. Работа с городами");
        System.out.println("4. Работа с дробями");
        System.out.println("5. Выход");
        System.out.print("Выберите действие: ");
    }



    // === РАБОТА С ЛЮДЬМИ ===
    private static void workWithPeople() {
        while (true) {
            System.out.println("\nМЕНЮ ЛЮДЕЙ");
            System.out.println("1. Добавить человека");
            System.out.println("2. Показать конкретного человека");
            System.out.println("3. Показать всех людей");
            System.out.println("4. Вернуться в главное меню");
            System.out.print("Выберите действие: ");

            int choice = getMenuChoice(1, 4);

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    showSpecificPerson();
                    break;
                case 3:
                    showAllPeople();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void addPerson() {
        Person newPerson = Person.createFromInput(scanner);
        people.add(newPerson);
        System.out.println("Человек успешно добавлен: " + newPerson);
    }

    private static void showSpecificPerson() {
        if (people.isEmpty()) {
            System.out.println("Список людей пуст.");
            return;
        }

        showPeopleList();
        System.out.print("Введите номер человека: ");
        int number = getNumberFromList(people.size());

        Person person = people.get(number - 1);
    }

    private static void showAllPeople() {
        if (people.isEmpty()) {
            System.out.println("Список людей пуст.");
            return;
        }

        Person[] peopleArray = people.toArray(new Person[0]);
        Person.displayAll(peopleArray, "СПИСОК ВСЕХ ЛЮДЕЙ");
    }


    // === РАБОТА С ИМЕНАМИ ===
    private static void workWithNames() {
        while (true) {
            System.out.println("\nМЕНЮ ИМЕН");
            System.out.println("1. Добавить имя");
            System.out.println("2. Показать конкретное имя");
            System.out.println("3. Показать все имена");
            System.out.println("4. Вернуться в главное меню");
            System.out.print("Выберите действие: ");

            int choice = getMenuChoice(1, 4);

            switch (choice) {
                case 1:
                    addName();
                    break;
                case 2:
                    showSpecificName();
                    break;
                case 3:
                    showAllNames();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void addName() {
        Name newName = Name.createFromInput(scanner);
        names.add(newName);
        System.out.println("Имя успешно добавлено: " + newName);
    }

    private static void showSpecificName() {
        if (names.isEmpty()) {
            System.out.println("Список имен пуст.");
            return;
        }

        showNamesList();
        System.out.print("Введите номер имени: ");
        int number = getNumberFromList(names.size());

        Name name = names.get(number - 1);
        System.out.println("\nДЕТАЛЬНАЯ ИНФОРМАЦИЯ ОБ ИМЕНИ");
        System.out.println(name.getDetailedInfo());
    }

    private static void showAllNames() {
        if (names.isEmpty()) {
            System.out.println("Список имен пуст.");
            return;
        }

        Name[] namesArray = names.toArray(new Name[0]);
        Name.displayAll(namesArray, "СПИСОК ВСЕХ ИМЕН");
    }

    // === РАБОТА С ГОРОДАМИ ===
    private static void workWithCities() {
        while (true) {
            System.out.println("\nМЕНЮ ГОРОДОВ");
            System.out.println("1. Добавить город (только название)");
            System.out.println("2. Добавить город с путями"); // По заданию 4.8
            System.out.println("3. Показать конкретный город");
            System.out.println("4. Показать все города");
            System.out.println("5. Добавить путь между городами");
            System.out.println("6. Показать схему из рисунка");
            System.out.println("7. Вернуться в главное меню");
            System.out.print("Выберите действие: ");

            int choice = getMenuChoice(1, 7);

            switch (choice) {
                case 1:
                    addCity(); // Только название
                    break;
                case 2:
                    addCityWithRoutes(); // Название и пути
                case 3:
                    showSpecificCity();
                    break;
                case 4:
                    showAllCities();
                    break;
                case 5:
                    addRouteBetweenCities();
                    break;
                case 6:
                    showFigure2Scheme();
                    break;
                case 7:
                    return;
            }
        }
    }

    private static void addCity() { // Только с названием
        City newCity = City.createFromInput(scanner, cities);
        cities.add(newCity);
        System.out.println("Город успешно добавлен: " + newCity.toShortString());
    }

    private static void addCityWithRoutes() { // По названию и путям
        if (cities.isEmpty()) {
            System.out.println("Нет существующих городов для создания связей. Сначала создайте " +
                    "несколько городов.");
            return;
        }
        City newCity = City.createWithRoutesFromInput(scanner, cities);
        cities.add(newCity);
        System.out.println("Город успешно добавлен: " + newCity);
    }

    private static void showSpecificCity() {
        if (cities.isEmpty()) {
            System.out.println("Список городов пуст.");
            return;
        }

        showCitiesList();
        System.out.print("Введите номер города: ");
        int number = getNumberFromList(cities.size());

        City city = cities.get(number - 1);
        System.out.println("\nДЕТАЛЬНАЯ ИНФОРМАЦИЯ О ГОРОДЕ");
        System.out.println(city);
    }

    private static void showAllCities() {
        if (cities.isEmpty()) {
            System.out.println("Список городов пуст.");
            return;
        }

        City[] citiesArray = cities.toArray(new City[0]);
        City.displayAll(citiesArray, "СПИСОК ВСЕХ ГОРОДОВ");
    }

    private static void addRouteBetweenCities() {
        if (cities.size() < 2) {
            System.out.println("Для добавления пути нужно как минимум 2 города.");
            return;
        }

        showCitiesList();

        System.out.print("Введите номер города отправления: ");
        int fromIndex = getNumberFromList(cities.size());
        City fromCity = cities.get(fromIndex - 1);

        System.out.print("Введите номер города назначения: ");
        int toIndex = getNumberFromList(cities.size());
        City toCity = cities.get(toIndex - 1);

        if (fromCity.equals(toCity)) {
            System.out.println("Ошибка: нельзя добавить путь к самому себе.");
            return;
        }

        System.out.print("Введите стоимость поездки: ");
        int cost = getMenuChoice(0, 1000); // Используем существующий метод для ввода числа

        fromCity.addRoute(toCity, cost);
        System.out.println("Путь успешно добавлен: " + fromCity.toShortString() +
                " -> " + toCity.toShortString() + " (стоимость: " + cost + ")");
    }

    private static void showFigure2Scheme() {
        System.out.println("\nСХЕМА ГОРОДОВ ИЗ РИСУНКА");
        System.out.println("=".repeat(50));
        for (City city : cities) {
            System.out.println(city);
            System.out.println("-".repeat(30));
        }
        System.out.println("=".repeat(50));
    }


    // === РАБОТА С ДРОБЯМИ ===
    private static void workWithFractions() {
        while (true) {
            System.out.println("\nМЕНЮ ДРОБЕЙ");
            System.out.println("1. Добавить дробь");
            System.out.println("2. Показать конкретную дробь");
            System.out.println("3. Показать все дроби");
            System.out.println("4. Выполнить операции с дробями");
            System.out.println("5. Показать демонстрацию операций");
            System.out.println("6. Вернуться в главное меню");
            System.out.print("Выберите действие: ");

            int choice = getMenuChoice(1, 6);

            switch (choice) {
                case 1:
                    addFraction();
                    break;
                case 2:
                    showSpecificFraction();
                    break;
                case 3:
                    showAllFractions();
                    break;
                case 4:
                    performFractionOperations();
                    break;
                case 5:
                    demonstrateFractionOperations();
                    break;
                case 6:
                    return;
            }
        }
    }

    private static void addFraction() {
        Fraction newFraction = Fraction.createFromInput(scanner);
        fractions.add(newFraction);
        System.out.println("Дробь успешно добавлена: " + newFraction);
    }

    private static void showSpecificFraction() {
        if (fractions.isEmpty()) {
            System.out.println("Список дробей пуст.");
            return;
        }

        showFractionsList();
        System.out.print("Введите номер дроби: ");
        int number = getNumberFromList(fractions.size());

        Fraction fraction = fractions.get(number - 1);
        System.out.println("\nДЕТАЛЬНАЯ ИНФОРМАЦИЯ О ДРОБИ");
        System.out.println("Дробь: " + fraction);
        System.out.println("Десятичное значение: " + fraction.toDouble());
        System.out.println("Числитель: " + fraction.getNumerator());
        System.out.println("Знаменатель: " + fraction.getDenominator());
    }

    private static void showAllFractions() {
        if (fractions.isEmpty()) {
            System.out.println("Список дробей пуст.");
            return;
        }

        Fraction[] fractionsArray = fractions.toArray(new Fraction[0]);
        Fraction.displayAll(fractionsArray, "СПИСОК ВСЕХ ДРОБЕЙ");
    }

    private static void performFractionOperations() {
        if (fractions.size() < 2) {
            System.out.println("Для выполнения операций нужно как минимум 2 дроби.");
            return;
        }

        showFractionsList();

        System.out.print("Введите номер первой дроби: ");
        int firstIndex = getNumberFromList(fractions.size());
        Fraction first = fractions.get(firstIndex - 1);

        System.out.print("Введите номер второй дроби: ");
        int secondIndex = getNumberFromList(fractions.size());
        Fraction second = fractions.get(secondIndex - 1);

        System.out.println("\nВЫБЕРИТЕ ОПЕРАЦИЮ:");
        System.out.println("1. Сложение");
        System.out.println("2. Вычитание");
        System.out.println("3. Умножение");
        System.out.println("4. Деление");
        int operation = getMenuChoice(1, 4);

        Fraction result;
        String operationSymbol = "";

        switch (operation) {
            case 1:
                result = first.add(second);
                operationSymbol = " + ";
                break;
            case 2:
                result = first.subtract(second);
                operationSymbol = " - ";
                break;
            case 3:
                result = first.multiply(second);
                operationSymbol = " * ";
                break;
            case 4:
                try {
                    result = first.divide(second);
                    operationSymbol = " / ";
                } catch (ArithmeticException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                    return;
                }
                break;
            default:
                return;
        }

        System.out.println("\nРЕЗУЛЬТАТ ОПЕРАЦИИ:");
        System.out.println(first + operationSymbol + second + " = " + result);
        System.out.println("Десятичное значение: " + result.toDouble());
    }

    private static void demonstrateFractionOperations() {
        System.out.println("\nДЕМОНСТРАЦИЯ ОПЕРАЦИЙ С ДРОБЯМИ");
        System.out.println("=".repeat(60));

        // Создаем дроби для демонстрации
        Fraction f1 = new Fraction(1, 3);
        Fraction f2 = new Fraction(2, 3);
        Fraction f3 = new Fraction(3, 4);
        Fraction f4 = new Fraction(5, 6);

        // Примеры операций
        System.out.println("1. Сложение: " + f1 + " + " + f2 + " = " + f1.add(f2));
        System.out.println("2. Вычитание: " + f3 + " - " + f4 + " = " + f3.subtract(f4));
        System.out.println("3. Умножение: " + f1 + " * " + f2 + " = " + f1.multiply(f2));
        System.out.println("4. Деление: " + f3 + " / " + f4 + " = " + f3.divide(f4));
        System.out.println("5. Сложение с целым числом: " + f1 + " + 2 = " + f1.add(2));
        System.out.println("6. Умножение на целое число: " + f2 + " * 3 = " + f2.multiply(3));

        // Вычисление f1.sum(f2).div(f3).minus(5)
        Fraction complexResult = f1.add(f2).divide(f3).subtract(5);
        System.out.println("7. Сложное выражение: " + f1 + ".add(" + f2 + ").divide(" + f3 + ").subtract(5) = " + complexResult);

        System.out.println("=".repeat(60));
    }


    // === ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ===
    private static int getMenuChoice(int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.printf("Неверный выбор. Введите число от %d до %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: введите целое число: ");
            }
        }
    }

    private static int getNumberFromList(int max) {
        while (true) {
            try {
                int number = Integer.parseInt(scanner.nextLine().trim());
                if (number >= 1 && number <= max) {
                    return number;
                } else {
                    System.out.printf("Неверный номер. Введите число от 1 до %d: ", max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: введите целое число: ");
            }
        }
    }

    private static void showPeopleList() {
        System.out.println("Список людей:");
        for (int i = 0; i < people.size(); i++) {
            System.out.println((i + 1) + ". " + people.get(i).getName());
        }
    }

    private static void showNamesList() {
        System.out.println("Список имен:");
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }

    private static void showCitiesList() {
        System.out.println("Список городов:");
        for (int i = 0; i < cities.size(); i++) {
            System.out.println((i + 1) + ". " + cities.get(i).toShortString());
        }
    }

    private static void showFractionsList() {
        System.out.println("Список дробей:");
        for (int i = 0; i < fractions.size(); i++) {
            System.out.println((i + 1) + ". " + fractions.get(i));
        }
    }
}