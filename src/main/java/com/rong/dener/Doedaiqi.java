package com.rong.dener;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 
 * <p>Title: Doedaiqi</p>  
 * <p>Description: 迭代器</p>  
 * @author rong
 * @date 2018年11月21日 下午8:05:14
 */
public class Doedaiqi {

	public static void main(String[] args) {
		ByteBuf head = Unpooled.wrappedBuffer(new byte[]{1,2,3});
		head.forEachByte(value->{
			System.out.println(value);
			return true;
		});
	}
}
