package com.rong.ch1;

/**
 * 
 * <p>Title: ServerBoot</p>  
 * <p>Description:server启动类 </p>  
 * @author rong
 * @date 2018年10月8日 下午10:03:36
 */
public class ServerBoot {

	 private static final int PORT = 8000;

	    public static void main(String[] args) {
	        Server server = new Server(PORT);
	        server.start();
	    }

}
