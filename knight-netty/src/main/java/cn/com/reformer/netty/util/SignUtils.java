package cn.com.reformer.netty.util;


/**
 * Created by feixiang on 2017-06-13.
 */
public class SignUtils {

    byte[]  keys={0x59, (byte) 0xDC,0x27,0x7F,0x08,0x3A, (byte) 0x95,0x01, (byte) 0xA4, (byte) 0xBF,0x17,0x45, (byte) 0xA5,0x69, (byte) 0xE6,0x31};


    public static String getSigin(String sn,byte cmd,String notice){

        int _sn=Integer.parseInt(sn);
        int _notice=Integer.parseInt(notice);

        int _temp=_sn&cmd&_notice;

        return  String.valueOf(_temp);
    }
}
