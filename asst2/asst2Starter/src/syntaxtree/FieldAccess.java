package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * an instance-variable access expression, as in "myList.next"
 */
public class FieldAccess extends Exp
{

    // instance variables filled in by constructor
    public Exp exp; // the expression denoting the object to access
    public String varName; // the name of the instance variable in the object

    // instance variables filled in during later phases
    public FieldDecl varDec; // declaration of the instance variable being accessed

    /**
     * constructor
     * @param pos file position
     * @param aexp the expression denoting the object
     * @param avarName the name of the instance variable in the object being accessed
     */
    public FieldAccess(int pos, Exp aexp, String avarName)
    {
        super(pos);
        exp=aexp;
        varName=avarName;
    }

    public String name() {return "FieldAccess";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof FieldAccess)) return null;
        return v.visit(this, (FieldAccess)n);
    }
}
