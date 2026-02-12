package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * the expression 'super'
 */
public class Super extends Exp
{

    /**
     * constructor
     * @param pos file position
     */
    public Super(int pos)
    {
        super(pos);
    }

    public String name() {return "Super";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Super)) return null;
        return v.visit(this, (Super)n);
    }
}
