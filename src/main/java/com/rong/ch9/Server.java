package com.rong.ch9;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {
	
	public static void main(String[] args) {
		
		
		NioEventLoopGroup boss = new NioEventLoopGroup();
		NioEventLoopGroup work = new NioEventLoopGroup();
		
		ServerBootstrap bootstrap = new ServerBootstrap();
		bootstrap.channel(NioServerSocketChannel.class).group(boss,work).childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new Encoder());
				ch.pipeline().addLast(new BizHandler());
			}
		});
		try {
			ChannelFuture channelFuture = bootstrap.bind(8888).sync();
			channelFuture.channel().close().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
			
		
		}
}
