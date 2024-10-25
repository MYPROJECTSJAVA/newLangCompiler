public class Interpreter {
    public int interpret(Node node){
        if(node instanceof NumberNode){
            return visitNumberNode(node);
        }
        else if(node instanceof IdentifierNode){
            return visitIdentifierNode(node);
        }
        else if(node instanceof BinOpNode){
            return visitBinOpNode(node);
        }
        throw new RuntimeException("Unknown Node type");
    }
    private int visitNumberNode(Node node){
        return Integer.parseInt(node.getToken().getValue());
    }
    private int visitIdentifierNode(Node node){
        throw new RuntimeException("Currently not supported");
    }
    private int visitBinOpNode(Node node){
        int left=interpret(node.getLeft());
        int right=interpret(node.getRight());
        Token operator=node.getOperator();
        switch(operator.getTokenType()){
            case PLUS:
                return left + right;

            case MINUS:
                return left-right;

            case MUL:
                return left*right;

            case DIV:
                if(right==0){
                    throw new ArithmeticException("Divide by zero");
                }
                else{
                    return left/right;
                }
            default:
                throw new RuntimeException("Unknown operator: " + operator);
        }
        }
    }
