package cn.com.reformer.netty.msg;


/**
 *  Copyright 2017 the original author or authors hangzhou Reformer 
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @author zhangjin
 * @create 2017-05-08
**/
public class MessageID {

    /**
     * 心跳*
     */
    public static final byte MSG_0x01 = 0x01;
    /**
     * 开门*
     */
    public static final byte MSG_0x02 = 0x02;
    /**
     * （3）查询状态
     */
    public static final byte MSG_0x03 = 0x03;

    /**
     * （3）上传状态
     */
    public static final byte MSG_0x04 = 0x04;
    /**
     * 服务端端通用返回指令*
     */
    public static final byte MSG_0x3003 = 0x33;


}