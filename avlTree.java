/* Alexander Edwards
* 
*
*/

public class avlTree< T extends Comparable<T> >{
// ==== Java function pointer equivalent ====
	public interface FunctionPointer<F> {
		public void execute(F data);
	}
// ==== inOrderTraversal() ====
	private void inOrderTraversal(treeNode<T> current, FunctionPointer<T> fptr){
		if(current == null){
			return;
		}
		else{
			inOrderTraversal(current.getLeftNode(),fptr);
			fptr.execute(current.getData());
			inOrderTraversal(current.getRightNode(),fptr);
		}
	}
// ==== reverseOrderTraversal() ====
	private void reverseOrderTraversal(treeNode<T> current, FunctionPointer<T> fptr){
		if(current == null){
			return;
		}
		else{
			reverseOrderTraversal(current.getRightNode(),fptr);
			fptr.execute(current.getData());
			reverseOrderTraversal(current.getLeftNode(),fptr);
		}
	}

// ==== insert() ====
// Note to self: java passes by value, even references. No manipulating pointer in recursion.
	private treeNode<T> insertRecursiveHelper(T data, treeNode<T> currentNode){
		if(currentNode.getData().compareTo(data) == 0){       // case: obj1 equals obj2
			return currentNode;
		}
		else if(currentNode.getData().compareTo(data) < 0 ){ // case: obj1 less than obj2 
			if(currentNode.hasRight()){
				currentNode.setRightNode( insertRecursiveHelper(data, currentNode.getRightNode()) );
			}
			else{
				currentNode.setRightNode(new treeNode<T>(data));
				size++;
			}
			currentNode.incrementHeightByOne();
		}
		else if(currentNode.getData().compareTo(data) > 0){ // case: obj1 greater than obj2
			if(currentNode.hasLeft()){
				currentNode.setLeftNode( insertRecursiveHelper(data, currentNode.getLeftNode()) );
			}
			else{
				currentNode.setLeftNode(new treeNode<T>(data));
				size++;
			}
			currentNode.decrementHeightByOne();
		}

		// ---- check for balance ----
		if(currentNode.getHeight() <= -2){ // heavy on the left
			treeNode<T> tempNode = currentNode.getLeftNode();
			// **** right rotation ****
			if(tempNode.getHeight() == -1){
				tempNode.setRightNode(currentNode);
				currentNode.setLeftNode(null);
				currentNode.zeroHeight();
				tempNode.zeroHeight();
			}
			// **** right left rotation ****
			else if( tempNode.getHeight() == 1){
				tempNode.setLeftNode(tempNode.getRightNode());
				tempNode.setRightNode(currentNode);
				currentNode.setLeftNode(null);
				currentNode.zeroHeight();
				tempNode.zeroHeight();
			}

			return tempNode;
		}
		else if(currentNode.getHeight() >= 2){ // heavy on the right
			treeNode<T> tempNode = currentNode.getRightNode();
			// **** left rotation ****
			if(tempNode.getHeight() == 1){
				tempNode.setLeftNode(currentNode);
				currentNode.setRightNode(null);
				currentNode.zeroHeight();
				tempNode.zeroHeight();
			}
			// **** left right rotation ****
			else if(tempNode.getHeight() == -1){
				tempNode.setRightNode(tempNode.getLeftNode());
				tempNode.setLeftNode(currentNode);
				currentNode.setRightNode(null);
				currentNode.zeroHeight();
				tempNode.zeroHeight();
			}
			return tempNode;
		}
		
		return currentNode;
	}
	public void insert(T data){
		if(root == null){
			root = new treeNode<T>(data);
			size++;
		}
		else{
			root = insertRecursiveHelper(data, root);
		}
	}
// ==== calcHeight() ====
	private int calcHeight(treeNode<T> left, treeNode<T> right){
		int x, y;
		if(left != null){
			x = left.getHeight();
		}
		else{
			x = -1;
		}
		if(right != null){
			y = right.getHeight();
		}
		else{
			y = -1;
		}
		return x - y;
	}
// ==== printInOrder() ====
	public void printInOrder(){
		inOrderTraversal(root,new FunctionPointer<T>(){
									public void execute(T data){
										System.out.print(data.toString() + ", ");
									}
		} );
		System.out.println();
	}
// ==== printReverseOrder() ====
	public void printReverseOrder(){
		reverseOrderTraversal(root,new FunctionPointer<T>(){
									public void execute(T data){
										System.out.print(data.toString() + ", ");
									}
		} );
		System.out.println();	
	}
// ==== printHierarchy() ====
	private void printHierarchyRecursiveHelper(String formatingString, treeNode<T> currentNode){
		System.out.println(currentNode.getData().toString() + " :H-> " + currentNode.getHeight());		
		if(currentNode.hasLeft()){
			System.out.println(formatingString + "|");
			System.out.print(formatingString );
			System.out.print("|~~");
			printHierarchyRecursiveHelper( formatingString + "|  ", currentNode.getLeftNode());
		}
		if(currentNode.hasRight()){
			System.out.print(formatingString );
			System.out.print("|~~");
			printHierarchyRecursiveHelper( formatingString + "   ", currentNode.getRightNode());
			System.out.println(formatingString);
		}
		
		
	}
	public void printHierarchy(){
		if( root != null){
			printHierarchyRecursiveHelper("",root);
		}
	}
// ==== constructors ====
	public avlTree(){
		this.root = null;
		this.size = 0;
		this.fullFlag = false;
	}
// ==== isFull() ====	
	public boolean isFull(){
		return fullFlag;
	}
// ==== size() ====
	public int size(){
		return size;
	}
// **** data members ****
	private treeNode<T> root;
	private int size; // number of elements in tree
	private  boolean fullFlag;

}