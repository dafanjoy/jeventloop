package com.fan.eventLoop.dispatch;

import com.fan.eventLoop.context.EventContext;

public class EventLoopDispatch<T> extends AbstractDispatch {

	private volatile int core;

	private static EventLoopDispatch dispatch;

	public EventLoopDispatch(int core) {
		super(core);
	}

	public EventLoopDispatch() {
		super(4);
	}

	public static EventLoopDispatch<?> getInstance() {
		if (dispatch == null) {
			synchronized (EventLoopDispatch.class) {
				if (dispatch == null) {
					dispatch = new EventLoopDispatch<EventContext>();
				}
			}
		}
		return dispatch;
	}

	@Override
	public void dispatch(EventContext context) {
		// TODO Auto-generated method stub
		//JSONObject json = JSON.parseObject(context.getSource().toString());
	   
		context.setKey(context.getSource().toString());
		
		group.next(partaion(context.getKey()),context);
	}

}
