package com.helvetica.reader_writer.entities;

public class Counter {

    private Integer count;

    public Counter () {
        this.count = 0;
    }

    public Integer increment() {
        return ++count;
    }

    public Integer decrement() {
        return --count;
    }

    public Integer getCount() {
        return count;
    }
}
