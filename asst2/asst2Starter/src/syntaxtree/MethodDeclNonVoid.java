package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a method declaration that returns a value
 */
public class MethodDeclNonVoid extends MethodDecl
{

    // instance variables filled in by constructor
    public Type rtnType; // the method's return-type
    public Exp rtnExp; // the method's return-expression

    /**
     * constructor
     * @param pos file position
     * @param at the method's return type
     * @param as the name being declared
     * @param afl the list of formal parameter declarations
     * @param asl the statements that are the method's body
     * @param ae the method's return-expression
     */
    public MethodDeclNonVoid(int pos, Type at, String as, VarDeclList afl,
                             StmtList asl, Exp ae)
    {
        super(pos, as, afl, asl);
        rtnType=at;
        rtnExp=ae;
    }

    public String name() {return "MethodDeclNonVoid";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof MethodDeclNonVoid)) return null;
        return v.visit(this, (MethodDeclNonVoid)n);
    }
}
