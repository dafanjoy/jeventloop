package com.fan.eventLoop.context;

import java.util.EventObject;

public class EventContext extends EventObject {
	public EventContext(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}


	protected int id = 0;
	private String key;
	private int state;
	private int type;

}
