package com.jeventloop.partition;

public interface Partitioner {
   public int partition(int core,Integer partition, Object key);
}
