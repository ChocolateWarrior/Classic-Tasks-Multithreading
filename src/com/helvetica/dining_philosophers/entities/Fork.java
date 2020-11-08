package com.helvetica.dining_philosophers.entities;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Fork {

    private int number;
    private Semaphore forkSemaphore;
    private Random random;

    public Fork(int number) {
        this.number = number;
        this.forkSemaphore = new Semaphore(1);
        this.random = new Random();
    }

    public boolean occupy() throws InterruptedException {
        return forkSemaphore.tryAcquire(1,random.nextInt(400) + 100, TimeUnit.MILLISECONDS);
    }

    public void dismantle() {
        forkSemaphore.release();
    }

    public int getNumber() {
        return number;
    }
}
