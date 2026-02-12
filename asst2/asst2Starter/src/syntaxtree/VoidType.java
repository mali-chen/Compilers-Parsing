package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * the type, 'void'
 */
public class VoidType extends Type
{

    /**
     * constructor
     * @param pos file position
     */
    public VoidType(int pos)
    {
        super(pos);
    }

    /**
     * type equality
     * @param the object tested for being equal to me
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof VoidType;
    }

    public String name()       { return "VoidType"; }
    public String vtableName() { return "VOID"; }
    public String typeName()   { return "V"; }
    public boolean isVoid()    { return true; }
    public String toString()   { return "void"; }

    /**
     * hash code
     * @return the object's hash code
     */
    @Override
    public int hashCode()
    {
        return 23672;
    }

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof VoidType)) return null;
        return v.visit(this, (VoidType)n);
    }
}
