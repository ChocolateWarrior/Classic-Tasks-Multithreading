package com.helvetica.consumer_producer.entities;

public class Consumer extends Thread {

    private BufferService bufferService;

    public Consumer(BufferService bufferService) {
        this.bufferService = bufferService;
    }

    @Override
    public void run() {
        try {
            System.out.println("Trying to consume chunk...");
            bufferService.consumeChunk();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
