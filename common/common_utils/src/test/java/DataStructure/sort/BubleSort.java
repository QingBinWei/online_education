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
     * ϣ������
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
     * ϣ������ λ�Ʒ�
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
     * ��������
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
     * ѡ������ ��ð������ʱ���
     * ѡ������select sorting��Ҳ��һ�ּ򵥵����򷽷������Ļ���˼���ǣ���һ�δ� arr[0]~arr[n-1]��ѡȡ��Сֵ��
     * �� arr[0]�������ڶ��δ� arr[1]~arr[n-1]��ѡȡ��Сֵ���� arr[1]�����������δ� arr[2]~arr[n-1]��ѡȡ��Сֵ���� arr[2]
     * ������������ i �δ� arr[i-1]~arr[n-1]��ѡȡ��Сֵ���� arr[i-1]��������, �� n-1 �δ� arr[n-2]~arr[n-1]��ѡȡ��Сֵ��
     * �� arr[n-2]�������ܹ�ͨ�� n-1 �Σ��õ�һ�����������С�������е��������С�
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
     * ð������
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
