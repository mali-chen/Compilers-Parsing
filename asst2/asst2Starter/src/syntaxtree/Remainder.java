package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a binary '%' expression
 */
public class Remainder extends BinExp
{

    /**
     * constructor
     * @param pos file position
     * @param ae1 left operand
     * @param ae2 right operand
     */
    public Remainder(int pos, Exp ae1, Exp ae2)
    {
        super(pos, ae1, ae2);
    }

    public String name() {return "Remainder";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Remainder)) return null;
        return v.visit(this, (Remainder)n);
    }
}
