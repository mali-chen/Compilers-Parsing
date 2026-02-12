package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * the type 'boolean'
 */
public class BoolType extends Type
{

    /**
     * constructor
     * @param pos file position
     */
    public BoolType(int pos)
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
        return obj instanceof BoolType;
    }


    public String name()       { return "BoolType"; }
    public String vtableName() { return "BOOLEAN"; }
    public String typeName()   { return "Z"; }
    public boolean isBoolean() { return true; }
    public String toString()   { return "boolean";}

    /**
     * hash code
     * @return the object's hash code
     */
    @Override
    public int hashCode()
    {
        return 327236434;
    }

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof BoolType)) return null;
        return v.visit(this, (BoolType)n);
    }
}
