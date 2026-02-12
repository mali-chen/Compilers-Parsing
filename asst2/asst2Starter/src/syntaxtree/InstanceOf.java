package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a 'instanceof' expression
 */
public class InstanceOf extends Exp
{

    // instance variables filled in by constructor
    public Exp exp; // the expression being tested
    public Type checkType; // the type being checked against

    /**
     * constructor
     * @param pos file position
     * @param aexp the expression being tested
     * @param atype the type being tested against
     */
    public InstanceOf(int pos, Exp aexp, Type atype)
    {
        super(pos);
        exp=aexp;
        checkType=atype;
    }

    public String name() {return "InstanceOf";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof InstanceOf)) return null;
        return v.visit(this, (InstanceOf)n);
    }
}
