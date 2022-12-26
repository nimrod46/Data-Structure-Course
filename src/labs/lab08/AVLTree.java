package labs.lab08;

public class AVLTree<T extends Comparable<T>> {

    private final T value;
    private AVLTree<T> left;
    private AVLTree<T> right;
    private int height;

    public AVLTree(T value) {
        this.value = value;
        height = 0;
    }

    //add the value to the tree, and return the updated root of the tree.
    public AVLTree<T> add(T value) {
        if (value.compareTo(this.value) < 0) {
            if (left != null) {
                left = left.add(value);
                height = Math.max(left.height, right != null ? right.height : -1) + 1;
                if (getBalanceFactor() == 2) {
                    if (left.getBalanceFactor() >= 0) {
                        AVLTree<T> r = left;
                        AVLTree<T> tmp = left.right;
                        left.right = this;
                        this.left = tmp;
                        return r;
                    }
                }
                return this;
            }
            left = new AVLTree<>(value);
            height = Math.max(left.height, right != null ? right.height : -1) + 1;
            return this;
        }

        if (right != null) {
            right = right.add(value);
            height = Math.max(right.height, left != null ? left.height : -1) + 1;
            return this;
        }
        right = new AVLTree<>(value);
        height = Math.max(right.height, left != null ? left.height : -1) + 1;
        return this;
    }

    //return the value in this node
    public T getValue() {
        return value;
    }

    //return the left subTree of this node
    public AVLTree<T> getLeft() {
        return left;
    }

    //return the right subTree of this node
    public AVLTree<T> getRight() {
        return right;
    }

    private int getBalanceFactor() {
        int leftHeight = left != null ? left.height : -1;
        int rightHeight = right != null ? right.height : -1;
        return leftHeight - rightHeight;
    }

}
