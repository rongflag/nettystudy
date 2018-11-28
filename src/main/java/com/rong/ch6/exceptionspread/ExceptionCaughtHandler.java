package com.rong.ch6.exceptionspread;

import com.rong.ch6.BusinessException;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ExceptionCaughtHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // ..

        if (cause instanceof BusinessException) {
            System.out.println("BusinessException");
        }
    }
}
