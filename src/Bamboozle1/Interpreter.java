package Bamboozle1;

import Bamboozle1.grammar.BamboozleBaseListener;
import Bamboozle1.grammar.BamboozleParser;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.HashMap;
import java.util.Stack;

/**
 * This class cooperates with the Bamboozle.g4 and compilates the instructions as Java code.
 * Created by Andreas & Sara on 2016-02-05.
 */
public class Interpreter extends BamboozleBaseListener {
    //Var = Variable.
    private static class Var {
        int val;
    }

    //Instance Variables
    private final String inputFileName;
    private final HashMap<String, Var> vars = new HashMap<String, Var>();
    private Stack<Integer> exprVal = new Stack<Integer>();
    private int currentValue =0, targetValue =0;
    private String currentValStr="";

    /**
     * Constructor that receives a Filename for error Message.
     * @param filename Filename
     */
    public Interpreter(String filename) {
        this.inputFileName = filename;
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
   */
    public void enterDeclaration(BamboozleParser.DeclarationContext context) {
        String name = context.ID().getText();

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

    }

    //***************************REPEAT/ LOOP******************************
    @Override
    /**
     * When "Repeat" is finished. Works like a While-looping
     */
    public void exitRepeat(BamboozleParser.RepeatContext context) {
            //Stack= Targetvalue on top.
            targetValue = exprVal.pop();
            currentValue = exprVal.pop();

        ParseTreeWalker walker=new ParseTreeWalker();

        System.out.println("Interpreter-REPEAT: "+currentValue+" "+targetValue);

            while(currentValue<targetValue){
                    walker.walk(this,context.instruction());
                     currentValue=vars.get(currentValStr).val;

                System.out.println("Interpreter-REPEAT in loop: "+currentValue+" "+
                        targetValue);
            }

    }

    @Override
    /**
     * When "Repeat" is called. Works like a While-looping
     */
    public void enterRepeat(BamboozleParser.RepeatContext context) {
        String str= context.getText();
        //5= T in repeat
        String cur= str.substring(6,str.indexOf('<'));
        String target=str.substring(str.indexOf('<')+1,str.indexOf('{'));
        System.out.println("Interpreter-ENTER REPEAT: "+str+ " CUR:"+cur+" Tar:"+target);
        //save variable name to recall variable in loop.
        currentValStr=cur;


    }

    //***********************************************************************

    @Override
    /**
     * When "print" is called. Prints to Console.
     */
    public void exitPrint(BamboozleParser.PrintContext context) {
        Integer x= exprVal.pop();
        System.out.println(x);
    }
    @Override
    /**
     * Adds two values.
     */
    public void exitExpression(BamboozleParser.ExpressionContext context) {
//        System.out.println("EXPRESSION");
        if (context.expression() != null) {
            Integer one,two;
            one=exprVal.pop();
            two=exprVal.pop();
            exprVal.push(one+two);
        }
    }
    @Override
    /**
     * A nbr as a variable or as a integer, that will be used next in the code.
     */
    public void enterInfo(BamboozleParser.InfoContext context) {
//        System.out.println("INFO");
        if (context.ID() != null) {
            Var x=getVar(context.ID().getSymbol());
            exprVal.push(x.val);
        } else {
            Integer z=Integer.parseInt(context.INT().getText());
            exprVal.push(z);
        }
    }
}
