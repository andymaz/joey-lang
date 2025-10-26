package com.home.sweet;

import com.home.sweet.gen.*;
import com.home.sweet.node.*;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class AST_SugarVisitor extends SugarBaseVisitor<Node> {

    @Override
    public Node visitStart(SugarParser.StartContext ctx) {
        String policyName = ctx.name().getText();
        SentencesNode sentencesNode = (SentencesNode) visit(ctx.sentences());
        return new PolicyNode(policyName, sentencesNode);
    }

    @Override
    public Node visitSentences(SugarParser.SentencesContext ctx) {
        if (ctx.EMPTY() != null) {
            return new SentencesNode(new ArrayList<>()); // empty list
        }

        List<SentenceNode> sentenceList = new ArrayList<>();
        for (SugarParser.SentenceContext sctx : ctx.sentence()) {
            sentenceList.add((SentenceNode) visit(sctx));
        }
        return new SentencesNode(sentenceList);
    }

    @Override
    public Node visitSentence(SugarParser.SentenceContext ctx) {
        String subject = ctx.subject().getText();
        List<SingleNode> singles = new ArrayList<>();
        for (SugarParser.SingleContext sctx : ctx.single()) {
            singles.add((SingleNode) visit(sctx));
        }
        return new SentenceNode(subject, singles);
    }

    @Override
    public Node visitSingle(SugarParser.SingleContext ctx) {
        String ply = ctx.polarity().getText();
        String mdlty = ctx.modality().getText();
        WordsNode vbs = (WordsNode) visit(ctx.verbs());
        WordsNode obj = (WordsNode) visit(ctx.objects());
        return new SingleNode(ply, mdlty, vbs, obj);
    }

    @Override
    public Node visitVerbs(SugarParser.VerbsContext ctx) {
        return visitWords(ctx.words());
    }

    @Override
    public Node visitObjects(SugarParser.ObjectsContext ctx) {
        return visitWords(ctx.words());
    }


    @Override
    public Node visitSingle_word(SugarParser.Single_wordContext ctx) {
        return new WordsNode(List.of(ctx.WORD().getText()));
    }

    @Override
    public Node visitCompound_word(SugarParser.Compound_wordContext ctx) {
        List<String> list = new ArrayList<>();
        for (TerminalNode w : ctx.WORD()) list.add(w.getText());
        return new WordsNode(list);
    }

    // helper (not an override)
    public Node visitWords(SugarParser.WordsContext ctx) {
        if (ctx instanceof SugarParser.Single_wordContext)
            return visitSingle_word((SugarParser.Single_wordContext) ctx);
        else if (ctx instanceof SugarParser.Compound_wordContext)
            return visitCompound_word((SugarParser.Compound_wordContext) ctx);
        else
            throw new RuntimeException("Unknown words context: " + ctx.getText());
    }

}
