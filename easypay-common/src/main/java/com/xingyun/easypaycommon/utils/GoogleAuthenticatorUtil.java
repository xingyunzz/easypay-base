package com.xingyun.easypaycommon.utils;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 谷歌验证器Util
 */
public class GoogleAuthenticatorUtil {

    /**
     * 获取随机的密钥Key
     * @return
     */
    public static String getRandomSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base32 base32 = new Base32();
        String secretKey = base32.encodeToString(bytes);
        // make the secret key more human-readable by lower-casing and
        // inserting spaces between each group of 4 characters
        return secretKey.toUpperCase(); // .replaceAll("(.{4})(?=.{4})", "$1 ");
    }

    /**
     * 获取TOTP Code
     * @param secretKey
     * @return
     */
    public static String getTOTPCode(String secretKey) {
        String normalizedBase32Key = secretKey.replace(" ", "").toUpperCase();
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(normalizedBase32Key);
        String hexKey = Hex.encodeHexString(bytes);
        long time = (System.currentTimeMillis() / 1000) / 30;
        String hexTime = Long.toHexString(time);
        return TOTPUtil.generateTOTP(hexKey, hexTime, "6");
    }

    /**
     * 生成Google验证器条形码
     * @param secretKey
     * @param account
     * @param issuer
     * @return
     */
    public static String getGoogleAuthenticatorBarCode(String secretKey,
                                                       String account, String issuer) {
        String normalizedBase32Key = secretKey.replace(" ", "").toUpperCase();
        try {
            return String.format("otpauth://totp/%s?secret=%s&issuer=%s",
                    URLEncoder.encode(issuer + ":" + account, "UTF-8").replace("+", "%20"),
                    URLEncoder.encode(normalizedBase32Key, "UTF-8").replace("+", "%20"),
                    URLEncoder.encode(issuer, "UTF-8").replace("+", "%20"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    static int window_size = 3; // default 3 - max 17 (from google docs)最多可偏移的时间

    /**
     * set the windows size. This is an integer value representing the number of
     * 30 second windows we allow The bigger the window, the more tolerant of
     * clock skew we are.
     *
     * @param s
     *            window size - must be >=1 and <=17. Other values are ignored
     */
    public static void setWindowSize(int s) {
        if (s >= 1 && s <= 17)
            window_size = s;
    }

    /**
     * Check the code entered by the user to see if it is valid
     *
     * @param secret
     *            The users secret.
     * @param code
     *            The code displayed on the users device
     * @param timeMsec
     *            The time in msec (System.currentTimeMillis() for example)
     * @return
     */
    public static boolean checkCode(String secret, long code, long timeMsec) {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
        // convert unix msec time into a 30 second "window"
        // this is per the TOTP spec (see the RFC for details)
        long t = (timeMsec / 1000L) / 30L;
        // Window is used to check codes generated in the near past.
        // You can use this value to tune how far you're willing to go.
        for (int i = -window_size; i <= window_size; ++i) {
            long hash;
            try {
                hash = verifyCode(decodedKey, t + i);
            } catch (Exception e) {
                // Yes, this is bad form - but
                // the exceptions thrown would be rare and a static
                // configuration problem
                // e.printStackTrace();
                throw new RuntimeException(e.getMessage());
                // return false;
            }
            if (hash == code) {
                return true;
            }
        }
        // The validation code is invalid.
        return false;
    }

    /**
     * 验证Code
     * @param key
     * @param t
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    private static int verifyCode(byte[] key, long t)
            throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        // We're using a long because Java hasn't got unsigned int.
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            // We are dealing with signed bytes:
            // we just keep the first byte.
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
        return (int) truncatedHash;
    }


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        // 生成随机的密钥
/*        String secretKey = getRandomSecretKey();
        System.out.println("随机密钥：" + secretKey);

        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secretKey);
        long current = System.currentTimeMillis();
        int i = verifyCode(decodedKey, current);
        System.out.println(i);

        String normalizedBase32Key = secretKey.replace(" ", "").toUpperCase();
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(normalizedBase32Key);
        String hexKey = Hex.encodeHexString(bytes);
        long time = (current / 1000) / 30;
        String hexTime = Long.toHexString(time);
        System.out.println(TOTPUtil.generateTOTP(hexKey, hexTime, "6"));


        boolean b = checkCode(secretKey, 941264, current);
        System.out.println(b);*/

        String secretKey = getRandomSecretKey();
        System.out.println("随机密钥：" + secretKey);

        //6WSO6ZA4MITE24FIZWI3UB56DMLHJXNO
        //3J43VX27PCXXTWTGM4DSGBVLBZWPBPY3
        //G3SZ6LV2AAOPEKBCP2HPOG6SQEXLZ744
        String key  = secretKey;
        String totpCode = getTOTPCode(key);
        System.out.println(totpCode);
        String uri = getGoogleAuthenticatorBarCode(key, "kaijiang","开奖专用");
        System.out.println("TOPT密钥URI：" + uri);

        // 根据验证码，账户，服务商生成 TOPT 密钥的 URI
      /*  String secretKey = getRandomSecretKey();
        System.out.println("随机密钥：" + secretKey);
        String uri = getGoogleAuthenticatorBarCode(secretKey, "xy28ul");
        System.out.println("TOPT密钥URI：" + uri);*/


        /*String lastCode = null;
        while (true) {
            // 根据密钥获取此刻的动态口令
            String code = getTOTPCode(secretKey);
            if (!code.equals(lastCode)) {
                System.out.println("刷新了验证码：" + code + " " + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            }
            lastCode = code;
            try {
                Thread.sleep(1000);  // 线程暂停1秒
            } catch (InterruptedException e) {};
        }*/
    }

}
