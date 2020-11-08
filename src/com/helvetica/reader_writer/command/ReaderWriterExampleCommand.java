package com.helvetica.reader_writer.command;

import com.helvetica.reader_writer.entities.Counter;
import com.helvetica.reader_writer.entities.Reader;
import com.helvetica.reader_writer.entities.SharedResource;
import com.helvetica.reader_writer.entities.Writer;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//    Письменники — читачі з блокуванням нових читачів, коли письменник чекає на вхід. ReentrantLock
public class ReaderWriterExampleCommand {

    public static void execute() throws InterruptedException {
        ArrayDeque<String> poem = new ArrayDeque<>();
        poem.add("Poem By: \n");
        SharedResource resource = new SharedResource(poem);

        ReentrantLock writeLock = new ReentrantLock();
        ReentrantLock readLock = new ReentrantLock();

        Condition writeCondition = writeLock.newCondition();
        Condition readCondition = readLock.newCondition();

        Counter readerCounter = new Counter();
        Boolean isWriteLocked = Boolean.FALSE;

        Writer writer =
                new Writer(resource, writeLock, readLock, writeCondition, readCondition, 100, readerCounter, isWriteLocked);
        Reader readerFirst =
                new Reader(resource, writeLock, readLock, writeCondition, readCondition, readerCounter, isWriteLocked);
        Reader readerSecond =
                new Reader(resource, writeLock, readLock, writeCondition, readCondition, readerCounter, isWriteLocked);
        Reader readerThird =
                new Reader(resource, writeLock, readLock, writeCondition, readCondition, readerCounter, isWriteLocked);

        writer.start();
        readerFirst.start();
        readerSecond.start();
        readerThird.start();
    }
}
