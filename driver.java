
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
/*
		System.out.println("\n==== Tests for avlTree class ====");
		System.out.println("---- Creating tree of integers ----");
		System.out.println("Inserting: 8, 8, 4, 2, 6, 1, 3, 5, 7, 12, 10, 14, 9, 11, 13, 15");
		intTree.insert(8);
		intTree.insert(4);
		intTree.insert(2);
		intTree.insert(6);
		intTree.insert(1);
		intTree.insert(3);
		intTree.insert(5);
		intTree.insert(7);
		intTree.insert(12);
		intTree.insert(10);
		intTree.insert(14);
		intTree.insert(9);
		intTree.insert(11);
		intTree.insert(13);
		intTree.insert(15);
		System.out.println("Tree size: " + intTree.size() + " duplicate data is not inserted");
		System.out.println("Printing elements of Tree in order: ");
		intTree.printInOrder();
		System.out.println("Printing elements of Tree in reverse order: ");
		intTree.printReverseOrder();
		System.out.println("Printing Tree Heigherarchy: ");
		intTree.printHierarchy();
*/		
		System.out.println("==== creating tree of Doubles ====\n");
		avlTree<Integer> doubleTree = new avlTree<Integer>();
		doubleTree.insert(8);
		doubleTree.printHierarchy();
		doubleTree.insert(1);
		doubleTree.printHierarchy();
		doubleTree.insert(7);
		//doubleTree.insert(6);
		//doubleTree.insert(5);
		doubleTree.printHierarchy();
		
	}

}
