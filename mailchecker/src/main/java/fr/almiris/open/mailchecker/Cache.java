package fr.almiris.open.mailchecker;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A very simple cache (no background cleaning mecanism of expired entries)
 * @param <K> Key
 * @param <V> value
 */
public class Cache<K,V> {

	public class Entry<W> {
		W value;
		long when;
		public Entry(W value) {
			this.value = value;
			when = System.currentTimeMillis();
		}
		public W getValue() {
			return this.value;
		}
		public long when() {
			return this.when;
		}
	}
	
	public final static long DEFAULT_DURATION = 1000 * 3600; // 1 hour
	
	private int size;
	
	private long duration = DEFAULT_DURATION;
	
	private Map<K, Entry<V>> map = Collections.synchronizedMap(new HashMap<K, Entry<V>>());
		
	public Cache() {
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public V get(K key) {
		Entry<V> value = map.get(key);
		if (value == null) {
			return null;
		}
		if (System.currentTimeMillis() - value.when > duration) {
			map.remove(key);
			return null;
		}
		return value.getValue();
	}
	
	public void put(K key, V value) {
		V tmpValue = get(key);
		if (tmpValue != null && this.map.size() < this.size) {
			Entry<V> entry = new Entry<V>(value);
			this.map.put(key, entry);
		}
	}
	
}
