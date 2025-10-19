package com.home;

import com.home.gen.JoeyLexer;
import com.home.gen.JoeyParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import static org.antlr.v4.runtime.CharStreams.fromFileName;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private final static String DIR = "policies/";

    public static void main(String[] args) throws Exception {
        // Create an input stream
        CharStream input = fromFileName(DIR + "adams_family.txt");
        // Create the lexer and token stream
        JoeyLexer lexer = new JoeyLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create the parser
        JoeyParser parser = new JoeyParser(tokens);

        JoeyParser.StartContext tree = parser.start();
        String pretty = new PrettyPrint_JoeyVisitor().visit(tree);



        System.out.println(pretty);
    }
}