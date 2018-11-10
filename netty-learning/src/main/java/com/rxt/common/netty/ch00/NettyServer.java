/**
 * FileName: NettyServer
 * Author:   Ren Xiaotian
 * Date:     2018/11/7 20:59
 */

package com.rxt.common.netty.ch00;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServer {
    private static final String IP = "127.0.0.1";
    private static final int PORT = 6666;

    private static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;    //CPU核数*2
    private static final int BIZTHREADSIZE = 100;    //定义线程的大小：100

    //处理事件的线程组
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public static void start() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline channelPipeline = channel.pipeline();
                        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                        channelPipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        channelPipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        channelPipeline.addLast(new TcpServerHandler());    //具体的业务处理
                    }
                });

        ChannelFuture channelFuture = serverBootstrap.bind(IP, PORT).sync();    //绑定的过程比较耗时，故用同步的方法阻塞下

        channelFuture.channel().closeFuture().sync();    //注册个Future关闭的监听器
        System.out.println("Server started...");
    }

    private static void shutdown() {
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("启动Server...");
        NettyServer.start();

        //shutdown什么时候调用？写个钩子，必要时候调用shutdown()
    }
}
