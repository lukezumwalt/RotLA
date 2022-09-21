package Utilities;

// https://www.javatpoint.com/java-program-to-create-and-display-a-circular-linked-list
// A circular linked list is used to cycle the orbiter around a floor by having each element
// point forwards and the final node points back to the first in the list.

public class CircularLinkedList {
    public static class Node {
        String coordinateKey;
        public Node next;

        public Node(String coordinateKey) {
            this.coordinateKey = coordinateKey;
        }

        public String getCoordinateKey(){
            return this.coordinateKey;
        }
    }

    public Node head = null;
    public Node tail = null;

    public void add(String keyCoordinates) {
        Node newNode = new Node(keyCoordinates);
        // Checks if list is empty
        if (head == null) {
            // If empty, head and tail point to new node
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            // If not empty, current tail points to new node
            tail.next = newNode;
            // New node becomes the tail
            tail = newNode;
            // New tail now points to head
            tail.next = head;
        }
    }

    public Node getNext(Node N){
        return N.next;
    }
}
