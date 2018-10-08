```
package com.rong.netty.ch2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

public class Server {
	
	private static final int PORT = 8888;
	public static void main(String[] args) {
		NioEventLoopGroup boss = new NioEventLoopGroup(1);
		NioEventLoopGroup work = new NioEventLoopGroup(1);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boss, work)
				.channel(NioServerSocketChannel.class)
				// 设置每条连接 设置基本的 TCP 属性
				.childOption(ChannelOption.TCP_NODELAY, true) 
				// 创建连接 绑定基本属性
				.childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue")
				// 启动时的逻辑
				.handler(new ServerHandler())
				.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) {
//							ch.pipeline().addLast(new ServerHandler());
							// ..

						}
					});
			ChannelFuture channel = b.bind(PORT).sync();
			channel.channel().close().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			work.shutdownGracefully();
		}
	}
}
```

```
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
}
```