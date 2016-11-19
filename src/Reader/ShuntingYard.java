
package Reader;

/**
 
 * Implementacion utilizada, desde PARSER.java, encontrada en internet
 */
import java.util.Stack;
 
public class ShuntingYard {
 /*
    public static void main(String[] args) {
        String infix = "( a | b ) * . ( a . a | b . b )";
        System.out.printf("infix:   %s%n", infix);
        System.out.printf("postfix: %s%n", infixToPostfix(infix));
    }
 */
    public static String infixToPostfix(String infix) {
        final String ops = "ØÇº";
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s;
        s = new Stack();
 
        for (String token : infix.split("")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = ops.indexOf(c);
 
            // check for operator
            if (idx != -1) {
                if (s.isEmpty())
                    s.push(idx);
 
                else {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek();
                        int prec1 = idx;
                        if (prec2 > prec1)
                            sb.append(ops.charAt(s.pop()));
                        else break;
                    }
                    s.push(idx);
                }
            } 
            else if (c == '«') {
                s.push(-2); // -2 stands for '('
            } 
            else if (c == '»') {
                // until '(' on stack, pop operators.
                while (s.peek() != -2)
                    sb.append(ops.charAt(s.pop()));
                s.pop();
            }
            else {
                sb.append(token);
            }
        }
        while (!s.isEmpty())
            sb.append(ops.charAt(s.pop()));
        String Final = sb.reverse().toString();
        System.out.println("After Post-Fix: " + Final);
        return Final;
    }
}
