package visitor;

import syntaxtree.*;

public class Visitor
{
    ////////////////////////////////////////////////////
    // Abstract nodes
    ////////////////////////////////////////////////////

    public Object visit(AstNode n)     { return null; }
    public Object visit(Decl n)        { return null; }
    public Object visit(Stmt n)        { return null; }
    public Object visit(Exp n)         { return null; }
    public Object visit(Type n)        { return null; }

    public Object visit(Label n)       { return null; }
    public Object visit(BreakTarget n) { return null; }


    ////////////////////////////////////////////////////
    // Program
    ////////////////////////////////////////////////////

    public Object visit(Program n)
    {
        n.classDecls.accept(this);
        n.mainStmt.accept(this);
        return null;
    }

    ////////////////////////////////////////////////////
    // Decls
    ////////////////////////////////////////////////////

    public Object visit(ClassDecl n)
    {
        n.decls.accept(this);
        return null;
    }

    public Object visit(MethodDecl n)
    {
        n.params.accept(this);
        n.stmts.accept(this);
        return null;
    }

    public Object visit(MethodDeclVoid n)
    {
        return visit((MethodDecl)n);
    }

    public Object visit(MethodDeclNonVoid n)
    {
        n.rtnType.accept(this);
        visit((MethodDecl)n);
        n.rtnExp.accept(this);
        return null;
    }

    public Object visit(VarDecl n)
    {
        n.type.accept(this);
        return null;
    }

    public Object visit(FieldDecl n)
    {
        return visit((VarDecl)n);
    }

    public Object visit(ParamDecl n)
    {
        return visit((VarDecl)n);
    }

    public Object visit(LocalVarDecl n)
    {
        n.initExp.accept(this);
        return visit((VarDecl)n);
    }

    ////////////////////////////////////////////////////
    // Stmts
    ////////////////////////////////////////////////////

    public Object visit(Assign n)
    {
        n.lhs.accept(this);
        n.rhs.accept(this);
        return null;
    }

    public Object visit(Block n)
    {
        n.stmts.accept(this);
        return null;
    }

    public Object visit(CallStmt n)
    {
        n.callExp.accept(this);
        return null;
    }

    public Object visit(LocalDeclStmt n)
    {
        n.localVarDecl.accept(this);
        return null;
    }

    public Object visit(If n)
    {
        n.exp.accept(this);
        n.trueStmt.accept(this);
        n.falseStmt.accept(this);
        return null;
    }

    public Object visit(While n)
    {
        n.exp.accept(this);
        n.body.accept(this);
        return null;
    }

    public Object visit(Break n)
    {
        return null;
    }

    public Object visit(Switch n)
    {
        n.exp.accept(this);
        n.stmts.accept(this);
        return null;
    }

    public Object visit(Case n)
    {
        n.exp.accept(this);
        return null;
    }

    public Object visit(Default n)
    {
        return null;
    }

    ////////////////////////////////////////////////////
    // Expressions
    ////////////////////////////////////////////////////


    public Object visit(ArrayLookup n)
    {
        n.arrExp.accept(this);
        n.idxExp.accept(this);
        return null;
    }

    public Object visit(Cast n)
    {
        n.castType.accept(this);
        n.exp.accept(this);
        return null;
    }

    public Object visit(Call n)
    {
        n.args.accept(this);
        n.obj.accept(this);
        return null;
    }

    public Object visit(FieldAccess n)
    {
        n.exp.accept(this);
        return null;
    }

    public Object visit(InstanceOf n)
    {
        n.exp.accept(this);
        n.checkType.accept(this);
        return null;
    }

    public Object visit(NewArray n)
    {
        n.objType.accept(this);
        n.sizeExp.accept(this);
        return null;
    }

    public Object visit(NewObject n)
    {
        n.objType.accept(this);
        return null;
    }


    ////////////////////////////////////////////////////
    // Unary Expressions
    ////////////////////////////////////////////////////

    public Object visit(UnExp n)
    {
        n.exp.accept(this);
        return null;
    }

    public Object visit(Not n)         { return visit((UnExp)n); }
    public Object visit(ArrayLength n) { return visit((UnExp)n); }

    ////////////////////////////////////////////////////
    // Binary Expressions
    ////////////////////////////////////////////////////

    public Object visit(BinExp n)
    {
        n.left.accept(this);
        n.right.accept(this);
        return null;
    }

    public Object visit(And n)         { return visit((BinExp)n); }
    public Object visit(Equals n)      { return visit((BinExp)n); }
    public Object visit(LessThan n)    { return visit((BinExp)n); }
    public Object visit(GreaterThan n) { return visit((BinExp)n); }
    public Object visit(Minus n)       { return visit((BinExp)n); }
    public Object visit(Or n)          { return visit((BinExp)n); }
    public Object visit(Plus n)        { return visit((BinExp)n); }
    public Object visit(Times n)       { return visit((BinExp)n); }
    public Object visit(Divide n)      { return visit((BinExp)n); }
    public Object visit(Remainder n)   { return visit((BinExp)n); }

    ////////////////////////////////////////////////////
    // Leaf Expressions
    ////////////////////////////////////////////////////

    public Object visit(False n)     { return null; }
    public Object visit(Null n)      { return null; }
    public Object visit(Super n)     { return null; }
    public Object visit(This n)      { return null; }
    public Object visit(True n)      { return null; }
    public Object visit(IDExp n)     { return null; }
    public Object visit(IntLit n)    { return null; }
    public Object visit(StringLit n) { return null; }

    ////////////////////////////////////////////////////
    // Types
    ////////////////////////////////////////////////////

    public Object visit(ArrayType n)
    {
        n.baseType.accept(this);
        return null;
    }

    public Object visit(BoolType n)  { return null; }
    public Object visit(IntType n)   { return null; }
    public Object visit(IDType n)    { return null; }
    public Object visit(VoidType n)  { return null; }
    public Object visit(NullType n)  { return null; }
    public Object visit(ErrorType n) { return null; }

    ////////////////////////////////////////////////////
    // Lists
    ////////////////////////////////////////////////////

    public Object visit(AstList lst)
    {
        for(Object obj : lst)
        {
            if(obj != null && obj instanceof AstNode)
            {
                ((AstNode)obj).accept(this);
            }
        }
        return null;
    }

    public Object visit(ClassDeclList n) { return visit((AstList)n); }
    public Object visit(ExpList n)       { return visit((AstList)n); }
    public Object visit(DeclList n)      { return visit((AstList)n); }
    public Object visit(StmtList n)      { return visit((AstList)n); }
    public Object visit(VarDeclList n)   { return visit((AstList)n); }
}
