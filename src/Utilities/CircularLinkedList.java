package Utilities;

// https://www.javatpoint.com/java-program-to-create-and-display-a-circular-linked-list
// A circular linked list is used to cycle the orbiter around a floor by having each element
// point forwards and the final node points back to the first in the list.

public class CircularLinkedList {
    public class Node {

        // CONSTRUCTORS
        public Node(String coordinateKey) {
            this.coordinateKey = coordinateKey;
        }

        // PUBLIC ATTRIBUTES
        public String coordinateKey;
        public Node next;
    }

    // PUBLIC ATTRIBUTES
    public Node head = null;
    public Node tail = null;

    // PUBLIC METHODS
    public void add(String keyCoordinates) {
        Node newNode = new Node(keyCoordinates);
        // Checks if list is empty
        if (head == null) {
            // If empty, head and tail point to new node
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            // If not empty, tail points to new node
            tail.next = newNode;
            // New node becomes the tail
            tail = newNode;
            // Tail now points to head
            tail.next = head;
        }
    }

    public String getNext(Node N) {
        return N.next.coordinateKey;
    }
}
