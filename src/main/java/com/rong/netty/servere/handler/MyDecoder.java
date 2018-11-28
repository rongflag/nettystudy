package com.rong.netty.servere.handler;

import java.nio.Buffer;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ByteProcessor;

/**
 * 针对蓝彩系统做的一个解码器
 * 
 * <pre>
 * +----------+-----------+---------+---------+---------+---------------+-------+---------+------+  
 * | Header 1 |  address  | fixed   | type    | order   |  dataLength   | datas | CRC     | end  |
 * |  0x68    | 8位       | 0x69    | 类型2位 | 命令1位 | 数据总长度8位 | 。。。| CRC一位 | 0x0d |  
 * +----------+-----------+---------+---------+---------+---------------+-------+---------+------+
 * </pre>
 * <p>
 * Title: MyDecoder
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author rong
 * @date 2018年10月28日 下午9:48:00
 */
public class MyDecoder extends ByteToMessageDecoder {

	// 最大长度
	private int maxFrameLength = 4096;
	// 头 头长度
	private byte head = 0x69;
	private int headLength = 1;
	// 地址长度
	private int addressLength = 9;
	// 固定位
	private byte fixed = 0x69;
	// 类型长度
	private int typeLength = 2;
	// 命令长度
	private int orderLength = 1;
	// 数据长度域长度
	private int dataLength = 2;
	// CRC长度
	private int CRCLength = 1;
	// 结尾长度
	private int endLength = 1;
	// 结尾标标志
	private byte endByte = 0x0d;
	// 快速抛出 异常
	private boolean failFast = false;
	// 是否处于丢弃模式
	private boolean discardingTooLongFrame = false;
	// 丢弃长度
	private long tooLongFrameLength;
	// 丢弃长度
	private long bytesToDiscard;
	// 可读的最少长度
	private int lengthFieldEndOffset = headLength + addressLength + 1 + orderLength + CRCLength + endLength;

	/** 最后读取位置 */
	private int offset = 0;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		Object decoded = decode(ctx, in);
		if (decoded != null) {
			out.add(decoded);
		}
	}

	/**
	 * 
	 * <p>
	 * Title: decode
	 * </p>
	 * <p>
	 * Description:具体解析的的代码
	 * </p>
	 * 
	 * @param ctx
	 * @param in
	 * @return
	 */
	private Object decode(ChannelHandlerContext ctx, ByteBuf buffer) {

		if (discardingTooLongFrame) {
			// 丢弃模式
			discardingTooLongFrame(buffer);
		}

		if (buffer.readableBytes() < lengthFieldEndOffset) {
			return null;
		}

		// 找头
		final int eol = findEndOfLine(buffer);
		// 移动读指针
		buffer.readerIndex(eol);
		// 判断固定位
		byte mayFixd = buffer.getByte(eol + 8);
		if(mayFixd != fixed) {
			// 丢弃模式
			return null;
		}
		
		// 读取 长度域
		byte[] dst = new byte[2];
		buffer.getBytes(eol + 12 , dst, 0, 2);
		// 获取长度值
		int datasLength  = getDatasLength(dst);
		// 获取结束位
		byte end = buffer.getByte(eol + 12 + datasLength + 2);
		if(end != endByte) {
			// 丢弃模式
			return null;
		}
		
		// 判断CRC
		byte CRC = buffer.getByte(eol + 12 + datasLength + 1);
		if(CRC != getCRC(buffer)) {
			// 丢弃模式
			return null;
		}
		 // extract frame
        int readerIndex = buffer.readerIndex();
        ByteBuf frame = extractFrame(ctx,buffer, readerIndex, datasLength);
        buffer.readerIndex(readerIndex + datasLength);
        return frame;
	}
	
	/**
	 * 
	 * <p>Title: extractFrame</p>  
	 * <p>Description:截取值返回 </p>  
	 * @param ctx
	 * @param buffer
	 * @param index
	 * @param length
	 * @return
	 */
	protected ByteBuf extractFrame(ChannelHandlerContext ctx, ByteBuf buffer, int index, int length) {
        return buffer.retainedSlice(index, length);
    }
	
	/**
	 * 
	 * <p>Title: getCRC</p>  
	 * <p>Description:判断CRC </p>  
	 * @param buffer
	 * @return
	 */
	private byte getCRC(ByteBuf buffer) {
		return 0;
	}

	/**
	 * 
	 * <p>Title: getDatasLength</p>  
	 * <p>Description: 根据长度域获取长度值 </p>  
	 * @param dst
	 * @return
	 */
	private int getDatasLength(byte[] dst) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int findEndOfLine(ByteBuf buffer) {
		int totalLength = buffer.readableBytes();
		// 找0x68
		int i = buffer.forEachByte(buffer.readerIndex() + offset, totalLength - offset,new ByteProcessor.IndexOfProcessor(head));
		if (i >= 0) {
			offset = 0;
			if (i > 0 && buffer.getByte(i - 1) == '\r') {
				i--;
			}
		} else {
			offset = totalLength;
		}
		return i;
	}

	private void discardingTooLongFrame(ByteBuf in) {
		// TODO Auto-generated method stub

	}

}
