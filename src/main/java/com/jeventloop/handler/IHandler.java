package com.jeventloop.handler;

import com.jeventloop.record.EventRecord;

public interface IHandler {
	void execute(EventRecord<?, ?> record);
}
