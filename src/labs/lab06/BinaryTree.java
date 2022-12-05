package labs.lab06;

public class BinaryTree<T> implements BinaryTreeI<T> {

    private T value;
    private BinaryTreeI<T> left;
    private BinaryTreeI<T> right;

    public BinaryTree(T value) {
        this.value = value;
    }

    public BinaryTree(T value, BinaryTreeI<T> left, BinaryTreeI<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    @Override
    public BinaryTreeI<T> getLeft() {
        return left;
    }

    @Override
    public BinaryTreeI<T> getRight() {
        return right;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        this.left = left;
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        this.right = right;
    }

    @Override
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public int height() {
        int leftHeight = 0;
        if (left != null) {
            leftHeight = left.height() + 1;
        }
        int rightHeight = 0;
        if (right != null) {
            rightHeight = right.height() + 1;
        }
        return Math.max(leftHeight, rightHeight);
    }

    @Override
    public int size() {
        int leftSize = 0;
        if (left != null) {
            leftSize = left.size();
        }
        int rightSize = 0;
        if (right != null) {
            rightSize = right.size();
        }
        return leftSize + rightSize + 1;
    }

    @Override
    public void clear() {
        left = null;
        right = null;
    }

    @Override
    public String preOrder() {
        return preOrder(" ", " ");
    }

    @Override
    public String preOrder(String separationBeforeVal, String separationAfterVal) {
        String s = separationBeforeVal + value.toString() + separationAfterVal;
        if (left != null) {
            s += left.preOrder(separationBeforeVal, separationAfterVal);
        }
        if (right != null) {
            s += right.preOrder(separationBeforeVal, separationAfterVal);
        }
        return s;
    }

    @Override
    public String inOrder() {
        return inOrder(" ", " ");
    }

    @Override
    public String inOrder(String separationBeforeVal, String separationAfterVal) {
        String s = "";
        if (left != null) {
            s += left.inOrder(separationBeforeVal, separationAfterVal);
        }

        s += separationBeforeVal + value.toString() + separationAfterVal;

        if (right != null) {
            s += right.inOrder(separationBeforeVal, separationAfterVal);
        }
        return s;
    }

    @Override
    public String postOrder() {
        return postOrder(" ", " ");
    }

    @Override
    public String postOrder(String separationBeforeVal, String separationAfterVal) {
        String s = "";
        if (left != null) {
            s += left.postOrder(separationBeforeVal, separationAfterVal);
        }

        if (right != null) {
            s += right.postOrder(separationBeforeVal, separationAfterVal);
        }

        s += separationBeforeVal + value.toString() + separationAfterVal;
        return s;
    }
}
