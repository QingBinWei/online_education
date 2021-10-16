package DataStructure.sparse;

/**
 * ��ά�����ϡ������ת��
 */
public class SparseArray {
    public static void main(String[] args) {
        //�����ά����
        int  array[][]=new int[11][11];
        array[1][2]=1;
        array[2][3]=2;
        for (int arr[]:array){
            for (int item:arr){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //תϡ������
        int sum=0;
        for (int arr[]:array){
            for (int item:arr){
              if(item!=0){
                  sum++;
              }
            }
        }
        int sparse[][]=new int[sum+1][3];
        sparse[0][0]=11;
        sparse[0][1]=11;
        sparse[0][2]=sum;
        int row=1;
        for (int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(array[i][j]!=0){
                    sparse[row][0]=i;
                    sparse[row][1]=j;
                    sparse[row][2]=array[i][j];
                    row++;
                }
            }
        }
        //���ϡ������
        for (int arr[]:sparse){
            for (int item:arr){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        //ϡ������ת����
        array=new int [sparse[0][0]][sparse[0][1]];
        for (int i=1;i<sparse.length;i++){
            array[sparse[i][0]][sparse[i][1]]=sparse[i][2];
        }
        for (int arr[]:array){
            for (int item:arr){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
    }
}
