package com.fan.eventLoop;

import java.util.concurrent.Executor;

import com.fan.eventLoop.record.EventRecord;

public class ThreadEventLoop extends AbstractEventLoop {
	public void run() {
		// TODO Auto-generated method stub
		for (;;) {
			try {
				EventRecord<?, ?> record = arrayBlockingQueue.take();
				iHandler.execute(record);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}

	}

	public ThreadEventLoop(Executor executor) {
		super(executor);

	}

	public void execute(EventRecord<?, ?> record) {
		try {
			if (record == null) {
				throw new NullPointerException("EventRecord");
			}

			boolean inEventLoop = inEventLoop(Thread.currentThread());
			arrayBlockingQueue.put(record);
			if (!inEventLoop) {
				startThread();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}



}
