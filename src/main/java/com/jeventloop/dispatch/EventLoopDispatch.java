package com.jeventloop.dispatch;

import com.jeventloop.record.EventRecord;

public class EventLoopDispatch<K, V> extends AbstractDispatch<K, V> {

	public EventLoopDispatch(int core) {
		super(core);
	}
	
	//消息分发与执行
	public void dispatch(EventRecord<K, V> record) {
		int partation = partitioner.partition(core,record.partition(), record.key());//根据传入的消息数据，确定partation
		group.next(partation, record);//根据partation,确定执行的EventLoop
	}
}
