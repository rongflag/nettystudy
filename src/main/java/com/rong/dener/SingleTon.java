package com.rong.dener;

public class SingleTon {

	private  static SingleTon t = null;
	private SingleTon() {
		
	}
	
	public static SingleTon getInstance() {
		if(t == null) {
			synchronized (SingleTon.class) {
				if(t == null) {
					t = new SingleTon();
				}
			}
		}
		return t;
	}
}
