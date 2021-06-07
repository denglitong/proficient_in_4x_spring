package com.denglitong.placeholder;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author litong.deng@foxmail.com
 * @date 2021/6/6
 */
public class DESUtils {

    private static Key key;
    private static String KEY_STR = "myKey";

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");

            // `SHA1PRNG` is the generation algorithm,
            // refer algorithm in https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#SecureRandom
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);

            key = generator.generateKey();
            generator = null;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEncryptString(String str) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            BASE64Encoder base64En = new BASE64Encoder();
            byte[] strBytes = str.getBytes(StandardCharsets.UTF_8);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return base64En.encode(encryptStrBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDecryptString(String str) {
        try {
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            BASE64Decoder base64De = new BASE64Decoder();
            byte[] strBytes = base64De.decodeBuffer(str);
            byte[] decryptStrBytes = cipher.doFinal(strBytes);
            return new String(decryptStrBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            System.out.println("请输入要加密的字符，用空格分隔.");
        } else {
            for (String arg : args) {
                System.out.println(arg + " : " + getEncryptString(arg) + " : " + getDecryptString(getEncryptString(arg)));
            }
        }
    }
}
