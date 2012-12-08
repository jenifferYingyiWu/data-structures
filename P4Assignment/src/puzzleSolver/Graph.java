//Diego Waxemberg
package puzzleSolver;
import java.util.HashMap;

//A class that contains methods to create a graph of the puzzle and search the graph for the solution.
public class Graph {
	private Node root;
	private HashMap<String, Node> graph;
	private Node[] queue = new Node[30000];
	private Node[] stack = new Node[30000];
	private int stackIndex = 0;
	private int queueIndex = 0;

	//a constructor that takes the puzzle as the root of the graph.
	public Graph(Puzzle root) {
		this.root = new Node(root);
	}

	//returns the root of the graph.
	public Node getRoot() {
		return root;
	}
	
	//Performs a depth first search of the graph to find the solution. Starts at the root node and generates the graph as it searches.
	//Stores each state in a stack and pulls from the top of the stack for each subsequent test. Also uses a hash map to keep track of already tested states.
	//If the JVM runs low on memory it will stop the search and report as such.
	//NOTE: If the size of the puzzle is very large, it will cause the heap memory to drop by increments larger than my tolerance and will 
	//		output an error. I have tested puzzles up to 20x20 and did not run into this problem, however, I make no guarantees that it will
	//		not happen to you.
	public boolean depthFirstSearch() {
		graph = new HashMap<String, Node>(30000);
		addToStack(root);
		Node testState = root;
		int index = 0;
		boolean outOfMemory = false;
		while (index > -1 && !testState.getState().solved() && !outOfMemory) {
			if (!inHash(testState)) {
				if (testState.getState().canMoveDown()) {
					testState.setDown(new Node(new Puzzle(testState.getState()).moveDown()));
					testState.getDown().setUp(testState);
					testState.getDown().setParent(testState);
					addToStack(testState.getDown());
				}
				if (testState.getState().canMoveUp()) {
					testState.setUp(new Node(new Puzzle(testState.getState()).moveUp()));
					testState.getUp().setDown(testState);
					testState.getUp().setParent(testState);
					addToStack(testState.getUp());
				}
				if (testState.getState().canMoveLeft()) {
					testState.setLeft(new Node(new Puzzle(testState.getState()).moveLeft()));
					testState.getLeft().setRight(testState);
					testState.getLeft().setParent(testState);
					addToStack(testState.getLeft());
				}
				if (testState.getState().canMoveRight()) {
					testState.setRight(new Node(new Puzzle(testState.getState()).moveRight()));
					testState.getRight().setLeft(testState);
					testState.getRight().setParent(testState);
					addToStack(testState.getRight());
				}
				addToHashTable(testState);
				if (Runtime.getRuntime().freeMemory() < 2048) {
					outOfMemory = true;
				}
			}
			stack[index] = null;
			index = stackIndex;
			while (stack[index] == null && index > -1) {
				index--;
			}
			testState = stack[index];
		}
		if (testState.getState().solved()) {
			backTrace(testState);
			queue = null;
			return true;
		}
		else if (!outOfMemory){
			System.out.println("No solution.");
			queue = null;
			return false;
		}
		else {
			System.out.println("Out of Memory!");
			return false;
		}
	}
	
	//Performs a breadth first search of the graph to find the solution. Starts at the root node and generates the graph as it searches.
	//Stores each state in a queue and pulls from the front of the queue for each subsequent test. Also uses a hash map to keep track of already tested states.
	//If the JVM runs low on memory it will stop the search and report as such.
	//NOTE: If the size of the puzzle is very large, it will cause the heap memory to drop by increments larger than my tolerance and will 
	//		output an error. I have tested puzzles up to 20x20 and did not run into this problem, however, I make no guarantees that it will
	//		not happen to you.
	public boolean breadthFirstSearch() {
		graph = new HashMap<String, Node>(30000);
		addToQueue(root);
		Node testState = root;
		int index = 0;
		boolean outOfMemory = false;
		while (index < queueIndex && !testState.getState().solved() && !outOfMemory) {
			if (!inHash(testState)) {
				if (testState.getState().canMoveDown()) {
					testState.setDown(new Node(new Puzzle(testState.getState()).moveDown()));
					testState.getDown().setUp(testState);
					testState.getDown().setParent(testState);
					addToQueue(testState.getDown());
				}
				if (testState.getState().canMoveUp()) {
					testState.setUp(new Node(new Puzzle(testState.getState()).moveUp()));
					testState.getUp().setDown(testState);
					testState.getUp().setParent(testState);
					addToQueue(testState.getUp());
				}
				if (testState.getState().canMoveLeft()) {
					testState.setLeft(new Node(new Puzzle(testState.getState()).moveLeft()));
					testState.getLeft().setRight(testState);
					testState.getLeft().setParent(testState);
					addToQueue(testState.getLeft());
				}
				if (testState.getState().canMoveRight()) {
					testState.setRight(new Node(new Puzzle(testState.getState()).moveRight()));
					testState.getRight().setLeft(testState);
					testState.getRight().setParent(testState);
					addToQueue(testState.getRight());
				}
				addToHashTable(testState);
				if (Runtime.getRuntime().freeMemory() < 2048) {
					outOfMemory = true;
				}
			}
			testState = queue[index];
			index++;
		}
		if (testState.getState().solved()) {
			backTrace(testState);
			queue = null;
			return true;
		}
		else if (!outOfMemory){
			System.out.println("No solution.");
			queue = null;
			return false;
		}
		else {
			System.out.println("Out of Memory!");
			return false;
		}

	}
	
	//adds a node to the top of the stack (stored as an array)
	private void addToStack(Node element) {
		stack[stackIndex] = element;
		stackIndex++;
		if (stackIndex == stack.length) {
			Node[] tmp = new Node[stack.length*2];
			for (int i = 0; i < stack.length; i++) {
				tmp[i] = stack[i];
			}
			stack = tmp;
		}
	}
	
	//returns whether the given node is in the hash map (i.e. has been tested already)
	private boolean inHash(Node element) {
		return graph.containsKey(element.getState().serialize());
	}
	
	//adds a node to the hash map
	private void addToHashTable(Node element) {
		graph.put(element.getState().serialize(), element);
	}

	//adds a node to the end of the queue (stored as an array)
	private void addToQueue(Node element) {
		queue[queueIndex] = element;
		queueIndex++;
		if (queueIndex == queue.length) {
			Node[] tmp = new Node[queue.length*2];
			for (int i = 0; i < queue.length; i++) {
				tmp[i] = queue[i];
			}
			queue = tmp;
		}
	}
	
	//back-traces a node to the root in order to create the solution chain
	private void backTrace(Node state) {
		int numLevels = 1;
		Node lstPtr = state;
		while ((lstPtr = lstPtr.getParent()) != null) {
			numLevels++;
		}
		lstPtr = state;
		Puzzle[] solutionTrace = new Puzzle[numLevels];
		for (int i = solutionTrace.length - 1; i >= 0; i--) {
			solutionTrace[i] = lstPtr.getState();
			lstPtr = lstPtr.getParent();

		}
		getRoot().getState().setSolution(solutionTrace);
	}

	//a basic node class that contains 5 pointers (up, down, left, right, parent) and its Puzzle state.
	public class Node {
		private Node up;
		private Node right;
		private Node down;
		private Node left;
		private Node parent;
		private Puzzle state;
		
		//creates a node for a given puzzle
		public Node(Puzzle state) {
			this.state = state;
		}
		
		//returns the Puzzle state for this node
		public Puzzle getState() {
			return state;
		}
		
		//sets the Puzzle state for this node
		public void setState(Puzzle state) {
			this.state = state;
		}
		
		//returns this node's parent
		public Node getParent() {
			return parent;
		}

		//sets this node's parent
		public void setParent(Node parent) {
			this.parent = parent;
		}
		
		//returns the node that is in the up direction of this node
		public Node getUp() {
			return up;
		}
		
		//returns the node that is in the right direction of this node
		public Node getRight() {
			return right;
		}
		
		//returns the node that is in the down direction of this node
		public Node getDown() {
			return down;
		}
		
		//returns the node that is in the left direction of this node
		public Node getLeft() {
			return left;
		}
		
		//sets the node in the up direction
		public void setUp(Node up) {
			this.up = up;
		}

		//sets the node in the right direction
		public void setRight(Node right) {
			this.right = right;
		}
		
		//sets the node in the down direction
		public void setDown(Node down) {
			this.down = down;
		}

		//sets the node in the left direction
		public void setLeft(Node left) {
			this.left = left;
		}
	}
}
