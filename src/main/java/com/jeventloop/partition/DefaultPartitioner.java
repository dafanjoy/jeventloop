package com.jeventloop.partition;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import com.jeventloop.utils.ConstantUtil;

public class DefaultPartitioner implements Partitioner {

	private final ConcurrentMap<Integer, AtomicInteger> topicCounterMap = new ConcurrentHashMap<>();

	public int partition(int core, Integer partition, Object key) {
		if (partition != null ) {
			if(partition>core) {//可以自己指定partition，但不能大于core，也就是group个数
				throw new IllegalArgumentException(String
						.format("Invalid partition: %d. partition should always be greater than core.", partition));
			}
			return partition;
		} else if (partition == null && key == null) {//如果没有指定key或partition，则采用自增取余模式
			int nextValue = nextValue(core);
			return ConstantUtil.toPositive(nextValue) % core;

		} else {//如果partition为空，key不为空，则根据hash取余
			return Math.abs(key.hashCode() % core);
		}
	}

	private int nextValue(int core) {
		AtomicInteger counter = topicCounterMap.get(core);
		if (null == counter) {
			counter = new AtomicInteger(ThreadLocalRandom.current().nextInt());
			AtomicInteger currentCounter = topicCounterMap.putIfAbsent(core, counter);
			if (currentCounter != null) {
				counter = currentCounter;
			}
		}
		return counter.getAndIncrement();
	}
}