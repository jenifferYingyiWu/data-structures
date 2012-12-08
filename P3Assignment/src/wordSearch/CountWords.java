/**
 * This class contains the methods required to read in a text file, create a hashtable from the occurence of all
 * the words in the file and then output the table to a file.
 * @author Diego Waxemberg
 */
package wordSearch;
import java.io.*;
public class CountWords {
	
	/**
	 * A blank constructor
	 */
	public CountWords() {
	}

	/**
	 * A method that calls other methods to operate on the inputFile to produce the outputFile.
	 * @param inputFile The name of the file to be read as input.
	 * @param outputFile The name of the file to be written to as output.
	 */
	public void wordCount(String inputFile, String outputFile) {
		String[] words = readFile(inputFile);
		Node[] hashTable = createHashTable(words, 8);
		printHash(hashTable);
		writeToFile(outputFile, hashTable);
	}

	/**
	 * This method writes a hashtable to a file as text.
	 * @param fileName The name of the file to be used for output.
	 * @param hashTable The hashtable that contains the values to be written.
	 */
	public void writeToFile(String fileName, Node[] hashTable) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(fileName)));
			for (int i = 0; i < hashTable.length; i++) {
				Node lstPtr = hashTable[i];
				if (lstPtr != null) {
				StringBuilder builder = new StringBuilder("");
				while (lstPtr.getNext() != null) {
					builder.append("(" + lstPtr.getValue() + " " + lstPtr.getOccurrences() + ") ");
					lstPtr = lstPtr.getNext();
				}
				builder.append("\n\r");
				bw.write(builder.toString());
				}
			}
			bw.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * A method to print a hashtable to the console. It also prints the average length of collisions.
	 * @param hashTable The hashtable to be printed.
	 */
	public void printHash(Node[] hashTable) {
		int count = 0;
		for (int i = 0; i < hashTable.length; i++) {
			Node lstPtr = hashTable[i];
			if (lstPtr != null) {
				StringBuilder builder = new StringBuilder();
				while (lstPtr.getNext() != null) {
					builder.append("(" + lstPtr.getValue() + " " + lstPtr.getOccurrences() + ") ");
					lstPtr = lstPtr.getNext();
					count++;
				}
				System.out.println(builder.toString());
			}
		}
		System.out.println("Average length of collision lists: " + (double)count / (double)hashTable.length);
	}

	/**
	 * Reads a file into a string array separated into words.
	 * @param fileName The name of the text file to be read.
	 * @return A string array containing individual words.
	 */
	public String[] readFile(String fileName) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			FileReader fr = new FileReader(new File(fileName));
			BufferedReader reader = new BufferedReader(fr);
			String line = null;
			while((line = reader.readLine()) != null) {
				stringBuilder.append(line + "\n");
			}
			reader.close();
			return stringBuilder.toString().toLowerCase().split("[ --.?!:;,\"\'()\r\n]");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	/**
	 * Creates a hashtable from an array of words.
	 * @param words An array of words to be input to the hashtable.
	 * @param tableSize The size of hashtable.
	 * @return A hashtable stored as an array.
	 */
	public Node[] createHashTable(String[] words, int tableSize) {
		Node[] table = new Node[tableSize];
		int entries = 0;
		for (int i = 0; i < words.length; i++) {
			if (!(words[i].equals(" ")) && !(words[i].equals(""))) {
				int hashCode = Math.abs(words[i].hashCode() % tableSize);
				if (table[hashCode] == null) {
					table[hashCode] = new Node(words[i]);
					entries++;
					if ((double)entries/table.length == 1) {
						return createHashTable(words, tableSize+tableSize/2);
					}
				}
				else {
					Node lstPtr = table[hashCode];
					while (lstPtr.getNext() != null && !lstPtr.getValue().equals(words[i])) {
						lstPtr = lstPtr.getNext();
					}
					if (lstPtr.getValue().equals(words[i])) {
						lstPtr.incrementOccurrence();
					}
					else {
						lstPtr.setNext(new Node(words[i]));
					}
				}
			}
		}
		return table;
	}

	/**
	 * Takes in two strings and uses them as input and output files for an instance of this class.
	 * @param args The input and output files as index 0 and 1 respectively.
	 */
	public static void main(String[] args) {
		CountWords count = new CountWords();
		count.wordCount(args[0], args[1]);
	}
}
