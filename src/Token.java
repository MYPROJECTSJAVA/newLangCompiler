public class Token {
    private TokenType tokenType;
    private String lexeme;
    private int position;

    public Token(TokenType t,String L,int p){
        tokenType=t;
        lexeme=L;
        position=p;
    }



    public int getPosition(){
        return position;
    }
    public String getValue(){
        return lexeme;
    }
    public TokenType getTokenType(){
        return tokenType;
    }
    @Override
    public String toString(){
        return " TokenType: "+tokenType+" Value: "+lexeme+" Position: "+position;

    }

}
