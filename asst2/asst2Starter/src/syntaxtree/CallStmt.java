package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a statement that consists of a method call
 */
public class CallStmt extends Stmt
{

    // instance variables filled in by constructor
    public Call callExp; // the expression that is the actual call

    /**
     * constructor
     * @param pos file position
     * @param aexp the call-expression
     */
    public CallStmt(int pos, Call aexp)
    {
        super(pos);
        callExp = aexp;
    }

    public String name() {return "CallStmt";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof CallStmt)) return null;
        return v.visit(this, (CallStmt)n);
    }
}

