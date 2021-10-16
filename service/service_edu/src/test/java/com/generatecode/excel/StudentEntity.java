package com.generatecode.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class StudentEntity {
    @ExcelProperty(value = "编号",index = 0)
    private Integer sid;
    @ExcelProperty(value = "姓名",index = 1)
    private String sname;
}
