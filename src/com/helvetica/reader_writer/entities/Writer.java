package com.helvetica.reader_writer.entities;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Writer extends Thread {

    private SharedResource resource;

    private ReentrantLock writeLock;
    private ReentrantLock readLock;
    private Condition availableForReading;
    private Condition availableForWriting;
    private Boolean isWriteLocked;

    private int countOfMessagesCurrent;
    private int targetCountOfMessages;

    private Counter readerCounter;
    private Counter writerCounter;

    public Writer(SharedResource resource, ReentrantLock writeLock,
                  ReentrantLock readLock, Condition availableForWriting,
                  Condition availableForReading, int targetCountOfMessages,
                  Counter readerCounter,
                  Boolean isWriteLocked) {
        this.resource = resource;
        this.writeLock = writeLock;
        this.readLock = readLock;
        this.availableForReading = availableForReading;
        this.availableForWriting = availableForWriting;
        this.targetCountOfMessages = targetCountOfMessages;
        this.countOfMessagesCurrent = 0;
        this.readerCounter = readerCounter;
        this.isWriteLocked = isWriteLocked;
    }

    @Override
    public void run() {
        while (!isInterrupted() && countOfMessagesCurrent <= targetCountOfMessages) {
            try {
                System.out.println("In writer, reader count: " + readerCounter.getCount());
                isWriteLocked = writeLock.tryLock();
                readLock.lock();
                resource.appendAuthor(getName());
                countOfMessagesCurrent++;
                System.out.println("Poem entries: " + countOfMessagesCurrent);

//                writerCounter.decrement();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                readLock.unlock();
                if(isWriteLocked) {
                    writeLock.unlock();
                    isWriteLocked = false;
                }

                try {
                    sleep(6000); //Wrote message now rest
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
