package codec.SHA_Hash;

import jakarta.xml.bind.DatatypeConverter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

// TODO. Checksum的值可以设计成16进制的Hash Code
// 使用Java原生API来计算基于不同算法的Hash映射的值
public class ShaHashChecksum {

    // Support MD5, SHA-1, SHA-256 支持Hash加密算法
    private static final String HASH_FUNCTION = "SHA-256";

    // Hash加密相同数据生成相同结果
    // source data -> fO7TVu47Y5V9tYA9VgcrDY5R32sMLABLeJ4343mZmJA=
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String value = "source data";
        ShaHashChecksum checksumBuilder = new ShaHashChecksum();
        String checksum = checksumBuilder.messageDigest(value);
        System.out.println(checksum);
    }

    public String messageDigest(String string) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASH_FUNCTION);
        byte[] digestBytes = messageDigest.digest(string.getBytes(StandardCharsets.UTF_8));
        return toBase64(digestBytes);
    }

    // Hex (Base16) encoder 转成十六进制编码字符
    public String toHex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes);
    }

    // 转成64位的编码字符输出
    public String toBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] base64ToBytes(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }
}
