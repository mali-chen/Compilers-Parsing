package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * an expression consisting of a variable name
 */
public class IDExp extends Exp
{

    // instance variables filled in by constructor
    public String name; // the name of the variable

    // instance variables filled in during later phases
    public VarDecl link; // the declaration of the variable

    /**
     * constructor
     * @param pos file position
     * @param aname the name of the variable
     */
    public IDExp(int pos, String aname)
    {
        super(pos);
        name=aname;
        link=null;
    }

    public String name() {return "IDExp";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof IDExp)) return null;
        return v.visit(this, (IDExp)n);
    }
}
