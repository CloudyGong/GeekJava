package com.study.gdy.demo;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

    private static final String FILE_PATH = "/Hello.xlass";

    public static void main(String[] args) {
        System.out.println("Self defined class loader!");
        try {
            Class classHello = new HelloClassLoader().findClass("Hello");
            Method methodHello = classHello.getDeclaredMethod("hello", null);
            Object objectHello = classHello.newInstance();
            methodHello.invoke(objectHello, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        byte[] dataBytes = new byte[0];
        int len;
        try {
            inputStream = getClass().getResourceAsStream(FILE_PATH);
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            dataBytes = outputStream.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                inputStream.close();
            }
            if (null != outputStream) {
                outputStream.close();
            }
        }
        len = dataBytes.length;
        for (int i = 0; i < len; i++) {
            dataBytes[i] = (byte) (255 - dataBytes[i]);
        }
        return defineClass(name, dataBytes, 0, len);
    }

}
