package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a expression that creates a new object, as in "new Tree()"
 */
public class NewObject extends Exp
{

    // instance variables filled in by constructor
    public IDType objType; // the type of the object being created

    /**
     * constructor
     * @param pos file position
     * @param atype the type of object being created
     */
    public NewObject(int pos, IDType atype)
    {
        super(pos);
        objType=atype;
    }

    public String name() {return "NewObject";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof NewObject)) return null;
        return v.visit(this, (NewObject)n);
    }

}
