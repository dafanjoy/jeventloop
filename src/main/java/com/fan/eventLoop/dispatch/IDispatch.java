package com.fan.eventLoop.dispatch;

public interface IDispatch<T>{

	void dispatch(T context);
}
