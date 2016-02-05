package bamboozle;

import bamboozle.grammar.BamboozleLexer;
import bamboozle.grammar.BamboozleParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Andreas on 2016-02-05.
 */
public class RunBamboozle {
    public static void main(String[] args) throws IOException {
        String infnam;
        if (args.length > 0) {
            infnam = args[0];
        } else {
          //  infnam = JOptionPane.showInputDialog("Vilken fil vill du köra?");
            infnam = (String)JOptionPane.showInputDialog(null, "Vilken fil vill du köra?",
                    "Välj fil", JOptionPane.QUESTION_MESSAGE,null,null,"programs/Test2.bz");
        }
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(infnam));
        BamboozleLexer lexer = new BamboozleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BamboozleParser parser = new BamboozleParser(tokens);
      //  ParseTree tree = parser.code(); //
        ParseTree tree = parser.instruction();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Interpreter(infnam), tree);
    }
}
