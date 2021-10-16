package DataStructure.queue;

import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args) {
        //����һ��
        //����һ������
        queue queue = new queue(4);
        char key = ' '; //�����û�����
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //���һ���˵�
        while(loop) {
            System.out.println("s(show): ��ʾ����");
            System.out.println("e(exit): �˳�����");
            System.out.println("a(add): ������ݵ�����");
            System.out.println("g(get): �Ӷ���ȡ������");
            System.out.println("h(head): �鿴����ͷ������");
            key = scanner.next().charAt(0);//����һ���ַ�
            switch (key) {
                case 's':
                    queue.getQueue();
                    break;
                case 'a':
                    System.out.println("����һ����");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //ȡ������
                    try {
                        int res = queue.outQueue();
                        System.out.printf("ȡ����������%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //�鿴����ͷ������
                    try {
                        int res = queue.headQueue();
                        System.out.printf("����ͷ��������%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //�˳�
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("�����˳�~~");
    }

}

class queue{
    private int [] array;
    private int maxSize;
    private int front=0;
    private int rear=0;
    public queue(int size){
        maxSize=size;
        array=new int[size];
    }
    public boolean nullQueue(){
        return front==rear;
    }
    public boolean fullQueue(){
        return (rear+1)%maxSize==front;
    }
    public void addQueue(int num){
        if(fullQueue()){
            System.out.println("������");
            return;
        }
        array[rear]=num;
        rear=(rear+1)%maxSize;
    }
    public int outQueue(){
        if(nullQueue()){
            throw new RuntimeException("����Ϊ��");
        }
        int num=array[front];
        front=(front+1)%maxSize;
        return num;
    }
    public void getQueue(){
        if(nullQueue()){
            System.out.println("����Ϊ��");
            return;
        }
        for (int i =front; i <front+(rear+maxSize-front)%maxSize; i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,array[i%maxSize]);
        }
    }
    // ��ʾ���е�ͷ���ݣ� ע�ⲻ��ȡ������
    public int headQueue() {
        // �ж�
        if (nullQueue()) {
            throw new RuntimeException("���пյģ�û������~~");
        }
        return array[front];
    }
}
