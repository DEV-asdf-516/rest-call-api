package com.communitcation.rest.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteUtil {
    public static byte[] readToBytes(InputStream inputStream){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int readBytes;
        try{
            while((readBytes = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,readBytes);
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] toBytes = outputStream.toByteArray();
        return toBytes;
    }
}
