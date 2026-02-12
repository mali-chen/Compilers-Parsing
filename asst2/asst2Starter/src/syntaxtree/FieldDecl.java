package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * declaration of an instance variable
 */
public class FieldDecl extends VarDecl
{

    /**
     * constructor
     * @param pos file position
     * @param atype the type of the variable
     * @param aname the name being declared
     */
    public FieldDecl(int pos, Type atype, String aname)
    {
        super(pos, atype, aname);
    }

    public String name() {return "FieldDecl";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof FieldDecl)) return null;
        return v.visit(this, (FieldDecl)n);
    }
}
