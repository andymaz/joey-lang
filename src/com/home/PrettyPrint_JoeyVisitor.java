// Generated from /home/andrei/IdeaProjects/joey/src/com/home/Joey.g4 by ANTLR 4.13.2
package com.home;

import com.home.gen.*;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.List;


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
	public String visitWords(JoeyParser.WordsContext ctx) {
		// Single word case
		if (ctx.WORD().size() == 1 && ctx.LBRACKET() == null) {
			return ctx.WORD(0).getText();
		}
		// Bracketed list case
		else if (ctx.LBRACKET() != null) {
			StringBuilder temp = new StringBuilder();
			temp.append(ctx.LBRACKET().getText()); // "{"

			List<TerminalNode> words = ctx.WORD();
			for (int i = 0; i < words.size(); i++) {
				temp.append(words.get(i).getText());
				if (i < words.size() - 1) temp.append(","); // commas between words
			}

			temp.append(ctx.RBRACKET().getText()); // "}"
			return temp.toString();
		}
		return ""; // fallback (should not happen)
	}
}