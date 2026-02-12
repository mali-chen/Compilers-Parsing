package errorMsg;

/**
 * A class to represent a message in the compiler.
 * Each mesage contains a position, message string, and file.
 * The point is that we can sort all of the messages
 * based on their position in the source file.
 * This make more uniform output that can be more easily tested for.
 */
public class CompMessage implements Comparable<CompMessage>
{
    // protected sucks in Java, so these are effectively public.
    // buuuut... ya know, appearances.
    protected String message;
    protected int line;
    protected int chr;
    protected String file;

    public CompMessage(String m)
    {
        message = m;
    }

    /**
     * compare message based on their position in the file.
     */
    public int compareTo(CompMessage m)
    {
        // <
        if(line < m.line || line == m.line && chr < m.chr)
        {
            return -1;
        }
        // ==
        if(line == m.line && chr == m.chr)
        {
            return 0;
        }
        // >
        return 1;
    }

    public String toString()
    {
        return message;
    }
}
