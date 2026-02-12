package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * an 'if' statement
 */
public class If extends Stmt
{

    // instance variables filled in by constructor
    public Exp exp; // the test-expression
    public Stmt trueStmt; // the statement that executes if the test is true
    public Stmt falseStmt; // the statement that exeuctes if the test is false

    /**
     * constructor
     * @param pos file position
     * @param aexp the if-statement's test-expression
     * @param atrueStmt the statement that executes if test test is true
     * @param afalseStmt the statement that executes if test test is false
     */
    public If(int pos, Exp aexp, Stmt atrueStmt, Stmt afalseStmt)
    {
        super(pos);
        exp=aexp;
        trueStmt=atrueStmt;
        falseStmt=afalseStmt;
    }

    public String name() {return "If";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof If)) return null;
        return v.visit(this, (If)n);
    }
}

