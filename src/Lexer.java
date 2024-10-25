public class Lexer {
    private String input;
    private int position;

    public Lexer(String input){
        this.input=input;
        position=0;
    }
    private void skipSpaces(){
        while(Character.isWhitespace(peek())){
            position++;
        }
    }
    private char peek(){
        if(position>=input.length())return '\0';
        return input.charAt(position);
    }
    public Token nextToken(){
        //skip if space
        skipSpaces();
        //check end of file
        if(position>=input.length()){
            return new Token(TokenType.EOF,"",position);
        }
        char current=input.charAt(position);
        position++;
        //check for other Token
        if(Character.isDigit(current)){
            return number(current);
        }
        else if(Character.isLetter(current)){
            return identifier(current);
        }
        else if(current=='+'){
            return new Token(TokenType.PLUS,"+",position-1);
        }
        else if(current=='-'){
            return new Token(TokenType.MINUS,"-",position-1);
        }
        else if(current=='*'){
            return new Token(TokenType.MUL,"*",position-1);
        }
        else if(current=='/'){
            return new Token(TokenType.DIV,"/",position-1);
        }

        throw new RuntimeException(current+" at posittion "+(position-1)+" not a identified token ");

    }

    //helper functions

    private Token number(Character current){
        StringBuilder num=new StringBuilder();
        num.append(current);

        while(Character.isDigit(peek())){
            num.append(peek());
            position++;
        }
        return new Token(TokenType.NUMBER,num.toString(),position-1);
    }
    private Token identifier(Character current){
        StringBuilder variable=new StringBuilder();
        variable.append(current);
        while(Character.isLetter(peek())){
            variable.append(peek());
            position++;
        }
        return new Token(TokenType.IDENTIFIER,variable.toString(),position-1);
    }
    public static void main(String [] args){
        Lexer lexer=new Lexer(" 1+ x + aviskar ");
        int i=0;
        while(i<10){
            System.out.println(lexer.nextToken());
            i++;
        }
    }
}
