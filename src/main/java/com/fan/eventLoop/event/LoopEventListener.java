package com.fan.eventLoop.event;

import com.fan.eventLoop.dispatch.EventLoopDispatch;
import com.fan.eventLoop.record.EventRecord;

public class LoopEventListener extends AbstractEventListener {
	
	EventLoopDispatch dispatch = new EventLoopDispatch(4);

	@Override
	public void fireEvent(LoopEvent e) {
		EventRecord<String,String> eventRecord = new EventRecord<String, String>(null, e.getSource().toString());
		dispatch.dispatch(eventRecord);
	}

}
