/**
 * FileName: NettyClient
 * Author:   Ren Xiaotian
 * Date:     2018/11/10 17:33
 */

package com.rxt.common.netty.ch00;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.util.concurrent.CountDownLatch;

public class NettyClient implements  Runnable {

    @Override
    public void run() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup);
            bootstrap.channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ChannelPipeline channelPipeline = channel.pipeline();
                            channelPipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                            channelPipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                            channelPipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                            channelPipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                            channelPipeline.addLast("handler", new MyClient());
                        }
                    });

            for (int i = 0; i < 10; i++) {
                ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6666).sync();
                channelFuture.channel().writeAndFlush("Hello Server: " + Thread.currentThread().getName() + "-->" + i);
                channelFuture.channel().closeFuture().sync();    //注册个Future关闭的监听器
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 3;
        CountDownLatch countDownLatch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(new NettyClient(), ">>> Thread-" + i).start();
            countDownLatch.countDown();
        }

        countDownLatch.await();
    }
}
