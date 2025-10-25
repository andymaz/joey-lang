package com.home.sweet.node;

import java.util.List;

public class SentenceNode extends Node {
    public String subject;
    public List<SingleNode> singles;

    public SentenceNode(String subject, List<SingleNode> singles) {
        this.subject = subject;
        this.singles = singles;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(subject + " <== begin\n");
        for (SingleNode s : singles) {
            sb.append("      ").append(s).append("\n");
        }
        sb.append("    end");
        return sb.toString();
    }
}
