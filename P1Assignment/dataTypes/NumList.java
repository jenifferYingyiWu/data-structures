package dataTypes;
/**
 * An interface for a list ADT.
 * @author Diego Waxemberg
 */
public interface NumList {

	/**
	 * Gets the size of the list.
	 * @return the number of elements in the list
	 */
	public int size();
	
	/**
	 * A method to insert a new value into the list.
	 * @param i the location to insert the value at
	 * @param value the value to be inserted in the list
	 */
	public void insert(int i, double value);

	/**
	 * Removes an element in the list.
	 * @param i the location of the element to be removed
	 * @throws Exception if the location is not valid
	 */
	public void remove(int i) throws Exception;
	
	/**
	 * A method to get an element from the list.
	 * @param i the location of the element
	 * @return the value of the element at position i
	 * @throws Exception if the location is not valid
	 */
	public double lookup(int i) throws Exception;

	/**
	 * Removes all duplicate elements in the list.
	 */
	public void removeDuplicates();
	
	/**
	 * Prints the contents of the list to the console.
	 */
	public void print();
}
