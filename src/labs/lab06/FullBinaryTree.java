package labs.lab06;

public class FullBinaryTree<T> extends BinaryTree<T>{
    public FullBinaryTree(T value) {
        super(value);
    }

    public FullBinaryTree(T value, FullBinaryTree<T> left, FullBinaryTree<T> right) {
        super(value, left, right);
        if(left != right && (left == null || right == null)) {
            throw new IllegalArgumentException("Left and right args cannot be null!");
        }
    }

    @Override
    public void setLeft(BinaryTreeI<T> left) {
        if(left == null && getRight() == null) {
            return;
        }
        if(!(left instanceof FullBinaryTree)) {
            throw new IllegalArgumentException("Left argument must be of type FullBinaryTree!");
        }
        if(getRight() == null) {
            throw new RuntimeException("Right is null, cannot add left tree");
        }
        super.setLeft(left);
    }

    @Override
    public void setRight(BinaryTreeI<T> right) {
        if(right == null && getLeft() == null) {
            return;
        }
        if(!(right instanceof FullBinaryTree)) {
            throw new IllegalArgumentException("Right argument must be of type FullBinaryTree!");
        }
        if(getLeft() == null) {
            throw new RuntimeException("Left is null, cannot add right tree");
        }
        super.setRight(right);
    }
}
