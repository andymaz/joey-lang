package com.home.sweet;


import com.home.sweet.aux.SugarAux;
import com.home.sweet.gen.*;
import com.home.sweet.node.Node;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {

    private static final String DIR = "policies/";

    public static void main(String[] args) throws Exception {
        // --- Load input file ---
        CharStream input = fromFileName(DIR + "adams_family.sgr");

        // --- Create lexer and parser ---
        SugarLexer lexer = new SugarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SugarParser parser = new SugarParser(tokens);

        // --- Build the AST using the visitor ---
        AST_SugarVisitor visitor = new AST_SugarVisitor();
        Node ast = visitor.visit(parser.start());

        // --- Pretty-print the AST ---
        SugarAux aux = new SugarAux(ast);
        System.out.println(aux.prettyPrint());
    }
}
