package com.home.sweet.aux;

import com.home.sweet.node.*;
import java.util.stream.Collectors;

public class SugarAux {

    private final Node node;

    public SugarAux(Node node) {
        this.node = node;
    }

    /**
     * Generates a human-readable pretty print of the AST.
     */
    public String prettyPrint() {
        if (node instanceof PolicyNode) {
            PolicyNode policy = (PolicyNode) node;
            return "POLICY " + policy.name + ":\n" +
                    indent(new SugarAux(policy.sentences).prettyPrint());
        }

        else if (node instanceof SentencesNode) {
            SentencesNode sents = (SentencesNode) node;
            return sents.sentences.stream()
                    .map(s -> new SugarAux(s).prettyPrint())
                    .collect(Collectors.joining("\n"));
        }

        else if (node instanceof SentenceNode) {
            SentenceNode sent = (SentenceNode) node;
            String singles = sent.singles.stream()
                    .map(s -> new SugarAux(s).prettyPrint())
                    .collect(Collectors.joining("; "));
            return sent.subject + " <== begin " + singles + " end";
        }

        else if (node instanceof SingleNode) {
            SingleNode single = (SingleNode) node;
            String verbs = new SugarAux(single.verbs).prettyPrint();
            String objects = new SugarAux(single.objects).prettyPrint();
            return single.polarity + " " + single.modality + " " + verbs + " " + objects;
        }

        else if (node instanceof WordsNode) {
            WordsNode w = (WordsNode) node;
            return String.join(" ", w.words);
        }

        return "";  // fallback
    }

    //--- Utility to indent multi-line blocks for readability
    private String indent(String text) {
        return text.lines()
                .map(line -> "    " + line)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Placeholder for future semantic or transformation logic.
     * e.g. type-check, normalization, or policy evaluation.
     */
    public void analyze() {
        if (node instanceof PolicyNode policy) {
            System.out.println("Analyzing policy: " + policy.name);
            new SugarAux(policy.sentences).analyze();
        }
        else if (node instanceof SentencesNode sents) {
            sents.sentences.forEach(s -> new SugarAux(s).analyze());
        }
        else if (node instanceof SentenceNode sent) {
            System.out.println("Subject: " + sent.subject);
            sent.singles.forEach(s -> new SugarAux(s).analyze());
        }
        else if (node instanceof SingleNode single) {
            System.out.println("  Single: " + single.polarity + " " + single.modality);
        }
    }
}
