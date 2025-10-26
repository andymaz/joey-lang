package com.home.sweet.aux;

import com.home.sweet.node.*;
import java.util.stream.Collectors;

public class SugarAux {

    private final Node node;

    public SugarAux(Node node) {
        this.node = node;
    }

    public String prettyPrint() {
        if (node instanceof PolicyNode policy) {
            return "policy " + policy.name + ":\n" +
                    indent(new SugarAux(policy.sentences).prettyPrint());
        }

        if (node instanceof SentencesNode sents) {
            return "begin\n" +
                    indent(sents.sentences.stream()
                            .map(s -> new SugarAux(s).prettyPrint())
                            .collect(Collectors.joining("\n"))) +
                    "\nend";
        }

        if (node instanceof SentenceNode sent) {
            String singles = sent.singles.stream()
                    .map(s -> new SugarAux(s).prettyPrint())
                    .collect(Collectors.joining("; "));
            return sent.subject + " <== begin " + singles + " end";
        }

        if (node instanceof SingleNode single) {
            return single.polarity + " " + single.modality + " " +
                    new SugarAux(single.verbs).prettyPrint() + " " +
                    new SugarAux(single.objects).prettyPrint();
        }

        if (node instanceof WordsNode w) {
            return w.toString();
        }

        return "";
    }

    private String indent(String text) {
        return text.lines()
                .map(line -> "    " + line)
                .collect(Collectors.joining("\n"));
    }

    public void analyze() {
        if (node instanceof PolicyNode policy) {
            System.out.println("Analyzing policy: " + policy.name);
            new SugarAux(policy.sentences).analyze();
        } else if (node instanceof SentencesNode sents) {
            sents.sentences.forEach(s -> new SugarAux(s).analyze());
        } else if (node instanceof SentenceNode sent) {
            System.out.println("Subject: " + sent.subject);
            sent.singles.forEach(s -> new SugarAux(s).analyze());
        } else if (node instanceof SingleNode single) {
            System.out.println("  Single: " + single.polarity + " " + single.modality);
        }
    }
}
