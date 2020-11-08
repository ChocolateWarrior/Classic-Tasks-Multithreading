package com.helvetica.consumer_producer.entities;

import java.util.LinkedList;

public class Buffer {

    private int maxSize = 4;
    private LinkedList<DataChunk> data;

    public Buffer(int maxSize, LinkedList<DataChunk> data) {
        this.maxSize = maxSize;
        this.data = data;
    }

    public boolean isFull() {
        return data.size() == maxSize;
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public synchronized DataChunk pop(){
        return data.poll();
    }

    public synchronized void push(DataChunk chunk) {
        data.add(chunk);
    }

    public LinkedList<DataChunk> getData() {
        return data;
    }

    public int getMaxSize() {
        return maxSize;
    }
}
