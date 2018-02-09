package com.unionx.wantuo.utils;

public class ConstantUtil {
		
	
	public static String PHONE_SATATUS_SUCCESS = "0";
	public static String PHONE_SATATUS_ERROR = "1";
	public static String PHONE_PASSWORD_MESSAGE_ERROR = "密码错误！";
	public static String PHONE_SAVE_MESSAGE_SUCCESS = "保存成功！";
	public static String PHONE_SAVE_MESSAGE_ERROR = "保存失败！";
	public static String PHONE_UPDATE_MESSAGE_SUCCESS = "修改成功！";
	public static String PHONE_UPDATE_MESSAGE_ERROR = "修改失败！";
	public static String PHONE_SELECT_MESSAGE_SUCCESS = "查询成功！";
	public static String PHONE_SELECT_MESSAGE_ERROR = "查询失败！";
	public static String PHONE_DELETE_MESSAGE_SUCCESS = "删除成功！";
	public static String PHONE_DELETE_MESSAGE_ERROR = "删除失败！";
	/* 请求 */
	public static int POST_SATATUS_SUCCESS = 0;
	public static int POST_SATATUS_ERROR = 1;
	
	/* 请求信息描述*/
	public static String POST_MESSAGE_SUCCESS = "方法请求成功！";
	public static String POST_MESSAGE_ERROR = "方法请求失败！";
	public static String POST_LOGIN_MESSAGE_SUCCESS = "登录成功！";
	public static String POST_LOGIN_MESSAGE_ERROR = "登录失败！";

	/**
     *性别  
     *sex
     */
    public static final String SEX_MALE ="1";//男
    public static final String SEX_FEMALE ="2";//女

    public static int SATATUS_DELETE = 0;//删除
    public static String SATATUS_DELETE_NAME = "删除";//删除
    public static int SATATUS_NEW = 1;//新建
    public static int SATATUS_CHECK = 2;//审核
    
    public static int WARRANTY_SATATUS_SX = 2;//授权生效
    public static String WARRANTY_SATATUS_SX_NAME = "生效";//授权生效
    
	/* 推送 */
	public static int PUSH_ONE = 1;//个人推送
	public static int PUSH_ALL = 2;//群推
	
    public static String push_qt = "您好，您的孩子已签到";//推送学生签到
    public static String push_zj = "您好，您的孩子表现已总结";//推送学生表现总结
    public static String push_lx = "您好，您的孩子已离校";//推送学生离校
    public static String push_qq = "您好，您的孩子缺勤";//推送学生缺勤
}

