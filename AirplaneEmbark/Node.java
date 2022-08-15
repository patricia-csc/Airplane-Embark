package AirplaneEmbark;

/**
 * Clasa reprezintă un nod din heap ce conține ca tip
 * de date un pasager (clasa Passeneger).
 * @author Patricia POPA-MIHAI
 */
public class Node {
    private Passenger data;
    private int priority;
    Node left;
    Node right;

    Node() {

    }
    Node(Passenger data, int priority) {
        this.data = data;
        this.priority = priority;
        this.left = null;
        this.right = null;
    }

    int getPriority() {
        return this.priority;
    }
    Passenger getData() {
        return this.data;
    }
    String getID() {
        return this.data.getID();
    }

    void setPriority(int priority) {
        this.priority = priority;
    }
    void setData(Passenger data) {
        this.data = data;
    }
    void setParam(Passenger data, int priority) {
        this.data = data;
        this.priority = priority;
    }

    void swapRoot(Node root, Node parent, int numberOfNodes) {
        root.setParam(this.getData(), this.getPriority());
        if(numberOfNodes % 2 == 0) parent.left = null;
        parent.right = null;
    } 
}