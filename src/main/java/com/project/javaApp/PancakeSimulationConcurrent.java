package com.project.javaApp;



import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;





public class PancakeSimulationConcurrent {
    public static void main(String[] args) {
        ShopkeeperConcurrent shopkeeper = new ShopkeeperConcurrent();
        UserConcurrent[] users = new UserConcurrent[3];
        for (int i = 0; i < 3; i++) {
            users[i] = new UserConcurrent();
        }

        int timeSlot = 1;
        while (timeSlot <= 10) { // Simulating 10 time slots of 30 seconds each
            int pancakesMade = shopkeeper.makePancakes();

            CompletableFuture<Integer>[] userFutures = new CompletableFuture[3];

            for (int i = 0; i < 3; i++) {
                final int index = i;
                userFutures[i] = CompletableFuture.supplyAsync(() -> users[index].eatPancakes(pancakesMade));
            }

            CompletableFuture<Void> allOf = CompletableFuture.allOf(userFutures);

            try {
                allOf.get(); // Wait for all user eating tasks to complete
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            int totalPancakesEaten = 0;
            for (CompletableFuture<Integer> future : userFutures) {
                try {
                    totalPancakesEaten += future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
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
