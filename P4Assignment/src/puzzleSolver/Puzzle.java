//Diego Waxemberg
package puzzleSolver;
import java.util.Random;
//A class to represent a sliding puzzle.
public class Puzzle {
	private int[][] grid;
	private int[] blank;
	private int size;
	private Puzzle[] solution;
	
	//A constructor with no arguments calls the other constructor with an argument of 3.
	public Puzzle() {
		this(3);
	}
	
	//Constructor takes an integer as the dimension of the puzzle, e.g a value of 3 produces a 3x3 puzzle.
	public Puzzle(int n) {
		grid = new int[n][n];
		size = n;
		int count = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				grid[i][j] = count;
				count++;
			}
		}
		blank = new int[] {0,0};
	}
	
	//Constructor takes a puzzle as an argument and replicates it.
	public Puzzle(Puzzle p) {
		size = p.getSize();
		grid = new int[size][size];
		int[][] pGrid = p.getGrid().clone();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				grid[i][j] = pGrid[i][j];
			}
		}
		blank = p.getBlank().clone();
	}
	
	//a setter method for this puzzle's solution.
	public void setSolution(Puzzle[] solution) {
		this.solution = solution;
	}
	
	//returns this puzzle as a 2-dimensional array called grid.
	public int[][] getGrid() {
		return grid;
	}
	
	//returns the location of the "blank" spot on the board (the 0 for our purposes).
	public int[] getBlank() {
		return blank;
	}
	
	//returns the size of the board.
	public int getSize() {
		return size;
	}
	
	//returns whether this puzzle is equal to another puzzle (or any object input).
	public boolean equals(Object o) {
		if (o instanceof Puzzle) {
			Puzzle p = (Puzzle)o;
			int[][] grid1 = p.getGrid();
			int[][] grid2 = this.getGrid();
			for (int i = 0; i < grid1.length; i++) {
				for (int j = 0; j < grid1.length; j++) {
					if (grid1[i][j] != grid2[i][j])
						return false;
				}
			}
			return true;
		}
		return false;
	}
	
	//returns whether this puzzle is in the solved state or not. 
	public boolean solved() {
		return equals(new Puzzle(size));
	}
	
	//returns whether up is a valid move direction.
	public boolean canMoveUp() {
		return blank[0] > 0;
	}
	
	//returns whether down is a valid move direction.
	public boolean canMoveDown() {
		return blank[0] < size - 1;
	}
	
	//returns whether right is a valid move direction.
	public boolean canMoveRight() {
		return blank[1] < size - 1;
	}
	
	//returns whether left is a valid move direction.
	public boolean canMoveLeft() {
		return blank[1] > 0;
	}
	
	//Shuffles the puzzle by making 20 random moves. Changing the size of the for loop will increase or decrease the number of random moves.
	public void shuffleBoard() {
		Random r = new Random();
		float num;
		for (int i = 0; i < 20; i++) {
			num = r.nextFloat();
			if (num < 0.25) {
				moveLeft();
			}
			if (num >= 0.25 && num < 0.50) {
				moveRight();
			}
			if (num >= 0.50 && num < 0.75) {
				moveUp();
			}
			if (num > 0.75) {
				moveDown();
			}	
		}
	}
	
	//attempts to move the blank up.
	public Puzzle moveUp() {
		if (blank[0] > 0) {
			swap(blank, new int[] {blank[0] - 1, blank[1]});
			blank[0] = blank[0] - 1;
		}
		//System.out.println("Up");
		return this;
	}
	
	//attemps to move the blank down.
	public Puzzle moveDown() {
		if (blank[0] < grid.length - 1) {
			swap(blank, new int[] {blank[0] + 1, blank[1]});
			blank[0] = blank[0] + 1;
		}
		//System.out.println("Down");
		return this;
	}
	
	//attempts to move the blank left.
	public Puzzle moveLeft() {
		if (blank[1] > 0) {
			swap(blank, new int[] {blank[0], blank[1] - 1});
			blank[1] = blank[1] - 1;
		}
		//System.out.println("Left");
		return this;
	}
	
	//attempts to move the blank right.
	public Puzzle moveRight() {
		if (blank[1] < grid[0].length - 1) {
			swap(blank, new int[] {blank[0], blank[1] + 1});
			blank[1] = blank[1] + 1;
		}
		//System.out.println("Right");
		return this;
	}
	
	//swaps the position of two elements in the puzzle based on their coordinates.
	private void swap(int[] coords1, int[] coords2) {
		int tmp = grid[coords1[0]][coords1[1]];
		grid[coords1[0]][coords1[1]] = grid[coords2[0]][coords2[1]];
		grid[coords2[0]][coords2[1]] = tmp;
	}
	
	//prints out this puzzle.
	public void printPuzzle() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(grid[i][j] + "  ");
			}
			System.out.println();
		}
	}
	
	//prints out the solution to this puzzle, if it has already been solved.
	public void printSolution() {
		for (int i = 0; i < solution.length; i++) {
			solution[i].printPuzzle();
			System.out.println();
		}
	}
	
	//creates a string representation of this puzzle used as a key in a hash map.
	public String serialize() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				b.append(grid[i][j]);
			}
		}
		return b.toString();
	}
	
	//the main method of the program. uses the first argument input as the size of the puzzle then shuffles the puzzle and
	//uses the graph class to find the solution to the puzzle.
	public static void main(String[] args) {
		int puzzleSize = 3;
		if (args.length > 0) {
			try {
				puzzleSize = Integer.parseInt(args[0]);
			}
			catch (Exception e) {
				System.out.println("Incorrect input format. Using default puzzle size.");
				puzzleSize = 3;
			}
		}
		Puzzle p = new Puzzle(puzzleSize);
		System.out.println("Initial Puzzle: ");
		p.printPuzzle();
		p.shuffleBoard();
		System.out.println("Shuffled Puzzle: ");
		p.printPuzzle();
		System.out.println("--------");
		System.out.println("Depth First Search:");
		if (new Graph(p).depthFirstSearch()) {
			p.printSolution();
		}
		System.out.println("Breadth First Search:");
		if (new Graph(p).breadthFirstSearch()) {
			p.printSolution();
		}
	}
}
