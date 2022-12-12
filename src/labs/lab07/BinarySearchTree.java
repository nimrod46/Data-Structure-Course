package labs.lab07;

public class BinarySearchTree<T extends Comparable<T>> {

    BstNode root;

    // Binary Search Tree Node
    class BstNode {
        T val;
        BstNode left, right;

        public BstNode(T val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public enum Direction {
        LEFT, RIGHT
    }

    // Returns the val given a path from the root.
    // Used for testing. DO NOT DELETE.
    public T getValInPath(Direction... direction) {
        BstNode node = root;
        for (Direction d : direction) {
            if (d.equals(Direction.LEFT) && node.left != null)
                node = node.left;
            else if (d.equals(Direction.RIGHT) && node.right != null)
                node = node.right;
            else
                return null;
        }
        return node.val;
    }

    /**
     * Constructs an empty BinarySearchTree.
     */
    public BinarySearchTree() {
        root = null;
    }

    /**
     * returns the number of elements in the tree
     *
     * @param val
     */
    public int size() {
        return getNodeSize(root);
    }

    private int getNodeSize(BstNode node) {
        int size = 0;
        if (node == null) {
            return size;
        }
        size += getNodeSize(node.left);
        size += getNodeSize(node.right);
        return size + 1;
    }

    /**
     * Adds the object value to the tree as a leaf according to the parameter.
     *
     * @param val
     * @return True, if the element was added. Otherwise false.
     */
    public boolean add(T val) {
        if (root == null) {
            root = new BstNode(val);
            return true;
        }
        return addNodeRecursively(root, new BstNode(val));
    }

    private boolean addNodeRecursively(BstNode root, BstNode newNode) {
        int r = root.val.compareTo(newNode.val);
        if (r == 0) {
            return false;
        }

        if (r < 0) {
            if (root.right != null) {
                return addNodeRecursively(root.right, newNode);
            }
            root.right = newNode;
            return true;
        }
        if (root.left != null) {
            return addNodeRecursively(root.left, newNode);
        }
        root.left = newNode;
        return true;
    }

    /**
     * Removes the object in the tree which is equal to the parameter.
     * Nothing is done if not found
     *
     * @param val: the object to be looked for in the tree
     * @return True, if the element was removed. Otherwise false.
     */
    public boolean remove(T val) {
        int size = size();
        root = removeRecursively(root, val);
        return size != size();
    }

    private BstNode removeRecursively(BstNode node, T val) {
        if (node == null) {
            return null;
        }

        int c = node.val.compareTo(val);

        if (c == 0) {
            if (node.right != null && node.left != null) {
                node.val = getSuccessorFromRightRecursively(node.right);
                node.right = removeRecursively(node.right, node.val);
                return node;
            }

            if (node.right != null) {
                return node.right;
            }

            if (node.left != null) {
                return node.left;
            }
            return null;
        }
        if (c > 0) {
            if (node.left != null) {
                node.left = removeRecursively(node.left, val);
                return node;
            }
        }
        node.right = removeRecursively(node.right, val);
        return node;
    }

    private T getSuccessorFromRightRecursively(BstNode node) {
        if (node.left == null) {
            return node.val;
        }
        return getSuccessorFromRightRecursively(node.left);
    }

    /**
     * Looks for an object which is equal to the parameter.
     *
     * @param val: the object to be looked for in the tree
     * @return true if the tree contains this object. Otherwise, return false.
     */
    public boolean contains(T val) {
        return containsRecursively(root, val);
    }

    private boolean containsRecursively(BstNode node, T val) {
        if (node == null) {
            return false;
        }
        if (node.val.compareTo(val) == 0) {
            return true;
        }

        if (node.val.compareTo(val) > 0) {
            return containsRecursively(node.left, val);
        }

        return containsRecursively(node.right, val);
    }

    /**
     * Looks for the minimal object in the tree, which is greater than or equal to
     * the parameter.
     *
     * @param val: the object to be looked for in the tree
     * @return a reference to the found object.
     */
    public T findGe(T val) {

    }

    private T recFindGe(BstNode node, T val, Direction lastDir) {

    }
}
