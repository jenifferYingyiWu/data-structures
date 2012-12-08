package dataTypes;
/**
 * This is an ADT for a list stored as an array.
 * @author Diego Waxemberg
 */
public class NumArrayList implements NumList{
	private double[] array;
	private Exception e = new Exception("The requested location is not valid.");

	/**
	 * Creates a new array for the list that is empty.
	 */
	public NumArrayList() {	
		array = new double[0];
	}

	/**
	 * Gets the number of elements in the list
	 * @return the number of elements in the array
	 */
	@Override
	public int size() {
		return array.length;
	}

	/**
	 * Inserts a new element into the list.
	 * @param n the location for the new element
	 * @param value the value of the element to be inserted
	 */
	@Override
	public void insert(int n, double value) {
		double[] tmpArray = new double[array.length + 1];
		if (n < 0) {
			n = 0;
		}
		if (n > array.length) {
			n = array.length;
		}
		int i = 0;
		while (i < n) {
			tmpArray[i] = array[i];
			i++;
		}
		tmpArray[i] = value;
		i++;
		while (i < array.length + 1) {
			tmpArray[i] = array[i-1];
			i++;
		}
		array = tmpArray;
	}

	/**
	 * Finds an element in the list
	 * @param i the location of the element in the list
	 * @return the value of the element at location i or an exception if there is no element at i
	 * @throws Exception if the location is not valid for the list
	 */
	@Override
	public double lookup(int i) throws Exception{
		if (i >= 0 && i < array.length) {
			return array[i];
		}
		else {
			throw e;
		}
	}

	/**
	 * Prints the contents of the list to the console.
	 */
	@Override
	public void print() {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			if (i < array.length-1) {
				System.out.print(", ");
			}
		}
		System.out.println();
	}

	/**
	 * Removes an element from the list.
	 * @param n the location of the element to be removed
	 */
	@Override
	public void remove(int n) {
		if (array.length > n && n >= 0) {
			double[] tmpArray = new double[array.length-1];
			for (int i = 0; i < array.length-1; i++) {
				if (i < n)
					tmpArray[i] = array[i];
				else
					tmpArray[i] = array[i+1];
			}
			array = tmpArray;
		}
	}

	/**
	 * Removes all duplicate elements from the list.
	 */
	@Override
	public void removeDuplicates() {
		for (int i = 0; i < array.length-1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] == array[j]) {
					this.remove(j);
					j--;
				}
			}
		}
	}

	/**
	 * Tests the methods in this class by creating instances and invoking methods on them.
	 * @param args the arguments are unused
	 */
	public static void main(String[] args) {
		try {
			NumArrayList list = new NumArrayList();
			System.out.println("Size: " + list.size());
			list.remove(0);
			for (int i = 0; i < 10; i++) {
				list.insert(i,i);
			}
			list.print();
			System.out.println("Size: " + list.size());
			System.out.println("Insert(4,1):");
			list.insert(4, 1);
			list.print();
			System.out.println("Remove(3):");
			list.remove(3);
			list.print();
			System.out.println("Lookup(5): " + list.lookup(5));
			list.removeDuplicates();
			System.out.println("Remove Duplicates:");
			list.print();
			System.out.println("Size: " + list.size());
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		NumArrayList list = new NumArrayList();
		list.insert(0, 1);
		list.insert(0, 2);
		list.insert(0, 3);
		System.out.println("New List:");
		list.print();
		System.out.println("Try to insert 10.5 at -1:");
		list.insert(-1,10.5);
		list.print();
		System.out.println("Try to insert 4.2 at 15:");
		list.insert(15, 4.2);
		list.print();
		try {
			System.out.println("Lookup(0): ");
			System.out.println(list.lookup(0));
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			System.out.println("Lookup(10): ");
			System.out.println(list.lookup(10));
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
		try {
			System.out.println("Lookup(-5): ");
			System.out.println(list.lookup(-5));
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
