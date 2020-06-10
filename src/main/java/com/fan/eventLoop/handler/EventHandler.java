package com.fan.eventLoop.handler;

import com.fan.eventLoop.record.EventRecord;

public class EventHandler implements IHandler {

	@Override
	public void execute(EventRecord<?, ?> record) {
		// TODO Auto-generated method stub
		System.err.println(record.value());
		
	}

}
