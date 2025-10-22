package codec_algo.AES;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

public class AesEncryption extends AES {

    // 对称加密: 支持加密字母和数字以外的字符
    public static void main(String[] args) {
        AesEncryption aesEncryption = new AesEncryption();

        String password = "_#/!?.=$%+-|\\\\&*_#/!?.=$%+-|\\";
        byte[] ivBytes = aesEncryption.hexToBytes(USER_PASSWORD_IV);
        SecretKey secretKey = aesEncryption.toSecretKey();

        byte[] cipherTextBytes = aesEncryption.encrypt(password, secretKey, ivBytes);
        String passwordEncrypted = aesEncryption.base64BytesToString(cipherTextBytes);
        System.out.println("Clear text: " + password);
        System.out.println("Encrypted text: " + passwordEncrypted);
    }

    public byte[] encrypt(String clearText, SecretKey secretKey, byte[] iv) {
        try {
            Cipher aesCipher = Cipher.getInstance(ALGO_MODE_PADDING);
            aesCipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
            byte[] clearTextBytes = clearText.getBytes(StandardCharsets.UTF_8);
            return aesCipher.doFinal(clearTextBytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
