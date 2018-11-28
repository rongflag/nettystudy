package com.rong.ch2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 
 * <p>Title: AuthHandler</p>  
 * <p>Description: 自己写的一个handler</p>  
 * @author rong
 * @date 2018年10月18日 上午12:17:30
 */
public class AuthHandler extends SimpleChannelInboundHandler<ByteBuf>{
	
	/**
	 * 小需求，就是在连接后的第一包数据做权限验证，如果验证通过就删除自身，没通过就关闭连接
	 */
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf password) throws Exception {
		if(pass(password)) {
			ctx.pipeline().remove(this);
		}else {
			ctx.close();
		}
	}

	private boolean pass(ByteBuf password) {
		return false;
	}

}
