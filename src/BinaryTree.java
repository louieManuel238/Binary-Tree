//Helper Class Node
class Node{
	int value;
	Node left;
	Node right;

	Node(int value){
		this.value = value;
		right = null;
		left = null;
	}
}
public class BinaryTree{

	Node root;


	/**
	 * This method will recurse through the tree until the current is null
	 * 
	 * @param current The current node in the recursion
	 * @param value The value to be added
	 * @return A newly created Node assigned to be the left / right of a leaf Node
	 **/
	private Node addRecursion(Node current, int value){
		if(current == null)
			return new Node(value);

		if(value < current.value)
			current.left = addRecursion(current.left, value);
		else if(value > current.value)
			current.right = addRecursion(current.right, value);
		else
			return current;
		return current;
	}

	/**
	 * Used to add a new element in the tree
	 *
	 * @param The value to be added
	 **/
	public void add(int value){
		root = addRecursion(root, value);
	}

	/**
	 * This method will recurse 
	 *
	 *
	 * @param current The current node in the recursion
	 * @param value the value to be deleted
	 * @return Node
	 **/
	private Node deleteRecursion(Node current, int value){
		if(current == null)
			return null;

		if(value == current.value){
			// current is a leaf node
			if(current.left == null && current.right == null)
				return null;
			// current has child on the left
			if(current.right == null)
				return current.left;
			// current has child on the right
			if(current.left == null)
				return current.right;
			// current has left and right child
			if(current.left != null && current.right != null){
				// replace the value to be deleted with the smallest value of its right sub tree
				int smallestValue = findSmallestValue(current.right);
				current.value = smallestValue;
				// delete the smallest value of the right sub tree 
				current.right = deleteRecursion(current.right, smallestValue);
				return current;
			}
		}
		if(value < current.value){
			current.left = deleteRecursion(current.left, value);
		}
		current.right = deleteRecursion(current.right, value);
		return current;
	}

	/**
	 * Find the smallest value of a tree; 
	 * traversing the left branch since the left will always have smaller values than the right
	 *
	 * @param the starting node of traversal
	 * @return int smallest value
	 **/
	private int findSmallestValue(Node root){
		return root.left == null ? root.value : findSmallestValue(root.left);
	}

	/**
	 * Used to delete an element in the tree
	 *
	 * @param value The value to be deleted
	 **/
	public void delete(int value){
		deleteRecursion(root, value);
	}


	/**
	 * In-order Traversal
	 * <p>
	 * 		traverse left => visit node => traverse right
	 * </p>
	 * @param node
	 **/
	public void inOrderTraversal(Node node){
		if(node == null)
			return;
		inOrderTraversal(node.left);
		System.out.print(" "+ node.value);
		inOrderTraversal(node.right);
	}

	/**
	 * Pre-order Traversal
	 * <p>
	 * 		visit node => traverse left => traverse right
	 * </p>
	 * @param node
	 **/
	public void preOrderTraversal(Node node){
		if(node == null)
			return;
		System.out.print(" "+ node.value);
		preOrderTraversal(node.left);
		preOrderTraversal(node.right);
	}

	/**
	 * Post-order Traversal
	 * <p>
	 * 		traverse left => traverse right => visit node
	 * </p>
	 * @param node
	 **/
	public void postOrderTraversal(Node node){
		if(node == null)
			return;
		postOrderTraversal(node.left);
		postOrderTraversal(node.right);
		System.out.print(" "+ node.value);
	}

	/**
	 * Depth-first search (Itterative)
	 *
	 *
	 * @param value Value to be search
	 * @return null if not found
	 * @return int value searched
	 **/
	public Integer depthFirstSearch(int value){
		if(root == null){
			return null;
		}
		java.util.Stack<Node> frontier = new java.util.Stack<>();
		frontier.push(root);

		Node node = null;
		while(frontier.size() != 0){
			node = frontier.pop();
			if(node.value == value)
				return value;
			if(node.right != null)
				frontier.push(node.right);
			if(node.left != null)
				frontier.push(node.left);
			
		}
		return null;
	}

	/**
	 * Breadth-first search (Itterative)
	 *
	 *
	 *
	 *
	 **/
	public Integer breadthFirstSearch(int value){
		if(root == null)
			return null;
		java.util.Queue<Node> frontier = new java.util.LinkedList<>();
		frontier.add(root);

		Node node = null;
		while(frontier.size() != 0){
			node = frontier.remove();
			if(node.value == value)
				return value;
			if(node.left != null)
				frontier.add(node.left);
			if(node.right != null)
				frontier.add(node.right);
		}
		return null;
	}

	
	public static void main(String[] args) {
		BinaryTree bt = new BinaryTree();
		bt.add(6);
		bt.add(4);
		bt.add(8);
		bt.add(3);
		bt.add(5);
		bt.add(7);
		bt.add(9);
		bt.inOrderTraversal(bt.root);
		System.out.println("");
		bt.preOrderTraversal(bt.root);
		System.out.println("");
		bt.postOrderTraversal(bt.root);
		System.out.println("");

		System.out.println("");
		System.out.println(bt.breadthFirstSearch(3));
	}
}