package dataTypes;
/**
 * This small program creates two grade books and calculates information about them.
 * @author Diego Waxemberg
 */
public class Program {

	/**
	 * Finds the average grade of a grade book.
	 * @param grades a set of grades
	 * @return the average grade of the set
	 */
	public static double avgGrades(NumSet grades) {
		try {
			NumArrayList grade = grades.getArray();
			double sum = 0.0;
			for (int i = 0; i < grade.size(); i++) {
				sum += grade.lookup(i);
			}
			return sum / grade.size();
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	/**
	 * Finds the average of two grade books.
	 * @param grades1 a set of grades
	 * @param grades2 another set of grades
	 * @return the average of the tow sets of grades.
	 */
	public static double avgGrades(NumSet grades1, NumSet grades2) {
		try {
			NumArrayList grade1 = grades1.getArray();
			NumArrayList grade2 = grades2.getArray();
			double sum = 0.0;
			for (int i = 0; i < grade1.size(); i++) {
				sum += grade1.lookup(i);
			}
			for (int i = 0; i < grade2.size(); i++) {
				sum += grade2.lookup(i);
			}
			return sum / (grade1.size() + grade2.size());
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return -1;
		}
	}

	public static NumArrayList sortList(NumArrayList list) {
		try {
			NumArrayList sortedList = new NumArrayList();
			sortedList.insert(0, list.lookup(0));
			for (int i = 1; i < list.size(); i++) {
				int j = 0; 
				while (list.lookup(i) < sortedList.lookup(j) && j < sortedList.size()) {
					j++;
					if (j > sortedList.size() - 1) {
						break;
					}
				}
				sortedList.insert(j, list.lookup(i));
			}
			return sortedList;
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return list;
		}
	}

	public static double medianGrade(NumSet grades) {
		try {
			NumArrayList grade = sortList(grades.getArray());
			int low = 0;
			int high = grade.size() - 1;
			while (low < high) {
				low++;
				high--;
			}
			if (low == high) {
				return grade.lookup(low);
			}
			else {
				return (grade.lookup(low) + grade.lookup(high)) / 2.0;
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return -1;
		}
	}

	public static double medianGrade(NumSet grades1, NumSet grades2) {
		try {
			NumArrayList allGrades = new NumArrayList();
			int index = 0;
			for (int i = 0; i < grades1.getArray().size(); i++) {
				allGrades.insert(i, grades1.getArray().lookup(i));
				index++;
			}
			for (int i = 0; i < grades2.getArray().size(); i++) {
				allGrades.insert(index, grades2.getArray().lookup(i));
				index++;
			}
			allGrades = sortList(allGrades);
			int low = 0;
			int high = allGrades.size() - 1;
			while (low < high) {
				low++;
				high--;
			}
			if (low == high) {
				return allGrades.lookup(low);
			}
			else {
				return (allGrades.lookup(low) + allGrades.lookup(high)) / 2.0;
			}
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return -1;
		}

	}


	/**
	 * This method creates two sets of grades and uses methods to calculate median, averages, etc.
	 * @param args the arguments are unused
	 */
	public static void main(String[] args) {
		double[] grades1 = new double[] {87.4, 92.4, 90.3, 37.8, 59.3, 87.9, 88.2, 95.3, 100.0, 68.4};
		double[] grades2 = new double[] {88.3, 90.1, 86.0, 98.3, 100.0, 100.0, 78.3, 76.4, 69.0, 95.3};
		NumSet gradeBook1 = new NumSet(grades1);
		NumSet gradeBook2 = new NumSet(grades2);
		System.out.println("Class 1 grades are:");
		gradeBook1.getArray().print();
		System.out.println("Class 2 grades are:");
		gradeBook2.getArray().print();
		System.out.println("The set of unique grades is: ");
		NumSet uniqueGrades = new NumSet(new double[0]).union(gradeBook1, gradeBook2);
		uniqueGrades.print();
		System.out.println("The most frequent grades were: ");
		NumSet freqGrades = new NumSet(new double[0]).intersect(gradeBook1, gradeBook2);
		freqGrades.print();
		System.out.println("The average grade for class 1 was: " + avgGrades(gradeBook1));
		System.out.println("The average grade for class 2 was: " + avgGrades(gradeBook2));
		System.out.println("The average for both classes was: " + avgGrades(gradeBook1, gradeBook2));
		System.out.println("The median for class 1 was: " + medianGrade(gradeBook1));
		System.out.println("The median for class 2 was: " + medianGrade(gradeBook2));
		System.out.println("The median for both classes was: " + medianGrade(gradeBook1, gradeBook2));
	}

}
