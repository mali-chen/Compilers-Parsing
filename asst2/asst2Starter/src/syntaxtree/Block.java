package syntaxtree;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a compound statement (block): a list of statements enclosed in curly braces
 */
public class Block extends Stmt
{

    // instance variables filled in by constructor
    public StmtList stmts; // the list of the block's statements

    /**
     * constructor
     * @param pos file position
     * @param astmts the block's list of statements
     */
    public Block(int pos, StmtList astmts)
    {
        super(pos);
        stmts = astmts;
    }

    public String name() {return "Block";}

    public Object accept(Visitor v)
    {
        return v.visit(this);
    }

    public Object accept(Visitor2 v, AstNode n)
    {
        if(!(n instanceof Block)) return null;
        return v.visit(this, (Block)n);
    }
}

