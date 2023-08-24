package com.project.javaApp;

public class PancakeSimulation {
    public static void main(String[] args) {
        Shopkeeper shopkeeper = new Shopkeeper();
        User[] users = new User[3];
        for (int i = 0; i < 3; i++) {
            users[i] = new User();
        }

        int timeSlot = 1;
        while (timeSlot <= 10) { // Simulating 10 time slots of 30 seconds each
            int pancakesMade = shopkeeper.makePancakes();
            int totalPancakesEaten = 0;

            for (User user : users) {
                int pancakesEaten = user.eatPancakes();
                totalPancakesEaten += pancakesEaten;
            }

            System.out.println("Time Slot: " + timeSlot);
            System.out.println("Pancakes made by shopkeeper: " + pancakesMade);
            System.out.println("Pancakes eaten by users: " + totalPancakesEaten);

            if (totalPancakesEaten <= shopkeeper.getTotalPancakesMade()) {
                System.out.println("Shopkeeper met the needs of all users.");
            } else {
                System.out.println("Shopkeeper could not meet the needs of all users.");
                System.out.println("Unmet orders: " + (totalPancakesEaten - shopkeeper.getTotalPancakesMade()));
            }

            System.out.println("-------------------------");

            timeSlot++;
        }
    }
}

