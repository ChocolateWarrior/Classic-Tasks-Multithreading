package com.helvetica.consumer_producer.command;

import com.helvetica.consumer_producer.entities.*;

import java.util.ArrayDeque;
import java.util.LinkedList;

public class ProducerConsumerExampleCommand {

//    Виробники — споживачі. Object, synchronized, wait/ notify.
//    Письменники — читачі з блокуванням нових читачів, коли письменник чекає на вхід. ReentrantLock
//    Обідаючі філософи. Semaphore
//    Сплячий перукар. ReentrantLock
    public static void execute() throws InterruptedException {
        Buffer buffer = new Buffer(10, new LinkedList<>());
        BufferService bufferService = new BufferService(buffer);
        Producer producer = new Producer(bufferService);
        Consumer consumer = new Consumer(bufferService);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
