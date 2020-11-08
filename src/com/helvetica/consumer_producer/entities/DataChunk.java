package com.helvetica.consumer_producer.entities;

public class DataChunk {
    long id;

    public DataChunk() {
        this.id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }
}
