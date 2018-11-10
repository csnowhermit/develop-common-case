/**
 * FileName: TcpServerHandler
 * Author:   Ren Xiaotian
 * Date:     2018/11/10 16:42
 */

package com.rxt.common.netty.ch00;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 处理Server端的具体业务
 */
public class TcpServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        System.out.println("channelActive>>>");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        System.out.println("Server receive message: " + msg);
        ctx.channel().writeAndFlush("accept message: " + msg);
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        System.out.println("Server get a Exception: " + cause.getMessage());
    }
}
