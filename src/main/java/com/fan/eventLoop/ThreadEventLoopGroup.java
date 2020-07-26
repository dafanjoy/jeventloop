package com.fan.eventLoop;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

import com.fan.eventLoop.record.EventRecord;
import com.fan.eventLoop.thread.EventThreadFactory;
import com.fan.eventLoop.thread.ThreadPerTaskExecutor;

public class ThreadEventLoopGroup {

	private ThreadEventLoop[] children;

	public ThreadEventLoopGroup(int threads) {
		this(threads, null);
	}

	public ThreadEventLoopGroup(int threads, Executor executor) {
		if (executor == null) {//自定义线程工厂
			executor = new ThreadPerTaskExecutor(EventThreadFactory());
		}
		//根据传入的线程数创建一个EventLoop数组
		children = new ThreadEventLoop[threads];

		//初始化EventLoop
		for (int i = 0; i < threads; i++) {
			children[i] = new ThreadEventLoop(executor);
		}
	}

	protected ThreadFactory EventThreadFactory() {
		return new EventThreadFactory();
	}

	//根据partaion分配EventLoop
	public void next(int partaion, EventRecord<?, ?> context) { 
		children[partaion].execute(context);
	}

}
