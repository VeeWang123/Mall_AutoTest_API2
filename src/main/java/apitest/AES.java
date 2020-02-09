package apitest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author Oakley(fangchao)
 * @Date 2018-01-11 17:17
 */
public class AES {

    public static String KEY = "dd7e37d9f3287cd89ccb736c69074e26";
    private static Logger logger = LoggerFactory.getLogger(AES.class);

    /**
     * 加密
     *
     * @param sSrc
     * @param sKey
     * @return
     * @throws Exception
     */
    public static String encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            logger.error("AES {}", "Key为空null");
            return null;
        }
        // 判断Key是否为32位
        if (sKey.length() != 32) {
            logger.error("AES {}", "Key长度不是32位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * 解密
     *
     * @param sSrc
     * @param sKey
     * @return
     */
    public static String decrypt(String sSrc, String sKey) {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                logger.error("AES {}", "Key为空null");
                return null;
            }
            // 判断Key是否为32位
            if (sKey.length() != 32) {
                logger.error("AES {}", "Key长度不是32位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                logger.error("AES {},{}", e.getMessage(), e.toString());
                return null;
            }
        } catch (Exception ex) {
            logger.error("AES 解密异常 {},{}", ex.getMessage(), ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */
        String cKey = "dd7e37d9f3287cd89ccb736c69074e27";

        //解密
        String str = "Pt6Abx/KfaO036Thn3SfuE9SZjnPhsq6AmysDQIceU0FCAJcouXgXWnQS4+Oo+3CyMPl77yo5AwEOLXJXv3nihG2yXgIqw9q9v+jOs286TwQ1DvS7mNb3pmlCTz4Z/ttO1q5Fh4GHcMO6Z/PeVN1Gg==";
        String deString = AES.decrypt(str, cKey);
        logger.info("解密后的字串是：{}", deString);

        //加密
        String strs = "{\"phoneNumber\":\"15869003128\",\"password\":\"wanjun@123\",\"type\":0}";
        String strsencrypt = AES.encrypt(strs, cKey);
        logger.info("加密后的字串是：{}", strsencrypt);
    }
}