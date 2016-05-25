package com.github.zxq.xmpp.Info;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/2/18.
 */
public class ChatRoomInfo {
    public static String openfireAddress      = "10.7.7.100";
    public static int openfirePort            = 5222;
    public static String serverName           = "localhost";
    public static String serverRoomName       = "@conference.localhost";
    public static final boolean isChatRoom    = false;
    public static final String RECEIVER_MSG   = "android.bankeys.chatroom.receiver.msg";
    public static final String RECEIVER_FILE  = "android.bankeys.chatroom.receiver.file";
    public static final String RECEIVER_SERNO = "android.bankeys.chatroom.receiver.serno";
    public static final String RECEIVER_ORDER = "android.bankeys.chatroom.receiver.order";

    public static final String KEY_FROMMAME   = "fromName";
    public static final String KEY_TONAME     = "toName";
    public static final String KEY_MSG        = "msg";
    public static final String KEY_ROOMID     = "roomId";
    public static String account;
    /** 数据库名称 **/
    public static final String DB_NAME        = "GeneralChatRoom";

    public static String UserName;


    // 获取的离线时间，和真正时间差8个小时，在这里补回去，返回真正的时间
    public static String getOffDate(String qDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(qDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l1 = date.getTime() + 28800000;// 加上8小时的毫秒
        date.setTime(l1);
        String time = dateFormat.format(date);
        return time;
    }
}
