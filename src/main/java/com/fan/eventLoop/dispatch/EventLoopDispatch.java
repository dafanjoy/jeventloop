package com.fan.eventLoop.dispatch;

import com.fan.eventLoop.record.EventRecord;

public class EventLoopDispatch<K, V> extends AbstractDispatch<K, V> {

	public EventLoopDispatch(int core) {
		super(core);
	}

	public void dispatch(EventRecord<K, V> record) {
		int partation = partitioner.partition(core,record.partition(), record.key());
		group.next(partation, record);
	}


}
