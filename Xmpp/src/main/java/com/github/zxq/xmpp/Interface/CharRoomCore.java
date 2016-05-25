package com.github.zxq.xmpp.Interface;

import org.jivesoftware.smack.XMPPException;

/**
 * Created by Administrator on 2016/2/18.
 */
public interface CharRoomCore {
    /**
     * 连接成功
     */
    public void onConnectSuccessful();
    public void onErrConnect(XMPPException e);
}
