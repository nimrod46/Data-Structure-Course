package labs.lab05;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

import static java.io.StreamTokenizer.*;

public class EvaluatePostfix {

    private static StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
    private static Stack<Double> myStack = new DLinkedListStack<>();

    public static void main(String[] args) throws IOException {
        tokenizer.slashSlashComments(false);
        tokenizer.ordinaryChar('/');

        tokenizer.nextToken();
        int token = tokenizer.ttype;
        while (token != TT_EOF && token != TT_WORD) {
            if (token == TT_NUMBER) {
                myStack.push(tokenizer.nval);
            } else {
                if (myStack.isEmpty()) {
                    fault();
                }
                double d1 = myStack.pop();
                if (myStack.isEmpty()) {
                    fault();
                }
                double d2 = myStack.pop();
                double res = 0;
                switch ((char) token) {
                    case '*':
                        res = d2 * d1;
                        break;
                    case '/':
                        res = d2 / d1;
                        break;
                    case '-':
                        res = d2 - d1;
                        break;
                    case '+':
                        res = d2 + d1;
                        break;
                }
                myStack.push(res);
            }
            tokenizer.nextToken();
            token = tokenizer.ttype;
        }


        if (token == TT_WORD) {
            if (!tokenizer.sval.equals("quit")) {
                fault();
            }
        }

        if (myStack.isEmpty()) {
            fault();
        }
        double finalRes = myStack.pop();

        if (!myStack.isEmpty()) {
            fault();
        }

        System.out.println(finalRes);
    }

    private static void fault() {
        System.err.println(tokenizer);
        System.err.println(myStack);
        System.exit(1);
    }

}
