package com.helvetica.sleeping_barber.entities;

public class ClientBuffer {

    private int maxSize;
    private int currentSize;

    public ClientBuffer(int maxSize) {
        this.maxSize = maxSize;
        this.currentSize = 0;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void bookPlace() {
        currentSize++;
    }

    public void leavePlace() {
        currentSize--;
    }

    public boolean isFull() {
        return currentSize >= maxSize;
    }

}
