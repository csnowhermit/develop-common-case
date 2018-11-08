/**
 * FileName: FileChannelDemo
 * Author:   Ren Xiaotian
 * Date:     2018/11/8 20:50
 */

package com.rxt.common.nio.channel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件通道（FileChannel）Demo
 */
public class FileChannelDemo {
    public static void main(String[] args) {
        String pathName = "d:/nio_utf8.data";

        try {
            File file = new File(pathName);
            if (!file.exists()) {    //如果文件不存在，则创建
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileChannel fileChannel = fileOutputStream.getChannel();    //得到文件通道
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);    //创建ByteBuffer对象，position-0，limit=64

            byteBuffer.put("Hello World\n".getBytes("UTF-8"));    //position=12，limit=64
//            System.out.println(byteBuffer.position() + "、" + byteBuffer.limit());
            byteBuffer.flip();    //position=0，limit=12
//            System.out.println(byteBuffer.position() + "、" + byteBuffer.limit());

            fileChannel.write(byteBuffer);
            byteBuffer.clear();    //清空缓冲区

            //以下代码同理
            byteBuffer.put("你好，世界\n".getBytes("UTF-8"));
            byteBuffer.flip();

            fileChannel.write(byteBuffer);
            byteBuffer.clear();    //清空缓冲区

            fileOutputStream.close();
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Path path = Paths.get(pathName);
            FileChannel fileChannel = FileChannel.open(path);

            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fileChannel.size() + 1);
            Charset charset = Charset.forName("UTF-8");

            fileChannel.read(byteBuffer);    //阻塞模式，将内容读取至缓冲区中
            byteBuffer.flip();

            CharBuffer charBuffer = charset.decode(byteBuffer);
            System.out.println(charBuffer.toString());
            byteBuffer.clear();

            fileChannel.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
