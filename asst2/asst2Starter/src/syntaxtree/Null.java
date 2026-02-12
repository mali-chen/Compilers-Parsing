package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a expression that denotes 'null'
 */
public class Null extends Exp
{

    /**
     * constructor
     * @param pos file position
     */
    public Null(int pos)
    {
        super(pos);
    }

    public String name() {return "Null";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Null)) return null;
        return v.visit(this, (Null)n);
    }
}
