package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a 'break' statement
 */
public class Break extends Stmt
{
    // instance variables filled in during later phases
    public BreakTarget breakLink; // link to the while or switch the statement breaks out of

    /**
     * constructor
     * @param pos file position
     */
    public Break(int pos)
    {
        super(pos);
        breakLink = null;
    }

    public String name() {return "Break";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Break)) return null;
        return v.visit(this, (Break)n);
    }
}
