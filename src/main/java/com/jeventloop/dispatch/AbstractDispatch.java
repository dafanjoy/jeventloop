package com.jeventloop.dispatch;

import com.jeventloop.ThreadEventLoopGroup;
import com.jeventloop.partition.DefaultPartitioner;
import com.jeventloop.partition.Partitioner;

public abstract class AbstractDispatch<K,V> implements IDispatch<K, V> {

	protected ThreadEventLoopGroup group;

	protected volatile int core;

	protected Partitioner partitioner;

	protected AbstractDispatch(int core) {
		this.core = core;
		this.partitioner = new DefaultPartitioner();
		this.group = new ThreadEventLoopGroup(core);
	}
}
