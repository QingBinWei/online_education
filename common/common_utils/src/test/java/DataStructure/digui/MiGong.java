package DataStructure.digui;

public class MiGong {
    static int [][] map=new int [8][7];
    public static void main(String[] args) {
        for (int i = 0; i <7 ; i++) {
            map[0][i]=1;
            map[7][i]=1;
        }
        for (int i = 0; i <8 ; i++) {
            map[i][0]=1;
            map[i][6]=1;
        }
        map[2][1]=1;
        map[2][2]=1;



        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println("���Թ�+++++++++++++++++++++++++++++++++++");
        setWay(map,1,1);
       /* for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }*/


    }

    /**
     *  Ѱ�����·����������·���ĳ��ȴ洢�ڼ����У��Ƚϼ��ϳ��ȾͿ��ҵ����·��
     * @param map �Թ�
     * @param i �������������߷�
     * @param 0:δ�� 1:ǽ 2:�߹���ͨ· 3:�߹��Ĳ�ͨ·
     * @return
     */
    public static boolean setWay(int [][] map,int i,int j){
        if(map[6][5]==2){
            return true;
        }else {
            if(map[i][j]==0){
                map[i][j]=2;
                print();
                if(setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else {
                    map[i][j]=3;
                    return false;
                }
            }else {
                return false;
            }
        }
    }

    static void print(){
        for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }
        System.out.println("****************************************");

    }
}
