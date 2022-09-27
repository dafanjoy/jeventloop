package com.jeventloop.event;

import java.util.EventObject;


/** 
 * 事件类,用于封装事件源及一些与事件相关的参数. 
 */ 
public class LoopEvent extends EventObject {
	
	private static final long serialVersionUID = 1L;  

	public LoopEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
