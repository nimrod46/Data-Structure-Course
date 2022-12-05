package labs.lab06;

import java.io.IOException;
import java.io.StreamTokenizer;

public class ExpressionTree extends FullBinaryTree<String> {

    public ExpressionTree(String value) {
        super(value);
    }

    public ExpressionTree(String value, FullBinaryTree<String> left, FullBinaryTree<String> right) {
        super(value, left, right);
    }

    /*
     * Read the stream tokenizer until EOF and construct
     *  the expression tree corresponding to it.
     * The input contains a prefix expression.
     */
    public static ExpressionTree createTree(StreamTokenizer tokenizer) throws IOException {
        int c = tokenizer.nextToken();
        if (c == StreamTokenizer.TT_EOF) {
            return null;
        }
        if (c == StreamTokenizer.TT_NUMBER) {
            return new ExpressionTree((int) tokenizer.nval + "");
        }
        return new ExpressionTree((char) c + "", createTree(tokenizer), createTree(tokenizer));
    }

    /*
     * Returns the infix expression corresponding to the current tree (*)
     */
    public String infix() {
        //StringBuilder s = new StringBuilder();
        //s.append(getLeft().inOrder());

        return inOrder("(", ")");
    }

    /*
     * Returns the prefix expression corresponding to the current tree (*)
     */
    public String prefix() {
        return preOrder();
    }

    /*
     * Evaluates the expression corresponding to the current tree
     * and returns its value
     */
    public double evaluate() {
        return evaluate(this);
    }

    private static double evaluate(BinaryTreeI<String> tree) {
        switch (tree.getValue()) {
            case "*":
                return evaluate(tree.getLeft()) * evaluate(tree.getRight());
            case "-":
                return evaluate(tree.getLeft()) - evaluate(tree.getRight());
            case "/":
                return evaluate(tree.getLeft()) / evaluate(tree.getRight());
            case "+":
                return evaluate(tree.getLeft()) + evaluate(tree.getRight());
        }
        return Double.parseDouble(tree.getValue());
    }
}
