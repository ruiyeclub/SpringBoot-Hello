package cn.ruiyeclub.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DataConvertUtil {

    /**
     * @param inputStream 输入流
     * @Author: jinhaoxun
     * @Description: 将inputStream转byte[]
     * @Date: 2020/1/16 21:43
     * @Return: byte[]
     * @Throws: Exception
     */
    public static byte[] inputStreamTobyte2(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
            byteArrayOutputStream.write(buff, 0, rc);
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * @param bytes byte数组
     * @Author: jinhaoxun
     * @Description: 将byte[]转inputStream
     * @Date: 2020/1/16 21:43
     * @Return: InputStream
     * @Throws: Exception
     */
    public static InputStream byte2ToInputStream(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }
}