package org.kodluyoruz.week3and4.hw;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        SortedSet<City> list = new TreeSet<>();

        Map<String, City> cities = new HashMap<>();
        {
            City moscow = new City("Moscow", "MOW", "GMT+3");
            cities.put(moscow.getCityCode(), moscow);
            City newYork = new City("New York", "NYC", "GMT-5");
            cities.put(newYork.getCityCode(), newYork);
            City london = new City("London", "LON", "GMT");
            cities.put(london.getCityCode(), london);
            City berlin = new City("Berlin", "BER", "GMT+1");
            cities.put(berlin.getCityCode(), berlin);
            City delhi = new City("New Delhi", "DEL", "GMT+5:30");
            cities.put(delhi.getCityCode(), delhi);
        }
        SortedSet<City> sorted = new TreeSet<>(cities.values());

        System.out.println("Welcome to world clock!");
        System.out.println("These are the list of cities that you can select and see their live time");
        printAll(sorted);


        Scanner scan = new Scanner(System.in);
        int i = 0;
        boolean selectedCity = true;

        while (selectedCity) {
            System.out.print("Enter a city code to select city, you have to select at least 3 cities:");
            boolean validCityCode = false;
            String item = scan.next();
            for (City city : sorted) {
                if (item.equals(city.getCityCode()) && !list.contains(city)) {
                    list.add(city);
                    System.out.println("You selected :" + city.getName());
                    validCityCode = true;
                    i++;
                }
            }
            if (validCityCode != true) {
                System.out.println("Please Enter a valid city code");
            }
            if (i == 5) {
                selectedCity = false;

                for (City city : sorted) {
                    if (list.contains(city)) {
                        executorService.execute(city);
                    }

                }
            }
            if (i == 3 || i == 4) {

                String continueOrNot;
                System.out.println("You selected " + i + " cities if you want you can select " + (5 - i) + " more city. Enter 'Y' to continue");
                continueOrNot = scan.next();
                System.out.println(continueOrNot);
                if (!continueOrNot.matches("Y")) {
                    selectedCity = false;
                    for (City city : sorted) {
                        if (list.contains(city)) {
                            executorService.execute(city);
                        }

                    }
                }
            }


        }
    }

        static void printAll(Collection < City > collection) {
            if (!collection.isEmpty()) {
                for (Object object : collection) {
                    System.out.println(object.toString());

                }

            }
        }
    }
