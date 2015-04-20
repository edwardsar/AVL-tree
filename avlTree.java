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
// ==== insert() ====
// Note to self: java passes by value, even references.
	private void insertRecursiveHelper(T data, treeNode<T> current){
		if(current.getData().compareTo(data) == 0){       // case: equals
			return;
		}
		else if(current.getData().compareTo(data) > 0 ){ // case: less than
			if(current.hasLeft()){
				insertRecursiveHelper(data, current.getLeftNode());
			}
			else{
				current.setLeftNode(new treeNode<T>(data));
				size++;
			}
		}
		else if(current.getData().compareTo(data) < 0){ // case: greater than
			if(current.hasRight()){
				insertRecursiveHelper(data, current.getRightNode());
			}
			else{
				current.setRightNode(new treeNode<T>(data));
				size++;
			}
		}
	}
	public void insert(T data){
		if(root == null){
			root = new treeNode<T>(data);
			if(root == null){
				fullFlag = true;
				return;
			}
			size++;
		}
		else{
			insertRecursiveHelper(data, root);
		}
	}
// ==== print() ====
	public void print(){
		inOrderTraversal(root,new FunctionPointer<T>(){
									public void execute(T data){
										System.out.print(data.toString() + ", ");
									}
		} );
		System.out.println();
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