package dataStructures;

public class ChainedIterator<K, V> implements Iterator<Entry<K, V>> {

	private static final long serialVersionUID = 0L; // Constant for Serialization
	private int current; // The current position
	private Dictionary<K, V>[] table; // The table of dictionaries
	private Dictionary<K, V> dictionaries; // The dictionary
	private Iterator<Entry<K, V>> it; // The entry iterator

	// DictionaryIterator Constructor
	public ChainedIterator(Dictionary<K, V>[] table) {
		this.table = table;
		rewind();
	}

	@Override
	public boolean hasNext() {

		return current < table.length;
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {

		if (!this.hasNext())
			throw new NoSuchElementException();

		Entry<K, V> entry = it.next();

		if (!it.hasNext())
			getNextElement();

		return entry;
	}

	@Override
	public void rewind() {
		current = 0;
		dictionaries = table[current];
		while (current < table.length && table[current].isEmpty()) {

			current++;

		}

		if (current < table.length) {
			dictionaries = table[current];

			it = dictionaries.iterator();

		}

	}
	
	/**
	 * move to the next list of the map if the current list is empty or there is no element to read
	 */

	private void getNextElement() {

		do {

			current++;

		} while (current < table.length && table[current].isEmpty());

		if (current < table.length) {
			dictionaries = table[current];

			if (!dictionaries.isEmpty())
				it = dictionaries.iterator();
		}
	}
}