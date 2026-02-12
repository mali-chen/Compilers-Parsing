package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * an assignment statement
 */
public class Assign extends Stmt
{

    // instance variables filled in by constructor
    public Exp lhs; // the left operand
    public Exp rhs; // the right operand

    /**
     * constructor
     * @param pos file position
     * @param alhs the left operand
     * @param arhs the right operand
     */
    public Assign(int pos, Exp alhs, Exp arhs)
    {
        super(pos);
        lhs = alhs;
        rhs = arhs;
    }

    public String name() {return "Assign";}
    
    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Assign)) return null;
        return v.visit(this, (Assign)n);
    }
}

