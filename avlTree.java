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
	private void insertRecursiveHelper(T data, treeNode<T> current){
		if(current.getData().compareTo(data) == 0){       // case: obj1 equals obj2
			return;
		}
		else if(current.getData().compareTo(data) < 0 ){ // case: obj1 less than obj2 
			if(current.hasRight()){
				insertRecursiveHelper(data, current.getRightNode());
			}
			else{
				current.setRightNode(new treeNode<T>(data));
				size++;
			}
		}
		else if(current.getData().compareTo(data) > 0){ // case: obj1 greater than obj2
			if(current.hasLeft()){
				insertRecursiveHelper(data, current.getLeftNode());
			}
			else{
				current.setLeftNode(new treeNode<T>(data));
				size++;
			}
		}
	}
	public void insert(T data){
		if(root == null){
			root = new treeNode<T>(data);
			size++;
		}
		else{
			insertRecursiveHelper(data, root);
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
// ==== printHierarchy() ====
	private void printHierarchyRecursiveHelper(String formatingString, treeNode<T> current){
		System.out.println(current.getData().toString());		
		if(current.hasLeft()){
			System.out.print(formatingString );
			System.out.print("|~~");
			printHierarchyRecursiveHelper( formatingString + "|  ", current.getLeftNode());
		}
		if(current.hasRight()){
			System.out.print(formatingString );
			System.out.print("|~~");
			printHierarchyRecursiveHelper( formatingString + "   ", current.getRightNode());
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