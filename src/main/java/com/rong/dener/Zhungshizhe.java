package com.rong.dener;

/**
 * 
 * <p>
 * Title: Zhungshizhe
 * </p>
 * <p>
 * Description: 装饰和被装饰继承同一个接口。装饰者动态修改被装饰这的行为
 * </p>
 * 
 * @author rong
 * @date 2018年11月20日 下午10:48:26
 */
public class Zhungshizhe {
	
	public static void main(String[] args) {
		Lijian lijian = new Lijian(10);
		System.err.println(lijian.getPrice(200.0F));
		
		DaZhe daZhe = new DaZhe(5, lijian);
		System.out.println(daZhe.getPrice(200.0F));
	}
}

// 售卖接口
interface OnSalePlan {

	float getPrice(Float price);
}

// 无优惠
class NoYouhui implements OnSalePlan {
	public static NoYouhui INSTANCE = new NoYouhui();
	private NoYouhui() {

	}

	@Override
	public float getPrice(Float price) {
		return price;
	}

}
// 立减优惠
class Lijian implements OnSalePlan {

	private float money;

	public Lijian(float money) {
		this.money = money;
	}
	@Override
	public float getPrice(Float price) {
		return price - money;
	}

}

// 打折优惠
class DaZhe implements OnSalePlan {
	
	public int discont;
	private OnSalePlan prePlan;
	
	public DaZhe(int discont,OnSalePlan prePla) {
		this.discont = discont;
		this.prePlan = prePla;
	}
	
	public DaZhe(int discont) {
		this(discont ,NoYouhui.INSTANCE);
	}
	@Override
	public float getPrice(Float price) {
		return prePlan.getPrice(price) *  discont /10;
	}

}