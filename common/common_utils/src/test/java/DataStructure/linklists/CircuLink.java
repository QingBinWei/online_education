package DataStructure.linklists;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Լɪ���������
 */
public class CircuLink {
    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat();
        System.out.println(simpleDateFormat.format(new Date()));
        CiruLinkList circu=new CiruLinkList();
        circu.addNode(25);
        circu.list();
        System.out.println("����####################");
        circu.game(1,2);
       // System.out.println(PubFunc.getStringDate("yyyy-MM-dd HH:mm:ss:sss"));
        System.out.println(circu.digui(3));

    }

}
class CiruLinkList{
    private Boy frist=null;
    public void list(){
        if(frist==null){
            System.out.println("����Ϊ��");
            return;
        }
        Boy temp=frist;
         while (true){
             System.out.println(temp.no);
             if(temp.next==frist){
                 break;
             }
             temp=temp.next;
         }
    }
    public void game(int k,int m){
        int total=0;
        Boy temp=frist;
        while (true){
            total++;
            if(temp.next==frist){
                break;
            }
            temp=temp.next;
        }
        if(k>total||m>total){
            System.out.println("k��m�����Ϲ淶");
            return;
        }
        int num=0;
        temp=frist;
        Boy cur=null;
        while (true){
            if(num==k){
                break;
            }
            cur=temp;
            temp=temp.next;
            num++;
        }
        Boy boy=null;
        while (true){
            for (int i=1;i<m;i++){
                if(i==m-1){
                    //ɾ��ǰһ��λ��
                    boy=cur;
                }
                cur=cur.next;
            }
            System.out.println("���ӱ��:"+cur.no);
            //ɾ����һ��λ��
            cur=cur.next;
            boy.next=boy.next.next;
            if(cur==boy){
                System.out.println("���ӱ��:"+boy.no);
                break;
            }
        }
    }
     public void addNode(int nums){
       if(nums<1){
           System.out.println("����һ����");
           return;
       }
       Boy current=null;
       for (int i = 1; i <=nums ; i++) {
             Boy boy=new Boy(i);
             if(i==1){
                 frist=boy;
                 frist.next=boy;
                 current=frist;
             }else {
                 current.next=boy;
                 boy.next=frist;
                 current=current.next;
             }
       }
     }
      int digui(int i){
        if(i<=1){
            return 1;
        }
        return i*digui(i-1);

    }

}

class Boy{
    public int no;

    public Boy next;

    public Boy(int no) {
        this.no = no;
    }
    // Ϊ����ʾ��������������toString
    @Override
    public String toString() {
        return "HeroNode [no=" + no +  "]";
    }

}
