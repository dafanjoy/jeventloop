package com.fan.eventLoop;

import com.fan.eventLoop.context.EventContext;
import com.fan.eventLoop.dispatch.EventLoopDispatch;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	EventLoopDispatch dispatch = new EventLoopDispatch(4);
    	
        for(int i=0;i<1000000;i++) {
        	EventContext context = new EventContext(i);;
        	dispatch.dispatch(context);
        }
    }
}
