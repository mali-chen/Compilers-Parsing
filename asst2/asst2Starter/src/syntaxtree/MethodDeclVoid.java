package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a method declaration that does not return a value
 */
public class MethodDeclVoid extends MethodDecl
{

    /**
     * constructor
     * @param pos file position
     * @param as the name being declared
     * @param afl the list of formal parameter declarations
     * @param asl the statements that are the method's body
     */
    public MethodDeclVoid(int pos, String as, VarDeclList afl, StmtList asl)
    {
        super(pos, as, afl, asl);
    }

    public String name() {return "MethodDeclVoid";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof MethodDeclVoid)) return null;
        return v.visit(this, (MethodDeclVoid)n);
    }
}
