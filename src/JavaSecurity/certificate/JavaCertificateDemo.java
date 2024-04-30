package JavaSecurity.certificate;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class JavaCertificateDemo {

    public static void main(String[] args) throws FileNotFoundException {
        String x509Base64 = "code";
        byte[] x509bytes = Base64.getDecoder().decode(x509Base64);
        InputStream inputStream1 = new ByteArrayInputStream(x509bytes);

        // 提供的certificate的文件必须是有效的签名文件
        InputStream inputStream2 = new FileInputStream("src/JavaSecurity/certificate/test.crt");
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            X509Certificate signingCert = (X509Certificate) factory.generateCertificate(inputStream2);
            System.out.println(signingCert.toString());
        } catch (CertificateException e) {
            System.out.println(e.getMessage());
        }
    }
}
