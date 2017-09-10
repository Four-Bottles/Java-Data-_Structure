// package kejun;

@SuppressWarnings("unchecked")

public class SplayTree<AnyType extends Comparable<? super AnyType>> {

    public SplayTree() {
        nullNode = new BinaryNode<>(null);
        nullNode.left = nullNode.right = nullNode;
        root = nullNode;
        size = 0;
    }

    private BinaryNode<AnyType> splay(AnyType x, BinaryNode<AnyType> t) {
        BinaryNode<AnyType> leftTreeMax, rightTreeMin;

        header.left = header.right = nullNode;
        leftTreeMax = rightTreeMin = header;

        nullNode.element = x;   // Guarantee a match

        for (; ; ) {
            if (x.compareTo(t.element) < 0) {
                if (x.compareTo(t.left.element) < 0) {
                    t = rotateWithLeftChild( t );
                }
                if (t.left == nullNode) {
                    break;
                }
                // link Right
                rightTreeMin.left = t;
                rightTreeMin = t;
                t = t.left;
            } else if (x.compareTo(t.element) > 0) {
                if (x.compareTo(t.right.element) > 0){
                    t = rotateWithRightChild(t);
                }
                if (t.right == nullNode) {
                    break;
                }
                // link Left
                leftTreeMax.right = t;
                leftTreeMax = t;
                t = t.right;
            } else break;
        }
        leftTreeMax.right = t.left;
        rightTreeMin.left = t.right;
        t.left = header.right;
        t.right = header.left;
        return t;
    }

    public void insert(AnyType x) {
        if (newNode == null) {
            newNode = new BinaryNode<>(null);
        }
        newNode.element = x;
        if (root == nullNode) {
            newNode.left = newNode.right = nullNode;
            root = newNode;
        } else {
            root = splay(x, root);
            if (x.compareTo(root.element) < 0) {
                newNode.left = root.left;
                newNode.right = root;
                root.left = nullNode;
                root = newNode;
            } else {
                if (x.compareTo(root.element) > 0) {
                    newNode.right = root.right;
                    newNode.left = root;
                    root.right = nullNode;
                    root = newNode;
                } else {
                    return;    // No duplicates
                }
            }
        }
        size += 1;
        newNode = null;   // So next insert will call new
    }

    public void remove(AnyType x) {
        BinaryNode<AnyType> newTree;
        // Attention! Important. if x is found, it will be at the root;

        if (!contains(x)) {
            return;    // Item not found; do no
        }
        if (root.left == nullNode) {   // So if the program runs this code, it indicates that x is in the tree. And after the "contains(),
                                       // x becomes the root of the tree."
            newTree = root.right;
        } else {
            // Find the maximum in the left subtree
            // Splay it to the root; and then attach right child
            newTree = root.left;
            newTree = splay(x, newTree);
            newTree.right = root.right;
        }
        root = newTree;
        size -= 1;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("Empty Tree!");
        } else {
            printTree(root);
            System.out.println();
        }
    }

    public AnyType findByPosition(int index) {
        if (root == nullNode) {
            return null;
        } else {
            int count = 1;
            SplayTree<AnyType> newObject = new SplayTree();
            while (count != index) {
                AnyType dt = this.findMin();
                newObject.insert(dt);
                this.remove(dt);
                count += 1 ;
            }
            AnyType mid =  this.findMin();
            while (this.root != nullNode) {
                AnyType leave = this.findMin();
                newObject.insert(leave);
                this.remove(leave);
            }
            this.copy(newObject);
            return mid;
        }
    }

    public AnyType findMin() {
        // Remember
        if (isEmpty()) {
            return null;
        }
        BinaryNode<AnyType> ptr = root;
        while (ptr.left != nullNode) {
            ptr = ptr.left;
        }
        root = splay(ptr.element, root);
        return ptr.element;
    }

    public AnyType findMax() {
        // Remember
        if (isEmpty()) {
            return null;
        }
        BinaryNode<AnyType> ptr = root;
        while (ptr.right != nullNode) {
            ptr = ptr.right;
        }
        root = splay(ptr.element, root);
        return ptr.element;
    }

    public boolean contains(AnyType x) {
        // Remember
        root = splay(x, root);
        if (root.element.compareTo(x) != 0) {
            return false;
        } else return true;
    }

    public AnyType find(AnyType x) {
        root = splay(x, root);
        if (root.element.compareTo(x) != 0) {
            return null;
        } else {
            return x;
        }
    }

    public AnyType find(BinaryNode<AnyType> node){
        if (node == nullNode) {
            return null;
        } else {
            AnyType mid = node.element;
            this.splay(mid, root);
            return mid;
        }
    }

    public int size() {
        return size;
    }

    public void makeEmpty() {
        root = nullNode;
        size = 0;
    }

    public boolean isEmpty() {
        return root == nullNode;
    }

    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType> {
        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
            element = theElement;
            left = lt;
            right = rt;
        }

        AnyType element;    // The data in the node
        BinaryNode<AnyType> left;    // Left child
        BinaryNode<AnyType> right;    // Right child
    }

    protected BinaryNode<AnyType> root;
    private int size;
    private BinaryNode<AnyType> nullNode;
    private BinaryNode<AnyType> header = new BinaryNode<>(null);    // For splay
    private BinaryNode<AnyType> newNode = null;    // Used between different inserts

    private void copy(SplayTree<AnyType> tree) {       // For findByPosition, wee need to keep the integrity of the origin tree. And according to the
                                                       // grammar of Java, the parameter isn't passed by value. On the contray, it's called by refer.
        this.root = tree.root;
        this.size = tree.size;
        this.nullNode = tree.nullNode;
        this.header = tree.header;
        this.newNode = tree.newNode;
    }

    private void printTree(BinaryNode<AnyType> root) {
        if (root != root.left) {
            printTree(root.left);
            System.out.print(root.element + " ");
            printTree(root.right);
        }
    }

    private BinaryNode<AnyType> rotateWithLeftChild(BinaryNode<AnyType> k2) {
        // Remember
        BinaryNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        return k1;
    }
    private BinaryNode<AnyType> rotateWithRightChild(BinaryNode<AnyType> k1) {
        // Remember
        BinaryNode<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        return k2;
    }
}
