package com.github.zxq.xmpp.Tools;



import com.github.zxq.xmpp.Info.ChatRoomInfo;
import com.github.zxq.xmpp.Interface.ConnectChange;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.PacketFilter;

/**
 * Xmpp参数配置
 * Created by zhang on 2016/4/20.
 */
public class XMPPHelper {
    /**
     * 接收消息
     */
    private PacketListener packetListener;
    /**
     * 过滤消息
     */
    private PacketFilter packetFilter;
    private ConnectChange connectChange;

    public void setOpenfireAddress(String openfireAddress) {
        ChatRoomInfo.openfireAddress = openfireAddress;
    }

    public void setOpenfirePort(int openfirePort) {
        ChatRoomInfo.openfirePort = openfirePort;
    }

    public void setServerName(String serverName) {
        ChatRoomInfo.serverName = serverName;
    }

    public void setServerRoomName(String serverRoomName) {
        ChatRoomInfo.serverRoomName = serverRoomName;
    }

    public ConnectChange getConnectChange() {
        return connectChange;
    }

    public void setConnectChange(ConnectChange connectChange) {
        this.connectChange = connectChange;
    }

    private static XMPPHelper config;

    public static XMPPHelper getConfig() {
        if (config == null) {
            config = new XMPPHelper();
        }
        return config;
    }

    public PacketListener getPacketListener() {
        return packetListener;
    }

    public void setPacketListener(PacketListener packetListener) {
        this.packetListener = packetListener;
    }

    public PacketFilter getPacketTypeFilter() {
        return packetFilter;
    }

    public void setPacketTypeFilter(PacketFilter packetTypeFilter) {
        this.packetFilter = packetTypeFilter;
    }
}
