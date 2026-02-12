package visitor;

import syntaxtree.*;

public class EqualVisitor extends Visitor2
{

    public Boolean equal(AstNode a1, AstNode a2)
    {
        Boolean out = (Boolean)a1.accept(this, a2);
        return out != null && out && a1.pos == a2.pos;
    }

    public Boolean equal(AstList l1, AstList l2)
    {
        if(l2 == null || l1.size() != l2.size())
        {
            return false;
        }
        Boolean good = true;
        for(int i = 0; i < l1.size(); i++)
        {
            Object o1 = l1.get(i);
            Object o2 = l2.get(i);

            if(o1 instanceof AstNode && o2 instanceof AstNode)
            {
                good &= equal((AstNode)o1, (AstNode)o2);
            }
        }
        return good;
    }

    ////////////////////////////////////////////////////
    // Program
    ////////////////////////////////////////////////////

    public Object visit(Program p1, Program p2)
    {
        return equal(p1.classDecls, p2.classDecls);
    }

    ////////////////////////////////////////////////////
    // Decls
    ////////////////////////////////////////////////////

    public Object visit(ClassDecl c1, ClassDecl c2)
    {
        return equal(c1.decls, c2.decls);
    }

    public Object visit(MethodDecl m1, MethodDecl m2)
    {
        return m1.name.equals(m2.name) &&
               equal(m1.params, m2.params) &&
               equal(m1.stmts, m2.stmts);
    }

    public Object visit(MethodDeclNonVoid m1, MethodDeclNonVoid m2)
    {

        return (Boolean)visit((MethodDecl)m1, (MethodDecl)m2) &&
               equal(m1.rtnType, m2.rtnType) &&
               equal(m1.rtnExp, m2.rtnExp);
    }

    public Object visit(VarDecl v1, VarDecl v2)
    {
        return equal(v1.type, v2.type) && v1.name.equals(v2.name);
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
        return (Boolean)visit((VarDecl)v1, (VarDecl)v2) && equal(v1.initExp, v2.initExp);
    }

    ////////////////////////////////////////////////////
    // Statements
    ////////////////////////////////////////////////////

    public Object visit(Assign a1, Assign a2)
    {
        return equal(a1.lhs,a2.lhs) && equal(a1.rhs,a2.rhs);
    }

    public Object visit(Block b1, Block b2)
    {
        return equal(b1.stmts, b2.stmts);
    }

    public Object visit(CallStmt c1, CallStmt c2)
    {
        return equal(c1.callExp, c2.callExp);
    }

    public Object visit(LocalDeclStmt v1, LocalDeclStmt v2)
    {
        return equal(v1.localVarDecl, v2.localVarDecl);
    }

    public Object visit(If i1, If i2)
    {
        return equal(i1.exp, i2.exp) &&
               equal(i1.trueStmt, i2.trueStmt) &&
               equal(i1.falseStmt, i2.falseStmt);
    }

    public Object visit(While w1, While w2)
    {
        return equal(w1.exp, w2.exp) &&
               equal(w1.body, w2.body);
    }

    public Object visit(Break b1, Break b2)
    {
        return true;
    }

    public Object visit(Switch s1, Switch s2)
    {
        return equal(s1.exp, s2.exp) &&
               equal(s1.stmts, s2.stmts);
    }

    public Object visit(Case c1, Case c2)
    {
        return equal(c1.exp, c2.exp);
    }

    public Object visit(Default d1, Default d2)
    {
        return true;
    }


    ////////////////////////////////////////////////////
    // Expressions
    ////////////////////////////////////////////////////


    public Object visit(ArrayLookup a1, ArrayLookup a2)
    {
        return equal(a1.arrExp, a2.arrExp) &&
               equal(a1.idxExp, a2.idxExp);
    }

    public Object visit(Cast c1, Cast c2)
    {
        return equal(c1.castType, c2.castType) &&
               equal(c1.exp, c2.exp);
    }

    public Object visit(Call c1, Call c2)
    {
        return equal(c1.obj, c2.obj) &&
               equal(c1.args, c2.args);
    }

    public Object visit(FieldAccess a1, FieldAccess a2)
    {
        return equal(a1.exp, a2.exp);
    }

    public Object visit(InstanceOf i1, InstanceOf i2)
    {
        return equal(i1.exp, i2.exp) &&
               equal(i1.checkType, i2.checkType);
    }

    public Object visit(NewArray n1, NewArray n2)
    {
        return equal(n1.objType, n2.objType) &&
               equal(n1.sizeExp, n2.sizeExp);
    }

    public Object visit(NewObject n1, NewObject n2)
    {
        return equal(n1.objType, n2.objType);
    }

    public Object visit(UnExp u1, UnExp u2)
    {
        return equal(u1.exp, u2.exp);
    }

    public Object visit(BinExp b1, BinExp b2)
    {
        return equal(b1.left, b2.left) &&
               equal(b1.right, b2.right);
    }

    ////////////////////////////////////////////////////
    // Leaf Expressions
    ////////////////////////////////////////////////////

    public Object visit(False f1, False f2)         { return true; }
    public Object visit(Null n1, Null n2)           { return true; }
    public Object visit(Super s1, Super s2)         { return true; }
    public Object visit(This t1, This t2)           { return true; }
    public Object visit(True t1, True t2)           { return true; }

    public Object visit(IDExp i1, IDExp i2)
    {
        return i1.name.equals(i2.name);
    }
    public Object visit(IntLit i1, IntLit i2)
    {
        return Boolean.valueOf(i1.val == i2.val);
    }
    public Object visit(StringLit s1, StringLit s2)
    {
        return s1.str.equals(s2.str);
    }

    ////////////////////////////////////////////////////
    // Types
    ////////////////////////////////////////////////////

    public Object visit(ArrayType t1, ArrayType t2)
    {
        return equal(t1.baseType, t2.baseType);
    }
    public Object visit(IDType t1, IDType t2)
    {
        return t1.name.equals(t2.name);
    }

    public Object visit(BoolType t1, BoolType t2)   { return true; }
    public Object visit(IntType t1, IntType t2)     { return true; }
    public Object visit(VoidType t1, VoidType t2)   { return true; }
    public Object visit(NullType t1, NullType t2)   { return true; }
    public Object visit(ErrorType t1, ErrorType t2) { return true; }

    ////////////////////////////////////////////////////
    // Lists
    ////////////////////////////////////////////////////

    public Object visit(AstList l1, AstList l2)
    {
        return equal(l1, l2);
    }
}
