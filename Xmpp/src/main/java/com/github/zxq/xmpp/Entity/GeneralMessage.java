package com.github.zxq.xmpp.Entity;


/**
 * Created by Administrator on 2016/2/19.
 *
 * 聊天消息
 */
public class GeneralMessage {
    public static final int MSG_TEXT = 1;     //文本消息
    public static final int MSG_DOC = 2;      //doc文件
    public static final int MSG_IMG = 3;      //图片
    public static final int MSG_AUDIO = 4;    //语音

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int roomId;
    private String fromName;
    private String toName;
    /** 1 未读 ； 2 已读*/
    private int isRead;
    private String roomName;
    private String content;
    /** 1 发送成功 ， 2 发送失败 */
    private int isSend;

    public int getIsMe() {
        return isMe;
    }

    public void setIsMe(int isMe) {
        this.isMe = isMe;
    }

    /**
     * 1 ：是我 right
     * 2：其他人 left
     */
    private int isMe;

    public int getIsSend() {
        return isSend;
    }

    public void setIsSend(int isSend) {
        this.isSend = isSend;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "GeneralMessage{" +
                "roomId=" + roomId +
                ", fromName='" + fromName + '\'' +
                ", toName='" + toName + '\'' +
                ", isRead=" + isRead +
                ", roomName='" + roomName + '\'' +
                ", content='" + content + '\'' +
                ", isSend=" + isSend +
                ", isMe=" + isMe +
                '}';
    }
}
