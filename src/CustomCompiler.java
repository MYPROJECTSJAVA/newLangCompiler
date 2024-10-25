import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomCompiler {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java CustomCompiler <filename>");
            return;
        }

        String filename = args[0];
        String code = readFile(filename);

        if (code != null) {
            Parser parser=new Parser(code);
            Interpreter interpreter=new Interpreter();
            System.out.println(interpreter.interpret(parser.parse()));
        }
    }
    private static String readFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return null;
        }
    }
}
