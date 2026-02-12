package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * an array-access expression, as in a[i]
 */
public class ArrayLookup extends Exp
{

    // instance variables filled in by constructor
    public Exp arrExp; // the array-expression
    public Exp idxExp; // the index-expression

    /**
     * constructor
     * @param pos file position
     * @param aarrExp the expression before the brackets
     * @param aidxExp the expression inside the brackets
     */
    public ArrayLookup(int pos, Exp aarrExp, Exp aidxExp)
    {
        super(pos);
        arrExp=aarrExp;
        idxExp=aidxExp;
    }

    public String name() {return "ArrayLookup";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof ArrayLookup)) return null;
        return v.visit(this, (ArrayLookup)n);
    }
}
