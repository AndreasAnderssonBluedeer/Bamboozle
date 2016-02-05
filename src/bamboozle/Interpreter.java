package bamboozle;

import bamboozle.grammar.BamboozleBaseListener;
import bamboozle.grammar.BamboozleParser;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.Stack;

public class Interpreter extends BamboozleBaseListener {
    private static class Var { int val; }
    
    private final String inputFileName;
    private final HashMap<String, Var> vars = new HashMap<String, Var>();
    private Stack<Integer> exprVal = new Stack<Integer>();

    Interpreter(String filename) { this.inputFileName = filename; }

    private Var getVar(Token token) {
        String name = token.getText();
        Var v = vars.get(name);
        if (v == null) {
            error(token.getLine(), "undefined " + name);
            return new Var();   // avoid null pointer exception
        } else {
            return v;
        }
    }
    
    private void error(int line, String msg) {
        System.err.println(inputFileName + ":" + line + ": " + msg);
    }
    
    @Override
    public void enterDeclaration(BamboozleParser.DeclarationContext context) {
        String name = context.ID().getText();
        Var old = vars.put(name, new Var());
        if (old != null) {
            error(context.ID().getSymbol().getLine(), "redefined " + name);
        }
    }

    @Override
    public void exitAssign(BamboozleParser.AssignContext context) {
        getVar(context.ID().getSymbol()).val = exprVal.pop();
    }

    @Override
    public void exitPrint(BamboozleParser.PrintContext context) {
        System.out.println(exprVal.pop());
    }
    @Override
    public void exitExpression(BamboozleParser.ExpressionContext context) {
        if (context.expression() != null) {
            exprVal.push(exprVal.pop() + exprVal.pop());
        }
    }
    @Override
    public void enterInfo(BamboozleParser.InfoContext context) {
        if (context.ID() != null) {
            exprVal.push(getVar(context.ID().getSymbol()).val);
        } else {
            exprVal.push(Integer.parseInt(context.INT().getText()));
        }
    }
}
