
package dataStructures;



public class EntryClass<K,V> implements EntrySets<K,V> {

    private static final long serialVersionUID = 0L; // Constant for Serialization

    private K key; // The entry key
    private V value; // The entry value

    /**
     * EntryClass Constructor
     * @param key - the entry key
     * @param value - the entry value
     */
    public EntryClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
	public void setKey(K key) {
		this.key = key;
	}

    @Override
	public void setValue(V value) {
		this.value = value;
	}
    
    
}
