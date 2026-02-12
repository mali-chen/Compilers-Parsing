package syntaxtree;

import java.util.List;
import java.util.Vector;

import visitor.Visitor;
import visitor.Visitor2;

/**
 * a list of a given AST node type (abstract)
 * @param <T> the base type of the list
 */
public abstract class AstList<T> extends Vector<T>
{

    /**
     * constructor -- initializes to an empty list
     */
    public AstList()
    {
        super();
    }

    /**
     * constructor -- initializes with values pass in parameter
     * @
     * @param lst the list's initial elements
     */
    public AstList(List<T> lst)
    {
        this();
        this.addAll(lst);
    }

    /**
     * adds an element to the end of the list
     * @param n the element to add
     */
    public void addElement(T n)
    {
        super.addElement(n);
    }

    /**
     * adds an element to the front of the list
     * @param n the element to add
     */
    public void addElementAtFront(T n)
    {
        super.insertElementAt(n, 0);
    }

    public AstList append(T e)
    {
        addElement(e);
        return this;
    }
    public AstList prepend(T e)
    {
        addElementAtFront(e);
        return this;
    }

    public abstract String name();

    public abstract Object accept(Visitor v);
    public abstract Object accept(Visitor2 v, AstList<T> n);

}
