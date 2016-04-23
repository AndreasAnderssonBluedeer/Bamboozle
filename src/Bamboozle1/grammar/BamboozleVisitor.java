// Generated from /Users/Andreas/Documents/Studier/Systemprogramvara/Bamboozle/src/Bamboozle1/grammar/Bamboozle.g4 by ANTLR 4.5.1
package Bamboozle1.grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;



/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BamboozleParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BamboozleVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link BamboozleParser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(BamboozleParser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BamboozleParser#toDo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitToDo(BamboozleParser.ToDoContext ctx);
	/**
	 * Visit a parse tree produced by {@link BamboozleParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(BamboozleParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link BamboozleParser#assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(BamboozleParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link BamboozleParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(BamboozleParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link BamboozleParser#repeat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat(BamboozleParser.RepeatContext ctx);
	/**
	 * Visit a parse tree produced by {@link BamboozleParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(BamboozleParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link BamboozleParser#info}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfo(BamboozleParser.InfoContext ctx);
}