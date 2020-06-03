package com.fan.eventLoop.dispatch;

import com.fan.eventLoop.record.EventRecord;

public interface IDispatch<K, V> {

	void dispatch(EventRecord<K, V> record);
}
