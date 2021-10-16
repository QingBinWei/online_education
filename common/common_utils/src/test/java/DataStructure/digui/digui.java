package DataStructure.digui;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class digui {
    static  int num=0;
    public static void main(String[] args) throws ParseException {

        List list=new ArrayList();
        list.add(1);
        list.add(2);
        Arrays.toString(list.toArray());
        System.out.println( Arrays.toString(list.toArray()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss");
        String format = simpleDateFormat.format(new Date());
        Date parse = simpleDateFormat.parse(format);
        System.out.println(parse.toString());

       /* int arr []=new int[80000];
        for (int i = 0; i <80000 ; i++) {
            arr[i]=(int)(Math.random()*80000);
        }
        System.out.println(simpleDateFormat.format(new Date()));
        buble(arr);
        System.out.println(simpleDateFormat.format(new Date()));*/

        int [] nums={2,6,1,8,11,9,4,1,0,44,66};
        shellSort3(nums);
        System.out.println(Arrays.toString(nums));

        //汉诺塔
        remove(2,"a","b","c");
        System.out.println(num);
    }

    private static void remove(int n, String a, String b, String c) {
        num++;
        if(n==1){
            System.out.println(a+"==============>"+c);
        }else {
            remove(n-1,a,c,b);
            System.out.println(a+"============>"+c);
            remove(n-1,b,a,c);
        }
    }


    public static void shellSort3(int[] sort){
        int num=sort.length/2;
        while (num>0){
            for (int i = num; i <sort.length ; i++) {
                int index=i;
                int value=sort[i];
                if(sort[i-num]>sort[i]){
                    while (index-num>=0&&sort[index-num]>value){
                        sort[index]=sort[index-num];
                        index-=num;
                    }
                    if(index!=num){
                        sort[index]=value;
                    }
                }

            }
            num/=2;
        }
    }

    /**
     * 希尔排序
     * @param sort
     */
    public static void shellSort(int [] sort){
        int num=sort.length/2;
        while (num>0){
            for (int i = num; i <sort.length ; i++) {
                for (int j = i-num; j >=0; j-=num) {
                    if(sort[j]>sort[j+num]){
                        int tem=0;
                        tem=sort[j];
                        sort[j]=sort[j+1];
                        sort[j+1]=tem;
                    }
                }

            }
            num/=2;
        }
    }

    /**
     * 希尔排序优化 加入插入排序
     * @param sort
     */
    public static void shellSort2(int [] sort){
        int num=sort.length/2;
        while (num>0){
            for (int i = num; i <sort.length ; i++) {
                int index=i;
                int value=sort[i];
                if(sort[i]<sort[i-num]){
                    while (index-num>=0&&value<sort[index-num]){
                        sort[index]=sort[index-num];
                        index-=num;
                    }
                    if(index!=i){
                        sort[index]=value;
                    }
                }
            }
            num/=2;
        }
    }

    /** 2 6 1
     * 插入排序
     */
    public static void insert(int [] arg){
        for (int i = 1; i <arg.length ; i++) {
            int index = i-1;
            int value=arg[i];
            while (index >=0&&arg[index]>value){
                arg[index+1]=arg[index];
                index--;
            }
            if(index!=i-1){
                arg[index+1]=value;
            }
        }
    }


    /**
     * 冒泡排序
     */
      public static void buble(int [] arg){
          boolean flag=true;
          for (int i = 0; i <arg.length-1 ; i++) {
              for (int j = 0; j <arg.length-i-1 ; j++) {
                    if(arg[j]>arg[j+1]){
                        flag=false;
                        int temp=0;
                        temp=arg[j];
                        arg[j]=arg[j+1];
                        arg[j+1]=temp;
                    }
              }
              if(flag){
                  break;
              }

          }
      }

    /**
     * 递归
     * @param num
     * @return
     */
    public static int di(int num){
        if(num<=1){
            return num;
        }
        return di(num-1)*num;
    }
}
