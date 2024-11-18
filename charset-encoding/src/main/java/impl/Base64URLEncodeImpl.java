package impl;

import java.util.Base64;

// 一种编码算法(数字签名)，生成基于64位的编码结果
public class Base64URLEncodeImpl {

    // 使用Decoder解析基于64位编码的结果
    public static void main(String[] args) {
        String x509Base64 = "TCCAeWgAwIBAgIIOdwuMdHVGkTCCAeWgAwIBAgIIOdwuMdHVGk";
        byte[] x509bytes = Base64.getDecoder().decode(x509Base64);

        String value = new String(x509bytes);
        System.out.println(value);
    }
}
