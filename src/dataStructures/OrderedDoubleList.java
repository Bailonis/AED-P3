package dataStructures;



public class OrderedDoubleList<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	private static final long serialVersionUID = 0L; //Constant for Serialization

	protected DListNode<Entry<K, V>> head; // The DoubleLList head

	protected DListNode<Entry<K, V>> tail; // The DoubleLList tail

	protected int currentSize; // The DoubleLList currentSize

	/**
	 * OrderedDoubleLList Constructor
	 */
	public OrderedDoubleList() {

		this.head = null;
		this.tail = null;
		this.currentSize = 0;

	}

	@Override
	public boolean isEmpty() {

		return (currentSize == 0);
	}

	@Override
	public int size() {

		return currentSize;
	}

	@Override
	public V find(K key) {

		DListNode<Entry<K, V>> found = findNode(key);

		if (found == null)
			return null;
		else if (found.getElement().getKey().compareTo(key) == 0)
			return found.getElement().getValue();

		return null;
	}

	/**
	 * look for the node by key
	 *
	 * @param key - the node key
	 * @return the node
	 */
	protected DListNode<Entry<K, V>> findNode(K key) {

		DListNode<Entry<K, V>> current = head;

		while ((current != null) && (current.getElement().getKey().compareTo(key) < 0)) {

			current = current.getNext();

		}

		return current;

	}

	/**
	 * add to the head of the list the node with the entry element
	 *
	 * @param node - node to be added
	 * @param entry - entry of the node
	 */
	private void addFirst(DListNode<Entry<K, V>> node, Entry<K, V> entry) {

		node = new DListNode<Entry<K, V>>(entry, null, null);

		if (this.isEmpty()) {
			this.tail = node;
		} else {
			this.head.setPrevious(node);
			node.setNext(this.head);
		}
		this.head = node;

		currentSize++;

	}

	/**
	 * inserts the node with the entry element at the middle of the list
	 *
	 * @param node - node to be added
	 * @param entry - entry of the node
	 * @param found - the node found
	 */
	private void addMiddle(DListNode<Entry<K, V>> node, Entry<K, V> entry, DListNode<Entry<K, V>> found) {

		node = new DListNode<Entry<K, V>>(entry, null, null);

		node.setPrevious(found.getPrevious());

		found.getPrevious().setNext(node);

		found.setPrevious(node);

		node.setNext(found);

		currentSize++;

	}

	/**
	 *
	 * add to the tail of the list the node with the entry element
	 *
	 * @param node - node to be added
	 * @param entry - entry of the node
	 */
	private void addLast(DListNode<Entry<K, V>> node, Entry<K, V> entry) {

		node = new DListNode<Entry<K, V>>(entry, null, null);

		this.tail.setNext(node);

		node.setPrevious(tail);

		this.tail = node;

		currentSize++;

	}

	@Override
	public V insert(K key, V value) {

		Entry<K, V> entry = new EntryClass<K, V>(key, value);

		DListNode<Entry<K, V>> node = null;

		if (isEmpty()) {

			addFirst(node, entry);

		}

		else {

			DListNode<Entry<K, V>> found = findNode(key);

			if (found == null)
				addLast(node, entry);
			else if (found.getElement().getKey().compareTo(key) == 0) {
				V val = found.getElement().getValue();
				found.setElement(new EntryClass<K, V>(key, value));
				return val;
			} else {
				if (found == head)
					addFirst(node, entry);
				else
					addMiddle(node, entry, found);
			}

			return null;

		}

		return null;
	}

	/**
	 *	Remove the first node of the DoubleLList
	 */
	private void removeFirst() {

		this.head = head.getNext();

		if (this.head == null)
			tail = null;
		else
			this.head.setPrevious(null);

		currentSize--;

	}

	/**
	 * Remove the given node from the DoubleLList
	 * @param node - the node to be removed
	 */
	private void removeMiddle(DListNode<Entry<K, V>> node) {

		DListNode<Entry<K, V>> previous = node.getPrevious();

		DListNode<Entry<K, V>> next = node.getNext();

		previous.setNext(next);

		next.setPrevious(previous);

		currentSize--;

	}

	/**
	 * Remove the last node of the DoubleLList
	 */
	private void removeLast() {

		this.tail = tail.getPrevious();

		if (this.tail == null)
			head = null;
		else
			this.tail.setNext(null);

		currentSize--;

	}

	@Override
	public V remove(K key) {

		if (isEmpty())
			return null;

		else {

			DListNode<Entry<K, V>> node = findNode(key);

			if (node != null) {

				Entry<K, V> entry = node.getElement();

				if (entry.getKey().compareTo(key) == 0) {

					V value = entry.getValue();

					if (node == head)
						removeFirst();
					else if (node == tail)
						removeLast();
					else
						removeMiddle(node);

					return value;

				}

			}

		}

		return null;
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {

		return new DoublyLLIterator<Entry<K, V>>(head, tail);
	}

	@Override
	public Entry<K, V> minEntry() throws EmptyDictionaryException {

		if (isEmpty())
			throw new EmptyDictionaryException();

		return head.getElement();
	}

	@Override
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {

		if (isEmpty())
			throw new EmptyDictionaryException();

		return tail.getElement();
	}

}