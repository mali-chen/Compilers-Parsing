package syntaxtree;

import java.util.List;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a list of declarations
 */
public class DeclList extends AstList<Decl>
{

    public DeclList()
    {
        super();
    }

    public DeclList(List<Decl> lst)
    {
        super(lst);
    }

    public String name() {return "DeclList";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstList<Decl> n)
    {
        if(!(n instanceof DeclList)) return null;
        return v.visit(this, (DeclList)n);
    }

}
