package dataStructures;

public class OrderdDoubleListIterator<K, V> implements Iterator<dataStructures.Entry<K, V>> {

	/**
	 * Serial Version UID of the Class
	 */
	static final long serialVersionUID = 0L;

	/**
	 * Node with the first element in the iteration.
	 */
	protected DListNode<Entry<K, V>> firstNode;

	/**
	 * Node with the last element in the iteration.
	 */
	protected DListNode<Entry<K, V>> lastNode;

	/**
	 * Node with the next element in the iteration.
	 */
	protected DListNode<Entry<K, V>> nextToReturn;

	/**
	 * Node with the previous element in the iteration.
	 */
	protected DListNode<Entry<K, V>> prevToReturn;

	public OrderdDoubleListIterator(DListNode<Entry<K, V>> head, DListNode<Entry<K, V>> tail) {
		firstNode = head;
		lastNode = tail;
		nextToReturn = firstNode;
		prevToReturn = null;
	}

	@Override
	public boolean hasNext() {
		return nextToReturn != null;
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {
		if (!this.hasNext())
			throw new NoSuchElementException();

		Entry<K, V> element = nextToReturn.getElement();
		prevToReturn = nextToReturn.getPrevious();
		nextToReturn = nextToReturn.getNext();
		return element;
	}

	@Override
	public void rewind() {
		nextToReturn = firstNode;
		prevToReturn = null;
	}

}
