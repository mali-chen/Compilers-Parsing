package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a string literal, as in '"Hello World"'
 */
public class StringLit extends Exp
{

    // instance variables filled in by constructor
    public String str; // the string denoted by the string literal

    // instance variables filled in during later phases
    public StringLit uniqueCgRep; // the expression representing this one during code generation

    /**
     * constructor
     * @param pos file position
     * @param astr the string that the string literal denotes
     */
    public StringLit(int pos, String astr)
    {
        super(pos);
        str = astr;
        uniqueCgRep = null;
    }

    public String name() {return "StringLit";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof StringLit)) return null;
        return v.visit(this, (StringLit)n);
    }
}
