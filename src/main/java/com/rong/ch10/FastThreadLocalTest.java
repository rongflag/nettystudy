package com.rong.ch10;

import io.netty.util.concurrent.FastThreadLocal;

public class FastThreadLocalTest {

	
	private static FastThreadLocal<Object>thread = new FastThreadLocal<>();
	
	public static void main(String[] args) {
		new Thread(()->{
			System.out.println(thread.get());
			while (true) {
				thread.set(new Object());
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
		new Thread(()->{
			thread.set(new Object());
			while (true) {
				System.out.println(thread.get());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
