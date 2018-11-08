/**
 * FileName: ClientSocketChannelDemo
 * Author:   Ren Xiaotian
 * Date:     2018/11/8 21:53
 */

package com.rxt.common.nio.channel;

import com.rxt.common.nio.buffer.Buffers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 * 客户端：客户端每隔1~2秒自动向服务器发送数据，接收服务器接收到数据并显示
 */
public class ClientSocketChannelDemo {
    public static class TCPEchoClient implements Runnable {
        private String name;    //线程名
        private InetSocketAddress remoteAddress;
        private Random random = new Random();

        public TCPEchoClient(String name, String host, int port) {
            this.name = name;
            this.remoteAddress = new InetSocketAddress(host, port);
        }

        @Override
        public void run() {
            Charset charset = Charset.forName("UTF-8");
            Selector selector = null;

            try {
                SocketChannel socketChannel = SocketChannel.open();    //打开SocketChannel
                socketChannel.configureBlocking(false);    //设为非阻塞
                selector = Selector.open();    //打开选择器

                int interestOps = SelectionKey.OP_READ | SelectionKey.OP_WRITE;    //注册感兴趣的事件

                socketChannel.register(selector, interestOps, new Buffers(256, 256));    //

                socketChannel.connect(remoteAddress);    //连接远程服务器

                while (!socketChannel.finishConnect()) {    //等待三次握手完成
                    ;
                }

                System.out.println(name + " connect to Server successful");
            } catch (IOException e) {
                System.out.println(name + " connect to Server failed: " + e.getMessage());
                ;
                return;
            }

            try {
                int i = 1;
                while (!Thread.currentThread().isInterrupted()) {
                    selector.select();    //阻塞等待，从选择器拿到值

                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                    SelectionKey selectionKey = null;

                    while (iterator.hasNext()) {
                        selectionKey = iterator.next();
                        iterator.remove();

                        //提前准备好缓冲区
                        Buffers buffers = (Buffers) selectionKey.attachment();
                        ByteBuffer readBuffer = buffers.getReadBuffer();
                        ByteBuffer writeBuffer = buffers.getWriteBuffer();

                        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();    //获得SocketChannel的通道

                        if (selectionKey.isReadable()) {
                            socketChannel.read(readBuffer);
                            readBuffer.flip();

                            CharBuffer charBuffer = charset.decode(readBuffer);
                            System.out.println("Client端：服务端返回的消息：" + new String(charBuffer.array()).trim());
                            readBuffer.clear();    //清空读缓冲区
                        }

                        if (selectionKey.isWritable()) {
                            writeBuffer.put((name + "-" + i).getBytes("UTF-8"));
                            writeBuffer.flip();

                            socketChannel.write(writeBuffer);    //将自定义缓冲区的内容写入到socket底层缓冲区中
                            writeBuffer.clear();
                            i++;
                        }
                    }
                    Thread.sleep(1000 + random.nextInt(1000));    //每次停顿1～2秒
                }
            } catch (InterruptedException e) {
                System.out.println(name + " is interrupted: " + e.getMessage());
            } catch (IOException e) {
                System.out.println(name + " encounter a connect error: " + e.getMessage());
            } finally {
                try {
                    selector.close();
                } catch (IOException e1) {
                    System.out.println(name + " close selector failed: " + e1.getMessage());
                } finally {
                    System.out.println(name + "  closed");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String hostname = "localhost";
        int port = 9999;

        Thread ta = new Thread(new TCPEchoClient("thread a", hostname, port));
        Thread tb = new Thread(new TCPEchoClient("thread b", hostname, port));
        Thread tc = new Thread(new TCPEchoClient("thread c", hostname, port));
        Thread td = new Thread(new TCPEchoClient("thread d", hostname, port));

        ta.start();
        tb.start();
        tc.start();

        Thread.sleep(5000);

        ta.interrupt();    //结束客户端a

        td.start();    //开始客户端d
    }
}
