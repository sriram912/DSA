class FlightNode {
    int flightID, height;
    FlightNode left, right;

    FlightNode(int id) {
        flightID = id;
        height = 1;
    }
}

public class AirLinkAVL {

    int height(FlightNode node) {
        return (node == null) ? 0 : node.height;
    }

    int getBalance(FlightNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    FlightNode rightRotate(FlightNode y) {
        FlightNode x = y.left;
        FlightNode t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    FlightNode leftRotate(FlightNode x) {
        FlightNode y = x.right;
        FlightNode t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    FlightNode insert(FlightNode node, int id) {

        if (node == null)
            return new FlightNode(id);

        if (id < node.flightID)
            node.left = insert(node.left, id);
        else if (id > node.flightID)
            node.right = insert(node.right, id);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && id < node.left.flightID)
            return rightRotate(node);

        if (balance < -1 && id > node.right.flightID)
            return leftRotate(node);

        if (balance > 1 && id > node.left.flightID) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && id < node.right.flightID) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    void inorder(FlightNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.flightID + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        AirLinkAVL tree = new AirLinkAVL();
        FlightNode root = null;

        root = tree.insert(root, 101);
        root = tree.insert(root, 105);
        root = tree.insert(root, 103);
        root = tree.insert(root, 110);

        System.out.println("Sorted Flight Schedule:");
        tree.inorder(root);
    }
}