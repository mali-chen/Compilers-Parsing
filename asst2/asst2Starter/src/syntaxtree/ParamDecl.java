package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * declaration of a formal parameter in a method declaration's
 * parameter list
 */
public class ParamDecl extends VarDecl
{

    /**
     * constructor
     * @param pos file position
     * @param atype the type of the variable
     * @param aname the name being declared
     */
    public ParamDecl(int pos, Type atype, String aname)
    {
        super(pos, atype, aname);
    }

    public String name() {return "ParamDecl";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof ParamDecl)) return null;
        return v.visit(this, (ParamDecl)n);
    }
}
