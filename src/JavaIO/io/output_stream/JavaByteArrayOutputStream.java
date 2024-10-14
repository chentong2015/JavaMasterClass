package JavaIO.io.output_stream;

import java.io.ByteArrayOutputStream;

public class JavaByteArrayOutputStream {

    public static void main(String[] args) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(50);
        outputStream.write(60);
        outputStream.write(40);

        byte[] bytes = outputStream.toByteArray();
        System.out.println(new String(bytes));
    }
}
