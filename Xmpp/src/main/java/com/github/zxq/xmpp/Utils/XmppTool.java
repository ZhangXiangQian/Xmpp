package com.github.zxq.xmpp.Utils;



import com.github.zxq.xmpp.Info.ChatRoomInfo;
import com.github.zxq.xmpp.Log.LogUtil;
import com.github.zxq.xmpp.Tools.XMPPHelper;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
 * asmack加载的静态方式
 *
 */
public class XmppTool {
    private static ConnectionConfiguration connConfig;
    private static XmppTool xmppTool = new XmppTool();
    private static XMPPConnection con;
    private static ViConnectionListener connectionListener;

    // 静态加载ReconnectionManager ,重连后正常工作
    static {
        try {
            Class.forName("org.jivesoftware.smack.ReconnectionManager");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public static XmppTool getInstance() {
        return xmppTool;
    }


    // 通过IP+端口，打开连接
    private static void openConnection() {
        try {
            connConfig = new ConnectionConfiguration(ChatRoomInfo.openfireAddress, ChatRoomInfo.openfirePort);

            // 设置登录状态为离线
            connConfig.setSendPresence(false);
            connConfig.setSecurityMode(ConnectionConfiguration.SecurityMode.required);
            //允许重新连接
            connConfig.setReconnectionAllowed(true);
            connConfig.setServiceName(ChatRoomInfo.serverName);
            con = new XMPPConnection(connConfig);
            LogUtil.w("XmppTool......连前接111111111");
            con.connect();
            if (con.isConnected()) {
                LogUtil.w("conn is conn");
                connectionListener = new ViConnectionListener();
                con.addConnectionListener(connectionListener);
                con.addPacketListener(XMPPHelper.getConfig().getPacketListener(),XMPPHelper.getConfig().getPacketTypeFilter());
            }
            LogUtil.i("XmppTool......连后接22222222222");
        } catch (XMPPException e) {
            e.getXMPPError().getCode();
        }
    }

    // 获取连接
    public static XMPPConnection getConnection() {
        if (con == null || !con.isConnected()) {
            openConnection();
        }
        return con;
    }

    // 关闭连接
    public static void closeConnection() {
        if (con != null) {
            // 移除连接监听
            con.removeConnectionListener(connectionListener);
            if (con.isConnected())
                con.disconnect();
            con = null;
        }

    }
}
