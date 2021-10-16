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
        System.out.println("走迷宫+++++++++++++++++++++++++++++++++++");
        setWay(map,1,1);
       /* for (int i = 0; i <8 ; i++) {
            for (int j = 0; j <7 ; j++) {
                System.out.print(map[i][j]+"  ");
            }
            System.out.println();
        }*/


    }

    /**
     *  寻找最短路径：把所有路径的长度存储在集合中，比较集合长度就可找到最短路径
     * @param map 迷宫
     * @param i 采用上右下左走法
     * @param 0:未走 1:墙 2:走过的通路 3:走过的不通路
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
