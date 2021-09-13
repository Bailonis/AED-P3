package dataStructures;

public class BSTKeyOrderIterator<K, V> implements Iterator<Entry<K, V>> {

	private static final long serialVersionUID = 0L;

	protected BSTNode<K, V> root;

	protected Stack<BSTNode<K, V>> stack;

	public BSTKeyOrderIterator(BSTNode<K, V> root) {

		this.root = root;
		rewind();

	}

	@Override
	public boolean hasNext() {

		return !stack.isEmpty();
	}

	@Override
	public Entry<K, V> next() throws NoSuchElementException {

		if (!this.hasNext())
			throw new NoSuchElementException();

		BSTNode<K, V> node = stack.pop();

		BSTNode<K, V> rightnode = node.getRight();

		this.pathNodes(rightnode);

		return node.getEntry();
	}

	@Override
	public void rewind() {

		stack = new StackInList<BSTNode<K, V>>();
		this.pathNodes(root);

	}

	/**
	 * Path Nodes
	 * @param node
	 */
	private void pathNodes(BSTNode<K, V> node) {

		while (node != null) {

			stack.push(node);
			node = node.getLeft();

		}

	}

}
