
public class AVL {
	/*Class:AVL class
	 * Author:Arda Baran
	 * Description:
	 * This class represents the AVL Tree Data Structure.It is used to search book by ISBN Number.
	 * 
	 * 
	 * 
	 */	
	
	Node root;
	public AVL(){
		this.root=null;
	}
	public int findMax(int a ,int b) {
		return Math.max(a, b);
	}
	public int getHeight(Node node) {
		return (node==null) ? 0 : node.height;
	}
	public void updateHeight(Node node) {
		int leftChild=getHeight(node.getLeft());
		int rightChild = getHeight(node.getRight());
	if(node!=null) {
		node.height=findMax(leftChild,rightChild) + 1;
	}
	}
	public Node leftRotation(Node node) {
		if (node == null || node.getRight() == null) {
	        return node;
	    }
		Node rightChild= node.getRight();
		node.setRight(rightChild.getLeft());
	    rightChild.setLeft(node);
	    updateHeight(node);
	    updateHeight(rightChild);
	    return rightChild;
	}
	public Node rightRotation(Node node) {
		if (node == null || node.getLeft() == null) {
	        return node;
	    }
		Node leftChild = node.getLeft();
		node.setLeft(leftChild.getRight());
	    leftChild.setRight(node);
	    updateHeight(node);
	    updateHeight(leftChild);
	    return leftChild;    
	}
	public int balanceFactor(Node node) {
		return (node==null) ? 0 : getHeight(node.getLeft()) - getHeight(node.getRight());
	}
	public int compareBookIsbnIDs(Node node1,Node node2) {
		if(node1==null||node2==null) {
			return 0 ;	
		}
	return node1.getBook().getIsbn().compareTo(node2.getBook().getIsbn());
	}
	public Node balance(Node node) {
		  if (node == null) {
		        return null;
		    }
		  int leftComprasion=compareBookIsbnIDs(node.getLeft(),node);
		  int rightComprasion=compareBookIsbnIDs(node,node.getRight());
	      int balanceF=balanceFactor(node);
	if(balanceF >= 1) {
		if(leftComprasion >= 0) {
			return rightRotation(node);
		}else {
			node.setLeft(leftRotation(node.getLeft()));
		return rightRotation(node);
		}
	}else if (balanceF < -1) {
		if(rightComprasion <=0) {
			return leftRotation(node);
		}else {
			 node.setRight(rightRotation(node.getRight()));
	         return leftRotation(node);
		}
	}
	return node;
	}
	public Node insertBookToAvl(Node node , Book book ) {
		if(node == null) {
			return new Node(book);
		}
	if(book.getIsbn().compareTo(node.getBook().getIsbn()) < 0) {
		node.setLeft(insertBookToAvl(node.getLeft(),book));
	}else if (book.getIsbn().compareTo(node.getBook().getIsbn()) > 0) {
		node.setRight(insertBookToAvl(node.getRight(),book));
	}else {
		return node;
	}
	updateHeight(node);
	return balance(node);
	}
	public void insertBookToAvl(Book book) {
		root = insertBookToAvl(root,book);
	}
	public boolean search(Node node ,String isbn) {
		if(node==null) {
			return false;
		}
		int comprasion = isbn.compareTo(node.getBook().getIsbn());
		if(comprasion == 0) {
			return true;
		}
	if(comprasion < 0) {
		return search(node.getLeft(),isbn);
	}else {
		return search(node.getRight(),isbn);
	}
	}
public Book getBookByIsbn(Node node,String isbn) {
	if(node.getBook() == null ) {
		return null;
	}
	int comprasion = isbn.compareTo(node.getBook().getIsbn());
	if(comprasion == 0) {
		return node.getBook();
		
	}
	if(comprasion < 0) {
		return getBookByIsbn(node.getLeft(),isbn);
	}else {
		return getBookByIsbn(node.getRight(),isbn);
	}
	
}
public Book getBookByIsbn(String isbn) {
	return getBookByIsbn(root,isbn);
}
	public void searchBookByIsbn(Node node,String isbn) {
		if(node==null) {
			System.out.println("There is no record,database is empty.Please insert record first...");
			return ;
		}
		if(!search(node,isbn) && !search(node.getLeft(),isbn)&& !search(node.getRight(),isbn)) {
			System.out.println(isbn+" is not found in the database...");
		}
		
		int comprasion = isbn.compareTo(node.getBook().getIsbn());
	if(comprasion == 0 && search(node,isbn)) {
		System.out.println(node.getBook());
	}
	if(comprasion < 0 && search(node.getLeft(),isbn)) {
		searchBookByIsbn(node.getLeft(),isbn);	
	}
	if(comprasion > 0 && search(node.getRight(),isbn)) {
		searchBookByIsbn(node.getRight(),isbn);	
	}
	}
	public void searchBookByIsbn(String query) {
		searchBookByIsbn(root,query);
	}
	public void inOrder() {
	    inOrderRecursive(root);
	}

	public void inOrderRecursive(Node node) {
	    if (node != null) {
	        inOrderRecursive(node.getLeft());
	        System.out.println(node.getBook());
	        inOrderRecursive(node.getRight());
	    }
	}

	
}
class Node{
	/*Class:Node class
	 * Author:Arda Baran
	 * Description:
	 * This class represents the Node of AVL Tree
	 * 
	 * 
	 * 
	 */
	
	Book book;
	Node left,right;
	int height;
	Node(Book book){
		this.book=book;
		this.left=null;
		this.right=null;
		this.height=1;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

}