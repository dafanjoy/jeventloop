package com.fan.eventLoop.dispatch;

import com.fan.eventLoop.ThreadEventLoopGroup;
import com.fan.eventLoop.context.EventContext;

public abstract class AbstractDispatch implements IDispatch<EventContext> {
	
	protected ThreadEventLoopGroup group; 
	
	protected volatile int core;
	
	protected AbstractDispatch (int core){
		this.core=core;
	    group = new ThreadEventLoopGroup(core);
	}
	
	
	public int partaion (String key){
		return Math.abs(key.hashCode()%core);
	}
}
