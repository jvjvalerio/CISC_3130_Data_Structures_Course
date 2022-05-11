
public class Tree {
    Node root;

    // Tree Node
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Function to insert nodes in level order
    public Node insertLevelOrder(int[] arr, Node root, int i) {
        // Base case for recursion
        if (i < arr.length) {
            Node temp = new Node(arr[i]);
            root = temp;

            // insert left child
            root.left = insertLevelOrder(arr, root.left,
                    2 * i + 1);

            // insert right child
            root.right = insertLevelOrder(arr, root.right,
                    2 * i + 2);
        }
        return root;
    }

    // Function to print tree nodes in InOrder fashion
    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    void postOrder(Node node) {
        if (root != null) {
            inOrder(root.left);
            inOrder(root.right);
            System.out.print(root.data + " ");
        }

    }

    void preOrder(Node node) {
        if (root != null) {
            System.out.print(root.data + " ");
            inOrder(root.left);
            inOrder(root.right);
        }

    }

    public int count(Node root) {
        int count = 0;
        if (root != null) {
            count(root.left);
            count++;
            count(root.right);
        }
        return count;
    }

    // Driver Code
    public static void main(String args[]) throws Exception {
        Tree t2 = new Tree();
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 - 999 };
        System.out.println("inorder traversal:");
        t2.root = t2.insertLevelOrder(arr, t2.root, 0);
        t2.inOrder(t2.root);
        System.out.println("preorder traversal:");
        t2.preOrder(t2.root);
        System.out.println("postorder traversal:");
        t2.postOrder(t2.root);

        int x = t2.count(t2.root);
        System.out.println(" number of nodes left in the tree :" + x);

        int arr2[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, -999, 3, 1, 5, 17, 14, 62,
                -999, 17, 12, 75, 12, 37, 60, 90, 11, 15, 35, 45, 53, 67, 97, 95, -999 };
        Tree t3 = new Tree();
        System.out.println("inorder traversal:");
        t3.root = t3.insertLevelOrder(arr2, t3.root, 0);
        t3.inOrder(t3.root);
        System.out.println("preorder traversal:");
        t3.preOrder(t3.root);
        System.out.println("postorder traversal:");
        t3.postOrder(t3.root);

        int y = t3.count(t3.root);
        System.out.println(" number of nodes left in the tree :" + y);

        int arr3[] = { 150, 40, 60, 39, 34, 27, 10, 82, 15, -999, 43, 6, 9, -999, 14, 15, 13, 17, 18, 12, 16, 94, 46,
                34, 74, -999 };
        Tree t4 = new Tree();
        System.out.println("inorder traversal:");
        t4.root = t4.insertLevelOrder(arr3, t3.root, 0);
        t4.inOrder(t4.root);
        System.out.println("preorder traversal:");
        t4.preOrder(t4.root);
        System.out.println("postorder traversal:");
        t4.postOrder(t4.root);

        int z = t4.count(t4.root);
        System.out.println(" number of nodes left in the tree :" + z);
    }
}
