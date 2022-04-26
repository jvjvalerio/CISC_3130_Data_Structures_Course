
public class Assignment_4 {

    class Node {
        int value;
        Node left;
        Node right;

        Node (int value) {
            this.value = value;
            this.right = null;
            this.left = null;
        }
    }

    class BinaryTree {
        Node root;

        BinaryTree (int key) {
            this.root = new Node(key);
        }

        BinaryTree() {
            this.root = null;
        }
    }



    public static void main(String[] args) {
        BinaryTree setOne = new BinaryTree();

        setOne.root = new Node(1);
        setOne.root.left = new Node(2);


    }
}