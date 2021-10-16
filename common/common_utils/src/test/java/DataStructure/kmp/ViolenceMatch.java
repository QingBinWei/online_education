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
        int i = 0; // i 索引指向 s1
        int j = 0; // j 索引指向 s2
        while (i < s1Len && j < s2Len) {// 保证匹配时，不越界
            if(s1[i] == s2[j]) {//匹配 ok
                i++;
                j++;
            } else { //没有匹配成功
                //如果失配（即 str1[i]! = str2[j]），令 i = i - (j - 1)，j = 0。
                i = i - (j - 1);
                j = 0;
            }
        }
        //判断是否匹配成功
        if(j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }

}
