package DataStructure.digui;

public class HanTower {
    static  int num=0;
    public static void main(String[] args) {
       remove(5,"A","B","C");
        System.out.println(num);
    }
    static void remove(int n,String a,String b,String c){
        num++;
        if(n==1){
            System.out.println(a+"===>"+c);
        }else {
            remove(n-1,a,c,b);
            System.out.println(a+"===>"+c);
            remove(n-1,b,a,c);
        }

    }
}
