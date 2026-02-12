package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * the type 'int'
 */
public class IntType extends Type
{

    /**
     * constructor
     * @param pos file position
     */
    public IntType(int pos)
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
        return obj instanceof IntType;
    }

    public String name()       { return "IntType"; }
    public String vtableName() { return "INT"; }
    public String typeName()   { return "I"; }
    public boolean isInt()     { return true; }
    public String toString()   { return "int"; }

    /**
     * hash code
     * @return the object's hash code
     */
    @Override
    public int hashCode()
    {
        return 657643445;
    }

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof IntType)) return null;
        return v.visit(this, (IntType)n);
    }
}
