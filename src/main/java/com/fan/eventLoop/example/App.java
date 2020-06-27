package com.fan.eventLoop.example;

import com.fan.eventLoop.event.LoopEvent;
import com.fan.eventLoop.event.LoopEventListener;
import com.fan.eventLoop.event.LoopEventSource;

/**
 * Hello world!
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
