package com.home.sweet.node;

import java.util.List;

public class SentencesNode extends Node {
    public List<SentenceNode> sentences;

    public SentencesNode(List<SentenceNode> sentences) {
        this.sentences = sentences;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("  begin\n");
        for (SentenceNode s : sentences) {
            sb.append("    ").append(s).append("\n");
        }
        sb.append("  end");
        return sb.toString();
    }
}
