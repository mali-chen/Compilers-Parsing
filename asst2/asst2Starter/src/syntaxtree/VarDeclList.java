package syntaxtree;

import java.util.List;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a list of variable declarations
 */
public class VarDeclList extends AstList<VarDecl>
{

    public VarDeclList()
    {
        super();
    }

    public VarDeclList(List<VarDecl> lst)
    {
        super(lst);
    }

    public String name() {return "VarDeclList";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstList<VarDecl> n)
    {
        if(!(n instanceof VarDeclList)) return null;
        return v.visit(this, (VarDeclList)n);
    }
}
