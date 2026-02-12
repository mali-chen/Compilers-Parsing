package main;

import syntaxtree.*;
import visitor.*;

public class ASTPractice
{
    public static void main(String[] args)
    {
        /////////////////////////////
        // Make the code
        /////////////////////////////
        StmtList stmts = new StmtList();
        stmts.append(new LocalDeclStmt(0, 
                      new LocalVarDecl(0, new IntType(0), "x", new IntLit(0,5))));

        /////////////////////////////
        // create Methods
        /////////////////////////////
        DeclList decls = new DeclList();
        decls.append(new MethodDeclVoid(0, "main", new VarDeclList(), stmts));

        /////////////////////////////
        // create Classes
        /////////////////////////////
        ClassDeclList classes = new ClassDeclList();
        classes.append(new ClassDecl(0, "Test", "Object", decls));

        AstNode ast = new Program(0, classes);
        ast.accept(new PrettyPrintVisitor(System.out));
        ast.accept(new TreeDrawerVisitor(System.out));
    }
}
