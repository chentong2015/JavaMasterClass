package JavaNetworking;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 为了传递URL中出现的特殊字符, 需要对其进行"编码和解码"
 * 1. RFC3986文档规定，Url中只允许包含英文字母(a-zA-Z), 数字(0-9), -_.~ 4个特殊字符以及所有保留字符
 * 2. Url的编码格式采用的是ASCII码(8 bits)，而不是Unicode, 不能在Url中包含任何非ASCII字符
 */
public class UrlEncoderDecoder {

    /**
     * 百分号编码(Url Encoding，percent-encoding): 使用%百分号加上两位的字符
     * 对应字符在ASCII字符集中表示，将十六进制的表示形式写到%后面
     * http://g.cn/search?q=%61%62%63  ==>  q=abc
     */

    /**
     * <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
     * Html页面中, 浏览器就会使用gb2312去渲染此文档
     * 提交表单时, Url编码使用的字符集就是gb2312
     * 当HTML文档中没有设置此meta标签，则浏览器会根据当前用户喜好去自动选择字符集
     */
    private void testEncoderDecoder() throws UnsupportedEncodingException {
        String URL = "http://www.example.com/test<>";
        // 在编码和解码时，提供不同的方案
        String encoderURL = URLEncoder.encode(URL, "UTF-8");
        String decoderURL = URLDecoder.decode(URL, "UTF-8");
    }
}
