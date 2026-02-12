package visitor;

import syntaxtree.*;

public class Visitor2
{
    ////////////////////////////////////////////////////
    // Abstract nodes
    ////////////////////////////////////////////////////

    public Object visit(AstNode a1, AstNode a2) { return null; }
    public Object visit(Decl d1, Decl d2)       { return null; }
    public Object visit(Stmt s1, Stmt s2)       { return null; }
    public Object visit(Exp e1, Exp e2)         { return null; }
    public Object visit(Type t1, Type t2)       { return null; }

    public Object visit(Label l1, Label l2)             { return null; }
    public Object visit(BreakTarget b1, BreakTarget b2) { return null; }

    ////////////////////////////////////////////////////
    // Program
    ////////////////////////////////////////////////////

    public Object visit(Program p1, Program p2)
    {
        p1.classDecls.accept(this, p2.classDecls);
        p1.mainStmt.accept(this, p2.mainStmt);
        return null;
    }

    ////////////////////////////////////////////////////
    // Decls
    ////////////////////////////////////////////////////

    public Object visit(ClassDecl c1, ClassDecl c2)
    {
        c1.decls.accept(this, c2.decls);
        return null;
    }

    public Object visit(MethodDecl m1, MethodDecl m2)
    {
        m1.params.accept(this, m2.params);
        m1.stmts.accept(this, m2.stmts);
        return null;
    }

    public Object visit(MethodDeclVoid m1, MethodDeclVoid m2)
    {
        return visit((MethodDecl)m1, (MethodDecl)m2);
    }

    public Object visit(MethodDeclNonVoid m1, MethodDeclNonVoid m2)
    {
        m1.rtnType.accept(this, m2.rtnType);
        visit((MethodDecl)m1, (MethodDecl)m2);
        m1.rtnExp.accept(this, m2.rtnExp);
        return null;
    }

    public Object visit(VarDecl v1, VarDecl v2)
    {
        v1.type.accept(this, v2.type);
        return null;
    }

    public Object visit(FieldDecl v1, FieldDecl v2)
    {
        return visit((VarDecl)v1, (VarDecl)v2);
    }

    public Object visit(ParamDecl p1, ParamDecl p2)
    {
        return visit((VarDecl)p1, (VarDecl)p2);
    }

    public Object visit(LocalVarDecl v1, LocalVarDecl v2)
    {
        v1.initExp.accept(this, v2.initExp);
        return visit((VarDecl)v1, (VarDecl)v2);
    }

    ////////////////////////////////////////////////////
    // Stmts
    ////////////////////////////////////////////////////

    public Object visit(Assign a1, Assign a2)
    {
        a1.lhs.accept(this, a2.lhs);
        a1.rhs.accept(this, a2.rhs);
        return null;
    }

    public Object visit(Block b1, Block b2)
    {
        b1.stmts.accept(this, b2.stmts);
        return null;
    }

    public Object visit(CallStmt c1, CallStmt c2)
    {
        c1.callExp.accept(this, c2.callExp);
        return null;
    }

    public Object visit(LocalDeclStmt v1, LocalDeclStmt v2)
    {
        v1.localVarDecl.accept(this, v2.localVarDecl);
        return null;
    }

    public Object visit(If i1, If i2)
    {
        i1.exp.accept(this, i2.exp);
        i1.trueStmt.accept(this, i2.trueStmt);
        i1.falseStmt.accept(this, i2.falseStmt);
        return null;
    }

    public Object visit(While w1, While w2)
    {
        w1.exp.accept(this, w2.exp);
        w1.body.accept(this, w2.body);
        return null;
    }

    public Object visit(Break b1, Break b2)
    {
        return null;
    }

    public Object visit(Switch s1, Switch s2)
    {
        s1.exp.accept(this, s2.exp);
        s1.stmts.accept(this, s2.stmts);
        return null;
    }

    public Object visit(Case c1, Case c2)
    {
        c1.exp.accept(this, c2.exp);
        return null;
    }

    public Object visit(Default d1, Default d2)
    {
        return null;
    }


    ////////////////////////////////////////////////////
    // Expressions
    ////////////////////////////////////////////////////


    public Object visit(ArrayLookup a1, ArrayLookup a2)
    {
        a1.arrExp.accept(this, a2.arrExp);
        a1.idxExp.accept(this, a2.idxExp);
        return null;
    }

    public Object visit(Cast c1, Cast c2)
    {
        c1.castType.accept(this, c2.castType);
        c1.exp.accept(this, c2.exp);
        return null;
    }

    public Object visit(Call c1, Call c2)
    {
        c1.obj.accept(this, c2.obj);
        c1.args.accept(this, c2.args);
        return null;
    }

    public Object visit(FieldAccess a1, FieldAccess a2)
    {
        a1.exp.accept(this, a2.exp);
        return null;
    }

    public Object visit(InstanceOf i1, InstanceOf i2)
    {
        i1.exp.accept(this, i2.exp);
        i1.checkType.accept(this, i2.checkType);
        return null;
    }

    public Object visit(NewArray n1, NewArray n2)
    {
        n1.objType.accept(this, n2.objType);
        n1.sizeExp.accept(this, n2.sizeExp);
        return null;
    }

    public Object visit(NewObject n1, NewObject n2)
    {
        n1.objType.accept(this, n2.objType);
        return null;
    }


    ////////////////////////////////////////////////////
    // Unary Expressions
    ////////////////////////////////////////////////////

    public Object visit(UnExp u1, UnExp u2)
    {
        u1.exp.accept(this, u2.exp);
        return null;
    }

    public Object visit(Not u1, Not u2)                 { return visit((UnExp)u1, (UnExp)u2); }
    public Object visit(ArrayLength a1, ArrayLength a2) { return visit((UnExp)a1, (UnExp)a2); }

    ////////////////////////////////////////////////////
    // Binary Expressions
    ////////////////////////////////////////////////////

    public Object visit(BinExp b1, BinExp b2)
    {
        b1.left.accept(this, b2.left);
        b1.right.accept(this, b2.right);
        return null;
    }

    public Object visit(And a1, And a2)                 { return visit((BinExp)a1, (BinExp)a2); }
    public Object visit(Equals e1, Equals e2)           { return visit((BinExp)e1, (BinExp)e2); }
    public Object visit(LessThan l1, LessThan l2)       { return visit((BinExp)l1, (BinExp)l2); }
    public Object visit(GreaterThan g1, GreaterThan g2) { return visit((BinExp)g1, (BinExp)g2); }
    public Object visit(Minus m1, Minus m2)             { return visit((BinExp)m1, (BinExp)m2); }
    public Object visit(Or o1, Or o2)                   { return visit((BinExp)o1, (BinExp)o2); }
    public Object visit(Plus p1, Plus p2)               { return visit((BinExp)p1, (BinExp)p2); }
    public Object visit(Times t1, Times t2)             { return visit((BinExp)t1, (BinExp)t2); }
    public Object visit(Divide d1, Divide d2)           { return visit((BinExp)d1, (BinExp)d2); }
    public Object visit(Remainder r1, Remainder r2)     { return visit((BinExp)r1, (BinExp)r2); }

    ////////////////////////////////////////////////////
    // Leaf Expressions
    ////////////////////////////////////////////////////

    public Object visit(False f1, False f2)         { return null; }
    public Object visit(Null n1, Null n2)           { return null; }
    public Object visit(Super s1, Super s2)         { return null; }
    public Object visit(This t1, This t2)           { return null; }
    public Object visit(True t1, True t2)           { return null; }
    public Object visit(IDExp i1, IDExp i2)         { return null; }
    public Object visit(IntLit i1, IntLit i2)       { return null; }
    public Object visit(StringLit s1, StringLit s2) { return null; }

    ////////////////////////////////////////////////////
    // Types
    ////////////////////////////////////////////////////

    public Object visit(ArrayType t1, ArrayType t2)
    {
        t1.baseType.accept(this, t2.baseType);
        return null;
    }

    public Object visit(BoolType t1, BoolType t2)   { return null; }
    public Object visit(IntType t1, IntType t2)     { return null; }
    public Object visit(IDType t1, IDType t2)       { return null; }
    public Object visit(VoidType t1, VoidType t2)   { return null; }
    public Object visit(NullType t1, NullType t2)   { return null; }
    public Object visit(ErrorType t1, ErrorType t2) { return null; }

    ////////////////////////////////////////////////////
    // Lists
    ////////////////////////////////////////////////////

    public Object visit(AstList l1, AstList l2)
    {
        if(l1.size() != l2.size())
        {
            return null;
        }
        for(int i = 0; i < l1.size(); i++)
        {
            Object o1 = l1.get(i);
            Object o2 = l2.get(i);
            if(o1 instanceof AstNode && o2 instanceof AstNode)
            {
                ((AstNode)o1).accept(this, (AstNode)o2);
            }
        }
        return null;
    }

    public Object visit(ClassDeclList c1, ClassDeclList c2) { return visit((AstList)c1, (AstList)c2); }
    public Object visit(ExpList e1, ExpList e2)             { return visit((AstList)e1, (AstList)e2); }
    public Object visit(DeclList d1, DeclList d2)           { return visit((AstList)d1, (AstList)d2); }
    public Object visit(StmtList s1, StmtList s2)           { return visit((AstList)s1, (AstList)s2); }
    public Object visit(VarDeclList v1, VarDeclList v2)     { return visit((AstList)v1, (AstList)v2); }
}
