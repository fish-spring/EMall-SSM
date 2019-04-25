package util;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

public class MD5Util {
    public static String encodeToMd5(String password){
        DigestUtils.md2Hex(password);
        return null;
    }
}
