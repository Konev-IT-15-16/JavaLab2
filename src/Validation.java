import java.util.Scanner;
import java.util.List;

public final class Validation {

    // Приватный конструктор предотвращает создание экземпляров
    private Validation() {
        throw new AssertionError("InputValidator is a utility class!");
    }

    public static boolean isUpper(char c) {
        return (c >= 'A' && c <= 'Z') ||
                (c >= 'А' && c <= 'Я') ||
                c == 'Ё';
    }

    public static boolean isLower(char c) {
        return (c >= 'a' && c <= 'z') ||
                (c >= 'а' && c <= 'я') ||
                c == 'ё';
    }

    public static boolean containsOnlyLetters(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char firstChar = str.charAt(0);
        if (!isUpper(firstChar)) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!isLower(c)) {
                return false;
            }
        }
        return true;
    }

    public static String getInputString(Scanner scanner, String text) {
        System.out.print(text);
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty() && containsOnlyLetters(input)) {
                return input;
            }
            System.out.print("Ошибка: некорректный ввод (имя должно начинаться с заглавной буквы" +
                    " и дальше содержать только строчные). " + text);
        }
    }

    public static int getInputInt(Scanner scanner, String text, int min, int max) {
        System.out.print(text);
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("Ошибка: значение должно быть от %d до %d. %s",
                            min, max, text);
                }
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: введите целое число. " + text);
            }
        }
    }


    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static boolean isValidCityName(String name) {
        if (name == null || name.length() != 1) {
            return false;
        }
        char c = name.charAt(0);
        return (c >= 'A' && c <= 'Z');
    }

    public static boolean isCityNameUnique(String name, List<City> cities) {
        if (name == null || cities == null) {
            return false;
        }
        for (City city : cities) {
            if (city.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public static String getInputCityName(Scanner scanner, String text, List<City> cities) {
        System.out.print(text);
        while (true) {
            String input = scanner.nextLine().trim().toUpperCase();

            if (!isValidCityName(input)) {
                System.out.print("Ошибка: название города должно быть одной заглавной латинской буквой. " + text);
                continue;
            }

            if (!isCityNameUnique(input, cities)) {
                System.out.print("Ошибка: город с таким названием уже существует. " + text);
                continue;
            }

            return input;
        }
    }
}
