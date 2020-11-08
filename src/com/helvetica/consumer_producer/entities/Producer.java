package com.helvetica.consumer_producer.entities;

public class Producer extends Thread {

    private BufferService bufferService;

    public Producer(BufferService bufferService) {
        this.bufferService = bufferService;
    }

    @Override
    public void run() {
        try {
            System.out.println("Trying to produce chunk...");
            bufferService.produceChunk();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
