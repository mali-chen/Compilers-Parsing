package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a declaration (abstract)
 */
public abstract class Decl extends AstNode
{

    // instance variables filled in by constructor
    public String name; // the name being declared

    /**
     * constructor
     * @param pos file position
     * @param aname the name being declared
     */
    public Decl(int pos, String aname)
    {
        super(pos);
        name=aname;
    }

    /*** remaining methods are visitor- and display-related ***/

    public String name() {return "Decl";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Decl)) return null;
        return v.visit(this, (Decl)n);
    }
}
