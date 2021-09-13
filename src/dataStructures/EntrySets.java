
package dataStructures;



public interface EntrySets<K,V> extends Entry<K,V> {
	
	/**
	 * Set key
	 * @param key - the new key
	 */
	void setKey(K key);
	
	/**
	 * Set value
	 * @param value - the new value
	 */
	void setValue(V value);

}
