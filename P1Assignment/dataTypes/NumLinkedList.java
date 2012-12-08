package dataTypes;
/**
 * An ADT that uses a linked list to store values.
 * @author Diego Waxemberg
 */
public class NumLinkedList implements NumList {
	private Node head;
	private Exception e = new Exception("The requested location is not valid.");

	/**
	 * Creates a new list with no elements (i.e. a linked list with no nodes).
	 */
	public NumLinkedList() {
		head = new Node();
	}

	/**
	 * Gets the pointer to the list (the head). 
	 * @return the head of the list
	 */
	public Node getHead() {
		return head;
	}

	/**
	 * Sets the pointer to the list (the head).
	 * @param head a null node that will point to the first node
	 */
	public void setHead(Node head) {
		this.head = head;
	}

	public boolean swap(Node p) {
		Node pNext = p.getNext();
		if (pNext != null) {
			Node lstPtr = head; 
			while (lstPtr.getNext() != null && lstPtr.getNext() != p) {
				lstPtr = lstPtr.getNext();
			}
			if (lstPtr.getNext() != p) {
				return false;
			}
			lstPtr.setNext(p.getNext());
			p.setNext(pNext.getNext());
			pNext.setNext(p);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * A method to return the number of elements in the list.
	 * @return the number of elements in the list
	 */ 
	@Override
	public int size() {
		Node lstPtr = getHead();
		int length = 0;
		while (lstPtr.getNext() != null) {
			lstPtr = lstPtr.getNext();
			length++;
		}
		return length;
	}

	/**
	 * Inserts a new element into the list
	 * @param n the location to insert the value
	 * @param value the value to be inserted in the list
	 */ 
	@Override
	public void insert(int n, double value) {
		if (n < 0) {
			n = 0;
		}
		if (n > size()) {
			n = size();
		}
		Node newNode = new Node();
		newNode.setValue(value);
		if (n == 0) {
			getHead().setNext(newNode);
			return;
		}
		Node lstPtr = getHead();
		int i = 0;
		while (lstPtr.getNext() != null && i < n) {
			lstPtr = lstPtr.getNext();
			i++;
		}
		if (i == n) {
			newNode.setNext(lstPtr.getNext());
			lstPtr.setNext(newNode);
		} 
	} 

	/**
	 * Removes an element from the list
	 * @param n the location of the element to be removed.
	 */
	@Override
	public void remove(int n) {
		if (n >= 0 && n < size()) {
			Node lstPtr = getHead();
			if (lstPtr.getNext() != null) {
				lstPtr = lstPtr.getNext();
				int i;
				for (i = 0; lstPtr.getNext() != null && i < n - 1; i++) {
					lstPtr.getNext();
				}
				if (i == n - 1) {
					lstPtr.setNext(lstPtr.getNext().getNext());
				} 
			} 
		} 
	}

	/**
	 * Gets an element from the list
	 * @param n the location of the element in the list
	 * @return the value of the element in the list or an exception if there is no element at the requested position
	 * @throws Exception if the location is not valid
	 */
	@Override
	public double lookup(int n) throws Exception {
		if (n >= 0) {
			Node lstPtr = getHead();
			int i = 0;
			while (lstPtr != null && i < n + 1) {
				lstPtr = lstPtr.getNext();
				i++;
			}
			if (i == n + 1) {
				return lstPtr.getValue();
			} 
			else {
				throw e;
			}
		} 
		else {
			throw e;
		}
	}

	/**
	 * Removes all duplicate elements in the list.
	 */
	@Override
	public void removeDuplicates() {
		try {
			Node lstPtr1 = getHead();
			Node lstPtr2 = null;
			if (lstPtr1.getNext() != null) {
				lstPtr1 = lstPtr1.getNext();
				int count1 = 0;
				int count2;
				while (lstPtr1 != null) {
					count2 = count1 + 1;
					lstPtr2 = lstPtr1.getNext();
					while (lstPtr2 != null) {
						if (lstPtr2.getValue() == lstPtr1.getValue()) {
							remove(count2);
							count2++;
						}
						lstPtr2 = lstPtr2.getNext();

					}
					count1++;
					lstPtr1 = lstPtr1.getNext();
				}
			}
		}
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	/**
	 * Prints out the contents of the list to the console.
	 */
	@Override
	public void print() {
		try {
			for (int i = 0; i < size(); i++) {
				System.out.print(lookup(i) + " ");
			}
			System.out.println();
		} 
		catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
	}

	/**
	 * This method tests all methods in this class by creating instances and invoking methods on those instances.
	 * @param args the arguments are unused.
	 */
	public static void main(String[] args) {
		NumLinkedList a = new NumLinkedList();
		System.out.println("Size: " + a.size());
		a.insert(-5, 1.4);
		a.insert(0, 4.2);
		a.insert(1, 3.75);
		a.insert(2, 6);
		System.out.println("Size: " + a.size());
		a.insert(3, 3.75);
		a.insert(4, 7);
		a.insert(2, 10.2);
		a.insert(5, 10.2);
		a.insert(15, 4.2);
		a.print();
		System.out.println("Swap: " + a.swap(a.getHead().getNext().getNext()));
		a.print();
		System.out.println("Value at 0:" + a.getHead().getNext().getValue());
		System.out.println("Size: " + a.size());
		try {
			System.out.println("Lookup(-1):");
			System.out.print(a.lookup(-1));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			System.out.println("Lookup(0): ");
			System.out.println(a.lookup(0));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			System.out.println("Lookup(4): ");
			System.out.println(a.lookup(4));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			System.out.println("Lookup(7): ");
			System.out.println(a.lookup(7));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			System.out.println("Lookup(10): ");
			System.out.println(a.lookup(10));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		a.print();
		System.out.println("Remove duplicates:");
		a.removeDuplicates();
		a.print();
		System.out.println("Size: " + a.size());
		try {
			a.remove(-2);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			a.remove(2);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("Remove(2)");
		a.print();
		System.out.println("Size: " + a.size());
		try {
			System.out.println("Lookup(2): ");
			System.out.println(a.lookup(2));
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
