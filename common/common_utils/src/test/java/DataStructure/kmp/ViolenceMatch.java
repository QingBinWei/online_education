package DataStructure.kmp;

public class ViolenceMatch {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println(str1.charAt(0));
        System.out.println(str1.indexOf(str2));
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);

    }
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int s1Len = s1.length;
        int s2Len = s2.length;
        int i = 0; // i ����ָ�� s1
        int j = 0; // j ����ָ�� s2
        while (i < s1Len && j < s2Len) {// ��֤ƥ��ʱ����Խ��
            if(s1[i] == s2[j]) {//ƥ�� ok
                i++;
                j++;
            } else { //û��ƥ��ɹ�
                //���ʧ�䣨�� str1[i]! = str2[j]������ i = i - (j - 1)��j = 0��
                i = i - (j - 1);
                j = 0;
            }
        }
        //�ж��Ƿ�ƥ��ɹ�
        if(j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }

}
