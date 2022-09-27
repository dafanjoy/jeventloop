package com.jeventloop.example;

import com.jeventloop.event.LoopEvent;
import com.jeventloop.event.LoopEventListener;
import com.jeventloop.event.LoopEventSource;

/**
 * 基于事件机制触发消息分发
 *
 */
public class App {
	public static void main(String[] args) {
		LoopEventSource source = new LoopEventSource();
		source.addEventListener(new LoopEventListener()); 
		for (int i = 0; i < 1000000; i++) {
			LoopEvent event = new LoopEvent(i);
			source.notifies(event);
		}
	}
}
