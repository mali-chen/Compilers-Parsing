package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a expression 'false'
 */
public class False extends Exp
{

    /**
     * constructor
     * @param pos file position
     */
    public False(int pos)
    {
        super(pos);
    }

    public String name() {return "False";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof False)) return null;
        return v.visit(this, (False)n);
    }
}
