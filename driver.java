
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

	}

}
