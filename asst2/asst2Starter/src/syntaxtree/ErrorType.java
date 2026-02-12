package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * the type of an error (like looking up a method without it existing.
 */
public class ErrorType extends Type
{

    /**
     * constructor
     * @param pos file position
     */
    public ErrorType(int pos)
    {
        super(pos);
    }

    public String name()       { return "ErrorType"; }
    public String vtableName() { return "ERROR"; }
    public String typeName()   { return "X"; }
    public boolean isError()   { return true; }
    public String toString()   { return "ERROR";}

    /**
     * type equality
     * @param the object tested for being equal to me
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof ErrorType;
    }

    /**
     * hash code
     * @return the object's hash code
     */
    @Override
    public int hashCode()
    {
        return 7326835;
    }

    /*** remaining methods are visitor- and display-related ***/

    public Object accept(Visitor v)
    {
        return v.visit(this);
    } 

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof ErrorType)) return null;
        return v.visit(this, (ErrorType)n);
    }
}
