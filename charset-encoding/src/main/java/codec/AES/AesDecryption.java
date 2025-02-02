package codec.AES;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

public class AesDecryption extends AES {

    public static void main(String[] args) throws Exception {
        AesDecryption aesDecryption = new AesDecryption();

        String base64CipherText = "+ai7dQc5eHvziEgMsqr7mw==";
        byte[] cipherTextBytes = aesDecryption.base64ToBytes(base64CipherText);
        SecretKey secretKey = aesDecryption.toSecretKey();
        byte[] ivBytes = aesDecryption.hexToBytes(USER_PASSWORD_IV);

        String originalText = aesDecryption.decrypt(cipherTextBytes, secretKey, ivBytes);
        System.out.println(originalText);
    }

    public String decrypt(byte[] cipherTextBytes, SecretKey secretKey, byte[] ivBytes) {
        try {
            Cipher aesCipher = Cipher.getInstance(ALGO_MODE_PADDING);
            aesCipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
            byte[] clearTextBytes = aesCipher.doFinal(cipherTextBytes);
            return new String(clearTextBytes, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
