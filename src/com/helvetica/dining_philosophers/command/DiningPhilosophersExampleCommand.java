package com.helvetica.dining_philosophers.command;

import com.helvetica.dining_philosophers.entities.Fork;
import com.helvetica.dining_philosophers.entities.Philosopher;

public class DiningPhilosophersExampleCommand {

    private static final int COUNT = 5;

    public static void execute() {
        System.out.println(System.nanoTime() + ": Dinner is started");

        Fork[] forks = new Fork[COUNT];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i + 1);
        }

        Thread[] threads = new Thread[COUNT];
        Philosopher[] philosophers = new Philosopher[COUNT];
        for (int i = 0; i < philosophers.length; i++) {
            Fork leftFork = forks[i];
            Fork rightFork = forks[(i + 1) % forks.length];

            if (leftFork.getNumber() < rightFork.getNumber()) {
                philosophers[i] = new Philosopher(leftFork, rightFork);
            } else {
                philosophers[i] = new Philosopher(rightFork, leftFork);
            }

            threads[i] = new Thread(philosophers[i], "Philosopher " + (i + 1));
            threads[i].start();
        }

    }
}
