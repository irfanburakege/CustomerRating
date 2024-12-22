public class LinkedList {
    private Node head;

    private static class Node {
        int customerNo;
        CustomerData data;
        Node next;

        Node(int customerNo, CustomerData data) {
            this.customerNo = customerNo;
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        this.head = null;
    }

    //Adding with order method
    public void addWithOrder(int customerNo, CustomerData data) {
        Node newNode = new Node(customerNo, data);

        if (head == null || head.customerNo > customerNo) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.customerNo < customerNo) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println("Customer No: " + current.customerNo + "\t" + current.data.toString());
            current = current.next;
        }
    }
}
