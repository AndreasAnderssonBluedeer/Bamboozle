package bamboozle;

import bamboozle.grammar.BamboozleBaseListener;
import bamboozle.grammar.BamboozleParser;
import org.antlr.v4.runtime.Token;

import java.util.HashMap;
import java.util.Stack;

public class Interpreter extends BamboozleBaseListener {
    private static class Var {
        int val;
        String name;
    }

    private HackAsmGenerator hag;
    private final String inputFileName;
    private final HashMap<String, Var> vars = new HashMap<String, Var>();
    private Stack<Integer> exprVal = new Stack<Integer>();
    private String varName ;
    private ByteCodeGenerator bcg;

    Interpreter(String filename,ByteCodeGenerator bcg) {
        this.inputFileName = filename;
        this.bcg=bcg;
        hag=new HackAsmGenerator(inputFileName);
    }

    private Var getVar(Token token) {
        String name = token.getText();
        Var v = vars.get(name);
        if (v == null) {
            error(token.getLine(), "undefined " + name);
            System.out.println("GetVar1:"+v);
            return new Var();   // avoid null pointer exception
        } else {
            System.out.println("GetVar2:"+name);
            v.name=name;
            return v;
        }
    }
    
    private void error(int line, String msg) {
        System.err.println(inputFileName + ":" + line + ": " + msg);
    }
    
    @Override
    public void enterDeclaration(BamboozleParser.DeclarationContext context) {
        String name = context.ID().getText();
        bcg.createVariable(name);
        System.out.println("enterDecl: var "+name);
        hag.writeDecl(name);

        Var old = vars.put(name, new Var());
        if (old != null) {
            error(context.ID().getSymbol().getLine(), "redefined " + name);
        }
    }

    @Override
    public void exitAssign(BamboozleParser.AssignContext context) {

        Var x=getVar(context.ID().getSymbol());
        x.val = exprVal.pop();
//        bcg.loadConst(x.val);
        bcg.storeValue(x.name);
        hag.writeAssign(x.name,x.val);
        System.out.println("Exit Assign ID/VAL:"+x.name+"/"+x.val);

    }

    @Override
    public void exitPrint(BamboozleParser.PrintContext context) {
        Integer x= exprVal.pop();
        bcg.print(varName);
        System.out.println(x);
        //OBS Hämta variabelnamnet för att skriva ut, Getvar?
    }
    @Override
    public void exitExpression(BamboozleParser.ExpressionContext context) {
        if (context.expression() != null) {
            Integer one,two;
            one=exprVal.pop();
            two=exprVal.pop();
            System.out.println("exitExpr incl push:"+one+"+"+two);
            exprVal.push(one+two);
            bcg.addValues();
        }
    }
    @Override
    public void enterInfo(BamboozleParser.InfoContext context) {
        if (context.ID() != null) {
            Var x=getVar(context.ID().getSymbol());
            System.out.println("EnterInfo ID/Val:"+x.name+"/"+x.val);
            exprVal.push(x.val);
            varName=(x.name);
            bcg.loadLocal(x.name);
        } else {
            Integer z=Integer.parseInt(context.INT().getText());
            System.out.println("EnterInfo value:"+z);
            exprVal.push(z);
            bcg.loadConst(z);
        }
    }
}
