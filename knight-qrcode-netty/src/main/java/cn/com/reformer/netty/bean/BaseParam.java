package cn.com.reformer.netty.bean;

import cn.com.reformer.netty.msg.MessagePacket;

public class BaseParam  implements MessagePacket{
   
	private int type;//类型  1心跳 2 遥控开  3 查询状态
	private  String sn; //自己的id
	private  String version;
	private String nonce;
	private String sign;
	private  String sentid; //发送的用户

	private  String sentMsg;  // 发送的消息
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getSentid() {
		return sentid;
	}
	public void setSentid(String sentid) {
		this.sentid = sentid;
	}
	public String getSentMsg() {
		return sentMsg;
	}
	public void setSentMsg(String sentMsg) {
		this.sentMsg = sentMsg;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
