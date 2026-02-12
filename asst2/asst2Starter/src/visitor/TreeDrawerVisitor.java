package visitor;

import java.io.PrintStream;
import syntaxtree.*;

public class TreeDrawerVisitor extends Visitor
{
    private PrintStream ps;
    private int indent;

    public TreeDrawerVisitor(PrintStream ps)
    {
        this.ps = ps;
        indent = 0;
    }


    private void write(String node, AstNode n)
    {
        ps.println(" ".repeat(indent) + node + "#"+n.uniqueId);
    }
    private void write(String node, AstNode n, AstNode l)
    {
        if(l != null)
            ps.println(" ".repeat(indent) + node + "#"+n.uniqueId + ";@" + l.uniqueId);
        else
            ps.println(" ".repeat(indent) + node + "#"+n.uniqueId);
    }

    private void run(AstNode n)
    {
        indent++;
        if(n == null)
        {
            ps.println(" ".repeat(indent) + "??null??");
        }
        else
        {
            n.accept(this);
        }
        indent--;
    }
    private void run(AstList lst)
    {
        indent++;
        if(lst == null)
        {
            ps.println(" ".repeat(indent) + "??null??");
        }
        else
        {
            ps.println(" ".repeat(indent) + "*====list====*");
            for(Object obj : lst)
            {
                run((AstNode)obj);
            }
        }
        indent--;
    }


    public Object visit(Program n)
    {
        write("Program",n);
        run(n.classDecls);
        run(n.mainStmt);
        return null;
    }

    ////////////////////////////////////////////////////
    // Decls
    ////////////////////////////////////////////////////

    public Object visit(ClassDecl n)
    {
        write("ClassDecl["+n.name+","+n.superName+"]",n, n.superLink);
        run(n.decls);
        return null;
    }


    public Object visit(MethodDeclVoid n)
    {
        write("MethodDeclVoid["+n.name+"]",n, n.superMethod);
        run(n.params);
        run(n.stmts);
        return null;
    }

    public Object visit(MethodDeclNonVoid n)
    {
        write("MethodDeclNonVoid["+n.name+"]",n, n.superMethod);
        run(n.rtnType);
        run(n.params);
        run(n.stmts);
        run(n.rtnExp);
        return null;
    }


    public Object visit(FieldDecl n)
    {
        write("FieldDecl["+n.name+"]",n);
        return null;
    }

    public Object visit(ParamDecl n)
    {
        write("ParamDecl["+n.name+"]",n);
        run(n.type);
        return null;
    }

    public Object visit(LocalVarDecl n)
    {
        write("LocalVarDecl["+n.name+"]",n);
        run(n.type);
        run(n.initExp);
        return null;
    }

    ////////////////////////////////////////////////////
    // Stmts
    ////////////////////////////////////////////////////

    public Object visit(Assign n)
    {
        write("Assign",n);
        run(n.lhs);
        run(n.rhs);
        return null;
    }

    public Object visit(Block n)
    {
        write("Block",n);
        run(n.stmts);
        return null;
    }

    public Object visit(CallStmt n)
    {
        write("CallStmt",n);
        run(n.callExp);
        return null;
    }

    public Object visit(LocalDeclStmt n)
    {
        write("LocalDeclStmt",n);
        run(n.localVarDecl);
        return null;
    }

    public Object visit(If n)
    {
        write("If",n);
        run(n.exp);
        run(n.trueStmt);
        run(n.falseStmt);
        return null;
    }

    public Object visit(While n)
    {
        write("While",n);
        run(n.exp);
        run(n.body);
        return null;
    }

    public Object visit(Break n)
    {
        write("Break",n,n.breakLink);
        return null;
    }

    public Object visit(Switch n)
    {
        write("Switch",n);
        run(n.exp);
        run(n.stmts);
        return null;
    }

    public Object visit(Case n)
    {
        write("Case",n, n.enclosingSwitch);
        run(n.exp);
        return null;
    }

    public Object visit(Default n)
    {
        write("Default",n, n.enclosingSwitch);
        return null;
    }

    ////////////////////////////////////////////////////
    // Expressions
    ////////////////////////////////////////////////////


    public Object visit(ArrayLookup n)
    {
        write("ArrayLookup",n);
        run(n.arrExp);
        run(n.idxExp);
        return null;
    }

    public Object visit(Cast n)
    {
        write("Cast",n);
        run(n.castType);
        run(n.exp);
        return null;
    }

    public Object visit(Call n)
    {
        write("Call["+n.methName+"]",n,n.methodLink);
        run(n.args);
        run(n.obj);
        return null;
    }

    public Object visit(FieldAccess n)
    {
        write("FieldAccess["+n.varName+"]",n, n.varDec);
        run(n.exp);
        return null;
    }

    public Object visit(InstanceOf n)
    {
        write("InstanceOf",n);
        run(n.exp);
        run(n.checkType);
        return null;
    }

    public Object visit(NewArray n)
    {
        write("NewArray",n);
        run(n.objType);
        run(n.sizeExp);
        return null;
    }

    public Object visit(NewObject n)
    {
        write("NewObject",n);
        run(n.objType);
        return null;
    }


    ////////////////////////////////////////////////////
    // Unary Expressions
    ////////////////////////////////////////////////////

    public Object visit(UnExp n)
    {
        run(n.exp);
        return null;
    }

    public Object visit(Not n)
    {
        write("Not",n);
        return visit((UnExp)n);
    }
    public Object visit(ArrayLength n)
    {
        write("ArrayLength",n);
        return visit((UnExp)n);
    }

    ////////////////////////////////////////////////////
    // Binary Expressions
    ////////////////////////////////////////////////////

    public Object visit(BinExp n)
    {
        run(n.left);
        run(n.right);
        return null;
    }

    public Object visit(And n)
    {
        write("And",n);
        return visit((BinExp)n);
    }
    public Object visit(Equals n)
    {
        write("Equals",n);
        return visit((BinExp)n);
    }
    public Object visit(LessThan n)
    {
        write("LessThan",n);
        return visit((BinExp)n);
    }
    public Object visit(GreaterThan n)
    {
        write("GreaterThan",n);
        return visit((BinExp)n);
    }
    public Object visit(Minus n)
    {
        write("Minus",n);
        return visit((BinExp)n);
    }
    public Object visit(Or n)
    {
        write("Or",n);
        return visit((BinExp)n);
    }
    public Object visit(Plus n)
    {
        write("Plus",n);
        return visit((BinExp)n);
    }
    public Object visit(Times n)
    {
        write("Times",n);
        return visit((BinExp)n);
    }
    public Object visit(Divide n)
    {
        write("Divide",n);
        return visit((BinExp)n);
    }
    public Object visit(Remainder n)
    {
        write("Remainder",n);
        return visit((BinExp)n);
    }

    ////////////////////////////////////////////////////
    // Leaf Expressions
    ////////////////////////////////////////////////////

    public Object visit(False n)
    {
        write("False",n);
        return null;
    }
    public Object visit(Null n)
    {
        write("Null",n);
        return null;
    }
    public Object visit(Super n)
    {
        write("Super",n);
        return null;
    }
    public Object visit(This n)
    {
        write("This",n);
        return null;
    }
    public Object visit(True n)
    {
        write("True",n);
        return null;
    }
    public Object visit(IDExp n)
    {
        write("IDExp["+n.name+"]",n,n.link);
        return null;
    }
    public Object visit(IntLit n)
    {
        write("IntLit["+n.val+"]",n);
        return null;
    }
    public Object visit(StringLit n)
    {
        String str = "";
        for(int i = 0; i < n.str.length(); i++)
        {
            char c = n.str.charAt(i);
            switch(c)
            {
                case '\\': str += "\\\\"; break;
                case '\"': str += "\\\""; break;
                case '\n': str += "\\n";  break;
                case '\t': str += "\\t";  break;
                case '\r': str += "\\r";  break;
                case '\f': str += "\\f";  break;
                default:   str += c;      break;
            }
        }
        write("StringLit["+str+"]", n, n.uniqueCgRep);
        return null;
    }

    ////////////////////////////////////////////////////
    // Types
    ////////////////////////////////////////////////////

    public Object visit(ArrayType n)
    {
        write("ArrayType",n);
        run(n.baseType);
        return null;
    }

    public Object visit(BoolType n)
    {
        write("BooleanType",n);
        return null;
    }
    public Object visit(IntType n)
    {
        write("IntType",n);
        return null;
    }
    public Object visit(IDType n)
    {
        write("IDType["+n.name+"]",n, n.link);
        return null;
    }
    public Object visit(VoidType n)
    {
        write("VoidType",n);
        return null;
    }
    public Object visit(NullType n)
    {
        write("NullType",n);
        return null;
    }
    public Object visit(ErrorType n)
    {
        write("ErrorType",n);
        return null;
    }

}
