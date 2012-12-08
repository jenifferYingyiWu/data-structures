package dataTypes;
/**
 * This class utilizes an ADT to store a set of doubles.
 * @author Diego Waxemberg
 */
public class NumSet {
	private NumArrayList arrList;

	/**
	 * Initializes a variable that holds the list for this set as an Array List.
	 * @param set The values to be initialized in this set 
	 */
	public NumSet(double[] set) {
		try {
			arrList = new NumArrayList();
			for (int i = 0; i < set.length; i++) {
				arrList.insert(i, set[i]);
			}
			arrList.removeDuplicates();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Sets the list in the appropriate variable.
	 * @param arrList an Array List to be associated with this set.
	 */
	public void setElements(NumArrayList arrList) {
		this.arrList = arrList;
		arrList.removeDuplicates();
	}

	/**
	 * Inserts a value into the set at position n.
	 * @param n the position to insert a value
	 * @param value the value to be inserted in the set
	 */
	public void insertElement(int n, double value) {
		try {
			NumArrayList tmpList = getArray();
			tmpList.insert(n, value);
			setElements(tmpList);
		}
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Gets the set as an Array List.
	 * @return the array list variable.
	 */
	public NumArrayList getArray() {
		return arrList;
	}

	/**
	 * Takes two sets and finds the intersect of them, then returns a new set.
	 * @param set1 a set
	 * @param set2 another set
	 * @return A new set containing the intersect  of set1 and set2.
	 */
	public NumSet intersect(NumSet set1, NumSet set2) {
		try {
			NumArrayList list1 = set1.getArray();
			NumArrayList list2 = set2.getArray();
			list1.removeDuplicates();
			list2.removeDuplicates();
			NumArrayList intList = new NumArrayList();
			int index = 0;
			for (int i = 0; i < list1.size(); i++) {
				for (int j = 0; j < list2.size(); j++) {
					if (list1.lookup(i) == list2.lookup(j)) {
						intList.insert(index, list1.lookup(i));
						index++;
					}
				}
			}	
			double[] newSet = new double[intList.size()];
			for (int i = 0; i < intList.size(); i++) {
				newSet[i] = intList.lookup(i);
			}
			return new NumSet(newSet);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return new NumSet(new double[] {});
		}
	}

	/**
	 * Takes two sets and finds the union of them, then returns a new set.
	 * @param set1 a set
	 * @param set2 another set
	 * @return A new set containing the union of set1 and set2.
	 */
	public NumSet union(NumSet set1, NumSet set2) {
		try {
			NumArrayList list1 = set1.getArray();
			NumArrayList list2 = set2.getArray();
			NumArrayList unionList = new NumArrayList();
			list1.removeDuplicates();
			list2.removeDuplicates();
			int index = 0;
			for (int i = 0; i < list1.size(); i++) {
				unionList.insert(index, list1.lookup(i));
				index++;
			}
			for (int i = 0; i < list2.size(); i++) {
				unionList.insert(index, list2.lookup(i));
				index++;
			}
			double[] newSet = new double[unionList.size()];
			for (int i = 0; i < unionList.size(); i++) {
				newSet[i] = unionList.lookup(i);
			}
			return new NumSet(newSet);
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return new NumSet(new double[] {});
		}

	}

	/**
	 * Prints out all of the elements in the set.
	 */
	public void print() {
		NumArrayList list = this.getArray();
		list.print();
	}

	/**
	 * Creates two sets and prints out the intersect and union of the two.
	 * @param args The arguments are unused.
	 */
	public static void main(String[] args) {
		double[] set1array = new double[10];
		double[] set2array = new double[10];
		for (int i = 0; i < 10; i++) {
			set1array[i] =  i * 1.5;
			set2array[i] = i * 0.75;
		}
		NumSet set1 = new NumSet(set1array);
		NumSet set2 = new NumSet(set2array);
		
		System.out.println("Set1:");
		set1.print();
		System.out.println("Set2:");
		set2.print();
		System.out.println("Set1 union Set2:");
		NumSet set3 = new NumSet(new double[] {}).union(set1, set2);
		set3.print();
		System.out.println("Set1 intersect Set2:");
		NumSet set4 = new NumSet(new double[] {}).intersect(set1, set2);
		set4.print();
	}

}
