package DataStructure.queue;

import java.util.Scanner;

public class ArrayQueue {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        queue queue = new queue(4);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.getQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.outQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
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
            System.out.println("队列满");
            return;
        }
        array[rear]=num;
        rear=(rear+1)%maxSize;
    }
    public int outQueue(){
        if(nullQueue()){
            throw new RuntimeException("队列为空");
        }
        int num=array[front];
        front=(front+1)%maxSize;
        return num;
    }
    public void getQueue(){
        if(nullQueue()){
            System.out.println("队列为空");
            return;
        }
        for (int i =front; i <front+(rear+maxSize-front)%maxSize; i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,array[i%maxSize]);
        }
    }
    // 显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        // 判断
        if (nullQueue()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return array[front];
    }
}
