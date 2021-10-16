package DataStructure.sort;


import java.util.Arrays;

public class BubleSort {
    public static void main(String[] args) {
       /* int arr []=new int[80000];
        for (int i = 0; i <80000 ; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        System.out.println(PubFunc.getStringDate("yyyy-MM-dd HH:mm:ss:sss"));
        shellSort(arr);
        System.out.println(PubFunc.getStringDate("yyyy-MM-dd HH:mm:ss:sss"));*/

        int [] sort={8,9,1,7,2,3,5,};
        insertSort(sort);
        System.out.println(Arrays.toString(sort));


    }

    /**
     * 希尔排序
     * @param sort
     */
    public static void  shellSort(int [] sort){
        int num=sort.length/2;
        while (num>0){
            for (int i = num; i <sort.length ; i++) {
                for (int j = i-num;j >=0 ; j-=num) {
                    if(sort[j]>sort[j+num]){
                        int temp=sort[j+num];
                        sort[j+num]=sort[j];
                        sort[j]=temp;
                    }
                }
            }
            num=num/2;
        }

    }

    /**
     * 希尔排序 位移法
     * @param sort
     */
    public static void  shellSort2(int [] sort){
        int num=sort.length/2;
         while (num>0){
            for (int i = num; i <sort.length ; i++) {
               int j=i;
               int temp=sort[j];
               if(sort[j]<sort[j-num]){
                   while (j-num>=0&&temp<sort[j-num]){
                          sort[j]=sort[j-num];
                          j-=num;
                   }
                   sort[j]=temp;
               }
            }
            num=num/2;
        }
    }

    /**
     * 插入排序
     * 3 1 4 2
     * (1 3) 4 2
     * (1 3 4) 2
     * (1 2 3 4)
     * @param sort
     */
    public static void  insertSort(int [] sort){
        for(int i = 1; i <sort.length; i++){
            int value=sort[i];
            int index=i-1;
            while (index>=0&&value<sort[index]){
             sort[index+1]=sort[index];
             index--;
            }
            if(index+1!=i){
                sort[index+1]=value;
            }
        }
    }

    /**
     * 选择排序 比冒泡排序时间短
     * 选择排序（select sorting）也是一种简单的排序方法。它的基本思想是：第一次从 arr[0]~arr[n-1]中选取最小值，
     * 与 arr[0]交换，第二次从 arr[1]~arr[n-1]中选取最小值，与 arr[1]交换，第三次从 arr[2]~arr[n-1]中选取最小值，与 arr[2]
     * 交换，…，第 i 次从 arr[i-1]~arr[n-1]中选取最小值，与 arr[i-1]交换，…, 第 n-1 次从 arr[n-2]~arr[n-1]中选取最小值，
     * 与 arr[n-2]交换，总共通过 n-1 次，得到一个按排序码从小到大排列的有序序列。
     * @param sort
     */
    public static void  selectSort(int [] sort){
        for(int i = 0; i <sort.length-1 ; i++){
            int minIndex=i;
            int min=sort[i];
            for (int j = i+1; j <sort.length; j++) {
                if(min>sort[j]){
                    min=sort[j];
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                sort[minIndex]=sort[i];
                sort[i]=min;
            }
        }
    }
    /**
     * 冒泡排序
     * @param sort
     */
    public static void  bubbleSort(int [] sort){
        for(int i = 0; i <sort.length-1 ; i++){
            boolean isSort=true;
            for (int j = i+1; j <sort.length; j++) {
                if(sort[i]>sort[j]){
                    int temp=sort[i];
                    sort[i]=sort[j];
                    sort[j]=temp;
                    isSort=false;
                }
            }
            if(isSort){
                break;
            }
            System.out.println(Arrays.toString(sort));
        }
    }
}
