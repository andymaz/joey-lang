package com.home.sweet.node;

public class SingleNode extends Node {
    public String polarity;
    public String modality;
    public WordsNode verbs;
    public WordsNode objects;

    public SingleNode(String polarity, String modality, WordsNode verbs, WordsNode objects) {
        this.polarity = polarity;
        this.modality = modality;
        this.verbs = verbs;
        this.objects = objects;
    }

    @Override
    public String toString() {
        return polarity + " " + modality + " " + verbs + " " + objects;
    }
}

