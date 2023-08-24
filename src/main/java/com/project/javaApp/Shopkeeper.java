package com.project.javaApp;

import java.util.Random;

public class Shopkeeper {
    private int totalPancakesMade;

    public int makePancakes() {
        Random rand = new Random();
        int pancakesMade = rand.nextInt(13); // Maximum 12 pancakes
        totalPancakesMade += pancakesMade;
        return pancakesMade;
    }

    public int getTotalPancakesMade() {
        return totalPancakesMade;
    }
}