package visitor;

import syntaxtree.*;
import java.util.*;

public class ASTGenVisitor extends Visitor
{

    private Vector<Vector<String>> lists;
    int v;

    public ASTGenVisitor()
    {
        v = 0;
        lists = new Vector<Vector<String>>();
    }

    public Object visit(Program n)
    {
        String p = "new Program("+n.pos+","+ n.classDecls.accept(this)+")";
        String out = "import syntaxtree.*;\n";
        out += "import visitor.*;\n";
        out += "public class Test\n";
        out += "{\n";
        out += "    public static void main(String[] args)\n";
        out += "    {\n";
        for(List<String> list : lists)
        {
            for(String stmt : list)
            {
                out += "        "+stmt+"\n";
            }
        }
        out += "        AstNode ast = "+p+";\n";
        out += "    }\n";
        out += "}\n";
        return out;
    }


    public Object visitList(int x, String type, AstList lst)
    {
        Vector<String> lines = new Vector<String>();
        lines.add(type+" v"+x+" = new "+type+"();");
        for(Object o : lst)
        {
            lines.add("v"+x+".add("+((AstNode)o).accept(this)+");");
        }
        lists.add(lines);
        return "v"+x;
    }
    public Object visit(ClassDeclList n)
    {
        return visitList(v++, "ClassDeclList", n);
    }
    public Object visit(DeclList n)
    {
        return visitList(v++, "DeclList", n);
    }
    public Object visit(ExpList n)
    {
        return visitList(v++, "ExpList", n);
    }
    public Object visit(StmtList n)
    {
        return visitList(v++, "StmtList", n);
    }
    public Object visit(VarDeclList n)
    {
        return visitList(v++, "VarDeclList", n);
    }


    public Object visit(ArrayLookup n)
    {
        return "new ArrayLookup("+n.pos+","+
               n.arrExp.accept(this)+","+
               n.idxExp.accept(this)+")";
    }

    public Object visit(ArrayType n)
    {
        return "new ArrayType("+n.pos+","+
               n.baseType.accept(this)+")";
    }

    public Object visit(Assign n)
    {
        return "new Assign("+n.pos+","+
               n.lhs.accept(this)+","+
               n.rhs.accept(this)+")";
    }

    public Object visit(And n)
    {
        return "new And("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(Equals n)
    {
        return "new Equals("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(GreaterThan n)
    {
        return "new GreaterThan("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(LessThan n)
    {
        return "new LessThan("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(Minus n)
    {
        return "new Minus("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(Or n)
    {
        return "new Or("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(Plus n)
    {
        return "new Plus("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(Times n)
    {
        return "new Times("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(Divide n)
    {
        return "new Divide("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }
    public Object visit(Remainder n)
    {
        return "new Remainder("+n.pos+","+
               n.left.accept(this)+","+
               n.right.accept(this)+")";
    }

    public Object visit(Block n)
    {
        return "new Block("+n.pos+","+n.stmts.accept(this)+")";
    }

    public Object visit(Cast n)
    {
        return "new Cast("+n.pos+","+
               n.castType.accept(this)+","+
               n.exp.accept(this)+")";
    }

    public Object visit(Call n)
    {
        return "new Call("+n.pos+","+
               n.obj.accept(this)+","+
               "\""+n.methName+"\","+
               n.args.accept(this)+")";
    }

    public Object visit(Case n)
    {
        return "new Case("+n.pos+","+
               n.exp.accept(this)+")";
    }

    public Object visit(ClassDecl n)
    {
        return "new ClassDecl("+n.pos+","+
               "\""+n.name+"\","+
               "\""+n.superName+"\","+
               n.decls.accept(this)+")";
    }

    public Object visit(CallStmt n)
    {
        return "new CallStmt("+n.pos+","+
               n.callExp.accept(this)+")";
    }

    public Object visit(If n)
    {
        return "new If("+n.pos+","+
               n.exp.accept(this)+","+
               n.trueStmt.accept(this)+","+
               n.falseStmt.accept(this)+")";
    }

    public Object visit(InstanceOf n)
    {
        return "new InstanceOf("+n.pos+","+
               n.exp.accept(this)+","+
               n.checkType.accept(this)+")";
    }


    public Object visit(FieldAccess n)
    {
        return "new FieldAccess("+n.pos+","+
               n.exp.accept(this)+","+
               "\""+n.varName+"\")";
    }

    public Object visit(LocalDeclStmt n)
    {
        return "new LocalDeclStmt("+n.pos+","+
               n.localVarDecl.accept(this)+")";
    }


    public Object visit(LocalVarDecl n)
    {
        return "new LocalVarDecl("+n.pos+","+
               ""+ n.type.accept(this)+","+
               "\""+ n.name+"\","+
               n.initExp.accept(this)+")";
    }

    public Object visit(MethodDeclVoid n)
    {
        return "new MethodDeclVoid("+n.pos+","+
               "\""+n.name+"\","+
               n.params.accept(this)+","+
               n.stmts.accept(this)+")";
    }

    public Object visit(MethodDeclNonVoid n)
    {
        return "new MethodDeclNonVoid("+n.pos+","+
               n.rtnType.accept(this)+","+
               "\""+n.name+"\","+
               n.params.accept(this)+","+
               n.stmts.accept(this)+","+
               n.rtnExp.accept(this)+")";
    }

    public Object visit(NewArray n)
    {
        return "new NewArray("+n.pos+","+
               n.objType.accept(this)+","+
               n.sizeExp.accept(this)+")";
    }

    public Object visit(NewObject n)
    {
        return "new NewObject("+n.pos+","+
               n.objType.accept(this)+")";
    }

    public Object visit(Switch n)
    {
        return "new Switch("+n.pos+","+
               n.exp.accept(this)+","+
               n.stmts.accept(this)+")";
    }

    public Object visit(ParamDecl n)
    {
        return "new ParamDecl("+n.pos+","+
               n.type.accept(this)+","+
               "\""+n.name+"\")";
    }
    public Object visit(FieldDecl n)
    {
        return "new FieldDecl("+n.pos+","+
               n.type.accept(this)+","+
               "\""+n.name+"\")";
    }
    public Object visit(Not n)
    {
        return "new Not("+n.pos+","+
               n.exp.accept(this)+")";
    }
    public Object visit(ArrayLength n)
    {
        return "new ArrayLength("+n.pos+","+
               n.exp.accept(this)+")";
    }

    public Object visit(While n)
    {
        return "new While("+n.pos+","+
               n.exp.accept(this)+","+
               n.body.accept(this)+")";
    }

    public Object visit(BoolType n)
    {
        return "new BoolType("+n.pos+")";
    }

    public Object visit(Break n)
    {
        return "new Break("+n.pos+")";
    }

    public Object visit(Default n)
    {
        return "new Default("+n.pos+")";
    }

    public Object visit(False n)
    {
        return "new False("+n.pos+")";
    }

    public Object visit(IDExp n)
    {
        return "new IDExp("+n.pos+",\""+n.name+"\")";
    }

    public Object visit(IDType n)
    {
        return "new IDType("+n.pos+",\""+n.name+"\")";
    }

    public Object visit(IntLit n)
    {
        return "new IntLit("+n.pos+","+n.val+")";
    }

    public Object visit(IntType n)
    {
        return "new IntType("+n.pos+")";
    }

    public Object visit(Null n)
    {
        return "new Null("+n.pos+")";
    }

    public Object visit(NullType n)
    {
        return "new NullType("+n.pos+")";
    }


    public Object visit(StringLit n)
    {
        return "new StringLit("+n.pos+",\""+n.str+"\")";
    }

    public Object visit(Super n)
    {
        return "new Super("+n.pos+")";
    }

    public Object visit(This n)
    {
        return "new This("+n.pos+")";
    }

    public Object visit(True n)
    {
        return "new True("+n.pos+")";
    }

    public Object visit(VoidType n)
    {
        return "new VoidType("+n.pos+")";
    }
}
