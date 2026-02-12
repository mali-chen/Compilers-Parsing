package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a MiniJava program
 */
public class Program extends AstNode
{

    // instance variables filled in by constructor
    public ClassDeclList classDecls; // the list of class declarations
    public Stmt mainStmt; // the (call) statement that starts the execution
    public ClassDeclList predefinedDecls; // for the predefined classes

    /**
     * constructor
     * @param pos file position
     * @param acl the program's list of class declarations
     */
    public Program(int pos, ClassDeclList acl)
    {
        super(pos);
        IDType mainType = new IDType(-1, "Main");
        Exp newExp = new NewObject(-1, mainType);
        Call callExp = new Call(-1, newExp, "main", new ExpList());
        mainStmt = new CallStmt(-1, callExp);
        classDecls=acl;
        predefinedDecls = new ClassDeclList();
    }

    public void setPredefined(ClassDeclList nodes)
    {
        predefinedDecls = nodes;
    }

    public String name() {return "Program";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Program)) return null;
        return v.visit(this, (Program)n);
    }
}
