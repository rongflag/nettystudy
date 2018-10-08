package com.rong.ch1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * <p>
 * Title: Server
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author rong
 * @date 2018年10月8日 下午10:02:33
 */
public class Server {

	private ServerSocket serverSocket;

	public Server(int port) {
		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("服务端启动成功，端口:" + port);
		} catch (IOException exception) {
			System.out.println("服务端启动失败");
		}
	}

	public void start() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				doStart();
			}
		}).start();
	}

	private void doStart() {
		while (true) {
			try {
				Socket client = serverSocket.accept();
				new ClientHandler(client).start();
			} catch (IOException e) {
				System.out.println("服务端异常");
			}
		}
	}
}
