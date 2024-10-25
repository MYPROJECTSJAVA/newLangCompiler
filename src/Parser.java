public class Parser {
    Lexer lexer;
    Token currentToken;
    public Parser(String input){
        lexer=new Lexer(input);
        currentToken=lexer.nextToken();
    }

    //helper
    private Token eat(TokenType type){
        if(currentToken.getTokenType()==type){

            currentToken=lexer.nextToken();
            return currentToken;
        }
        else{
            throw new RuntimeException("Unexpected token "+currentToken.toString()+" at line: "+currentToken.getPosition());
        }
    }
    public Node parse(){ return expression();}
    private Node expression(){
        Node node = term();

        while(currentToken.getTokenType()==TokenType.PLUS || currentToken.getTokenType()==TokenType.MINUS){
            Token token=currentToken;
            if(currentToken.getTokenType()==TokenType.PLUS){
                eat(TokenType.PLUS);
            }
            else if(currentToken.getTokenType()==TokenType.MINUS){
                eat(TokenType.MINUS);
            }
            node=new BinOpNode(node,token,term());
        }
        return node;

    }
    private Node term(){
        Node node=factor();

        while(currentToken.getTokenType()==TokenType.DIV || currentToken.getTokenType()==TokenType.MUL){
            Token token=currentToken;
            if(currentToken.getTokenType()==TokenType.DIV){
                eat(TokenType.DIV);
            }
            else if(currentToken.getTokenType()==TokenType.MUL){
                eat(TokenType.MUL);
            }
            node=new BinOpNode(node,token,factor());
        }
        return node;

    }
    private Node factor() {
        Token token = currentToken;
        if (currentToken.getTokenType() == TokenType.NUMBER) {
            eat(TokenType.NUMBER);//if current token is of type number moves to next Token
            return new NumberNode(token);
        } else if (currentToken.getTokenType() == TokenType.IDENTIFIER) {
            eat(TokenType.IDENTIFIER);//if current token is of type identifier moves to next Token
            return new IdentifierNode(token);
        } else if (token.getTokenType() == TokenType.EOF) {
            throw new RuntimeException("Unexpected end of input");
        } else {
            throw new RuntimeException("Unexpected token " + currentToken.toString() + " at line: " + currentToken.getPosition());
        }
    }
    public static void main(String[] args) {
        String input = "x + 5 * y";

        Parser parser = new Parser(input);

        Node result = parser.parse();
        System.out.println(result);  // You can print the AST here or use a debugger to inspect the structure
    }
}
