package com.jeventloop;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import com.jeventloop.handler.EventHandler;
import com.jeventloop.handler.IHandler;
import com.jeventloop.record.EventRecord;

public abstract class AbstractEventLoop {

	//阻塞队列，消息会先进入阻塞队列中，再由线程循环处理
	protected final ArrayBlockingQueue<EventRecord<?,?>> arrayBlockingQueue = new ArrayBlockingQueue<EventRecord<?,?>>(1024);

	//绑定线程
	protected volatile Thread thread;

	protected final Executor executor;
	
	protected IHandler iHandler = new EventHandler();

	//声明AtomicIntegerFieldUpdater类，用于控制线程启动状态
	private static final AtomicIntegerFieldUpdater<AbstractEventLoop> STATE_UPDATER = AtomicIntegerFieldUpdater
			.newUpdater(AbstractEventLoop.class, "state");

	private volatile int state = ST_NOT_STARTED;
	private static final int ST_NOT_STARTED = 1;
	private static final int ST_STARTED = 2;

	protected AbstractEventLoop(Executor executor) {
		this.executor = executor;
	}

	public boolean inEventLoop(Thread thread) {
		return thread == this.thread;
	}

	//启动线程
	protected void startThread() {
		if (state == ST_NOT_STARTED) {
			if (STATE_UPDATER.compareAndSet(this, ST_NOT_STARTED, ST_STARTED)) {//通过CAS的方式保证线程不会重复启动
				try {
					doStartThread();//
				} catch (Throwable cause) {
					STATE_UPDATER.set(this, ST_NOT_STARTED);
				}
			}
		}
	}

	protected void doStartThread() {
		assert thread == null;
		executor.execute(new Runnable() {//通过自定义线程工厂开始执行线程
			@Override
			public void run() {
				thread = Thread.currentThread();
				AbstractEventLoop.this.run();//执行实现类的run方法

			}
		});
	}
	
    protected abstract void run();
}
