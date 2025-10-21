import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Person {
    /* Используяется для задания 1.2
    private String name;
     */
    private Name name;
    private int height;

    // Конструкторы
    public Person(Name name, int height) {
        setName(name);
        setHeight(height);
    }
    public Person(String firstName, int height) {
        this(new Name(firstName), height);
    }

    public Person(String lastName, String firstName, int height) {
        this(new Name(lastName, firstName), height);
    }

    public Person(String lastName, String firstName, String middleName, int height) {
        this(new Name(lastName, firstName, middleName), height);
    }


    // Геттеры и сеттеры
    /* Задание 1.2
    public String getName() {
        return name;
    }*/

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name != null ? name : new Name();
    }

    // Для обратной совместимости
    public void setName(String firstName) {
        this.name = new Name(firstName);
    }

    public int getHeight() {
        return height;
    }


    /* 1.2
    public void setName(String name) {
        if (Validation.isNotEmpty(name) && Validation.containsOnlyLetters(name)) {
            this.name = name;
        } else {
            System.out.println("Ошибка: имя не может быть пустым и должно содержать только буквы");
            this.name = "Неизвестно";
        }
    }*/


    /* 1.2
    public int getHeight() {
        return height;
    }*/

    public void setHeight(int height) {
        if (Validation.isInRange(height, 1, 300)) {
            this.height = height;
        } else {
            System.out.println("Ошибка: рост должен быть от 1 до 300 см");
            this.height = 1;
        }
    }


    public static Person createFromInput(Scanner scanner) {
        System.out.println("\nСоздание нового человека");
        Name name = Name.createFromInput(scanner);
        int height = Validation.getInputInt(scanner, "Введите рост (см): ", 1, 300);
        return new Person(name, height);
    }


    public static void displayAll(Person[] people, String title) {
        System.out.println("\n" + title);
        System.out.println("=".repeat(50));
        for (int i = 0; i < people.length; i++) {
            System.out.printf("%2d. %s\n", (i + 1), people[i]);
        }
        System.out.println("=".repeat(50));
    }


    @Override
    public String toString() {
        return "Имя: " + name.toString() + ", рост: " + height + " см";
    }
}