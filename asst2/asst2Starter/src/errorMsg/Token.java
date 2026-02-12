package errorMsg;

/**
 * A class to represent a token during lexical analysis.
 * This allows us to print out token in the order they are in the file
 */
public class Token extends CompMessage
{
    public Token(String m)
    {
        super(m);
    }

    public String toString()
    {
        return line + "." + chr + ": " + message;
    }
}
