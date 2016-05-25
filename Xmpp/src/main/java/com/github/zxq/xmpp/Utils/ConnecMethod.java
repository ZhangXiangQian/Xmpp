package com.github.zxq.xmpp.Utils;

import android.content.Context;


import com.github.zxq.xmpp.Exception.ChatError;
import com.github.zxq.xmpp.Exception.ChatException;
import com.github.zxq.xmpp.Info.ChatRoomInfo;
import com.github.zxq.xmpp.Log.LogUtil;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.FormField;
import org.jivesoftware.smackx.MessageEventManager;
import org.jivesoftware.smackx.MessageEventNotificationListener;
import org.jivesoftware.smackx.muc.InvitationListener;
import org.jivesoftware.smackx.muc.MultiUserChat;

import java.util.Iterator;

/**
 * XMPP连接方法工具类
 *
 * @author ZhangXiangQian
 */
public class ConnecMethod {

    /**
     * 登陆方法
     * 这部分相对简单，登陆之前判断XMPP是否连接，登陆成功将用户状态改为在线
     *
     * @param account
     * @param password
     * @return
     */
    public static void login(String account, String password) throws ChatException{
        ChatRoomInfo.account = account;
        LogUtil.w("login is error");
        if (XmppTool.getInstance().getConnection() == null) {// 如果网络没有连接上
            throw new ChatException(ChatError.Network_Disconnected);
        }
        /** 登录
         *  SASL的认证方式 在客户端要声明，客户端必须支持PLAIN模式--用户名和密码通过网络传输，不安全
         *  关于SASL认证，有兴趣的可以查询下其他资料
         * */
        SASLAuthentication.supportSASLMechanism("PLAIN", 0);
        try {
            XmppTool.getInstance().getConnection().login(account, password);
        } catch (XMPPException e) {
            e.printStackTrace();
            if(e != null && e.getXMPPError() != null){
                throw new ChatException(e.getXMPPError().getCode(),e.getMessage());
            }
        }
        // 设置登录状态：在线
        Presence presence = new Presence(Presence.Type.available);
        XmppTool.getInstance().getConnection().sendPacket(presence);
    }

    static MultiUserChat muc = null;

    /**
     * 登录时，获取群聊天室名，加入所有以存的群聊，不用点击时再加入,可以实现一登录就获取离线信息，和接收在线信息
     * 房间ID  加上  @conference.localhost
     * 在这里 即使我们加入成功，后台依旧会显示 “No response from server.” 的警告，但目前对后续基本没有影响 可以忽略
     */
    public static void loginJoinRoomChat(PacketListener packetListener, String account, String roomId) throws XMPPException {
        LogUtil.i("loginJoinRoomChat...\nroomID = " + roomId + ChatRoomInfo.serverRoomName + "\naccount:" + account + ChatRoomInfo.serverName);
        muc = new MultiUserChat(XmppTool.getInstance().getConnection(), roomId + ChatRoomInfo.serverRoomName);
        muc.addMessageListener(packetListener);
        muc.join(ChatRoomInfo.account + "@" + ChatRoomInfo.serverName);
        //  muc.addMessageListener(new ChatPacketListener(context));
        LogUtil.i(roomId + " join success");
    }


    /**
     * 创建聊天室
     *
     * @param name            聊天室名称 加后缀 @conference.localhost
     * @param description     描述
     * @param attachment_id   附件ID
     * @param contacts        联系人 ID
     * @param constraint_type 加入限制类型 为NULL 没有 限制
     * @param user_id         用户ID
     * @param top             是否置顶  1为置顶 默认为0
     */
    public static void createRoom(PacketListener packetListener, String name, String description, String attachment_id, String contacts, String constraint_type, String user_id, int top) throws XMPPException {

        MultiUserChat muc = new MultiUserChat(XmppTool.getInstance().getConnection(), name + ChatRoomInfo.serverRoomName);
        muc.addMessageListener(packetListener);
        muc.create(ChatRoomInfo.account);
        Form form = muc.getConfigurationForm();
        Form submitForm = form.createAnswerForm();
        Iterator it = form.getFields();
        while (it.hasNext()) {
            FormField field = (FormField) it.next();
            if (!FormField.TYPE_HIDDEN.equals(field.getType()) && field.getVariable() != null) {
                submitForm.setDefaultAnswer(field.getVariable());
            }
        }
        muc.sendConfigurationForm(form);
    }

    /**
     * 发送消息
     *
     * @param roomId  房间号
     * @param sendMsg
     * @return
     */
    public static void sendMessageEventRequest(String from, int roomId, String sendMsg) throws ChatException {
        Chat chat1 = XmppTool.getInstance().getConnection().getChatManager().createChat(roomId + ChatRoomInfo.serverRoomName, null);
        MessageEventManager messageEventManager = new MessageEventManager(XmppTool.getInstance().getConnection());
        messageEventManager
                .addMessageEventNotificationListener(new MessageEventNotificationListener() {
                    public void deliveredNotification(String from, String packetID) {
                        LogUtil.i("From: " + from + " PacketID: " + packetID + "(delivered)");
                    }

                    public void displayedNotification(String from, String packetID) {
                        LogUtil.i("From: " + from + " PacketID: " + packetID + "(displayed)");
                    }

                    public void composingNotification(String from, String packetID) {
                        LogUtil.i("From: " + from + " PacketID: " + packetID + "(composing)");
                    }

                    public void offlineNotification(String from, String packetID) {
                        LogUtil.i("From: " + from + " PacketID: " + packetID + "(offline)");
                    }

                    public void cancelledNotification(String from, String packetID) {
                        LogUtil.i("From: " + from + " PacketID: " + packetID + "(cancelled)");
                    }
                });
        // Create the message to send with the roster

        Message msg = new Message();
        // msg.setSubject("Any subject you want");
        msg.setBody(sendMsg);
        msg.setFrom(from + "@" + ChatRoomInfo.serverName);
        LogUtil.i("fromName:" + from + ChatRoomInfo.serverName + "\nroomid:" + roomId + ChatRoomInfo.serverRoomName + "\nmsg:" + sendMsg);
        try {
            chat1.sendMessage(msg);
        } catch (XMPPException e) {
            e.printStackTrace();
            if(e != null && e.getXMPPError() != null){
                throw new ChatException(e.getXMPPError().getCode(),e.getMessage());
            }
        }
    }

    /**
     * 被邀请事件  监听
     */
    public static void addInvitationListener(Context context) {
        MultiUserChat.addInvitationListener(XmppTool.getInstance().getConnection(), new InvitationListener() {
            @Override
            public void invitationReceived(Connection connection, String s, String s1, String s2, String s3, Message message) {
                LogUtil.i("接收到聊天室的邀请......");
            }
        });
    }
}