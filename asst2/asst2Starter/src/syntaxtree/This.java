package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * the expression, 'this'
 */
public class This extends Exp
{

    /**
     * constructor
     * @param pos file position
     */
    public This(int pos)
    {
        super(pos);
    }

    public String name() {return "This";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof This)) return null;
        return v.visit(this, (This)n);
    }
}
