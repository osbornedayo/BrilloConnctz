package com.project.javaApp;

import java.util.Random;

public class User {
    private int pancakesEaten;

    public int eatPancakes() {
        Random rand = new Random();
        int pancakesToEat = rand.nextInt(6); // Maximum 5 pancakes
        if (pancakesToEat + pancakesEaten > 5) {
            pancakesToEat = 5 - pancakesEaten; // Eat only what's possible
        }
        pancakesEaten += pancakesToEat;
        return pancakesToEat;
    }

    public int getPancakesEaten() {
        return pancakesEaten;
    }
}