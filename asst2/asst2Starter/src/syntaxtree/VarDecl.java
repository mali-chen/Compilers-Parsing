package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a variable declaration (abstract)
 */
public abstract class VarDecl extends Decl
{

    // instance variables filled in by constructor
    public Type type; // the type of the variable being declared

    // instance variables filled in during later phases
    public int offset; // the variable's stack (or object) offset

    /**
     * constructor
     * @param pos file position
     * @param atype the type of the variable
     * @param aname the name being declared
     */
    public VarDecl(int pos, Type atype, String aname)
    {
        super(pos, aname);
        type=atype;
        offset = Integer.MIN_VALUE;
    }

    public String name() {return "VarDecl";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof VarDecl)) return null;
        return v.visit(this, (VarDecl)n);
    }
}
