package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a binary '<' expression
 */
public class LessThan extends BinExp
{

    /**
     * constructor
     * @param pos file position
     * @param ae1 left operand
     * @param ae2 right operand
     */
    public LessThan(int pos, Exp ae1, Exp ae2)
    {
        super(pos, ae1, ae2);
    }

    public String name() {return "LessThan";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof LessThan)) return null;
        return v.visit(this, (LessThan)n);
    }
}
