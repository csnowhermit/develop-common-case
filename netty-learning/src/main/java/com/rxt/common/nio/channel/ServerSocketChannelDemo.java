/**
 * FileName: ServerSocketChannelDemo
 * Author:   Ren Xiaotian
 * Date:     2018/11/8 21:06
 */

package com.rxt.common.nio.channel;

import com.rxt.common.nio.buffer.Buffers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

/**
 *
 */
public class ServerSocketChannelDemo {
    public static class TCPEchoServer implements Runnable {
        private InetSocketAddress localAddress;    //服务器地址

        public TCPEchoServer(int port) {
            this.localAddress = new InetSocketAddress(port);
        }

        @Override
        public void run() {
            Charset charset = Charset.forName("UTF-8");

            ServerSocketChannel serverSocketChannel = null;
            Selector selector = null;
            Random random = new Random();

            try {
                serverSocketChannel = ServerSocketChannel.open();    //打开服务端通道
                serverSocketChannel.configureBlocking(false);    //设置为非阻塞
                selector = Selector.open();    //打开选择器

                serverSocketChannel.bind(localAddress, 100);    //绑定服务器地址，最多可连接100个客户端
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);    //将选择器注册到通道，对accept事件感兴趣

                System.out.println("Server started successful");
            } catch (IOException e) {
                System.out.println("Server started failed: " + e.getMessage());
            }

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int n = selector.select();    //从选择器中取，如果没有这步，则没有结果
                    if (n == 0) {    //0代表没有任何事件进来
                        continue;
                    }

                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = (Iterator<SelectionKey>) selectionKeySet.iterator();
                    SelectionKey selectionKey = null;

                    while (iterator.hasNext()) {
                        selectionKey = iterator.next();
                        iterator.remove();    //移除，以免重复取出

                        try {
                            //处理客户端的连接
                            if (selectionKey.isAcceptable()) {
                                SocketChannel socketChannel = serverSocketChannel.accept();   //等到客户端的channel
                                socketChannel.configureBlocking(false);    //设置为非阻塞

                                int interestOps = SelectionKey.OP_READ;    //对读事件感兴趣
                                socketChannel.register(selector, interestOps, new Buffers(256, 256));
                                System.out.println("Server端：accept from: " + socketChannel.getRemoteAddress());
                            }

                            //处理可读消息
                            if (selectionKey.isReadable()) {
                                //先准备好缓冲区
                                Buffers buffers = (Buffers) selectionKey.attachment();
                                ByteBuffer readBuffer = buffers.getReadBuffer();
                                ByteBuffer writeBuffer = buffers.getWriteBuffer();

                                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                                socketChannel.read(readBuffer);    //将数据从底层缓冲区读到自定义缓冲区中
                                readBuffer.flip();

                                CharBuffer charBuffer = charset.decode(readBuffer);    //解码接收到的数据
                                System.out.println("Server端：接收到客户端的消息：" + new String(charBuffer.array()).trim());

                                readBuffer.rewind();    //重置缓冲区指针位置

                                //准备给客户端返回数据
                                writeBuffer.put("Echo from Server: ".getBytes("UTF-8"));
                                writeBuffer.put(readBuffer);    //将客户端发来的数据原样返回
                                writeBuffer.put("\n".getBytes("UTF-8"));

                                readBuffer.clear();    //清空读缓冲区
                                selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);    //为通道添加写事件
                            }

                            //处理可写消息
                            if (selectionKey.isWritable()) {
                                Buffers buffers = (Buffers) selectionKey.attachment();
                                ByteBuffer writeBuffer = buffers.getWriteBuffer();
                                writeBuffer.flip();

                                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                                int len = 0;
                                while (writeBuffer.hasRemaining()) {
                                    len = socketChannel.write(writeBuffer);
                                    if (len == 0) {    //说明底层socket写缓冲区已写满
                                        break;
                                    }
                                }

                                writeBuffer.compact();

                                if (len != 0) {    //说明数据已全部写入到底层的socket写缓冲区
                                    selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_WRITE));    //取消通道的写事件
                                }
                            }
                        } catch (IOException e) {
                            System.out.println("Server encounter client error: " + e.getMessage());
                            selectionKey.cancel();
                            selectionKey.channel().close();
                        }
                    }
                    Thread.sleep(random.nextInt(500));
                }
            } catch (InterruptedException e) {
                System.out.println("serverThread is interrupted: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("serverThread selecotr error: " + e.getMessage());
            } finally {
                try {
                    selector.close();
                } catch (IOException e) {
                    System.out.println("selector close failed");
                } finally {
                    System.out.println("server closed successful");
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 9999;
        Thread serverThread = new Thread(new TCPEchoServer(port));
        serverThread.start();
        Thread.sleep(100000);

        serverThread.interrupt();    //结束服务器线程
    }
}
