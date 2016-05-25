package com.github.zxq.xmpp.Exception;


/**
 * Created by zhang on 2016/5/24.
 */
public class ChatException extends Exception{
    private int code;
    private String message;
    public ChatException(int code,String message){
        this.code = code;
        this.message = message;
    }

    public ChatException(ChatError error){
        this.code = error.getCode();
        this.message = error.getMessage();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
