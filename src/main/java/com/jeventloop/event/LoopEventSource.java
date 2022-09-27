package com.jeventloop.event;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 事件源
 *
 */
public class LoopEventSource {
	// 监听器容器
	private static Set<AbstractEventListener> listeners;

	public LoopEventSource() {
		listeners = new HashSet<AbstractEventListener>();
	}

	// 给事件源注册监听器
	public void addEventListener(AbstractEventListener listener) {
		listeners.add(listener);
	}

	// 当事件发生时,通知注册在该事件源上的所有监听器做出相应的反应（调用回调方法）
	public void notifies(LoopEvent event) {
		AbstractEventListener listener = null;
		try {
			Iterator<AbstractEventListener> iterator = listeners.iterator();
			while (iterator.hasNext()) {
				listener = iterator.next();
				listener.fireEvent(event); // 如果事件消息类型不同，可以进行封装
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
