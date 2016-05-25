package com.github.zxq.xmpp.Log;

import android.util.Log;

public class LogUtil
{

	public static String customTagPrefix = "BANKEYS_GENERAL_LOG_CHATROOM";

	public static boolean isPrintInfo = true;

	/**
	 * 错误信息
	 * 
	 * @param msg
	 */
	public static void e(String msg)
	{
		String tag = generateTag(getCallerStackTraceElement());
		if (isPrintInfo)
			Log.e(tag, msg);
	}

	/**
	 * 
	 * @param msg
	 */
	public static void i(String msg)
	{
		String tag = generateTag(getCallerStackTraceElement());
		if (isPrintInfo)
			Log.i(tag, msg);
	}

	/**
	 * debug调试信息
	 * 
	 * @param msg
	 */
	public static void d(String msg)
	{
		String tag = generateTag(getCallerStackTraceElement());
		if (isPrintInfo)
			Log.d(tag, msg);
	}

	/**
	 * 警告
	 * 
	 * @param msg
	 */
	public static void w(String msg)
	{
		String tag = generateTag(getCallerStackTraceElement());
		if (isPrintInfo)
			Log.w(tag, msg);
	}

	private static StackTraceElement getCallerStackTraceElement()
	{
		return Thread.currentThread().getStackTrace()[4];
	}

	private static String generateTag(StackTraceElement caller)
	{
		String tag = "%s.%s(L:%d)";
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
		tag = isNull(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
		return tag;
	}
	/**
	 * 查看字符串是否为 Null
	 *
	 * @param params
	 * @return TRUE : 为空,FALSE 不为
	 */
	private static boolean isNull(String... params) {
		if (params == null) {
			return true;
		}
		for (String param : params) {
			if (param == null || param.equals("")) {
				return true;
			}
		}
		return false;
	}
}
