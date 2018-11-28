package com.rong.ch2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * <p>
 * Title: ServerHandler
 * </p>
 * <p>
 * Description: netty-handler基本实现
 * </p>
 * 
 * @author rong
 * @date 2018年10月8日 下午10:23:45
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("channelActive");
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) {
		System.out.println("channelRegistered");
	}

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) {
		System.out.println("handlerAdded");
	}

	@Override
	public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
		super.channelRead(ctx, msg);

		new Thread(new Runnable() {
			@Override
			public void run() {
				// 耗时的操作
				String result = loadFromDB();

				ctx.channel().writeAndFlush(result);
			}
		}).start();
	}

	private String loadFromDB() {
		return "hello world!";
	}
}
