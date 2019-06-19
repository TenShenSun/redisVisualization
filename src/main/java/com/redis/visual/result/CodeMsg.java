package com.redis.visual.result;

public class CodeMsg {
    private int code;
    private String msg;

    /** 成功 code:0 mag:"OK" */
    public static CodeMsg SUCCESS = new CodeMsg(0,"ok");



    /** key值不能为空 code:500100 msg:"key值不能为空" */
    public static CodeMsg KEY_ERROR = new CodeMsg(500100,"key值不能为空");
    /** hash类型的values不能为为空 code:500200 msg:"hash类型的values不能为空" */
    public static CodeMsg KEY_FIELD_VALUE_ERROR = new CodeMsg(500200,"hash类型的values不能为空");

    public CodeMsg(){}

    public CodeMsg(int code,String msg){
        this.code = code;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

