package com.home.sweet.node;

import java.util.List;
import java.util.stream.Collectors;

public class WordsNode extends Node {
    public List<String> words;

    public WordsNode(List<String> words) {
        this.words = words;
    }

    @Override
    public String toString() {
        if (words.size() == 1) {
            return words.get(0); // single word, no braces
        } else {
            // multiple words, join with commas and wrap with braces
            return "{" + words.stream().collect(Collectors.joining(", ")) + "}";
        }
    }
}
