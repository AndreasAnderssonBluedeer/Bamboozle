package bamboozle2;

import bamboozle2.grammar.BamboozleBaseListener;
import bamboozle2.grammar.BamboozleParser;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class cooperates with the Bamboozle.g4 and compilates the instructions as Java code.
 * It does also send calls to ByteCodeGenerator to create Java Bytecode.
 * Created by Andreas & Sara on 2016-02-05.
 */
public class Interpreter extends BamboozleBaseListener {
    //Var = Variable.
    private static class Var {
        int val;
        String name;
    }

    //Instance Variables
    private final String inputFileName;
    private final HashMap<String, Var> vars = new HashMap<String, Var>();
    private Stack<Integer> exprVal = new Stack<Integer>();
    private String varName ;
    private ByteCodeGenerator bcg;

    /**
     * Constructor that receives a Filename and a ByteCodeGenerator.
     * ByteCodeGenerator is used for the ByteCode generation, and FileName for error Message.
     * @param filename Filename
     * @param bcg ByteCode-Generator
     */
    public Interpreter(String filename, ByteCodeGenerator bcg) {
        this.inputFileName = filename;
        this.bcg=bcg;
    }

    /**
     * Returns a Var / Variable using Token
     * @param token token/Variable name
     * @return Var
     */
    private Var getVar(Token token) {
        String name = token.getText();
        Var v = vars.get(name);
        if (v == null) {
            error(token.getLine(), "undefined " + name);
            return new Var();   // avoid null pointer exception
        } else {
            v.name=name;
            return v;
        }
    }

    /**
     * Prints an Error in the Console with Line nbr and Message.
     * @param line -line nbr
     * @param msg -message
     */
    private void error(int line, String msg) {
        System.err.println(inputFileName + ":" + line + ": " + msg);
    }
    
    @Override
    /**
     * Method to be performed when the code "Declaration" is found from the Grammar.
     * Creates a Variable.
     */
    public void enterDeclaration(BamboozleParser.DeclarationContext context) {
        String name = context.ID().getText();
        bcg.createVariable(name);

        Var old = vars.put(name, new Var());
        if (old != null) {
            error(context.ID().getSymbol().getLine(), "redefined " + name);
        }
    }

    @Override
    /**
     * When exiting assign in the grammar. Used to store a value to a variable.
     */
    public void exitAssign(BamboozleParser.AssignContext context) {

        Var x=getVar(context.ID().getSymbol());
        x.val = exprVal.pop();
        bcg.storeValue(x.name);

    }

    @Override
    /**
     * When "print" is called. Prints to Console.
     */
    public void exitPrint(BamboozleParser.PrintContext context) {
        Integer x= exprVal.pop();
        System.out.println(x);
        bcg.print(varName);
    }
    @Override
    /**
     * Adds two values.
     */
    public void exitExpression(BamboozleParser.ExpressionContext context) {
        if (context.expression() != null) {
            Integer one,two;
            one=exprVal.pop();
            two=exprVal.pop();
            exprVal.push(one+two);
            bcg.addValues();
        }
    }
    @Override
    /**
     * A nbr as a variable or as a integer, that will be used next in the code.
     */
    public void enterInfo(BamboozleParser.InfoContext context) {
        if (context.ID() != null) {
            Var x=getVar(context.ID().getSymbol());
            exprVal.push(x.val);
            varName=(x.name);
            bcg.loadLocal(x.name);
        } else {
            Integer z=Integer.parseInt(context.INT().getText());
            exprVal.push(z);
            bcg.loadConst(z);
        }
    }
}
