package parse;
import java.util.List;
import errorMsg.*;
import java.lang.reflect.Field;
import syntaxtree.*;
import wrangLR.runtime.MessageObject;
import wrangLR.runtime.FilePosObject;

public class MJGrammar implements MessageObject, FilePosObject
{

    // constructor
    // @param em error-message object
    public MJGrammar(ErrorMsg em)
    {
        errorMsg = em;
        topObject = null;
    }

    // error message object
    private ErrorMsg errorMsg;

    // object to be returned by the parser
    private Program topObject;

    // These 2 methods are needed by WrangLR
    // DO NOT USE THEM! They will not pass tests
    // We don't need any errors or warnings in this assignment.
    public void warning(int pos, String msg)
    {
        errorMsg.info(pos, msg);
    }

    public void error(int pos, String msg)
    {
        errorMsg.error(pos, msg);
    }

    // method for converting file position to line/char position
    // @param pos the file position
    // @return the string that denotes the file position
    public String filePosString(int pos)
    {
        return errorMsg.lineAndChar(pos);
    }

    // method that registers a newline
    // @param pos the file position of the newline character
    public void registerNewline(int pos)
    {
        errorMsg.newline(pos-1);
    }

    // returns the object produced by the parse
    // @return the top-level object produced by the parser
    public Program parseResult()
    {
        return topObject;
    }

    //===============================================================
    // start symbol
    //===============================================================

    //: <start> ::= ws* <program> =>
    public void topLevel(Program obj)
    {
        topObject = obj;
    }

    //================================================================
    // top-level constructs
    //================================================================

    //: <program> ::= # <class decl>+ =>
    public Program createProgram(int pos, List<ClassDecl> vec)
    {
        return new Program(pos, new ClassDeclList(vec));
    }

    //: <class decl> ::= `class # ID `{ <decl in class>* `} =>
    public ClassDecl createClassDecl(int pos, String name, List<Decl> vec)
    {
        return new ClassDecl(pos, name, "Object", new DeclList(vec));
    }
    
    // extends
    //: <class decl> ::= `class # ID `extends ID `{ <decl in class>* `} =>
    public ClassDecl createClassDeclExt(int pos, String name, String superName, List<Decl> vec)
    {
        return new ClassDecl(pos, name, superName, new DeclList(vec));
    }

    //: <decl in class> ::= <method decl> => pass
    //: <delc in class> ::= <field decl> => pass
    
    // field declarations
    //: <field decl> ::= <type> # ID `; =>
    public Decl createFieldDecl(Type t, int pos, String name){
        return new FieldDecl(pos, t, name);
    }

    // void functions
    //: <method decl> ::= `public `void # ID `( `) `{ <stmt>* `} =>
    public Decl createMethodDeclVoid(int pos, String name, List<Stmt> stmts)
    {
        return new MethodDeclVoid(pos, name, new VarDeclList(new VarDeclList()),
                                  new StmtList(stmts));
    }
    
    // non void functions
    //: <method decl> ::= `public <type> # ID `( `) `{ <stmt>* `return <expr> `; `} =>
    public Decl createMethodDeclNonVoid(Type t, int pos, String name,
                                        List<Stmt> stmts, Exp returnExp)
    {
        return new MethodDeclNonVoid(pos, t, name, new VarDeclList(new VarDeclList()), 
                                    new StmtList(stmts), returnExp);
    }

    //: <type> ::= # `int =>
    public Type intType(int pos)
    {
        return new IntType(pos);
    }
    //: <type> ::= # `boolean =>
    public Type booleanType(int pos)
    {
        return new BoolType(pos);
    }
    //: <type> ::= # ID =>
    public Type idType(int pos, String name)
    {
        return new IDType(pos, name);
    }
    //: <type> ::= # <type> <empty bracket pair> =>
    public Type newArrayType(int pos, Type t, Object dummy)
    {
        return new ArrayType(pos, t);
    }

    //: <empty bracket pair> ::= `[ `] => null

    //================================================================
    // statement-level constructs
    //================================================================

    //: <stmt> ::= <assign> `; => pass
    public Stmt newAssignStmt(Assign s) {return s; }

    // statement call
    //: <stmt call> ::= # <callExp> `; =>
    public Stmt newCallStmt(int pos, Call exp){
        return new CallStmt(pos, exp);
    }
    //: <stmt> ::= <stmt call> => pass
    
    // if statement with else
    //: <if stmt> ::= # `if `( <expr> `) <stmt> `else <stmt> =>
    public Stmt newIf(int pos, Exp cond, Stmt trueBranch, Stmt falseBranch){
        // if (falseBranch == null) {
        //     falseBranch = new Block(pos, new StmtList()); // empty else block
        // }
        return new If(pos, cond, trueBranch, falseBranch);
    }
    // if without else
    //: <if stmt> ::= # `if `( <expr> `) <stmt> ! `else =>
    public Stmt newIf(int pos, Exp cond, Stmt trueBranch){
        return new If(pos, cond, trueBranch, new Block(pos, new StmtList()));
    }
            
    // while statment
    //: <while stmt> ::= `while # `( <expr> `) <stmt> =>
    public Stmt newWhile(int pos, Exp cond, Stmt s){
        return new While(pos, cond, s);
    }

    // "for" loop translated to while loop
    //: <for stmt> ::= # `for `( <assign> `; <expr> `; <assign> `) <stmt> =>
    public Stmt newFor(int pos, Stmt init, Exp cond, Stmt step, Stmt body) {
        //body block
        StmtList loopBody = new StmtList();
        loopBody.add(body);
        loopBody.add(step); // i = i + 1
        
        While whileLoop = new While(pos, cond, new Block(pos, loopBody));

        //wrap everything in outer block: 
        StmtList outerList = new StmtList();
        outerList.add(init); // int i = 0
        outerList.add(whileLoop);
        
        return new Block(pos, outerList);
    }

    //: <stmt> ::= <if stmt> => pass
    //: <stmt> ::= <while stmt> => pass
    //: <stmt> ::= <for stmt> => pass

    // empty statement 
    //: <stmt> ::= `; =>
    public Stmt emptyStmt(){
        return new Block(0, new StmtList());
    }

    //: <stmt> ::= # `{ <stmt>* `} =>
    public Stmt newBlock(int pos, List<Stmt> sl)
    {
        return new Block(pos, new StmtList(sl));
    }
    //: <stmt> ::= <local var decl> `; => pass
    public Stmt stmtLocal(LocalDeclStmt l) { return l; }

    // increment ++
    //: <stmt> ::= # ID `++ `; => 
    public Stmt stmtIncrement(int pos, String name) {
        Exp var = new IDExp(pos, name);       
        Exp rhs = new Plus(pos, var, new IntLit(pos, 1)); // x + 1
        return new Assign(pos, var, rhs); // x = x + 1
    }

    // decrement -- 
    //: <stmt> ::= # ID `-- `; => 
    public Stmt stmtDecrement(int pos, String name) {
        Exp var = new IDExp(pos, name);       
        Exp rhs = new Minus(pos, var, new IntLit(pos, 1)); // x - 1
        return new Assign(pos, var, rhs); // x = x - 1
    }

    //: <assign> ::= <expr> # `= <expr> =>
    public Stmt assign(Exp lhs, int pos, Exp rhs)
    {
        return new Assign(pos, lhs, rhs);
    }

    //: <local var decl> ::= <type> # ID `= <expr> =>
    public Stmt localVarDecl(Type t, int pos, String name, Exp init)
    {
        return new LocalDeclStmt(pos, new LocalVarDecl(pos, t, name, init));
    }

    //================================================================
    // expressions
    //================================================================

    // these precedence levels have not been filled in at all, so there
    // are only pass-through productions

    //: <expr> ::= <expr8> => pass

    // || node
    //: <expr8> ::= <expr8> # `|| <expr7> =>
    public Exp newOr(Exp e1, int pos, Exp e2)
    {
        return new Or(pos, e1, e2);
    }
    //: <expr8> ::= <expr7> => pass

    // && node
    //: <expr7> ::= <expr7> # `&& <expr6> =>
    public Exp newAnd(Exp e1, int pos, Exp e2)
    {
        return new And(pos, e1, e2);
    }
    //: <expr7> ::= <expr6> => pass

    // == node
    //: <expr6> ::= <expr6> # `== <expr5> =>
    public Exp newEquals(Exp e1, int pos, Exp e2)
    {
        return new Equals(pos, e1, e2);
    }

    // != node 
    //: <expr6> ::= <expr6> # `!= <expr5> =>
    public Exp newNotEquals(Exp e1, int pos, Exp e2){
        // transform a != b into !(a == b)
        return new Not(pos, new Equals(pos, e1, e2));
    }
    //: <expr6> ::= <expr5> => pass

    // <= node
    //: <expr5> ::= <expr5> # `<= <expr4> =>
    public Exp newNotLessThan(Exp e1, int pos, Exp e2){
        // transform a <= b into !(a > b)
        return new Not(pos, new GreaterThan(pos, e1, e2)); 
    }

    // >= node
    //: <expr5> ::= <expr5> # `>= <expr4> =>
    public Exp newNotGreaterThan(Exp e1, int pos, Exp e2){
        // transform a >= b into !(a < b)
        return new Not(pos, new LessThan(pos, e1, e2));
    }

    // < node
    //: <expr5> ::= <expr5> # `< <expr4> =>
    public Exp newLess(Exp e1, int pos, Exp e2){
        return new LessThan(pos, e1, e2);
    }

    // > node
    //: <expr5> ::= <expr5> # `> <expr4> =>
    public Exp newGreater(Exp e1, int pos, Exp e2){
        return new GreaterThan(pos, e1, e2);
    }

    // instanceof
    //: <expr5> ::= <expr5> #  `instanceof <type> =>
    public Exp newInstanceOf(Exp e, int pos, Type t){
        return new InstanceOf(pos, e, t);
    }

    //: <expr5> ::= <expr4> => pass

    // these remaining precedence levels have been filled in to some extent,
    // but most or all of them have need to be expanded

    // + node
    //: <expr4> ::= <expr4> # `+ <expr3> =>
    public Exp newPlus(Exp e1, int pos, Exp e2)
    {
        return new Plus(pos, e1, e2);
    }

    // - node
    //: <expr4> ::= <expr4> # `- <expr3> =>
    public Exp newMinus(Exp e1, int pos, Exp e2) {
        return new Minus(pos, e1, e2);
    }

    //: <expr4> ::= <expr3> => pass

    // * node
    //: <expr3> ::= <expr3> # `* <expr2> =>
    public Exp newTimes(Exp e1, int pos, Exp e2)
    {
        return new Times(pos, e1, e2);
    }

    // / node
    //: <expr3> ::= <expr3> # `/ <expr2> =>
    public Exp newDivde(Exp e1, int pos, Exp e2){
        return new Divide(pos, e1, e2);
    }

    // % node
    //: <expr3> ::= <expr3> # `% <expr2> =>
    public Exp newRemainder(Exp e1, int pos, Exp e2){
        return new Remainder(pos, e1, e2);
    }

    //: <expr3> ::= <expr2> => pass

    //: <expr2> ::= <cast expr> => pass
    //: <expr2> ::= <unary expr> => pass

    //: <cast expr> ::= # `( <type> `) <cast expr> =>
    public Exp newCast(int pos, Type t, Exp e)
    {
        return new Cast(pos, t, e);
    }
    //: <cast expr> ::= # `( <type> `) <expr1> => Exp newCast(int, Type, Exp)

    // unary -
    //: <unary expr> ::= # `- <unary expr> =>
    public Exp newUnaryMinus(int pos, Exp e)
    {   // -x transform to 0 - x
        return new Minus(pos, new IntLit(pos, 0), e);
    }

    // unary +
    //: <unary expr> ::= # `+ <unary expr> =>
    public Exp newUnaryPlus(int pos, Exp e)
    {   // +x transform to 0 + x
        return new Plus(pos, new IntLit(pos,0), e);
    }

    // unary !
    //: <unary expr> ::= # `! <unary expr> =>
    public Exp newNot(int pos, Exp e)
    {
        return new Not(pos, e);
    }

    //: <unary expr> ::= <expr1> => pass

    //: <expr list> ::= =>
    public ExpList emptyArgs(){
        return new ExpList();
    }
    
    //: <expr list> ::= <expr> =>
    public ExpList oneArg(Exp e){
        ExpList el = new ExpList();
        el.add(e);
        return el;
    }

    //: <expr list> ::= <expr list> `, <expr> =>
    public ExpList moreArgs(ExpList el, Exp e){
        el.add(e);
        return el;
    }

    //: <expr1> ::= <callExp> =>
    public Exp callExpToExpr(Call c) {
        return c;
    }

    //method call
    //: <callExp> ::= <expr1> # `. ID `( <expr list> `) =>
    public Call callFull(Exp e, int pos, String name, ExpList el) {
        return new Call(pos, e, name, el);
    }

    //expression call
    //: <callExp> ::= # ID `( <expr list> `) =>
    public Call callSimple(int pos, String name, ExpList el) {
        return new Call(pos, new This(pos), name, el);
    }
    
    //: <expr1> ::= # ID  =>
    public Exp newIDExp(int pos, String name)
    {
        return new IDExp(pos, name);
    }
    //: <expr1> ::= <expr1> !<empty bracket pair> # `[ <expr> `] =>
    public Exp newArrayLookup(Exp e1, int pos, Exp e2)
    {
        return new ArrayLookup(pos, e1, e2);
    }
    //: <expr1> ::= # INTLIT =>
    public Exp newIntLit(int pos, int n)
    {
        return new IntLit(pos, n);
    }

    //: <expr1> ::= # `this =>
    public Exp newThis(int pos) {
        return new This(pos);
    }

    //: <expr1> ::= # `super =>
    public Exp newSuper(int pos){
        return new Super(pos);
    }

    // dot (instance variable access)
    //: <expr1> ::= <expr1> # `. ID =>
    public Exp newFieldAccess(Exp e, int pos, String name){
        if(name.equals("length"))
            return new ArrayLength(pos, e);

        return new FieldAccess(pos, e, name);
    }

    // new object
    //: <expr1> ::= # `new ID `( `) =>
    public Exp newObject(int pos, String name){
        return new NewObject(pos, new IDType(pos, name));
    }

    // new array
    //: <expr1> ::= # `new <type> `[ <expr> `] =>
    public Exp newArray(int pos, Type t, Exp e){
        return new NewArray(pos, t, e);
    }

    // empty parameter 
    //: <param list> ::= =>
    public VarDeclList emptyParams() {
        return new VarDeclList();
    }

    // single parameter
    //: <param list> ::= <type> # ID =>
    public VarDeclList oneParam(Type t, int pos, String name) {
        VarDeclList params = new VarDeclList();
        params.add(new ParamDecl(pos, t, name));
        return params;
    }

    // multiple parameters
    //: <param list> ::= <param list> `, <type> # ID =>
    public VarDeclList moreParams(VarDeclList params, Type t, int pos, String name) {
        params.add(new ParamDecl(pos, t, name));
        return params;
    }

    //================================================================
    // Lexical grammar for filtered language begins here: DO NOT
    // MODIFY ANYTHING BELOW THIS, UNLESS YOU REPLACE IT WITH YOUR
    // ENTIRE LEXICAL GRAMMAR, and set the constant FILTER_GRAMMAR
    // (defined near the top of this file) to false.
    //================================================================

    //: letter ::= {"a".."z" "A".."Z"} => pass
    //: letter128 ::= {225..250 193..218} =>
    public char sub128(char orig)
    {
        return (char)(orig-128);
    }
    //: digit ::= {"0".."9"} => pass
    //: digit128 ::= {176..185} => char sub128(char)
    //: any ::= {0..127} => pass
    //: any128 ::= {128..255} => char sub128(char)
    //: ws ::= " "
    //: ws ::= {10} registerNewline
    //: registerNewline ::= # => void registerNewline(int)
    //: `boolean ::= "#bo" ws*
    //: `class ::= "#cl" ws*
    //: `extends ::= "#ex" ws*
    //: `void ::= "#vo" ws*
    //: `int ::= "#it" ws*
    //: `while ::= "#wh" ws*
    //: `if ::= '#+' ws*
    //: `else ::= "#el" ws*
    //: `for ::= "#fo" ws*
    //: `break ::= "#br" ws*
    //: `this ::= "#th" ws*
    //: `false ::= '#fa' ws*
    //: `true ::= "#tr" ws*
    //: `super ::= "#su" ws*
    //: `null ::= "#nu" ws*
    //: `return ::= "#re" ws*
    //: `instanceof ::= "#in" ws*
    //: `new ::= "#ne" ws*
    //: `case ::= "#ce" ws*
    //: `default ::= "#de" ws*
    //: `do ::= "#-" ws*
    //: `public ::= "#pu" ws*
    //: `switch ::= "#sw" ws*

    //: `! ::=  "!" ws* => void
    //: `!= ::=  "@!" ws* => void
    //: `% ::= "%" ws* => void
    //: `&& ::= "@&" ws* => void
    //: `* ::= "*" ws* => void
    //: `( ::= "(" ws* => void
    //: `) ::= ")" ws* => void
    //: `{ ::= "{" ws* => void
    //: `} ::= "}" ws* => void
    //: `- ::= "-" ws* => void
    //: `+ ::= "+" ws* => void
    //: `= ::= "=" ws* => void
    //: `== ::= "@=" ws* => void
    //: `[ ::= "[" ws* => void
    //: `] ::= "]" ws* => void
    //: `|| ::= "@|" ws* => void
    //: `< ::= "<" ws* => void
    //: `<= ::= "@<" ws* => void
    //: `, ::= "," ws* => void
    //: `> ::= ">"  !'=' ws* => void
    //: `>= ::= "@>" ws* => void
    //: `: ::= ":" ws* => void
    //: `. ::= "." ws* => void
    //: `; ::= ";" ws* => void
    //: `++ ::= "@+" ws* => void
    //: `-- ::= "@-" ws* => void
    //: `/ ::= "/" ws* => void


    //: ID ::= letter128 ws* => text
    //: ID ::= letter idChar* idChar128 ws* => text

    //: INTLIT ::= {"1".."9"} digit* digit128 ws* =>
    public int convertToInt(char c, List<Character> mid, char last)
    {
        return Integer.parseInt(""+c+mid+last);
    }
    //: INTLIT ::= digit128 ws* =>
    public int convertToInt(char c)
    {
        return Integer.parseInt(""+c);
    }
    //: INTLIT ::= "0" hexDigit* hexDigit128 ws* =>
    public int convert16ToInt(char c, List<Character> mid, char last)
    {
        return Integer.parseInt(""+c+mid+last, 16);
    }
    //: STRINGLIT ::= '@"' ws* =>
    public String emptyString(char x, char xx)
    {
        return "";
    }
    //: STRINGLIT ::= '"' any* any128 ws* =>
    public String string(char x, List<Character> mid, char last)
    {
        return ""+mid+last;
    }
    //: CHARLIT ::= "'" any ws* =>
    public int charVal(char x, char val)
    {
        // treat all character literal as ints with their ASCII value
        return (int) val;
    }

    //: idChar ::= letter => pass
    //: idChar ::= digit => pass
    //: idChar ::= "_" => pass
    //: idChar128 ::= letter128 => pass
    //: idChar128 ::= digit128 => pass
    //: idChar128 ::= {223} =>
    public char underscore(char x)
    {
        return '_';
    }
    //: hexDigit ::= {"0".."9" "A".."Z" "a".."z"} => pass
    //: hexDigit128 ::= {176..185 225..230 193..198} => char sub128(char)

}
