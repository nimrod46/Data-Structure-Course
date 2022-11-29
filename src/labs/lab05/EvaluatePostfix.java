package labs.lab05;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

import static java.io.StreamTokenizer.*;

public class EvaluatePostfix {

    private static final StreamTokenizer tokenizer = new StreamTokenizer(new InputStreamReader(System.in));
    private static final Stack<Double> myStack = new DLinkedListStack<>();

    public static void main(String[] args) throws IOException {
        tokenizer.slashSlashComments(false);
        tokenizer.ordinaryChar('/');
        int token;
        while (true) {
            tokenizer.nextToken();
            token = tokenizer.ttype;
            if (token == TT_EOF || token == TT_WORD) {
                break;
            }

            if (token == TT_NUMBER) {
                myStack.push(tokenizer.nval);
                continue;
            }

            double res = getResultByOp((char) token);
            myStack.push(res);
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

    private static double getResultByOp(char token) {
        if (myStack.isEmpty()) {
            fault();
        }
        double d1 = myStack.pop();
        if (myStack.isEmpty()) {
            fault();
        }
        double d2 = myStack.pop();
        double res = 0;
        switch (token) {
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
            default:
                fault();
                break;
        }
        return res;
    }

    private static void fault() {
        System.err.println(tokenizer);
        System.err.println(myStack);
        System.exit(1);
    }

}
