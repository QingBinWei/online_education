package DataStructure.io;


import java.io.*;

public class FileOperation {
    public static void main(String[] args) {
        final int low = -128;
        InputStream in=null;
        OutputStream out=null;
        BufferedReader bufferedReader=null;
        BufferedWriter bufferedWriter=null;
        try {
            in=new FileInputStream("C:\\Users\\Administrator\\Desktop\\word.txt");
            //字节流读取文件
            byte buf[]=new byte[in.available()];
            in.read(buf);
            ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(buf);
            System.out.println(byteArrayInputStream);
            String str=new String(buf,"utf-8");
            System.out.println(str);
            out=new FileOutputStream("C:\\Users\\Administrator\\Desktop\\read.txt");
            out.write(buf);

            InputStreamReader inputStreamReader=new InputStreamReader(in,"UTF-8");
            bufferedReader=new BufferedReader(inputStreamReader);
           /* FileReader fileReader=new FileReader("C:\\Users\\Administrator\\Desktop\\word.txt");
            bufferedReader=new BufferedReader(fileReader);*/
            OutputStreamWriter outputStreamWriter=new OutputStreamWriter(new FileOutputStream("C:\\Users\\Administrator\\Desktop\\read.txt"));
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                System.out.println(line);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bufferedWriter.close();
                bufferedReader.close();
                in.close();
                out.close();;
            }catch (Exception e){

            }

        }
    }
}
