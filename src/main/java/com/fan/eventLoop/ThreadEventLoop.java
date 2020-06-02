package com.fan.eventLoop;

import java.util.ArrayList;
import java.util.concurrent.Executor;

import com.fan.eventLoop.context.EventContext;

public class ThreadEventLoop extends AbstractEventLoop {
	


	public void run() {
		ArrayList<String> list = new ArrayList<>();
		// TODO Auto-generated method stub
		for (;;) {
			try {
				EventContext context = arrayBlockingQueue.take();
				list.add(context.getSource().toString());
				//测试可看到消息key为空时，消息平均分配到了eventloop当中
				if(list.size()>=250000) {
					System.err.println(Thread.currentThread().getName()+"---"+list.size());
				}
				
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
