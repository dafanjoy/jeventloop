package com.fan.eventLoop.dispatch;

import com.fan.eventLoop.ThreadEventLoopGroup;
import com.fan.eventLoop.partition.DefaultPartitioner;
import com.fan.eventLoop.partition.Partitioner;

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
