package com.project.javaApp;

import java.util.Random;

public   class UserConcurrent {
    private int pancakesEaten;

    public int eatPancakes(int maxPancakes) {
        Random rand = new Random();
        int pancakesToEat = rand.nextInt(6); // Maximum 5 pancakes
        if (pancakesToEat + pancakesEaten > maxPancakes) {
            pancakesToEat = maxPancakes - pancakesEaten; // Eat only what's possible
        }
        pancakesEaten += pancakesToEat;
        return pancakesToEat;
    }

    public int getPancakesEaten() {
        return pancakesEaten;
    }
}