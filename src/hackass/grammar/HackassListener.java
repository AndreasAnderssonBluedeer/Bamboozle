// Generated from C:/Users/Andreas/Documents/Bamboozle/src/hackass/grammar\Hackass.g4 by ANTLR 4.5.1
package hackass.grammar;



import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HackassParser}.
 */
public interface HackassListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HackassParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(HackassParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(HackassParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link HackassParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(HackassParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(HackassParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link HackassParser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(HackassParser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(HackassParser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link HackassParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(HackassParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(HackassParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link HackassParser#ainstr}.
	 * @param ctx the parse tree
	 */
	void enterAinstr(HackassParser.AinstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#ainstr}.
	 * @param ctx the parse tree
	 */
	void exitAinstr(HackassParser.AinstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link HackassParser#cinstr}.
	 * @param ctx the parse tree
	 */
	void enterCinstr(HackassParser.CinstrContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#cinstr}.
	 * @param ctx the parse tree
	 */
	void exitCinstr(HackassParser.CinstrContext ctx);
	/**
	 * Enter a parse tree produced by {@link HackassParser#dest}.
	 * @param ctx the parse tree
	 */
	void enterDest(HackassParser.DestContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#dest}.
	 * @param ctx the parse tree
	 */
	void exitDest(HackassParser.DestContext ctx);
	/**
	 * Enter a parse tree produced by {@link HackassParser#jump}.
	 * @param ctx the parse tree
	 */
	void enterJump(HackassParser.JumpContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#jump}.
	 * @param ctx the parse tree
	 */
	void exitJump(HackassParser.JumpContext ctx);
	/**
	 * Enter a parse tree produced by {@link HackassParser#comp}.
	 * @param ctx the parse tree
	 */
	void enterComp(HackassParser.CompContext ctx);
	/**
	 * Exit a parse tree produced by {@link HackassParser#comp}.
	 * @param ctx the parse tree
	 */
	void exitComp(HackassParser.CompContext ctx);
}