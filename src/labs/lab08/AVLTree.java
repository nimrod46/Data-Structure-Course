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
                updateHeight();
                if (getBalanceFactor() == 2) {
                    if (left.getBalanceFactor() >= 0) {
                        return leftRotate(this);
                    }
                    return leftRightRotate(this);
                }
                return this;
            }
            left = new AVLTree<>(value);
            updateHeight();
            return this;
        }

        if (right != null) {
            right = right.add(value);
            updateHeight();
            if (getBalanceFactor() == -2) {
                if (right.getBalanceFactor() <= 0) {
                    return rightRotate(this);
                }
                return rightLeftRotate(this);
            }
            return this;
        }
        right = new AVLTree<>(value);
        updateHeight();
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

    private void updateHeight() {
        height = Math.max(left != null ? left.height : -1, right != null ? right.height : -1) + 1;
    }

    private int getBalanceFactor() {
        int leftHeight = left != null ? left.height : -1;
        int rightHeight = right != null ? right.height : -1;
        return leftHeight - rightHeight;
    }

    private static <Y extends Comparable<Y>> AVLTree<Y> leftRightRotate(AVLTree<Y> head) {
        AVLTree<Y> left = head.left;
        AVLTree<Y> leftRight = head.left.right;
        left.right = leftRight.left;
        leftRight.left = left;
        head.left = leftRight;
        return leftRotate(head);
    }

    private static <Y extends Comparable<Y>> AVLTree<Y> leftRotate(AVLTree<Y> head) {
        AVLTree<Y> newHead = head.left;
        AVLTree<Y> tmp = head.left.right;
        head.left.right = head;
        head.left = tmp;
        newHead.right.updateHeight();
        newHead.left.updateHeight();
        newHead.updateHeight();
        return newHead;
    }

    private static <Y extends Comparable<Y>> AVLTree<Y> rightLeftRotate(AVLTree<Y> head) {
        AVLTree<Y> right = head.right;
        AVLTree<Y> rightLeft = head.right.left;
        right.left = rightLeft.right;
        rightLeft.right = right;
        head.right = rightLeft;
        return rightRotate(head);
    }

    private static <Y extends Comparable<Y>> AVLTree<Y> rightRotate(AVLTree<Y> head) {
        AVLTree<Y> newHead = head.right;
        AVLTree<Y> tmp = head.right.left;
        head.right.left = head;
        head.right = tmp;
        newHead.left.updateHeight();
        newHead.right.updateHeight();
        newHead.updateHeight();
        return newHead;
    }
}
