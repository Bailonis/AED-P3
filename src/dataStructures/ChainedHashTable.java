
package dataStructures;

/**
 * Chained Hash table implementation
 * 
 * @author AED Team
 * 
 * @version 1.0
 * 
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value
 */

public class ChainedHashTable<K extends Comparable<K>, V> extends HashTable<K, V> {
	
	/**
	 * Serial Version UID of the Class.
	 */
	static final long serialVersionUID = 0L; // Constant for Serialization
	private static final int GROWTH = 2; // The Growth Constant
	
	/**
	 * The array of dictionaries.
	 */
	protected Dictionary<K, V>[] table;

	/**
	 * Constructor of an empty chained hash table, with the specified initial
	 * capacity. Each position of the array is initialized to a new ordered list
	 * maxSize is initialized to the capacity.
	 * 
	 * @param capacity defines the table capacity.
	 */
	@SuppressWarnings("unchecked")
	public ChainedHashTable(int capacity) {
		int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
		// Compiler gives a warning.
		table = (Dictionary<K, V>[]) new Dictionary[arraySize];

		initialize();
		
		maxSize = capacity;
		currentSize = 0;
	}

	/**
	 * ChainedHashTable Constructor
	 */
	public ChainedHashTable() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Returns the hash value of the specified key.
	 * 
	 * @param key to be encoded
	 * @return hash value of the specified key
	 */
	protected int hash(K key) {
		return Math.abs(key.hashCode()) % table.length;
	}

	@Override
	public V find(K key) {
		return table[this.hash(key)].find(key);
	}

	@Override
	public V insert(K key, V value) {
		if (this.isFull())
			this.rehash();

		V existValue = table[this.hash(key)].insert(key, value);

		if (existValue == null)
			currentSize++; // there is no key with the given key, so it is inserted in the map

		return existValue;
	}
	/**
	 * increases the size of the dictionary
	 */

	@SuppressWarnings("unchecked")
	private void rehash() {
		
		maxSize *= GROWTH;
		
		Iterator<Entry<K, V>> it = this.iterator();

		table = (Dictionary<K, V>[]) new Dictionary[HashTable
				.nextPrime((int) (1.1 * maxSize))];

		initialize();
		
		Entry<K, V> entry;
		K key;

		while (it.hasNext()) {

			entry = it.next();
			key = entry.getKey();
			table[hash(key)].insert(key, entry.getValue());
		}

	}
	
	/**
	 * initialize table
	 */
	private void initialize() {
		
		for (int i = 0; i < table.length; i++) {
			table[i] = new OrderedDoubleList<K, V>();
		}	
		
	}

	@Override
	public V remove(K key) {

		if (this.isEmpty())
			return null;
		V remove = table[this.hash(key)].remove(key);

		if (remove != null)
			currentSize--;
		return remove;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		return new ChainedIterator<>(table);
	}
}