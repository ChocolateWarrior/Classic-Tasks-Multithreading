package com.helvetica.consumer_producer.entities;

public class BufferService {

    private Buffer dataBuffer;

    public BufferService(Buffer buffer) {
        this.dataBuffer = buffer;
    }

    public void produceChunk() throws InterruptedException {
        while (true) {
            synchronized (this) {
                DataChunk dataChunk = new DataChunk();
                while (dataBuffer.getData().size() == dataBuffer.getMaxSize()) {
                    wait();
                }
                dataBuffer.push(dataChunk);
                System.out.println("Producer produced chunk with ID: " + dataChunk.getId());
                notify();
                Thread.sleep(1000);

            }
        }
    }

    public void consumeChunk() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (dataBuffer.getData().isEmpty()) {
                    wait();
                }
                DataChunk dataChunk = dataBuffer.pop();
                System.out.println("Consumer consumed chunk with ID: " + dataChunk.getId());
                notify();
                Thread.sleep(1000);

            }
        }
    }

    private boolean isBufferEmpty() {
        return dataBuffer.isEmpty();
    }

    private boolean isBufferFull() {
        return dataBuffer.isFull();
    }


}
