package com.helvetica.reader_writer.entities;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Reader extends Thread {

    private SharedResource resource;

    private ReentrantLock writeLock;
    private ReentrantLock readLock;
    private Condition availableForReading;
    private Condition availableForWriting;
    private Boolean isWriteLocked;

    private Counter readerCounter;

    public Reader(SharedResource resource, ReentrantLock writeLock,
                  ReentrantLock readLock, Condition availableForWriting,
                  Condition availableForReading, Counter readerCounter,
                  Boolean isWriteLocked
    ) {
        this.resource = resource;
        this.writeLock = writeLock;
        this.readLock = readLock;
        this.availableForReading = availableForReading;
        this.availableForWriting = availableForWriting;
        this.readerCounter = readerCounter;
        this.isWriteLocked = isWriteLocked;
    }

    @Override
    public void run() {
        while (!isInterrupted() && !isWriteLocked) {
            System.out.println("in reader " + getName());
            try {
                writeLock.lock();
                readLock.lock();

                if (readerCounter.getCount() == 0) {
                    readerCounter.increment();
                    readLock.unlock();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.printf("\nReader %s read: %s", getName(), resource.getPoem());
            try {

                readerCounter.decrement();
                readLock.lock();
                writeLock.unlock();
                availableForReading.signal();
                readLock.unlock();
                sleep(1000); // Read message, now rest
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
