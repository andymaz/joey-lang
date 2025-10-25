package com.home.sweet.node;

import java.util.List;

public class WordsNode extends Node {
    public List<String> words;

    public WordsNode(List<String> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        if (words.size() == 1) {
            return words.get(0);
        } else {
            return "{" + String.join(", ", words) + "}";
        }
    }
}
