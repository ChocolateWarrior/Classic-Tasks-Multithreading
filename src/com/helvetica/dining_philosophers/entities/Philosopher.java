package com.helvetica.dining_philosophers.entities;

public class Philosopher extends Thread {

    private Fork leftFork;
    private Fork rightFork;

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                if (leftFork.occupy()) {
                    if(rightFork.occupy()) {
                        eat();
                        leftFork.dismantle();
                        rightFork.dismantle();
                    } else {
                        leftFork.dismantle();
                    }
                } else {
                    think();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void eat() throws InterruptedException {
        System.out.printf("\nPhilosopher %s is eating...", getName());
        sleep(500);
    }

    private void think() throws InterruptedException {
        System.out.printf("\nPhilosopher %s is thinking...", getName());
        sleep(500);
    }

    public Philosopher(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }
}
