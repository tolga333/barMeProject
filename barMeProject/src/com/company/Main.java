package com.company;

import java.util.*;

public class Main {

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
            System.out.println("Локация: " + restaurant[1] + "м");
            System.out.println("Разстояние от вас: " + distance + "м");
            System.out.println("Работно време: " + restaurant[2]);
            System.out.println();
        }
    }
    // Метод за извеждане на карта
    private static void displayMap(String[][] restaurants, int userLocation) {
//        System.out.println("Списък с заведения (сортиран по разстояние):");
//
//        // Добавяме локацията на потребителя в списъка с заведенията
//        String[] user = {"Потребител", String.valueOf(userLocation), "X"};
//        String[][] allRestaurants = Arrays.copyOf(restaurants, restaurants.length + 1);
//        allRestaurants[restaurants.length] = user;
//
//        // Сортираме масива със заведения по разстояние във възходящ ред
//        Arrays.sort(allRestaurants, Comparator.comparingInt(o -> Integer.parseInt(o[1])));
//
//        // Извеждаме сортирания списък с заведенията
//        for (int i = 0; i < allRestaurants.length; i++) {
//            System.out.println((i + 1) + ". " + allRestaurants[i][0] + " (" + allRestaurants[i][2] + ")");
//        }


        }

    // Филтриране на отворените заведения
    private static String[][] filterOpenRestaurants(String[][] restaurants) {
        return restaurants;
    }

    private static void sortRestaurantsByClosingTime(String[][] restaurants) {


        // Създаване на компаратор за сравнение на ресторантите по цифрите след "-" в часа на затваряне
        Comparator<String[]> closingTimeComparator = Comparator.comparing(restaurant -> {
            String closingTime = restaurant[2];
            String digitsAfterDash = closingTime.substring(closingTime.indexOf("-") + 1).trim();
            if (digitsAfterDash.equals("00:00")) {
                return 2400; // Заменяме "00:00" с 2400
            } else {
                return Integer.parseInt(digitsAfterDash.replace(":", ""));
            }
        });

        // Сортиране на ресторантите по цифрите след "-" в часа на затваряне
        Arrays.sort(restaurants, closingTimeComparator);

        // Отпечатване на сортирания списък с ресторанти
        for (String[] restaurant : restaurants) {
            System.out.println("Ресторант: " + restaurant[0]);
            System.out.println("Работно време: " + restaurant[2]);
        }
    }

    public static void main(String[] args) {
        String[][] restaurants = {
                {"Ресторант 'Българе'", "500", "10:00 - 23:00"},
                {"Пицария 'Татко Пица'", "750", "11:00 - 00:00"},
                {"Кафе-бар 'Сохо'", "300", "09:00 - 22:00"},
                {"Ресторант 'Лятно Кино'", "900", "12:00 - 23:00"},
                {"Кафе 'Феймус'", "600", "08:00 - 21:00"},
                {"Ресторант 'Капитан Блъд'", "400", "11:30 - 00:30"},
                {"Пицария 'Стоп Моцарела'", "800", "10:00 - 22:30"},
                {"Кафе 'Кафе 65'", "350", "08:30 - 20:30"},
                {"Ресторант 'Лес'", "550", "09:00 - 23:30"},
                {"Бар-ресторант 'Нжой'", "700", "17:00 - 02:00"}
        };

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
                sortRestaurantsByClosingTime(restaurants);
                break;
            case 3:
                displayMap(restaurants,userLocation);
                break;
            default:
                System.out.println("Невалидна опция. Моля, изберете отново.");
        }
    }
}
