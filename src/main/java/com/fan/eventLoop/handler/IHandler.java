package com.fan.eventLoop.handler;

import com.fan.eventLoop.record.EventRecord;

public interface IHandler {
	void execute(EventRecord<?, ?> record);
}
