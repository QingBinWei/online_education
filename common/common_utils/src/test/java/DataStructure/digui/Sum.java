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
        System.out.println("�ƶ����ϲ��"+ a+ "��"+ b+ "\t");
    }
    static void hannuota(int n,char a,char b,char c)//��Ҫ����ÿһ�󲽶�����һ����Ҫ�ߵġ�
    {
        tep++;
        if(n==1) {move(a,c);}//��a�Ƶ�c
        else
        {
            hannuota(n-1,a,c,b);//��n-1����a����c�Ƶ�b
            move(a,c); //����n�����һ������a�Ƶ�c��
            hannuota(n-1,b,a,c);//�ٽ�n-1����b����a�Ƶ�c
        }
    }

    static void digui(int num){
        if(num==0){

        }else {
            System.out.println("ǰ:"+num);
            digui(num-1);
            System.out.println("��:"+num);
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
