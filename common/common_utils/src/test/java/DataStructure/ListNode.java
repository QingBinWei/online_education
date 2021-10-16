package DataStructure;

import java.util.LinkedList;

public class ListNode{
    public static void main(String[] args) {
        LinkedList list=new LinkedList();
        Node node=new Node(1);
        Node node1=new Node(4);
        Node node3=new Node(11);
        Node node4=new Node(12);
        linklist  lk=new linklist();
        lk.addNode(node);
        lk.addNode(node1);
        Node node7 = lk.delNode(lk.getNode());
        linklist  lk1=new linklist();
        lk1.addNode(node3);
        lk1.addNode(node4);
        Node node8 = lk.delNode(lk1.getNode());
        /*Node node6 = lk1.mergeTwoLists(node7, node8);*/
        Node node16 = lk1.delNode(node7, node8);


    }
}
class linklist {
    Node head=new Node(0);
    public Node getNode(){
        return head;
    }
    public void addNode(Node node) {
        Node temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }
    public Node delNode(Node l1, Node l2) {
        Node p1=l1;
        Node p2=l2;
        Node p3=new Node(0);
        Node p=p3;
        while (p1==null){
            while (p2==null){
                if(p1.val<=p2.val){
                    p.next=p1;
                    p1=p1.next;
                }
            }
        }
        while(true){
            if(p1==null&&p2==null){
                break;
            }else if(p1!=null&&p2==null){
                p.next=p1;
                break;
            }else if(p1==null&&p2!=null){
                p.next=p2;
                break;
            }else if(p1.val<=p2.val){
                p.next=p1;
                p1=p1.next;
            }else{
                p.next=p2;
                p2=p2.next;
            }
            p=p.next;
        }

        return p3.next;
    }

    public Node delNode(Node node) {
        Node node1=node.next;
        return node1;
    }


    public Node mergeTwoLists(Node l1, Node l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        Node head = null;
        if (l1.val <= l2.val){
            head = l1;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1, l2.next);
        }
        return head;
    }
}
class Node{
    int val;
    Node next;

    Node(int x){
        val = x;
    }

}



