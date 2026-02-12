package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a named type (i.e., a class name)
 */
public class IDType extends Type
{

    // instance variables filled in by constructor
    public String name; // the name of the type

    // instance variables filled in during later phases
    public ClassDecl link; // the type declaration

    /**
     * constructor
     * @param pos file position
     * @param aname the name of the type
     */
    public IDType(int pos, String aname)
    {
        super(pos);
        name = aname;
        link = null;
    }



    /**
     * type equality
     * @param the object tested for being equal to me
     */
    @Override
    public boolean equals(Object obj)
    {
        return obj instanceof IDType &&
               this.link != null &&
               this.link == ((IDType)obj).link;
    }

    public String name()       { return "IDType"; }
    public String vtableName() { return name; }
    public String typeName()   { return name; }
    public boolean isID()      { return true; }

    public boolean isObject()  { return name.equals("Object"); }
    public String toString()   { return name; }

    /**
     * hash code
     * @return the object's hash code
     */
    @Override
    public int hashCode()
    {
        if (name == null)
        {
            return 23977;
        }
        else return 826427*name.hashCode()+83473;
    }

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof IDType)) return null;
        return v.visit(this, (IDType)n);
    }
}
