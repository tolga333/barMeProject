package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    // Метод за сортиране на ресторантите по разстояние от потребителя
    private static void sortRestaurantsByDistance(String[][] restaurants, int userLocation) {
        Arrays.sort(restaurants, Comparator.comparingInt(r -> Math.abs(Integer.parseInt(r[1]) - userLocation)));
    }

    // Метод за принтиране на данните за заведенията
    private static void printRestaurantData(String[][] restaurants) {
        System.out.println("Сортирани заведения:");
        for (String[] restaurant : restaurants) {
            System.out.println("Име: " + restaurant[0]);
            System.out.println("Локация: " + restaurant[1] + "м");
            System.out.println("Работно време: " + restaurant[2]);
            System.out.println();
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

                break;
            case 2:

                break;
            case 3:

                break;
            default:
                System.out.println("Невалидна опция. Моля, изберете отново.");
        }
    }
}
