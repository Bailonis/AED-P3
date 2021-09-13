
package dataStructures;

import java.io.Serializable;

/**
 * Double List Node Implementation
 * 
 * @author AED  Team
 * 
 * @version 1.0
 * 
 * @param <E> Generic Element
 * 
 */

class DListNode<E> implements Serializable {

	/**
	 * Constant for serialization
	 */
    static final long serialVersionUID = 0L;


    /**
     * Element stored in the node.
     */
    private E element;

    /**
     * (Pointer to) the previous node.
     */
    private DListNode<E> previous;

    /**
     * (Pointer to) the next node.
     */
    private DListNode<E> next;


    /**
     * DListNode Constructor
     * @param theElement - The element to be contained in the node
     * @param thePrevious - the previous node
     * @param theNext - the next node
     */
    public DListNode(E theElement, DListNode<E> thePrevious, DListNode<E> theNext) {
        element = theElement;
        previous = thePrevious;
        next = theNext;
    }


    /**
     * DListNode Constructor
     * @param theElement to be contained in the node
     */
    public DListNode(E theElement) {
        this(theElement, null, null);
    }


    /**
     * Get Element
     * @return the element contained in the node
     */
    public E getElement() {
        return element;
    }


    /**
     * Get Previous
     * @return the previous node
     */
    public DListNode<E> getPrevious() {
        return previous;
    }


    /**
     * Get Next
     * @return the next node
     */
    public DListNode<E> getNext() {
        return next;
    }


    /**
     * Set Element
     * @param newElement - New element to replace the current element
     */
    public void setElement(E newElement) {
        element = newElement;
    }


    /**
     * Set Previous
     * @param newPrevious - node to replace the current previous node
     */
    public void setPrevious(DListNode<E> newPrevious) {
        previous = newPrevious;
    }


    /**
     * Set Next
     * @param newNext - node to replace the next node
     */
    public void setNext(DListNode<E> newNext) {
        next = newNext;
    }


}
