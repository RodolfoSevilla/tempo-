import java.util.List;

public class DoublyLinkedList {

        //Represent a node of the doubly linked list

        class Node  {
            String data;
            int count;
            Node previous;
            Node next;
            public Node(String data) {
                this.data = data;
                this.count = 1;
                this.previous = this.next = null;
            }
        }

        //Represent the head and tail of the doubly linked list
        Node head, tail = null;
        Node shortestWord = null;
        Node longestWord = null;
        int totalWords = 0;
        int totalLetters = 0;

        //addNode() will add a node to the list
        public void addNode(String data) {
            totalWords++;
            totalLetters += data.length();

            //If list is empty

            if(head == null) {
                Node newNode = new Node(data);
                head = tail = newNode;
                shortestWord = newNode;
                longestWord = newNode;
            }
            else {
                Node curr = tail;
                while (curr != null){
                    if(curr.data.equals(data)) {
                       curr.count++;

                       Node temp;

                       if(curr.previous != null){
                           temp = curr.previous;
                       } else{
                           return;
                       }

                       while ((curr.count > temp.count)){
                           if(temp.previous == null){
                               return;
                           }
                           else {
                               temp = temp.previous;
                           }
                       }

                       Node currPrev = curr.previous;
                       Node  currNext = curr.next != null ? curr.next : null;
                       curr.previous = temp;
                       if(temp.next != curr) {
                           curr.next = temp.next;
                       }
                       temp.next = curr;
                       currPrev.next = currNext;


                       if(currNext != null) {
                           currNext.previous = currPrev;
                       }

                       return;
                    }
                    curr = curr.previous;
                }
                Node newNode = new Node(data);

                //newNode will be added after tail such that tail's next will point to newNode
                tail.next = newNode;
                //newNode's previous will point to tail
                newNode.previous = tail;
                //newNode will become new tail
                tail = newNode;
                if(data.length() < shortestWord.data.length()){
                    shortestWord = newNode;
                }
                if(data.length() > longestWord.data.length()){
                    longestWord = newNode;
                }
            }
        }

        //sorted by word length


    public DoublyLinkedList sortListnumWords() {
        DoublyLinkedList sorted = new DoublyLinkedList();
        Node currNode = head;

        return sorted;
    }


    public boolean searchNode(String value) {
        Node current = head;

        if(head == null) {
            return false;
        }
        while(current != null) {
            if(current.data == value) {
               return true;
            }
            current = current.next;
        }
        return false;
    }
        public void display() {
            Node current = head;
            if(head == null) {
                System.out.println("List is empty");
                return;
            }
            while(current != null) {
                System.out.print(current.data + " " + current.count + "\n");
                current = current.next;
            }
            System.out.println();
        }

    }
