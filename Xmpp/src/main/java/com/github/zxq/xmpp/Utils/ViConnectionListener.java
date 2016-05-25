package com.github.zxq.xmpp.Utils;

import com.github.zxq.xmpp.Interface.XmppConnectStatus;
import com.github.zxq.xmpp.Log.LogUtil;
import com.github.zxq.xmpp.Tools.XMPPHelper;

import org.jivesoftware.smack.ConnectionListener;

/**
 * 同账号登录等情况监听
 */
public class ViConnectionListener implements ConnectionListener {


    public ViConnectionListener() {

    }

    @Override
    public void connectionClosed() {
        LogUtil.i("---------connectionClosed---------");
        setConnectStatus(XmppConnectStatus.Close);
    }

    @Override
    public void connectionClosedOnError(Exception e) {
        LogUtil.i("ViConnectionListener-->connectionClosedOnError \n" + e.getMessage());
        if (e.getMessage().contains("conflict")) { //
            setConnectStatus(XmppConnectStatus.RemoteLoginErr);
            //关闭连接，由于是被人挤下线，可能是用户自己，所以关闭连接，让用户重新登录是一个比较好的选择
            // XmppTool.getInstance().closeConnection();
            // 接下来你可以通过发送一个广播，提示用户被挤下线，重连很简单，就是重新登录
        } else if (e.getMessage().contains("Connection timed out")) {// 连接超时
            setConnectStatus(XmppConnectStatus.ConnectTimeOutErr);
        } else if (e.getMessage().contains("system-shutdown")) {// 服务器关闭
            setConnectStatus(XmppConnectStatus.SystemShutdownErr);
        } else{
            setConnectStatus(XmppConnectStatus.Error);
        }
    }

    @Override
    public void reconnectingIn(int arg0) {
        LogUtil.i("ViConnectionListener-->reconnectingIn 正在重新连接......");
        setConnectStatus(XmppConnectStatus.Reconnecting);
    }

    @Override
    public void reconnectionFailed(Exception arg0) {
        LogUtil.i("ViConnectionListener-->reconnectionFailed  重新连接失败......");
        setConnectStatus(XmppConnectStatus.Failure);
    }

    @Override
    public void reconnectionSuccessful() {
        LogUtil.i("连接成功/登陆成功......");
        setConnectStatus(XmppConnectStatus.Successful);
        // 设置登录状态：在线
//        Presence presence = new Presence(Presence.Type.available);
//        XmppTool.getInstance().getConnection(null,null, null).sendPacket(presence);
    }

    private void setConnectStatus(XmppConnectStatus status) {
        if (XMPPHelper.getConfig().getConnectChange() != null) {
            XMPPHelper.getConfig().getConnectChange().onConnectChange(status);
        }
    }
}
