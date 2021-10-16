package DataStructure.linklists;

public class SingLinkedList {
    public static void main(String[] args) {
        Node node1=new Node(1,"宋江");
        Node node2=new Node(2,"卢俊义");
        Node node3=new Node(3,"吴用");
        Node node4=new Node(4,"林冲");
        Node node5=new Node(5,"鲁智深");
        Node node6=new Node(6,"王英");



        LinkedList linkedList=new LinkedList();
        linkedList.addNode(node1);
        linkedList.addNode(node2);
        linkedList.addNode(node3);
        linkedList.list();
        System.out.println("====================");
        LinkedList linkedList2=new LinkedList();
        linkedList2.addNode(node4);
        linkedList2.addNode(node5);
        linkedList2.addNode(node6);
        linkedList2.list();
        linkedList.contactNode(linkedList.getNode(),linkedList2.getNode());


       /* Node node6 = linkedList.getNode( linkedList.getNode());
        System.out.println("反转之后的");
        linkedList.list();*/


       /* linkedList.addNodeByNo(node4);
        linkedList.list();
        linkedList.updateNodeByNo(node2,node5);
        linkedList.list();
        linkedList.delNodeByNo(node3);
        linkedList.list();
        int length = linkedList.getLength(linkedList.getNode());
        System.out.println(length);
        Node node = linkedList.getNodeByIndex(1, linkedList.getNode());
        System.out.println(node);
        linkedList.list();*/




    }
}
class LinkedList{
    private Node head=new Node(0,"");
    public Node getNode(){
        return head;
    }
    public void addNode(Node node){
        Node temp=head;
        while (true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=node;
    }
    public void  addNodeByNo(Node node){
        Node temp=head;
        while (true){
            if(temp.next==null){
                break;
            }
            if(node.no==temp.next.no){
                System.out.println("编号已存在");
                break;
            }
            if(node.no<temp.next.no){
                node.next=temp.next;
                temp.next=node;
                System.out.println("插入一个节点后");
                break;
            }
            temp=temp.next;
        }
    }
    public void  updateNodeByNo(Node node,Node upnode){
        Node temp=head;
        while (true){
            if(temp.next==null){
                break;
            }
            if(node.no==temp.next.no){
                upnode.next=temp.next.next;
                temp.next=upnode;
                System.out.println("将原编号"+node.no+"修改为"+upnode.no);
                break;
            }
            temp=temp.next;
        }

    }
    public void  delNodeByNo(Node node){
        Node temp=head;
        while (true){
            if(temp.next==null){
                break;
            }
            if(node.no==temp.next.no){
                temp.next=temp.next.next;
                System.out.println("删除一个节点后");
                break;
            }

            temp=temp.next;
        }

    }
    public int getLength(Node node){
        if(node.next==null){
            return 0;
        }
        int num=0;
        while (node.next!=null){
            node=node.next;
            num++;
        }
        return num;
    }
    public Node getNodeByIndex(int index,Node node){
        if(node.next==null){
            return null;
        }
        int num=0;
        Node reNode=null;
        Node temp=node;
        while (node.next!=null){
            node=node.next;
            num++;
        }
        int order=num-index+1;
        if(order<0||order>num){
            return null;
        }
        num=0;
        while (temp.next!=null){
            temp=temp.next;
            num++;
            if(num==order){
                reNode=node;
            }
        }
        return reNode;
    }
    public Node getNode(Node node){
        java.util.LinkedList list=new java.util.LinkedList();
        list.add(1);
        list.add(3);
        list.add(1,2);

        System.out.println("--------------------");
        Node cur=node.next;
        Node next=null;
        Node reverseNde=new Node(0,"");
        if(cur==null||cur.next==null){
            return node;
        }
        while (cur!=null){
           next=cur.next;
           cur.next= reverseNde.next;
           reverseNde.next=cur;
           cur=next;
        }
        node.next=reverseNde.next;
        return  node;
    }
    public void list(){
        Node temp=head.next;
        if(temp==null){
            System.out.println("链表为空");
            return;
        }
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }

    public Node contactNode1(Node node, Node node1) {
        Node newNode=new Node(0,"");
        Node next=null;
        Node cur=node.next;
        while (node.next!=null){
            while (node1.next!=null){
                if(node.next.no<=node1.next.no){
                    next=cur.next;
                    node.next=newNode.next;
                    newNode.next=node;
                    cur=next;
                    break;
                }
                if(node.next.no>node1.next.no){
                    newNode.next=node1.next;
                    newNode=newNode.next;
                }
                node1=node1.next;
            }
            node=node.next;
        }

        return newNode;

    }
    public Node contactNode(Node node, Node node1) {
        if (node == null) return node1;
        if (node1 == null) return node;
        Node temp=new Node(0,"");
        if(node.no==0){
            node=node.next;
        }
        if(node1.no==0){
            node1=node1.next;
        }

        if(node.no<=node1.no){
            temp=node;
            temp.next=contactNode(node.next,node1);
        }
        if(node.no>node1.no){
            temp=node1;
            temp.next=contactNode(node,node1.next);
        }

        return temp;
    }
}


class Node{
    public int no;
    public String name;
    public Node next;
    public Node(int no,String name){
        this.no=no;
        this.name=name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
