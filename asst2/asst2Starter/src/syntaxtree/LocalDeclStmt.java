package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a statement that consists of a local variable declaration
 */
public class LocalDeclStmt extends Stmt
{

    // instance variables filled in by constructor
    public LocalVarDecl localVarDecl; // the actual declaration

    /**
     * constructor
     * @param pos file position
     * @param decl the local variable declaration
     */
    public LocalDeclStmt(int pos, LocalVarDecl decl)
    {
        super(pos);
        localVarDecl = decl;
    }

    public String name() {return "LocalDeclStmt";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof LocalDeclStmt)) return null;
        return v.visit(this, (LocalDeclStmt)n);
    }
}

