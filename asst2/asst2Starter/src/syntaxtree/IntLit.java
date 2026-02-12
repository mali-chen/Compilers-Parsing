package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a expression that is an integer constant, as in "34"
 */
public class IntLit extends Exp
{

    // instance variables filled in by constructor
    public int val; // the value denoted by the integer literal

    /**
     * constructor
     * @param pos file position
     * @param aval the value denoted by this integer literal
     */
    public IntLit(int pos, int aval)
    {
        super(pos);
        val=aval;
    }

    public String name() {return "IntLit";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof IntLit)) return null;
        return v.visit(this, (IntLit)n);
    }
}
