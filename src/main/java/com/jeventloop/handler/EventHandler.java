package com.jeventloop.handler;

import com.jeventloop.record.EventRecord;

public class EventHandler implements IHandler {

	@Override
	public void execute(EventRecord<?, ?> record) {
		// TODO Auto-generated method stub
		System.err.println(record.value());
		
	}

}
