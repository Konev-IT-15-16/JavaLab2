import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Name {
    private String lastName;
    private String firstName;
    private String middleName;

    // Конструкторы
    public Name() {
        this(null, null, null);
    }

    public Name(String firstName) {
        this(null, firstName, null);
    }

    public Name(String lastName, String firstName) {
        this(lastName, firstName, null);
    }

    public Name(String lastName, String firstName, String middleName) {
        setLastName(lastName);
        setFirstName(firstName);
        setMiddleName(middleName);
    }

    // Геттеры
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    // Сеттеры
    public void setLastName(String lastName) {
        this.lastName = normalizeNameComponent(lastName);
    }

    public void setFirstName(String firstName) {
        this.firstName = normalizeNameComponent(firstName);
    }

    public void setMiddleName(String middleName) {
        this.middleName = normalizeNameComponent(middleName);
    }

    public static Name createFromInput(Scanner scanner) {
        System.out.println("\nСОЗДАНИЕ НОВОГО ИМЕНИ");

        System.out.print("Введите фамилию (или Enter чтобы пропустить): ");
        String lastName = scanner.nextLine().trim();
        if (lastName.isEmpty()) lastName = null;

        String firstName = Validation.getInputString(scanner, "Введите имя: ");

        System.out.print("Введите отчество (или Enter чтобы пропустить): ");
        String middleName = scanner.nextLine().trim();
        if (middleName.isEmpty()) middleName = null;

        return new Name(lastName, firstName, middleName);
    }

    public static void displayAll(Name[] names, String title) {
        System.out.println("\n" + title);
        System.out.println("=".repeat(70));
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%2d. %s\n", (i + 1), names[i].getDetailedInfo());
        }
        System.out.println("=".repeat(70));
    }

    // Проверочные методы
    public boolean hasLastName() {
        return lastName != null && !lastName.isEmpty();
    }

    public boolean hasFirstName() {
        return firstName != null && !firstName.isEmpty();
    }

    public boolean hasMiddleName() {
        return middleName != null && !middleName.isEmpty();
    }

    public boolean isEmpty() {
        return !hasLastName() && !hasFirstName() && !hasMiddleName();
    }



    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (hasLastName()) {
            result.append(lastName);
        }

        if (hasFirstName()) {
            if (result.length() > 0) result.append(" ");
            result.append(firstName);
        }

        if (hasMiddleName()) {
            if (result.length() > 0) result.append(" ");
            result.append(middleName);
        }

        return result.length() == 0 ? "Неизвестно" : result.toString();
    }


    public String toInitialsString() {
        StringBuilder result = new StringBuilder();

        if (hasLastName()) {
            result.append(lastName);
        }

        if (hasFirstName()) {
            if (result.length() > 0) result.append(" ");
            result.append(getInitial(firstName));
        }

        if (hasMiddleName()) {
            if (result.length() > 0) result.append(".");
            result.append(getInitial(middleName)).append(".");
        }

        return result.length() == 0 ? "Неизвестно" : result.toString();
    }


    public String getDetailedInfo() {
        return toString();
    }

    // Приватные вспомогательные методы
    private String normalizeNameComponent(String component) {
        if (component != null && !component.trim().isEmpty() &&
                Validation.containsOnlyLetters(component)) {
            return component.trim();
        }
        return null;
    }

    private String getInitial(String name) {
        if (name == null || name.isEmpty()) return "";
        return name.substring(0, 1).toUpperCase();
    }

    private static String getPercentage(int part, int total) {
        return String.format("%.1f", (double) part / total * 100);
    }
}