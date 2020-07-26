package com.fan.eventLoop.event;

import com.fan.eventLoop.dispatch.EventLoopDispatch;
import com.fan.eventLoop.record.EventRecord;

/**
 * 事件监听器
 *
 */
public class LoopEventListener extends AbstractEventListener {
	
	EventLoopDispatch<String,String> dispatcher = new EventLoopDispatch<String,String>(4);//定义及初始化一个事件分发器

	@Override
	public void fireEvent(LoopEvent e) {
		EventRecord<String,String> eventRecord = new EventRecord<String, String>(null, e.getSource().toString());//转为内部统一的消息类型
		dispatcher.dispatch(eventRecord);//分发消息事件
	}

}
