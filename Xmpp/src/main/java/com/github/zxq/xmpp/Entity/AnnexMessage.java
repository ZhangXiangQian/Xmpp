package com.github.zxq.xmpp.Entity;

/**
 * Created by Administrator on 2016/2/29.
 */
public class AnnexMessage {
    private String type;
    private String name;
    private String effectdata;
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffectdata() {
        return effectdata;
    }

    public void setEffectdata(String effectdata) {
        this.effectdata = effectdata;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
