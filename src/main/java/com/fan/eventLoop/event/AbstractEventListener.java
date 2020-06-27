package com.fan.eventLoop.event;

import java.util.EventListener;

/**
 * 事件监听器抽象类
 *
 */
public abstract class AbstractEventListener implements EventListener {
	 //报警发生后的回调方法  
    public abstract void fireEvent(LoopEvent e);

}
