package org.sunger.net.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/18.
 */
public class ErrorEntity implements Serializable {
    private int error_code;

    private String error;

    private String request;

    public void setError_code(int error_code){
        this.error_code = error_code;
    }
    public int getError_code(){
        return this.error_code;
    }
    public void setError(String error){
        this.error = error;
    }
    public String getError(){
        return this.error;
    }
    public void setRequest(String request){
        this.request = request;
    }
    public String getRequest(){
        return this.request;
    }
}
