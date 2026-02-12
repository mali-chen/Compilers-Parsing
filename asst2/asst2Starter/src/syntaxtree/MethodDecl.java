package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a method declaration (abstract)
 */
public abstract class MethodDecl extends Decl
{
    // instance variables filled in by constructor
    public VarDeclList params; // the method's formal parameters
    public StmtList stmts; // the method's body

    // instance variables filled in during later phases
    public MethodDecl superMethod; // the method that this method is overriding, if any
    public int paramSize; // the total size of the parameters for the method.
    public int vtableOffset; // this method's position in the v-table
    public ClassDecl classDecl; // the class in which the method is declared

    /**
     * constructor
     * @param pos file position
     * @param aname the name being declared
     * @param aformals the list of formal parameter declarations
     * @param astmts the statements that are the method's body
     */
    public MethodDecl(int pos, String aname, VarDeclList aparams, StmtList astmts)
    {
        super(pos,aname);
        params=aparams;
        stmts=astmts;
        superMethod = null;
        vtableOffset = Integer.MIN_VALUE;
    }

    public String name() {return "MethodDecl";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof MethodDecl)) return null;
        return v.visit(this, (MethodDecl)n);
    }
}
