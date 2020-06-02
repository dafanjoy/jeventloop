package com.fan.eventLoop.partition;

public interface Partitioner {
   public int partition(int core, Object key);
}
