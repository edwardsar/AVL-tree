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
// Note to self: java passes by value, even references. No directly manipulating pointer in recursion.
	private treeNode<T> insertRecursiveHelper(T data, treeNode<T> currentNode){
		int compareResult = currentNode.getData().compareTo(data);
		if(compareResult == 0){       // case: obj1 is equals obj2
			return currentNode;
		}
		else if(compareResult < 0 ){ // case: obj1 is less than obj2 
			if(currentNode.hasRight()){
				currentNode.setRightNode( insertRecursiveHelper(data, currentNode.getRightNode()) );
				currentNode.incrementHeightByOne();
			}
			else{
				currentNode.setRightNode(new treeNode<T>(data));
				size++;
			}
		}
		else if(compareResult > 0){ // case: obj1 is greater than obj2
			if(currentNode.hasLeft()){
				currentNode.setLeftNode( insertRecursiveHelper(data, currentNode.getLeftNode()) );
				currentNode.incrementHeightByOne();
			}
			else{
				currentNode.setLeftNode(new treeNode<T>(data));
				size++;
			}		
		}
 		//if height of children differ by more than 1 rotate
		int balanceFactor = currentNode.getBalanceFactor();
		if(balanceFactor == 2){
				// rotate right
				if(!currentNode.getLeftNode().hasRight()){
					treeNode<T> tempNode = currentNode.getLeftNode();
					tempNode.setRightNode(currentNode);
					currentNode.setLeftNode(null);
					currentNode.computeNewHeight();
					tempNode.computeNewHeight();
					return tempNode;
				}
				// double rotate right
				else{
					treeNode<T> tempNode = currentNode.getLeftNode();
					currentNode.setLeftNode(tempNode.getRightNode());
					tempNode.setRightNode(currentNode);
					currentNode.computeNewHeight();
					tempNode.computeNewHeight();
					return tempNode;
				}
		}
		if(balanceFactor == -2){
				// rotate left
				if(!currentNode.getRightNode().hasLeft()){
					treeNode<T> tempNode = currentNode.getRightNode();
					tempNode.setLeftNode(currentNode);
					currentNode.setRightNode(null);
					currentNode.computeNewHeight();
					tempNode.computeNewHeight();
					return tempNode;
				}
				// double rotate left
				else{
					treeNode<T> tempNode = currentNode.getRightNode();
					currentNode.setRightNode(tempNode.getLeftNode());
					tempNode.setLeftNode(currentNode);
					currentNode.computeNewHeight();
					tempNode.computeNewHeight();
					return tempNode;
				}
		}
		currentNode.computeNewHeight();
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
// ==== printHierarchy()  && PrintHeirarchyRecursivehelper()====
	public void printHierarchy(){
		if( root != null){
			printHierarchyRecursiveHelper("",root);
		}
	}
	private void printHierarchyRecursiveHelper(String formatingString, treeNode<T> currentNode){
		System.out.println(currentNode.getData().toString() + ":> " + currentNode.getBalanceFactor());		
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
// ==== getSuccessorData() ====
// Note: in order process to find successor to deleted node i.e node with greatest data in left subtree
// and re-balances on the way back up the tree
	private T getSuccessorData(treeNode<T> currentNode){
		if(currentNode.hasRight()){
			return getSuccessorData(currentNode.getRightNode());
			
		}
		else{
			return currentNode.getData();
		}
	}
// ==== remove() && removeRecursiveHelper() ====
	private treeNode<T> removeRecursiveHelper(T data, treeNode<T> currentNode){
		int compareResult = currentNode.getData().compareTo(data);
		if(compareResult == 0){       // case: obj1 is equals obj2
			int numberOfSubtrees = currentNode.getNumberOfSubtrees(); 
			if( numberOfSubtrees == 0){ 
				return null;
			}
			else if(numberOfSubtrees == 1){
				if(currentNode.hasLeft()){
					return currentNode.getLeftNode();
				}
				else{
					return currentNode.getRightNode();
				}
			}
			else{
				currentNode.setData( getSuccessorData(currentNode.getLeftNode())); // will find the largest data of this currentNode's left subtree
				currentNode.setLeftNode(removeRecursiveHelper(currentNode.getData(), currentNode.getLeftNode()));
				
			}
		}
		else if(compareResult < 0 ){ // case: obj1 is less than obj2 
			if(currentNode.hasRight()){
				currentNode.setRightNode( removeRecursiveHelper(data, currentNode.getRightNode()) );
			}
			else{
				return currentNode;
			}

		}
		else if(compareResult > 0){ // case: obj1 is greater than obj2
			if(currentNode.hasLeft()){
				currentNode.setLeftNode( removeRecursiveHelper(data, currentNode.getLeftNode()) );
			}
			else{
				return currentNode;
			}
		
		}
		currentNode.computeNewHeight();
		int balanceFactor = currentNode.getBalanceFactor();
		if(balanceFactor == 2){ // heavy on left
			treeNode<T> tempNode = currentNode.getLeftNode();
			currentNode.setLeftNode(tempNode.getRightNode());
			tempNode.setRightNode(currentNode);
			currentNode.computeNewHeight();
			tempNode.computeNewHeight();
			return tempNode;
		}
		else if(balanceFactor == -2){ // heavy on right
			treeNode<T> tempNode = currentNode.getRightNode();
			currentNode.setRightNode(tempNode.getLeftNode());
			tempNode.setLeftNode(currentNode);
			currentNode.computeNewHeight();
			tempNode.computeNewHeight();
			return tempNode;
		}
		return currentNode;
	}
	public void remove(T data){
		if( root == null){
			return;
		}
		else{
			root = removeRecursiveHelper(data, root);
		}
	}
// ==== search && searchRecursiveHelper() ====
	private boolean searchRecursiveHelper(T data, treeNode<T> currentNode){
		int compareResult = currentNode.getData().compareTo(data);
		if(compareResult == 0){ // obj1 is equals obj2
			return true;
		}
		else if(compareResult < 0){ // obj1 is less than obj2
			if(currentNode.hasRight()){
				return searchRecursiveHelper(data, currentNode.getRightNode());
			}
			else{
				return false;
			}
		}
		else if(compareResult > 0){ // obj1 is greater than obj2
			if(currentNode.hasLeft()){
				return searchRecursiveHelper(data, currentNode.getLeftNode());
			}
			else{
				return false;
			}
		}
		return false;
	}
	public boolean search(T data){
		if(root == null){
			return false;
		}
		else{
			return searchRecursiveHelper(data, root);
		}
	}
// ==== constructors ====
	public avlTree(){
		this.root = null;
		this.size = 0;
		this.fullFlag = false;
	}
// ==== isFull() ====	
	public boolean isFull(){ // TODO
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