package com.nsl.support;

import java.io.Serializable;

public class Response implements Serializable{

    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
