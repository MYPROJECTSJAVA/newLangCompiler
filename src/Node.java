abstract class Node {
    abstract Token getToken();

    public Node getLeft() {
        return null;
    }

    public Node getRight() {
        return null;
    }

    public Token getOperator() {
        return null;
    }
}
class NumberNode extends Node{
    private Token token;
    public NumberNode(Token t){
        token=t;
    }
    public Token getToken(){
        return token;
    }
}
class IdentifierNode extends Node{
    private Token token;
    public IdentifierNode(Token t){
        token=t;
    }
    public Token getToken(){return token;}
}
class BinOpNode extends Node{
    private Token operator;
    private Node left,right;
    public BinOpNode(Node left,Token t,Node right){
        operator=t;
        this.left=left;
        this.right=right;
    }
    public Token getOperator(){return operator;}
    public Node getRight(){return right;}
    public Node getLeft(){return left;}


    @Override
    Token getToken() {
        return null;
    }
}