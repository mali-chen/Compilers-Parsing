package errorMsg;

/**
 * Represents a warning message in the compiler.
 * Use the static methods to create a warning.
 */
public class CompWarning extends CompMessage
{
    private CompWarning(String m)
    {
        super(m);
    }

    public String toString()
    {
        return file + "::" + line + "." + chr + "(warning): " + message;
    }

    ////////////////////////////////////////////////////
    // Lexical Analysis
    ////////////////////////////////////////////////////
    
    /**
     * A warning if there is a comment in comment. <br/>
     * Example: <br/>
     * {@literal /} {@literal*} This is a
     * {@literal /} {@literal *} nested comment {@literal *} {@literal/}
     */
    public static CompWarning CommentInCommentWarning()
    {
        return new CompWarning("found /* inside multi-line comment");
    }

}
