package com.rong.ch10;

import io.netty.util.Recycler;
import io.netty.util.Recycler.Handle;

public class RecylerTrest {

	private static final Recycler<User> TT = new Recycler<User>() {

		@Override
		protected User newObject(Handle<User> handle) {
			return new User(handle);
		}

	};

	public static void main(String[] args) {
		User user = TT.get();

		user.recycle();

		User user1 = TT.get();
		
		System.out.println(user  == user1);

	}

}

class User {

	private Recycler.Handle<User> handler;

	public User(Recycler.Handle<User> handler) {
		this.handler = handler;
	}

	public void recycle() {
		handler.recycle(this);
	}
}