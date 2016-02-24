package Bamboozle1;

import Bamboozle1.grammar.BamboozleLexer;
import Bamboozle1.grammar.BamboozleParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * A class to start the compilation of the programming language Bamboozle.
 * Compilates as Java via the Grammar from Bamboozle.g4.
 * Created by Andreas & Sara on 2016-02-05.
 */
public class RunBamboozle {
    /**
     * Start-method. Lets the user choose a file/program to run and a filename to save the Java Bytecode that will be
     * saved in src/ByteCode/*FILENAME*
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        String infnam;
        if (args.length > 0) {
            infnam = args[0];
        } else {
          //  infnam = JOptionPane.showInputDialog("Vilken fil vill du köra?");
            infnam = (String)JOptionPane.showInputDialog(null, "Vilken fil vill du köra?",
                    "Välj fil", JOptionPane.QUESTION_MESSAGE,null,null,"programs/Test3.bz");
        }

        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(infnam));
        BamboozleLexer lexer = new BamboozleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BamboozleParser parser = new BamboozleParser(tokens);
        ParseTree tree = parser.instruction();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Interpreter(infnam), tree);

    }
}
