import java.util.Scanner;

class Node{
    int data;
    Node next;

    public Node(int data){
        this.data = data;
        this.next =null;
    }
}

public class LinkedListOperations {
    Node head;

    public void displayList() {
        if(head != null){
            Node temp = head;
            while(temp != null){
                System.out.print(temp.data+" ");
                temp = temp.next;
            }
            System.out.println();
        } else{
            System.out.println("Linked List is empty");
        }
    }

    public void insertAtEnd(int data) {
        if(head == null){
            head = new Node(data);
        } else{
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
    }

    public void insertAtFront(int data) {
        Node newNode = new Node(data);
        if(head == null){
            head = newNode ;
        } else{
            newNode.next = head;
            head = newNode;
        }
    }

    public static void main(String[] args) {
        LinkedListOperations l1 = new LinkedListOperations();
        Scanner scan = new Scanner(System.in);
        boolean flag = false;
        while (true) {
            System.out.println("1. Insert Node at end");
            System.out.println("2. Insert Node at front");
            System.out.println("3. Insert Node at given position");
            System.out.println("4. Delete Node at end");
            System.out.println("5. Delete node at front");
            System.out.println("6. Delete Node at given position");
            System.out.println("7. Delete given element");
            System.out.println("8. Insert another Linked List between given two nodes");
            System.out.println("9. Display List");
            System.out.println("10. Length");
            System.out.println("11. Search an element");
            System.out.println("12. Exit");
            int option = scan.nextInt();
            int data;
            switch (option) {
                case 1:
                    System.out.println("Enter Value: ");
                    data = scan.nextInt();
                    l1.insertAtEnd(data);
                    break;
                case 2:
                    System.out.println("Enter Value: ");
                    data = scan.nextInt();
                    l1.insertAtFront(data);
                    break;
                case 3:
                    System.out.println("Enter position and Value: ");
                    int pos = scan.nextInt();
                    data = scan.nextInt();
                    l1.insertAtGivenPosition(pos, data);
                    break;
                case 4:
                    l1.deleteAtEnd();
                    break;
                case 5:
                    l1.deleteAtFront();
                    break;
                case 6:
                    System.out.println("Enter position: ");
                    int p = scan.nextInt();
                    l1.deleteAtGivenPosition(p);
                    break;
                case 7:
                    System.out.println("Enter element: ");
                    l1.deleteGivenElement(scan.nextInt());
                    break;
                case 8:
                    System.out.println("Enter start and end elements: ");
                    int start = scan.nextInt();
                    int end = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Enter elements to be inserted: ");
                    String elements = scan.nextLine();
                    String[] ele = elements.split(" ");
                    LinkedListOperations l2 = new LinkedListOperations();
                    for(int i = 0 ; i < ele.length; i++){
                        l2.insertAtEnd(Integer.parseInt(ele[i]));
                    }
                    l1.insertBetweenNodes(start, end, l2.head);
                    break;
                case 9:
                    l1.displayList();
                    break;
                case 10:
                    System.out.println("Length of the list: "+l1.length());
                    break;
                case 11:
                    System.out.println("Enter key: ");
                    l1.search(scan.nextInt());
                    break;
                case 12:
                    flag = true;
                default:
                    break;
            }
            if (flag == true)
                break;
        }
    }

    private void search(int key) {
        boolean flag = false;
        if(head == null){
            System.out.println("List is empty");
        } else{
            Node t = head;
            while(t != null){
                if(t.data == key){
                    flag = true;
                    break;
                }
                t = t.next;
            }
        }
        if(!flag)
            System.out.println("Element not found");
        else
            System.out.println("Element found");
    }

    private void insertBetweenNodes(int start, int end, Node element) {
        Node t = head;
        boolean flag = false;
        if(head == null){
            System.out.println("Linked List is empty");
        } else{
            while(t != null){
                if(t.data == start && t.next != null && t.next.data == end){
                    Node secondList = element;
                    while(secondList.next != null){
                        secondList = secondList.next;
                    }
                    secondList.next = t.next;
                    t.next = element;
                    flag = true;
                    break;
                } else{
                    t = t.next;
                }
            }
        }
        if(!flag)
            System.out.println("Range not found");
    }

    private void deleteGivenElement(int key) {
        boolean flag = false;
        if(head == null){
            flag = true;
            System.out.println("List is empty");
        } else{
            Node t = head;
            if(t.data == key){
                flag = true;
                deleteAtFront();
            } else{
                while(t.next != null){
                    if(t.next.data == key){
                        t.next = t.next.next;
                        flag = true;
                        break;
                    } else{
                        t = t.next;
                    }
                }
                if(flag && t.data == key){
                    flag = true;
                    deleteAtEnd();
                }
            }
        }
        if(!flag)
            System.out.println("Element not found");
    }

    private void deleteAtGivenPosition(int pos) {
        if(head == null){
            System.out.println("Linked List is empty");
        } else if(length() == 1 && pos == 0){
            head = null;
        } else if(pos == length() - 1){
            deleteAtEnd();
        } else if(pos == 0 && length() > 1){
          deleteAtFront();
        } else if(pos >= 1 && pos < length() - 2){
            Node t = head;
            int count = 0;
            while(t.next != null && count < pos - 1){
                ++count;
                t = t.next;
            }
            t.next = t.next.next;
        } else{
            System.out.println("Invalid position");
        }
    }

    private void deleteAtFront() {
        if(head == null){
            System.out.println("Linked List is empty");
        } else if(length() == 1){
            head = null;
        } else{
            head = head.next;
        }
    }

    private void deleteAtEnd() {
        if(head == null){
            System.out.println("Linked List is empty");
        } else if(length()==1){
            head = null;
        } else{
            Node t = head;
            while(t.next != null && t.next.next != null){
                t = t.next;
            }
            t.next = null;
        }
    }

    private int length() {
        int count = 0;
        if(head == null){
            return count;
        } else{
            Node t = head;
            while(t != null){
                ++count;
                t = t.next;
            }
        }
        return count;
    }

    private void insertAtGivenPosition(int pos, int data) {
        if(head != null){
            if(pos == 0){
                insertAtFront(data);
            } else if(pos >= 1 && pos <= length()){
                Node newNode = new Node(data);
                Node t = head;
                int count = 0;
                while(t.next != null && count != pos-1){
                    ++count;
                    t = t.next;
                }
                newNode.next = t.next;
                t.next = newNode;
            } else{
                System.out.println("Invalid position");
            }
        } else{
            System.out.println("Linked List is empty");
        }
    }
}
