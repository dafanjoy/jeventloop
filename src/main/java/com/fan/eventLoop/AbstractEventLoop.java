package com.fan.eventLoop;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import com.fan.eventLoop.context.EventContext;

public abstract class AbstractEventLoop {

	protected final ArrayBlockingQueue<EventContext> arrayBlockingQueue = new ArrayBlockingQueue<EventContext>(1024);

	protected volatile Thread thread;

	protected final Executor executor;

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

	protected void startThread() {
		if (state == ST_NOT_STARTED) {
			if (STATE_UPDATER.compareAndSet(this, ST_NOT_STARTED, ST_STARTED)) {
				try {
					doStartThread();
				} catch (Throwable cause) {
					STATE_UPDATER.set(this, ST_NOT_STARTED);
				}
			}
		}
	}
	

	protected void doStartThread() {
		assert thread == null;
		executor.execute(new Runnable() {
			@Override
			public void run() {
				thread = Thread.currentThread();
				AbstractEventLoop.this.run();

			}
		});
	}
	

    /**
     *
     */
    protected abstract void run();
}
