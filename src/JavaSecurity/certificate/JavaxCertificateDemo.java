package JavaSecurity.certificate;

import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

// TODO. javax X509Certificate标记废弃同时将会被移除
// @Deprecated(since="9", forRemoval=true)
// Use the classes in java.security.cert instead
public class JavaxCertificateDemo {

    public static void main(String[] args) throws Exception {
        String x509Base64 = "code";
        byte[] x509bytes = Base64.getDecoder().decode(x509Base64);
        X509Certificate certificate1 = X509Certificate.getInstance(x509bytes);

        InputStream inputStream = new FileInputStream("test.crt");
        try {
           X509Certificate certificate2 = X509Certificate.getInstance(inputStream);
           System.out.println(certificate2);
        } catch (CertificateException e) {
            System.out.println(e.getMessage());
        }
    }
}
