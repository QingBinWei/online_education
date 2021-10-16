package com.guigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data  //shengcheng get set
@AllArgsConstructor
public class ReturnResult {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();
    private ReturnResult(){}

    public static ReturnResult ok(){
        ReturnResult returnResult=new ReturnResult();
        returnResult.setCode(ResultCode.SUCCESS);
        returnResult.setMessage("success");
        returnResult.setSuccess(true);
        return returnResult;
    }

    public static ReturnResult fail(){
        ReturnResult returnResult=new ReturnResult();
        returnResult.setCode(ResultCode.ERROR);
        returnResult.setMessage("fail");
        returnResult.setSuccess(false);
        return returnResult;
    }

    public ReturnResult success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public ReturnResult message(String message){
        this.setMessage(message);
        return this;
    }

    public ReturnResult code(Integer code){
        this.setCode(code);
        return this;
    }

    public ReturnResult data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public ReturnResult data(Map<String, Object> map){
        this.setData(map);
        return this;
    }
}
