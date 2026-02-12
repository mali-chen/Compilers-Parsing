package errorMsg;
import java.util.List;
import syntaxtree.Type;

/**
 * Represent an error in the compiler.
 * Use the static methods below to reate an error.
 * Don't use the CompError constructor.
 */
public class CompError extends CompMessage
{
    /**
     * Create a compilation error object.
     * DON'T USE THIS. I need to make it public for WrangLR.
     */
    public CompError(String m)
    {
        super(m);
    }

    /**
     * Represent an error as a string using the format.
     * {@code file::line.char: error message}
     */
    public String toString()
    {
        return file + "::" + line + "." + chr + ": " + message;
    }

    //////////////////////////////////////////////////////////////////////////////////
    // Lexer Errors
    //
    // Used in TokenGrammar
    //////////////////////////////////////////////////////////////////////////////////

    /**
     * Error for character literal contianing more than one character. <br/>
     * Example: {@code 'abc'}
     *
     * @param c the first character in the literal
     * @param cs the rest of the characters in the literal
     */
    public static CompError BigChar(char c, List<Character> cs)
    {
        return new CompError("found character string " + c + cs.toString());
    }

    /**
     * Error for empty character literal. <br/>
     * Example: {@code ''}
     */
    public static CompError EmptyChar()
    {
        return new CompError("found empty character");
    }

    /**
     * Error for Illegal character (usually invalid ASCII code). <br/>
     * illegal character are ASCII values 0..8, 11, 14..31, and 127..255
     */
    public static CompError IllegalChar(char c)
    {
        return new CompError("Illegal character ("+(int)c+") found.");
    }

    /**
     * Error for Integer that is not between {@code -2,147,483,648} and {@code 2,147,483,647}. <br/>
     * Example: {@code 2147483648}
     *
     * @param s the string representing the integer that's out of range.
     */
    public static CompError OutOfRange(String s)
    {
        return new CompError("Integer literal value "+s+" is out of range.");
    }

    /**
     * Error for an unterminated character. <br/>
     * Example: {@code 'x}
     */
    public static CompError UnterminatedChar()
    {
        return new CompError("unterminated character literal");
    }

    /**
     * An Unterminated Single line comment. <br/>
     * This can only happen if the comment is the very last line of the file.
     */
    public static CompError UnterminatedSingleLineComment()
    {
        return new CompError("unterminated single line comment");
    }

    /**
     * An unterminated multi line comment. <br/>
     * This can only happen if the comment goes to the end of the file.
     */
    public static CompError UnterminatedMultiLineComment()
    {
        return new CompError("unterminated multi line comment");
    }

    /**
     * An unterminated String. <br/>
     * This can conly happen if the string goes to the end of the file.
     */
    public static CompError UnterminatedString()
    {
        return new CompError("unterminated string literal");
    }

}
