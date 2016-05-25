package com.github.zxq.xmpp.Interface;

/**
 * Created by zhang on 2016/4/20.
 */
public enum XmppConnectStatus {
    //异地登录
    RemoteLoginErr,
    //连接超时
    ConnectTimeOutErr,
    //服务器关闭
    SystemShutdownErr,
    //正在重新连接
    Reconnecting,
    //连接成功
    Successful,
    //失败
    Failure,
    //其它错误
    Error,
    //关闭
    Close;

}
