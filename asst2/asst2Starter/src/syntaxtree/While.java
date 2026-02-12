package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a 'while' statement
 */
public class While extends BreakTarget
{

    // instance variables filled in by constructor
    public Exp exp; // the test-expression
    public Stmt body; // the while-statement's body

    /**
     * constructor
     * @param pos file position
     * @param aexp the while-statement's test-expression
     * @param abody the while-statement's body
     */
    public While(int pos, Exp aexp, Stmt abody)
    {
        super(pos);
        exp=aexp;
        body=abody;
    }

    public String name() {return "BreakTarget";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof While)) return null;
        return v.visit(this, (While)n);
    }
}

