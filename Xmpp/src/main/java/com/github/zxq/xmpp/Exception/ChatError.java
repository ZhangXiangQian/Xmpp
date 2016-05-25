package com.github.zxq.xmpp.Exception;

/**
 * Created by zhang on 2016/5/24.
 */
public class ChatError {
    public static ChatError interna_server_error = new ChatError(500,"服务器错误");
    public static ChatError remote_server_timeout = new ChatError(404,"连接超时");
    public static ChatError Network_Disconnected = new ChatError(400,"网络断开连接");
    private int code;
    private String message;
    private ChatError(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
