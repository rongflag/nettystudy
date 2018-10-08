package com.rong.ch1;

import java.io.IOException;
import java.net.Socket;

/**
 * 
 * <p>Title: Client</p>  
 * <p>Description: ch2</p>  
 * @author rong
 * @date 2018年10月8日 下午10:01:10
 */
public class Client {

	 private static final String HOST = "127.0.0.1";
	    private static final int PORT = 8000;
	    private static final int SLEEP_TIME = 5000;

	    public static void main(String[] args) throws IOException {
	        final Socket socket = new Socket(HOST, PORT);

	        new Thread(new Runnable() {
	            @Override
	            public void run() {
	                System.out.println("客户端启动成功!");
	                while (true) {
	                    try {
	                        String message = "hello world";
	                        System.out.println("客户端发送数据: " + message);
	                        socket.getOutputStream().write(message.getBytes());
	                    } catch (Exception e) {
	                        System.out.println("写数据出错!");
	                    }
	                    sleep();
	                }


	            }
	        }).start();

	    }

	    private static void sleep() {
	        try {
	            Thread.sleep(SLEEP_TIME);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
		}
	}
}
