package syntaxtree;

import java.util.List;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a list of class declaration
 */
public class ClassDeclList extends AstList<ClassDecl>
{

    public ClassDeclList()
    {
        super();
    }

    public ClassDeclList(List<ClassDecl> lst)
    {
        super(lst);
    }

    public String name() {return "ClassDeclList";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    public Object accept(Visitor2 v, AstList<ClassDecl> n)
    {
        if(!(n instanceof ClassDeclList)) return null;
        return v.visit(this, (ClassDeclList)n);
    }

}
