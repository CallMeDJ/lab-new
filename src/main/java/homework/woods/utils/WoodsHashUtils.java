package homework.woods.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: woods
 * @date: 2019/9/1
 * @description:
 */
public class WoodsHashUtils {
    private String algorithm;

    public WoodsHashUtils(String algorithm)
    {
        this.algorithm = algorithm;
    }
    public int getHash( String key)  throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String encodeStr = "";
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(key.getBytes("UTF-8"));
        encodeStr = new String(messageDigest.digest());
        return encodeStr.hashCode();
    }
}
