/* Alexander Edwards
*  
*  
*/
public class treeNode< T > {
// ==== hasLeft() ====
	public boolean hasLeft(){
		if(leftNode == null){
			return false;
		}
		else{
			return true;
		}
	}
// ==== hasRight() ====
	public boolean hasRight(){
		if(rightNode == null){
			return false;
		}
		else{
			return true;
		}
	}
// **** Getters ****

// ==== T getData() ====
	public T getData(){
		return this.data;
	}
// ==== getLeftNode() ====
	public treeNode<T> getLeftNode(){
		return this.leftNode;
	}
// ==== getRightNode() ====
	public treeNode<T> getRightNode(){
		return	this.rightNode;
	}
// ==== getHeight() ====
	public int getHeight(){
		return this.height;
	}

// **** Setters ****

// ==== setData(T data) ====
	public void setData( T data){
		this.data = data;
	}
// ==== setLeftNode() ====
	public void setLeftNode(treeNode<T> left){
		this.leftNode = left;
	}
// ==== setRightNode() ====
	public void setRightNode(treeNode<T> right){
		this.rightNode = right;
	}
// ==== setHeight() ====
	public void setHeight(int height){
		this.height = height;
	}
// ==== incrementHeightByOne() ====
	public void incrementHeightByOne(int h){
		this.height++;
	}
// ==== decrementHeightByOne() ====
	public void decrementHeightByOne(){
		this.height--;
	}

// ==== constructors ====
	public treeNode(T data){
		this(data,null,null);
	}
	public treeNode(T data, treeNode<T> left, treeNode<T> right){
		this.data = data;
		this.leftNode = left;
		this.rightNode = right;
		this.height = 0;
	}
	public treeNode(treeNode<T> sourceOfCopy ){
		this.data = sourceOfCopy.data;
		this.leftNode = sourceOfCopy.leftNode;
		this.rightNode = sourceOfCopy.rightNode;
		this.height = sourceOfCopy.height;
	}

// **** data members ****
	private int height;
	private T data;
	private treeNode< T > leftNode;
	private treeNode< T > rightNode;
}
