package com.github.zxq.xmpp.Entity;

/**
 * Created by Administrator on 2016/2/29.
        * type  类型（四种):pic(图片),doc(文档),text(文字),audio(声音)
        * message data 字典 字符串 data
        */
public class PacketMsg {
    private String type;
    private String message;
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PacketMsg{" +
                "type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}

