package com.helvetica.reader_writer.entities;

import java.util.ArrayDeque;
import java.util.Optional;

public class SharedResource {

    private ArrayDeque<String> poem;

    public SharedResource (ArrayDeque<String> poem) {
        this.poem = poem;
    }

    public String getPoem() {
        Optional<String> poemOptional = poem.stream().reduce((x, y) -> x + y);
        return poemOptional.orElse("NO POEM PRESENT");
    }

    public void appendAuthor(String authorName) {
        poem.add(authorName + "\n");
    }
}
