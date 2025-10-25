package com.home.sweet.node;

public class PolicyNode extends Node {
    public String name;
    public SentencesNode sentences;

    public PolicyNode(String name, SentencesNode sentences) {
        this.name = name;
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        return "Policy(" + name + "):\n" + sentences;
    }
}
