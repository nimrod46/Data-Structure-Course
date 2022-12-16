package labs.lab07;

public class BinarySearchTree<T extends Comparable<T>> {

    private BstNode root;
    private int size;

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
        size = 0;
    }

    /**
     * returns the number of elements in the tree
     *
     * @param
     */
    public int size() {
        return size;
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
            size++;
            return true;
        }
        boolean added = addNodeRecursively(root, new BstNode(val));
        if (added) {
            size++;
        }
        return added;
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
                node.val = getLeftMostValue(node.right);
                node.right = removeRecursively(node.right, node.val);
                return node;
            }

            size--;

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

    private T getLeftMostValue(BstNode node) {
        if (node.left == null) {
            return node.val;
        }
        return getLeftMostValue(node.left);
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
        boolean isContains = contains(val);
        if (isContains) {
            return val;
        }

        if (root == null) {
            return null;
        }

        int c = root.val.compareTo(val);
        T v;
        if (c > 0) {
            v = recFindGe(root, val, Direction.LEFT);
        } else {
            v = recFindGe(root, val, Direction.RIGHT);
        }
        if (v.compareTo(val) < 0) {
            return null;
        }
        return v;
    }

    private T recFindGe(BstNode node, T val, Direction lastDir) {
        if (node == null) {
            return null;
        }
        int c = node.val.compareTo(val);
        if (c > 0) {
            if (lastDir != Direction.LEFT) {
                return null;
            }
            T t = recFindGe(node.left, val, lastDir);
            if (t != null) {
                return t;
            }
            return node.val;
        }
        if (lastDir != Direction.RIGHT) {
            return null;
        }
        T t = recFindGe(node.right, val, lastDir);
        if (t != null) {
            return t;
        }
        return node.val;
    }
}
