package com.fengwenyi.example.miaosha.exception;

import com.fengwenyi.example.miaosha.result.CodeMsg;

/**
 * @author Wenyi Feng
 * @since 2018-11-29
 */
public class GlobalException extends RuntimeException {

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
