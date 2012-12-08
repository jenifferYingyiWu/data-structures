package wordSearch;
/**
 * A node class for the linked list that stores a value as a string.
 * @author Diego Waxemberg
 */
public class Node {
	private String value;
	private int occurrences;
	private Node next;
	
	/**
	 * Creates a new node with an initial value.
	 * @param value The value to initialize the node with.
	 */
	public Node(String value) {
		this.value = value;
		this.occurrences = 1;
		this.next = null;
	}
	
	/**
	 * Gets the value the node stores
	 * @return the value of the element
	 */
	public String getValue() {
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
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Gets the number of occurrences
	 * @return the occurrences
	 */
	public int getOccurrences() {
		return occurrences;
	}
	
	/**
	 * Increases the value of occurrences by 1
	 */
	public void incrementOccurrence() {
		this.occurrences++;
	}
	
	/**
	 * Sets the value of occurrences
	 * @param occurrences the value to set occurrences to
	 */
	public void setOccurences(int occurrences) {
		this.occurrences = occurrences;
	}
	
	/**
	 * Sets the pointer to the next node
	 * @param next a node to be the next in the list
	 */
	public void setNext(Node next) {
		this.next = next;
	}
}
