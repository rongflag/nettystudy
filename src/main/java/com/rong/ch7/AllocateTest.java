package com.rong.ch7;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

/**
 * 
 * <p>Title: AllocateTest</p>  
 * <p>Description:分配内存流程 </p>  
 * @author rong
 * @date 2018年10月24日 下午10:21:58
 */
public class AllocateTest {
	
	public static void main(String[] args) {
		int pageSize = 1028 * 8;
		int subPageSize = 16;
		allocateSpave(pageSize);
	}

	private static void allocateSpave(int pageSize) {
		PooledByteBufAllocator allocator = PooledByteBufAllocator.DEFAULT;
		// 分配内存
		ByteBuf directBuffer = allocator.directBuffer(pageSize);
		// 回收内存
		directBuffer.release();
	}
}
