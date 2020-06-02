package com.fan.eventLoop.dispatch;

import com.fan.eventLoop.context.EventContext;
import com.fan.eventLoop.partition.DefaultPartitioner;
import com.fan.eventLoop.partition.Partitioner;

public class EventLoopDispatch extends AbstractDispatch {

	public EventLoopDispatch(int core) {
		super(core);
	}

	@Override
	public void dispatch(EventContext context) {
		int partation = partitioner.partition(core, context.getKey());
		group.next(partation, context);
	}
}
