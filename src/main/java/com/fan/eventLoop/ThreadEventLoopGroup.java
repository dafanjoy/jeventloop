package com.fan.eventLoop;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

import com.fan.eventLoop.context.EventContext;
import com.fan.eventLoop.thread.EventThreadFactory;
import com.fan.eventLoop.thread.ThreadPerTaskExecutor;

public class ThreadEventLoopGroup {

	private ThreadEventLoop[] children;

	public ThreadEventLoopGroup(int threads) {
		this(threads, null);
	}

	public ThreadEventLoopGroup(int threads, Executor executor) {
		if (executor == null) {
			executor = new ThreadPerTaskExecutor(EventThreadFactory());
		}
		children = new ThreadEventLoop[threads];

		for (int i = 0; i < threads; i++) {
			children[i] = new ThreadEventLoop(executor);
		}
	}

	protected ThreadFactory EventThreadFactory() {
		return new EventThreadFactory();
	}

	public void next(int partaion, EventContext context) { 
		children[partaion].execute(context);
	}

}
