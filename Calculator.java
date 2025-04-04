import java.io.*;

public class Calculator {
    public static void main(String[] args) {
        try {
            System.out.println("Enter arithmetic expressions (press Ctrl+D to exit):");
            Parser parser = new Parser(new Lexer(new InputStreamReader(System.in)));
            parser.parse();
            System.out.println("Result: " + parser.getResult());  // Print result here
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}