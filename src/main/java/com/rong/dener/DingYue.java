package com.rong.dener;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>Title: DingYue</p>  
 * <p>Description: 观察者和被观察者
 * 观察者订阅消息，被观察者订阅消息
 * 订阅能收到，取消订阅就收不到
 * </p>  
 * @author rong
 * @date 2018年11月20日 下午11:06:49
 */
public class DingYue {

}

/**
 * 
 * <p>Title: Obser</p>  
 * <p>Description: 被观察者</p>  
 * @author rong
 * @date 2018年11月20日 下午11:09:27
 */
interface Beiguancha{
	void regist(Guancha guancha);
	
	void remove(Guancha guancha);
	
	void notice();
}

interface Guancha{
	void notice(String msg);
}

class Girl implements Beiguancha{
	private String msg;
	private List<Guancha> list = null;
	public Girl() {
		if(list == null) {
			list = new ArrayList<Guancha>();
		}
	}
	@Override
	public void regist(Guancha guancha) {
		list.add(guancha);
	}

	@Override
	public void remove(Guancha guancha) {
		list.remove(guancha);
	}

	@Override
	public void notice() {
		for (Guancha guancha : list) {
			guancha.notice(msg);
		}
	}
	
	public void hasBoy() {
		msg = "有男朋友";
		notice();
	}
	public void noBoy() {
		msg = "没有男朋友";
		notice();
	}
	
}

class Boy implements Guancha{

	@Override
	public void notice(String msg) {
		// TODO Auto-generated method stub
		
	}
	
}