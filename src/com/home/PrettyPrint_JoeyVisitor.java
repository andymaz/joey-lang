// Generated from /home/andrei/IdeaProjects/joey/src/com/home/Joey.g4 by ANTLR 4.13.2
package com.home;

import com.home.gen.JoeyBaseVisitor;
import com.home.gen.JoeyParser;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.List;
import java.util.stream.Collectors;



@SuppressWarnings("CheckReturnValue")
public class PrettyPrint_JoeyVisitor extends JoeyBaseVisitor<String> {

	@Override public String visitStart(JoeyParser.StartContext ctx) { return visitSentence(ctx.sentence()); }

	@Override public String visitSentence(JoeyParser.SentenceContext ctx) {
		String temp = "";
		temp += ctx.BEGIN() + "\n";
		for (JoeyParser.SingleContext s : ctx.single()) {
			temp += "\t" + visitSingle(s) + "\n";
		}
		temp += ctx.END();
		return temp;
	}

	@Override public String visitSingle(JoeyParser.SingleContext ctx) {
		String temp = visitPolarity(ctx.polarity()) + " ";
		temp += visitModality(ctx.modality()) + " ";
		temp += visitSubject(ctx.subject()) + " ";
		temp += visitVerbs(ctx.verbs()) + " ";
		temp += visitObjects(ctx.objects());
		return temp;
	}

	@Override public String visitPolarity(JoeyParser.PolarityContext ctx) {
		if (ctx.POSITIVE() != null) {
			return "+ve";
		} else if (ctx.NEGATIVE() != null) {
			return "-ve";
		}
		return ""; // fallback, should never happen if grammar is correct
	}

	@Override public String visitModality(JoeyParser.ModalityContext ctx) {
		if (ctx.AUTHORISATION() != null) return "authorised";
		else if (ctx.OBLIGATION() != null) return "obligated";
		return "";
	}


	@Override public String visitSubject(JoeyParser.SubjectContext ctx) { return ctx.getText(); }

	@Override public String visitVerbs(JoeyParser.VerbsContext ctx) {
		return visitWords(ctx.words());
	}

	@Override public String visitObjects(JoeyParser.ObjectsContext ctx) {
		return visitWords(ctx.words());
	}

	@Override
	public String visitSingle_word(JoeyParser.Single_wordContext ctx) {
		// A single_word just contains one WORD token
		return ctx.WORD().getText();
	}

	@Override
	public String visitCompound_word(JoeyParser.Compound_wordContext ctx) {
		// Extract all WORD tokens between { and }
		List<TerminalNode> words = ctx.WORD();
		return words.stream()
				.map(TerminalNode::getText)
				.collect(Collectors.joining(", ", "{", "}"));
	}


	public String visitWords(JoeyParser.WordsContext ctx) {
		if (ctx instanceof JoeyParser.Single_wordContext)
			return visitSingle_word((JoeyParser.Single_wordContext) ctx);
		else if (ctx instanceof JoeyParser.Compound_wordContext)
			return visitCompound_word((JoeyParser.Compound_wordContext) ctx);
		else
			return "";
	}

}