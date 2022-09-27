package com.jeventloop.dispatch;

import com.jeventloop.record.EventRecord;

public interface IDispatch<K, V> {
	void dispatch(EventRecord<K, V> record);
}
