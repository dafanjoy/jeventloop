package com.fan.eventLoop;

import java.util.concurrent.Executor;

import com.fan.eventLoop.record.EventRecord;

public class ThreadEventLoop extends AbstractEventLoop {
	
	
	public ThreadEventLoop(Executor executor) {
		super(executor);

	}
	
	public void run() {
		//通过for(;;)轮询的方式从阻塞队列中获取数据进行处理
		for (;;) {
			try {
				EventRecord<?, ?> record = arrayBlockingQueue.take();//从阻塞队列中获取数据
				iHandler.execute(record);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}

	}

	public void execute(EventRecord<?, ?> record) {
		try {
			if (record == null) {//判断非空
				throw new NullPointerException("EventRecord");
			}

			boolean inEventLoop = inEventLoop(Thread.currentThread());
			arrayBlockingQueue.put(record);//把消息放入阻塞队列中
			if (!inEventLoop) {
				startThread();//启动EventLoop中改的线程
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}



}
