package com.fengwenyi.example.miaosha.result;

/**
 *
 * @author Wenyi Feng
 * @since 2018-11-29
 */
public class CodeMsg {

    // 通用错误吗
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");

    private Integer code;
    private String msg;

    public CodeMsg() {
    }

    public CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public CodeMsg fillArgs(Object ... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, msg);
    }
}
