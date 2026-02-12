package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a unary '!' expression
 */
public class Not extends UnExp
{

    /**
     * constructor
     * @param pos file position
     * @param ae the operand
     */
    public Not(int pos, Exp ae)
    {
        super(pos, ae);
    }

    public String name() {return "Not";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Not)) return null;
        return v.visit(this, (Not)n);
    }
}
