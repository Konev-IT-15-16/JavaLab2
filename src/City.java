import java.util.*;

public class City {
    private String name;
    private Map<City, Integer> routes; // Город -> стоимость поездки

    // Конструкторы
    // Для 3.3 и 4.8
    public City(String name) {
        setName(name);
        this.routes = new HashMap<>();
    }

    public City(String name, Map<City, Integer> routes) {
        setName(name);
        this.routes = new HashMap<>(routes);
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (Validation.isValidCityName(name)) {
            this.name = name.toUpperCase();
        } else {
            System.out.println("Ошибка: название города должно быть одной заглавной латинской" +
                    " буквой. Установлено значение 'A'");
            this.name = "A";
        }
    }

    public Map<City, Integer> getRoutes() {
        return new HashMap<>(routes);
    }

    // Добавление пути к другому городу
    public void addRoute(City city, int cost) {
        if (city != null && cost >= 0 && city != this) {
            routes.put(city, cost);
        }
    }

    // Удаление пути
    public void removeRoute(City city) {
        routes.remove(city);
    }

    // Получение стоимости поездки к городу
    public Integer getRouteCost(City city) {
        return routes.get(city);
    }

    // Текстовое представление города
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Город: ").append(name);

        if (!routes.isEmpty()) {
            sb.append("\nСвязанные города:");
            for (Map.Entry<City, Integer> entry : routes.entrySet()) {
                sb.append("\n  - ").append(entry.getKey().getName())
                        .append(": ").append(entry.getValue());
            }
        } else {
            sb.append("\nНет связанных городов");
        }

        return sb.toString();
    }

    // Короткое представление (только название)
    public String toShortString() {
        return name;
    }

    // Создание города из ввода
    public static City createFromInput(Scanner scanner, List<City> existingCities) {
        String cityName = Validation.getInputCityName(scanner, "Введите название города (одна " +
                "заглавная латинская буква): ", existingCities);
        return new City(cityName);
    }

    public static City createWithRoutesFromInput(Scanner scanner, List<City> existingCities) {
        String cityName = Validation.getInputCityName(scanner, "Введите название города (одна " +
                "заглавная латинская буква): ", existingCities);
        City newCity = new City(cityName);

        // Предлагаем добавить пути к существующим городам
        if (!existingCities.isEmpty()) {
            System.out.println("Хотите добавить пути к существующим городам? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("y") || choice.equals("да")) {
                addRoutesToCity(scanner, newCity, existingCities);
            }
        }

        return newCity;
    }

    private static void addRoutesToCity(Scanner scanner, City city, List<City> existingCities) {
        while (true) {
            System.out.println("\nДоступные города для связи:");
            for (int i = 0; i < existingCities.size(); i++) {
                System.out.println((i + 1) + ". " + existingCities.get(i).toShortString());
            }
            System.out.println("0. Завершить добавление путей");

            System.out.print("Выберите номер города для связи: ");
            int cityChoice = getIntInput(scanner, 0, existingCities.size());

            if (cityChoice == 0) {
                break;
            }

            City targetCity = existingCities.get(cityChoice - 1);

            System.out.print("Введите стоимость пути до города " + targetCity.toShortString() + ": ");
            int cost = getIntInput(scanner, 0, 1000); // Максимальная стоимость 1000

            city.addRoute(targetCity, cost);
            System.out.println("Путь добавлен: " + city.toShortString() + " -> " +
                    targetCity.toShortString() + " (стоимость: " + cost + ")");
        }
    }

    private static int getIntInput(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int number = Integer.parseInt(scanner.nextLine().trim());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.printf("Неверный ввод. Введите число от %d до %d: ", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.print("Ошибка: введите целое число: ");
            }
        }
    }


    // Отображение всех городов
    public static void displayAll(City[] cities, String title) {
        System.out.println("\n" + title);
        System.out.println("=".repeat(50));
        for (int i = 0; i < cities.length; i++) {
            System.out.printf("%2d. %s\n", (i + 1), cities[i].toShortString());
        }
        System.out.println("=".repeat(50));
    }

    // Создание схемы из рисунка 2
    public static List<City> createFigure2Scheme() {
        // Создаем города
        City a = new City("A");
        City b = new City("B");
        City c = new City("C");
        City d = new City("D");
        City e = new City("E");
        City f = new City("F");

        // Создаем пути согласно схеме (предполагаемая структура)
        a.addRoute(b, 5);
        a.addRoute(f, 1);
        a.addRoute(d, 6);

        b.addRoute(a, 5);
        b.addRoute(c, 3);

        c.addRoute(b, 3);
        c.addRoute(d, 4);

        d.addRoute(c, 4);
        d.addRoute(a, 6);
        d.addRoute(e, 2);

        e.addRoute(f, 2);

        f.addRoute(b, 1);
        f.addRoute(e, 2);

        List<City> cityList = new ArrayList<>();
        cityList.add(a);
        cityList.add(b);
        cityList.add(c);
        cityList.add(d);
        cityList.add(e);
        cityList.add(f);


        return cityList;
    }
}