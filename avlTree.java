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
		if(currentNode.getData().compareTo(data) == 0){       // case: obj1 equals obj2
			return currentNode;
		}
		else if(currentNode.getData().compareTo(data) < 0 ){ // case: obj1 less than obj2 
			if(currentNode.hasRight()){
				currentNode.setRightNode( insertRecursiveHelper(data, currentNode.getRightNode()) );
				currentNode.incrementHeightByOne();
			}
			else{
				currentNode.setRightNode(new treeNode<T>(data));
				size++;
			}
		}
		else if(currentNode.getData().compareTo(data) > 0){ // case: obj1 greater than obj2
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
		int balanceFactor = currentNode.getLeftsHeight() - currentNode.getRightsHeight();
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
// ==== printHierarchy()  && helper====
	public void printHierarchy(){
		if( root != null){
			printHierarchyRecursiveHelper("",root);
		}
	}
	private void printHierarchyRecursiveHelper(String formatingString, treeNode<T> currentNode){
		System.out.println(currentNode.getData().toString());		
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