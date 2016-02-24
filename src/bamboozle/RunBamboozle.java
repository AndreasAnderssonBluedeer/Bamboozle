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

/**
 * A class to start the compilation of the programming language Bamboozle.
 * Compilates as Java via the Grammar from Bamboozle.g4 and converts it to Java Bytecode via ByteCodeGenerator.
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
        String classname=(String)JOptionPane.showInputDialog(null, "Välj filnamn",
                "Välj filnam", JOptionPane.QUESTION_MESSAGE,null,null,"TestingGenerated");

        ByteCodeGenerator bcg=new ByteCodeGenerator(classname);
        ANTLRInputStream input = new ANTLRInputStream(new FileInputStream(infnam));
        BamboozleLexer lexer = new BamboozleLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BamboozleParser parser = new BamboozleParser(tokens);
        ParseTree tree = parser.instruction();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Interpreter(infnam,bcg), tree);
        bcg.writeEnd();
    }
}
