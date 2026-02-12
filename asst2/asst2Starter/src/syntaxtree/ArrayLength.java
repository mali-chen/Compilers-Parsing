package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a array-length expression, as in "a.length"
 */
public class ArrayLength extends UnExp
{

    /**
     * constructor
     * @param pos file position
     * @param arrExp the operand
     */
    public ArrayLength(int pos, Exp arrExp)
    {
        super(pos, arrExp);
    }

    public String name() {return "ArrayLength";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof ArrayLength)) return null;
        return v.visit(this, (ArrayLength)n);
    }
}
