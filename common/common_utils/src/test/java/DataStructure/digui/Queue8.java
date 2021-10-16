package DataStructure.digui;


/**
 * 八皇后问题
 */
public class Queue8 {
    int max=8;
    public int que[]=new int [max];
    public int arr[][]=new int [max][max];

    static int num=0;
    static int count=0;
    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();




        queue8.check(0);
        System.out.println("有"+num+"种排法");
        System.out.println("一共判断了:"+count+"次");
    }



    public void init(int [] arr){
        System.out.println();
        for (int i = 0; i <arr.length ; i++) {
            System.out.print(arr[i]+" ");
        }
    }

    public void check(int n){
        if(n==max){
            print();
            return;
        }
        for (int i = 0; i <max ; i++) {
            que[n]=i;
            if(judge(n)){
                check(n+1);
            }
        }

    }

    public boolean judge(int n){
        count++;
        for (int i = 0; i <n ; i++) {
            if(que[i]==que[n]||Math.abs(n-i)==Math.abs(que[n]-que[i])){
                return false;
            }
        }
        return true;
    }
    public void print(){
        num++;
        for (int i = 0; i < que.length; i++) {
            System.out.print(que[i] + " ");
        }
        System.out.println();
    }

}
