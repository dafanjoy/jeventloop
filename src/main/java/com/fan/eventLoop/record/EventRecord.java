package com.fan.eventLoop.record;

public class EventRecord<K, V> {

	private K key;
	private Integer partition;
	private final V value;
	private final Long timestamp;

	public EventRecord(Integer partition, K key, V value, Long timestamp) {
		if (timestamp != null && timestamp < 0)
			throw new IllegalArgumentException(String
					.format("Invalid timestamp: %d. Timestamp should always be non-negative or null.", timestamp));
		if (partition != null && partition < 0)
			throw new IllegalArgumentException(
					String.format("Invalid partition: %d. partition number should always be non-negative or null.", partition));
		this.partition = partition;
		this.key = key;
		this.value = value;
		this.timestamp = timestamp;
	}

	public EventRecord(Integer partation, K key, V value) {
		this(partation, key, value, null);
	}


	public EventRecord(K key, V value) {
		this(null, key, value, null);
	}

	/**
	 * @return The key (or null if no key is specified)
	 */
	public K key() {
		return key;
	}

	/**
	 * @return The value
	 */
	public V value() {
		return value;
	}

	/**
	 * @return The timestamp, which is in milliseconds since epoch.
	 */
	public Long timestamp() {
		return timestamp;
	}

	/**
	 * @return The partition to which the record will be sent (or null if no
	 *         partition was specified)
	 */
	public Integer partition() {
		return partition;
	}

}
