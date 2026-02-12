package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a MiniJava type (abstract)
 */
public abstract class Type extends AstNode
{

    /**
     * constructor
     * @param pos file position
     */
    public Type(int pos)
    {
        super(pos);
    }

    public String name() {return "Type";}

    public abstract String vtableName();
    public abstract String typeName();

    public boolean isBoolean() {return false;}
    public boolean isInt()     {return false;}
    public boolean isID()      {return false;}
    public boolean isVoid()    {return false;}
    public boolean isNull()    {return false;}
    public boolean isError()   {return false;}
    public boolean isArray()   {return false;}

    // This is specifically for the type Object
    public boolean isObject()   {return false;}


    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Type)) return null;
        return v.visit(this, (Type)n);
    }
}
