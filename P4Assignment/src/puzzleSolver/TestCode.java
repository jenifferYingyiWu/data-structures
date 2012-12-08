//Diego Waxemberg
package puzzleSolver;
//This class provides test code for my puzzle solving methods. 
public class TestCode {
	//The main method, performs all of the tests of Puzzle and Graph classes. (By extension testing the Node class as well).
	public static void main(String[] args) {
		Puzzle p = new Puzzle(2); //create a new puzzle that is 2x2 for simple testing
		p.printPuzzle();
		System.out.println("Test moving down:");
		p.moveDown();
		p.printPuzzle();
		System.out.println("Test moving up: ");
		p.moveUp();
		p.printPuzzle();
		System.out.println("Test moving right: ");
		p.moveRight();
		p.printPuzzle();
		System.out.println("Test moving left: ");
		p.moveLeft();
		p.printPuzzle();
		System.out.println("Test moving left (not a possible move): ");
		p.moveLeft();
		p.printPuzzle();
		System.out.println("Test shuffling (may need to run more than once for different results): ");
		p.shuffleBoard();
		p.printPuzzle();
		Graph g = new Graph(p); //create an instance of a graph for the puzzle.
		System.out.println("Test breadth first searching algorithm to find solution: ");
		if (g.breadthFirstSearch()) //if found, stores the solution in p.
			p.printSolution();
		System.out.println("Test depth first searching algorithm to find solution: ");
		if (g.depthFirstSearch()) //if found, stores the solution in p.
			p.printSolution();
	}
}
