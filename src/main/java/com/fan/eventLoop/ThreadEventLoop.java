package com.fan.eventLoop;

import java.util.concurrent.Executor;

import com.fan.eventLoop.context.EventContext;

public class ThreadEventLoop extends AbstractEventLoop {
	


	public void run() {
		// TODO Auto-generated method stub
		for (;;) {
			try {
				EventContext context = arrayBlockingQueue.take();;
				// System.err.println(thread.getName()+context.id+context.msg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
		}

	}

	public ThreadEventLoop(Executor executor) {
		super(executor);

	}




	public void execute(EventContext eventContext) {
		try {
			if (eventContext == null) {
				throw new NullPointerException("eventContext");
			}

			boolean inEventLoop = inEventLoop(Thread.currentThread());
			arrayBlockingQueue.put(eventContext);
			if (!inEventLoop) {
				startThread();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
		}
	}



}
