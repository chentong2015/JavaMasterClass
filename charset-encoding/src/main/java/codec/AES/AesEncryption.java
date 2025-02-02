package codec.AES;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

public class AesEncryption extends AES {

    public static void main(String[] args) {
        AesEncryption aesEncryption = new AesEncryption();

        String password = "batch";
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
