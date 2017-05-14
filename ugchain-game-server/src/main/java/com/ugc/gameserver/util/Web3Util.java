package com.ugc.gameserver.util;

import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;

import java.math.BigInteger;
import java.nio.ByteBuffer;

/**
 * Created by fanjl on 2017/5/8.
 */
public class Web3Util {

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static Uint64 toUint64(int src){
        BigInteger bigInteger = BigInteger.valueOf(new Integer(src).intValue());
        Uint64 uint64 = new Uint64(bigInteger);
        return uint64;
    }

    public static Uint256 toUint256(int src){
        BigInteger bigInteger = BigInteger.valueOf(new Integer(src).intValue());
        Uint256 uint256 = new Uint256(bigInteger);
        return uint256;
    }

    public static Bytes32 hexStringToByte32(String src){
        if(src.startsWith("0x")){
            return new Bytes32(hexStringToByte(src.substring(2)));
        }
        return new Bytes32(hexStringToByte(src));
    }

    public static byte[] hexStringToByte(String s) {
        int len = s.length();
        byte[] data = new byte[32];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static String bytes32ToHexString(Bytes32 bytes32) {
        char[] hexChars = new char[bytes32.getValue().length * 2];
        for ( int j = 0; j < bytes32.getValue().length; j++ ) {
            int v = bytes32.getValue()[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars).toLowerCase();
    }
    public static byte[] fromUtf8(byte[] src){
        ByteBuffer byteBuffer = ByteBuffer.allocate(src.length);
        for(byte b : src){
            if(b==0){
                break;
            }
            byteBuffer.put(b);
        }
        byteBuffer.flip();
        byte[] ret = new byte[byteBuffer.limit()];
        byteBuffer.get(ret,0,ret.length);
        return ret;
    }

}
