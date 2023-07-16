package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.*;

public class Main {

    // Метод за четене от файл
    public static String[][] readDataFromFile(String filename) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Определяне на броя на редовете във входния файл
            int numRows = 0;
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                numRows++;
            }
            scanner.close();

            // Създаване на двумерния масив
            String[][] restaurants = new String[numRows][3];

            // Отново отваряне на файла за четене на данните
            scanner = new Scanner(file);

            int rowIndex = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] restaurantData = line.split(",");
                restaurants[rowIndex] = restaurantData;
                rowIndex++;
            }
            scanner.close();
            return restaurants;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Метод за сортиране на ресторантите по разстояние от потребителя
    private static void sortRestaurantsByDistance(String[][] restaurants, int userLocation) {
        Arrays.sort(restaurants, Comparator.comparingInt(r -> Math.abs(Integer.parseInt(r[1]) - userLocation)));
    }

    // Метод за принтиране на данните за заведенията
    private static void printRestaurantData(String[][] restaurants, int userLocation) {
        System.out.println("Сортирани заведения:");
        for (String[] restaurant : restaurants) {
            int distance = Math.abs(Integer.parseInt(restaurant[1]) - userLocation);
            System.out.println("Име: " + restaurant[0]);
            System.out.println("Разстояние от вас: " + distance + "м");
            System.out.println("Работно време: " + restaurant[2]);
            System.out.println();
        }
    }

    // Метод за сортиране по време на затваряне
    private static void sortRestaurantsByClosingTime(String[][] restaurants) {
        // Получаване на текущия час от часовника на компютъра
        LocalTime currentTime = LocalTime.now();
        int restorantindex = 0;
        // Създаване на компаратор за сравнение на ресторантите според разликата от текущия час
        Comparator<String[]> closingTimeComparator = Comparator.comparing(restaurant -> {
            String closingTime = restaurant[2];
            String[] timeRange = closingTime.split(" - ");
            LocalTime startTime = LocalTime.parse(timeRange[0]);
            LocalTime endTime = LocalTime.parse(timeRange[1]);

            //  Обработка случаи, когато затварянето е след полунощ
            if (endTime.isBefore(startTime)) {
                endTime = endTime.plusHours(24);
            }

            return Math.abs(endTime.toSecondOfDay() - currentTime.toSecondOfDay());
        });

        // Сортиране на заведенията по часа на затваряне
        Arrays.sort(restaurants, closingTimeComparator);

        // Извеждане на отворените заведения в момента
        for (String[] restaurant : restaurants) {
            String closingTime = restaurant[2];
            String[] timeRange = closingTime.split(" - ");
            LocalTime startTime = LocalTime.parse(timeRange[0]);
            LocalTime endTime = LocalTime.parse(timeRange[1]);

            // Обработка случаи, когато затварянето е след полунощ
            if (endTime.isBefore(startTime)) {
                endTime = endTime.plusHours(24);
            }

            if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
                restorantindex++;
                System.out.print(restorantindex + " ");
                System.out.println("Заведение: " + restaurant[0]);
                System.out.println("Работно време: " + closingTime);
            }
        }
    }

    // Метод за извеждане на карта
    private static void displayMap(String[][] restaurants, int userLocation) {
        System.out.println("Списък с заведения (сортиран по разстояние):");
        // Добавяме локацията на потребителя в списъка с заведенията
        String[] user = {"Потребител(X)", String.valueOf(userLocation)};
        String[][] allRestaurants = Arrays.copyOf(restaurants, restaurants.length + 1);
        allRestaurants[restaurants.length] = user;

        // Сортираме масива със заведения по разстояние във възходящ ред
        Arrays.sort(allRestaurants, Comparator.comparingInt(o -> Integer.parseInt(o[1])));

        // Извеждаме сортирания списък с заведенията
        for (int i = 1; i < allRestaurants.length; i++) {
            if (allRestaurants[i][0].equals("Потребител(X)")) {
                System.out.print("X__");
            }
            System.out.print(i + "__");
        }
        System.out.println("");
        for (int i = 0; i < allRestaurants.length; i++) {
            System.out.println((i + 1) + ". " + allRestaurants[i][0]);
        }
    }


    public static void main(String[] args) {
        String[][] restaurants = readDataFromFile("restaurants.txt");

        // Въвеждане на локацията на потребителя
        Scanner scanner = new Scanner(System.in);
        System.out.print("Въведете вашата локация (в метри): ");
        int userLocation = scanner.nextInt();

        // Меню с опции
        System.out.println("Меню:");
        System.out.println("1. Списък на всички заведения");
        System.out.println("2. Списък на отворените заведения");
        System.out.println("3. Извеждане на карта");
        System.out.print("Изберете опция: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                // Сортиране на ресторантите според разстоянието от потребителя
                sortRestaurantsByDistance(restaurants, userLocation);
                // Извеждане на сортираните данни за заведенията
                printRestaurantData(restaurants, userLocation);
                break;
            case 2:
                // Сортира на заведения по време на затваряне и извеждане
                sortRestaurantsByClosingTime(restaurants);
                break;
            case 3:
                // Извеждане на карта на потребителя
                displayMap(restaurants, userLocation);
                break;
            default:
                System.out.println("Невалидна опция. Моля, изберете отново.");
        }
    }
}
