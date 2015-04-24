
public class driver {

	public static void main(String[] args) {

// ==== Tests for treeNode class ====
		System.out.println("==== Tests for treeNode class ====");
		System.out.println("  Declaring treeNode<String> t(\"hello\",null,null)");
		System.out.println("  Declaring treeNode<String> x(t) --copy constructor");
		treeNode<String> t = new treeNode<String>("hello",null,null);
		treeNode<String> x = new treeNode<String>(t);
		System.out.println("  t.getData =  " + t.getData());
		System.out.println("  x.getData() =  "+ x.getData());
		System.out.println("  Testing setLeftNode using: x.setLeftNode( new treeNode<String>(\"do\")) ");
		x.setLeftNode( new treeNode<String>("do") );
		System.out.println("   x.getLeftNode().getData() = "+ x.getLeftNode().getData());
		System.out.println("  Testing hasLeft/hasRight: ");
		System.out.println("   x.hasLeft() =  " + x.hasLeft() );
		System.out.println("   x.hasRight() =  " + x.hasRight());
		System.out.println("  Testing getHeight: ");
		System.out.println("   t.getHeight() = " + t.getHeight());

// ==== Test for AVL Tree class ====
		
		avlTree<Integer> intTree = new avlTree<Integer>();
		
		System.out.println("\n==== Tests for avlTree class ====");
		System.out.println("---- Creating tree of integers ----");
		System.out.println("Inserting: 6, 14, 8, 13, 8, 4, 2, 10, 1, 3, 5, 7, 12, 9, 11, 15");
		intTree.insert(6);
		intTree.insert(14);
		intTree.insert(8);
		intTree.insert(13);
		intTree.insert(8);
		intTree.insert(4);
		intTree.insert(2);
		intTree.insert(10);
		intTree.insert(1);
		intTree.insert(3);
		intTree.insert(5);
		intTree.insert(7);
		intTree.insert(12);
		intTree.insert(9);
		intTree.insert(11);
		intTree.insert(15);
		System.out.println("Tree size: " + intTree.size() + " duplicate data is not inserted");
		System.out.println("Printing elements of Tree in order: ");
		intTree.printInOrder();
		System.out.println("Printing elements of Tree in reverse order: ");
		intTree.printReverseOrder();
		System.out.println("Printing Tree Heigherarchy: ");
		intTree.printHierarchy();
		
		System.out.println("==== testing right rotate ====\n");
		avlTree<Integer> rightRotateTestTree = new avlTree<Integer>();
		System.out.println("Inserting: 4, 5, 3, 2, 1, 1, 3");
		rightRotateTestTree.insert(4);
		rightRotateTestTree.insert(5);
		rightRotateTestTree.insert(3);
		rightRotateTestTree.insert(2);
		rightRotateTestTree.insert(1);
		rightRotateTestTree.insert(1);
		rightRotateTestTree.insert(3);
		rightRotateTestTree.printHierarchy();
		System.out.println("==============================");
		
		System.out.println("==== testing double right rotate ====\n");
		avlTree<Integer> doubleRightRotateTestTree = new avlTree<Integer>();
		System.out.println("Inserting: 5, 3, 6, 4, 2, 1, 1, 3");
		doubleRightRotateTestTree.insert(5);
		doubleRightRotateTestTree.insert(3);
		doubleRightRotateTestTree.insert(6);
		doubleRightRotateTestTree.insert(4);
		doubleRightRotateTestTree.insert(2);
		doubleRightRotateTestTree.insert(1);
		doubleRightRotateTestTree.insert(1);
		doubleRightRotateTestTree.insert(3);
		doubleRightRotateTestTree.printHierarchy();
		System.out.println("==============================");
		
		System.out.println("==== testing left rotate ====\n");
		avlTree<Integer> leftRotateTestTree = new avlTree<Integer>();
		System.out.println("Inserting: 2, 1, 3, 4, 5");
		leftRotateTestTree.insert(2);
		leftRotateTestTree.insert(1);
		leftRotateTestTree.insert(3);
		leftRotateTestTree.insert(4);
		leftRotateTestTree.insert(5);
		leftRotateTestTree.printHierarchy();
		System.out.println("=============================");
		
		System.out.println("==== testing double left rotate ====\n");
		avlTree<Integer> doubleLeftRotateTestTree = new avlTree<Integer>();
		System.out.println("Inserting: 2, 1, 4, 3, 5, 6");
		doubleLeftRotateTestTree.insert(2);
		doubleLeftRotateTestTree.insert(1);
		doubleLeftRotateTestTree.insert(4);
		doubleLeftRotateTestTree.insert(3);
		doubleLeftRotateTestTree.insert(5);
		doubleLeftRotateTestTree.insert(6);
		doubleLeftRotateTestTree.printHierarchy();
		System.out.println("=============================");
	
		System.out.println("==== testing string insering ====\n");
		avlTree<String> stringTree = new avlTree<String>();
		stringTree.insert("a");
		stringTree.insert("b");
		stringTree.insert("c");
		stringTree.insert("d");
		stringTree.printHierarchy();
		System.out.println("=============================");
		
		System.out.println("==== testing search ====");
		avlTree<Integer> searchTestTree = new avlTree<Integer>();
		System.out.println("Inserting: 2, 1, 4, 3, 5, 6");
		searchTestTree.insert(2);
		searchTestTree.insert(1);
		searchTestTree.insert(4);
		searchTestTree.insert(3);
		searchTestTree.insert(5);
		searchTestTree.insert(6);
		System.out.println("searchTestTree.search(3) = " + searchTestTree.search(3));
		System.out.println("searchTestTree.search(10) = " + searchTestTree.search(10));
	}

}
