package com.fan.eventLoop.test;

import com.fan.eventLoop.dispatch.EventLoopDispatch;
import com.fan.eventLoop.record.EventRecord;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		EventLoopDispatch dispatch = new EventLoopDispatch(4);

		for (int i = 0; i < 1000000; i++) {
			EventRecord<String, String> record = new EventRecord<String, String>(i + "", i + "");
			dispatch.dispatch(record);
		}
	}
}
