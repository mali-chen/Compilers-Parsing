package syntaxtree;

import java.util.HashMap;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a class declaration
 */
public class ClassDecl extends Decl
{
    // superclass name
    public String superName;
    // list of declarations
    public DeclList decls;

    // These fields are left null until we do static analysis
    // We can't know the information at compile time.

    // Filled in by phase 1
    // environment containing all fields used in this class.
    public HashMap<String,FieldDecl> fieldEnv;
    // environment containing all methods used in this class.
    public HashMap<String,MethodDecl> methodEnv;

    // Filled in by phase 2
    // pointer to the actual superclass object.
    public ClassDecl superLink;
    // pointers to all subclasses
    public ClassDeclList subclasses;

    // Filled in by phase 5 (the code generator)
    // number of primitive fields
    public int numDataFields;
    // number of object fields
    public int numObjFields;

    /**
     * constructor
     * @param pos file position
     * @param aname the name being declared
     * @param asuperName the name of the superclass
     * @param aDeclList the list of declarations inside the class
     */
    public ClassDecl(int pos, String aname, String asuperName,
                     DeclList aDeclList)
    {
        super(pos, aname);
        superName=asuperName;
        decls = aDeclList;
        superLink = null;
        subclasses = new ClassDeclList();
        fieldEnv = new HashMap<String,FieldDecl>();
        methodEnv = new HashMap<String,MethodDecl>();
    }

    public String name() {return "ClassDecl";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }
    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof ClassDecl)) return null;
        return v.visit(this, (ClassDecl)n);
    }
}
