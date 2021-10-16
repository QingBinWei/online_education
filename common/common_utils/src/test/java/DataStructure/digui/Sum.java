package DataStructure.digui;

public class Sum {
    static int  tep=0;
    public static void main(String[] args) {
       // int sum = Sum(10, 1);
       // System.out.println(Jie(0));
        //digui(2);
        hannuota(5,'a','b','c');
        System.out.println(tep);
    }

    static void move(char a,char b)
    {
        System.out.println("移动最上层的"+ a+ "到"+ b+ "\t");
    }
    static void hannuota(int n,char a,char b,char c)//主要分析每一大步对于下一步需要走的。
    {
        tep++;
        if(n==1) {move(a,c);}//从a移到c
        else
        {
            hannuota(n-1,a,c,b);//将n-1个从a借助c移到b
            move(a,c); //将第n（最后一个）从a移到c。
            hannuota(n-1,b,a,c);//再将n-1个从b借助a移到c
        }
    }

    static void digui(int num){
        if(num==0){

        }else {
            System.out.println("前:"+num);
            digui(num-1);
            System.out.println("后:"+num);
        }
    }
    private static int Sum(int num,int sum){
        if(num==0){
            return sum;
        }
        sum=sum*num;
        num--;
        return Sum(num,sum);
    }
    private static int Jie(int n){

        if(n==1||n==0){
            return 1;
        }


        return n*Jie(n-1);
    }
}
