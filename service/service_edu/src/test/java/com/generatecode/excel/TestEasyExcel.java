package com.generatecode.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.ReadListener;

import java.util.ArrayList;
import java.util.List;

public class TestEasyExcel {
    public static void main(String[] args) {
        String fileName="D:\\ADT\\demo.xlsx";
       // EasyExcel.write(fileName,StudentEntity.class).sheet("学生表").doWrite(getData());
        EasyExcel.read(fileName, StudentEntity.class, new ExcelListener()).sheet().doRead();
    }

    private static List<StudentEntity> getData(){
        StudentEntity studentEntity=new StudentEntity();
        List<StudentEntity> list=new ArrayList<>();
        for (int i=0;i<20;i++){
            studentEntity.setSid(i);
            studentEntity.setSname(i+"哈哈");
            list.add(studentEntity);
        }
        return list;
    }
}
