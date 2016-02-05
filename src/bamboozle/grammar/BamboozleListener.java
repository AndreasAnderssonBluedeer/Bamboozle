// Generated from C:/Users/Andreas/Documents/Bamboozle/src/bamboozle/grammar\Bamboozle.g4 by ANTLR 4.5.1
package bamboozle.grammar;



import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BamboozleParser}.
 */
public interface BamboozleListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BamboozleParser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(BamboozleParser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BamboozleParser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(BamboozleParser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BamboozleParser#toDo}.
	 * @param ctx the parse tree
	 */
	void enterToDo(BamboozleParser.ToDoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BamboozleParser#toDo}.
	 * @param ctx the parse tree
	 */
	void exitToDo(BamboozleParser.ToDoContext ctx);
	/**
	 * Enter a parse tree produced by {@link BamboozleParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(BamboozleParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BamboozleParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(BamboozleParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BamboozleParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(BamboozleParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link BamboozleParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(BamboozleParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link BamboozleParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(BamboozleParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link BamboozleParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(BamboozleParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link BamboozleParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(BamboozleParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BamboozleParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(BamboozleParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BamboozleParser#info}.
	 * @param ctx the parse tree
	 */
	void enterInfo(BamboozleParser.InfoContext ctx);
	/**
	 * Exit a parse tree produced by {@link BamboozleParser#info}.
	 * @param ctx the parse tree
	 */
	void exitInfo(BamboozleParser.InfoContext ctx);
}