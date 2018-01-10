package cn.com.reformer.netty.communication;

import cn.com.reformer.netty.bean.BaseParam;
import cn.com.reformer.netty.bean.Client;
import cn.com.reformer.netty.util.SignUtils;
import cn.com.reformer.netty.util.msg.ClientManager;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;


/**
 *  Copyright 2017 the original author or authors hangzhou Reformer 
 * @Description: ${todo}(这里用一句话描述这个类的作用)
 * @author zhangjin
 * @create 2017-05-08
**/
public class CarLockTcpMessageSender extends TCPMessageSender {

    Logger logger = LoggerFactory.getLogger(CarLockTcpMessageSender.class);
    @Autowired(required = true)
    private ClientManager clientManager;

    public CarLockTcpMessageSender(Channel channel) {
        super(channel);
    }

    public CarLockTcpMessageSender() {
        super();
    }

    public void openDoor(String sn) {

        Client client = ClientManager.getClientBySN(sn);

        if(null != client){
            BaseParam baseParam = createBaseParam(sn);

            ChannelHandlerContext channel = client.getChannel();
            if(null != channel){
                String o = new Gson().toJson(baseParam);
                channel.writeAndFlush(o);
            }
            else{
                logger.debug("设备不在线，通道channel为空，执行失败");
            }

        }
        else{
            logger.debug("设备不在线，执行失败");
        }




    }
    public void getStatus(String sn) {

        Client client = ClientManager.getClientBySN(sn);

        if(null != client){
            BaseParam baseParam = createBaseParam2(sn);

            ChannelHandlerContext channel = client.getChannel();
            if(null != channel){
                String o = new Gson().toJson(baseParam);
                channel.writeAndFlush(o);
            }
            else{
                logger.debug("设备不在线，通道channel为空，执行失败");
            }

        }
        else{
            logger.debug("设备不在线，执行失败");
        }




    }
    private BaseParam createBaseParam(String sn) {
        byte cmd=0x02;
        BaseParam baseParam=new BaseParam();
        baseParam.setSn(sn);
        baseParam.setCmd(cmd);
        baseParam.setType((byte) 1);
        int randomDig=nextInt(10000,100000);
        byte[] nonce_byte=int2byte(randomDig);
        String str_nonce = bytesToHexString(nonce_byte);
        baseParam.setNonce(str_nonce);
        baseParam.setSign(SignUtils.getSigin(sn, cmd,str_nonce));
        return baseParam;
    }
    private BaseParam createBaseParam2(String sn) {
        byte cmd=0x03;
        BaseParam baseParam=new BaseParam();
        baseParam.setSn(sn);
        baseParam.setCmd(cmd);
        int randomDig=nextInt(10000,100000);
        byte[] nonce_byte=int2byte(randomDig);
        String str_nonce = bytesToHexString(nonce_byte);
        baseParam.setNonce(str_nonce);
        baseParam.setSign(SignUtils.getSigin(sn, cmd, str_nonce));
        return baseParam;
    }
    //大端模式
    public static byte[] int2byte(int res) {
        byte[] targets = new byte[4];

        targets[3] = (byte) (res & 0xff);// 最低位
        targets[2] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[1] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[0] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    public int nextInt(final int min, final int max) {

        Random rand = new Random();
        int tmp = Math.abs(rand.nextInt());

        return tmp % (max - min + 1) + min;

    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }
}
