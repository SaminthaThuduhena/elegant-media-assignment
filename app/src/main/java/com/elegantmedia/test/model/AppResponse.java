package com.elegantmedia.test.model;

import com.google.gson.Gson;

public class AppResponse {

    private static final String TAG = AppResponse.class.getSimpleName();

    private int retCode;
    private String retMsg;
    private Object responseInfo = null;

    public AppResponse() {
    }

    public AppResponse(int retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public Object getResponseInfo() {
        return responseInfo;
    }

    public void setResponseInfo(Object responseInfo) {
        this.responseInfo = responseInfo;
    }

    public <T> T getObjectToType(Class<T> t) {
        try{

            Gson gson = new Gson();
            String json = gson.toJson(responseInfo);
            Object value = new Gson().fromJson(json, t);
            return (T)value;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public <T> T getObjectToType(String stringJson, Class<T> t) {
        try{

            Gson gson = new Gson();
            Object value = new Gson().fromJson(stringJson, t);
            return (T)value;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "AppResponse{" +
                "retCode=" + retCode +
                ", retMsg='" + retMsg + '\'' +
                ", responseInfo=" + responseInfo +
                '}';
    }

    public void setError(String message) {
        this.retCode = -1;
        this.retMsg = message;
    }

    public void setError(int code, String message) {
        this.retCode = code;
        this.retMsg = message;
    }

    public boolean isSuccess() {
        return retCode == 200 || retCode == 0;
    }
}
