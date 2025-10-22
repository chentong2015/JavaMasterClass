package codec_algo.AES;

import jakarta.xml.bind.DatatypeConverter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public abstract class AES {

    static final int KEY_LENGTH = 128;
    static final String ALGORITHM = "AES";
    static final String ALGO_MODE_PADDING = "AES/CBC/PKCS5PADDING";

    static final String USER_PASSWORD_SECRET_KEY = "11535AF31C94F18C7EAAC2E320637E8F";
    static final String USER_PASSWORD_IV = "CEEA74A4915C47FA619E50C7D2255464";

    SecretKey toSecretKey() {
        byte[] secretKeyBytes = hexToBytes(AES.USER_PASSWORD_SECRET_KEY);
        return new SecretKeySpec(secretKeyBytes, ALGORITHM);
    }

    // 16进制解析成byte数组
    byte[] hexToBytes(String hexString) {
        return DatatypeConverter.parseHexBinary(hexString);
    }

    // base64编码结果转成byte数组
    byte[] base64ToBytes(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

    // base64编码的字节数组转成字符串
    String base64BytesToString(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
