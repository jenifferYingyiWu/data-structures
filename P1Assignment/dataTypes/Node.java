package dataTypes;
/**
 * A node class for the linked list that stores a value as a double.
 * @author Diego Waxemberg
 */
public class Node {
	private double value;
	private Node next;
	
	/**
	 * A blank constructor.
	 */
	public Node() {
	}
	
	/**
	 * Gets the value the node stores
	 * @return the value of the element
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Gets the pointer to the next node in the list
	 * @return the next node
	 */
	public Node getNext() {
		return next;
	}
	
	/**
	 * Sets the value for the node
	 * @param value the value to be set for the element
	 */
	public void setValue(double value) {
		this.value = value;
	}
	
	/**
	 * Sets the pointer to the next node
	 * @param next a node to be the next in the list
	 */
	public void setNext(Node next) {
		this.next = next;
	}
}
