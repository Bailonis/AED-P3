package dataStructures;

public class InvertibleQueueInList<E> extends QueueInList<E> implements InvertibleQueue<E> {

	boolean isInverted = true;

	@Override
	public void invert() {
		isInverted = !isInverted;

	}

	@Override
	public void enqueue(E element) {
		if (isInverted)
			list.addFirst(element);
		else
			list.addLast(element);
	}

	@Override
	public E dequeue() {
		if (list.isEmpty())
			throw new EmptyQueueException();
		if (isInverted)
			return list.removeLast();
		else
			return list.removeFirst();

	}

}
