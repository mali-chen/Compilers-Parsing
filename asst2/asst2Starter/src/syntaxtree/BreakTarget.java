package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a statement that a break statement can break out of (e.g.,
 * 'switch' or 'while' (abstract)
 */
public abstract class BreakTarget extends Stmt
{

    // instance variables filled in during later phases
    public int stackHeight; // the height of the stack at the time the statement is reached

    /**
     * constructor
     * @param pos file position
     */
    public BreakTarget(int pos)
    {
        super(pos);
        stackHeight = Integer.MIN_VALUE;
    }

    public String name() {return "BreakTarget";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof BreakTarget)) return null;
        return v.visit(this, (BreakTarget)n);
    }
}
