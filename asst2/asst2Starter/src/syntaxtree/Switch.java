package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a 'switch' statement
 */
public class Switch extends BreakTarget
{

    // instance variables filled in by constructor
    public Exp exp; // the switch-expression
    public StmtList stmts; // the statements in the switch-statment's body

    /**
     * constructor
     * @param pos file position
     * @param aexp the switch-expression
     * @param astmts the statements in the switch-statement's body
     */
    public Switch(int pos, Exp aexp, StmtList astmts)
    {
        super(pos);
        exp=aexp;
        stmts = astmts;
    }

    public String name() {return "Switch";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Switch)) return null;
        return v.visit(this, (Switch)n);
    }
}

