package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a label in a 'switch' statement (abstract)
 */
public abstract class Label extends Stmt
{

    // instance variables filled in during later phases
    public Switch enclosingSwitch; // the switch-statement associated with this label

    /**
     * constructor
     * @param pos file position
     */
    public Label(int pos)
    {
        super(pos);
        enclosingSwitch=null;
    }

    /**
     * the value, if any, that is associated with the label
     * @return the value associated with the label
     */
    public Exp labelValue()
    {
        return null;
    }

    public String name() {return "Label";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Label)) return null;
        return v.visit(this, (Label)n);
    }
}

