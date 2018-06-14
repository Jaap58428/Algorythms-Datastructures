package week6;

//Klasse voor een knoop voor een binaire boom
public class BKnoop<E> {
	
	private BKnoop<E> parent, leftChild, rightChild;
	private E userObject;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	
	// Constructors
	public BKnoop() {
	 this( null );
	}
	
	public BKnoop( E userObject ) {
	 parent = null;
	 leftChild = null;
	 rightChild = null;
	 this.userObject = userObject;
	}
	
	// Methoden
	public void add( BKnoop<E> newChild ) {
	 if( leftChild == null )
	   insert( newChild, LEFT );
	 else
	   if( rightChild == null )
	     insert( newChild, RIGHT );
	   else
	     throw new IllegalArgumentException(
	                      "Meer dan 2 kinderen" );
	}
	
	public E get() {
	 return userObject;
	}
	
	public BKnoop<E> getLeftChild() {
	 return leftChild;
	}
	
	public BKnoop<E> getRightChild() {
	 return rightChild;
	}
	
	public BKnoop<E> getParent() {
	 return parent;
	}
	
	public void insert( BKnoop<E> newChild, int childIndex ) {
	 if( isAncestor( newChild ) )
	   throw new IllegalArgumentException(
	             "Nieuw kind is voorouder" );
	 if( childIndex != LEFT &&
	     childIndex != RIGHT )
	   throw new IllegalArgumentException(
	             "Index moet 0 of 1 zijn" );
	
	 // if the newChild has a parent, remove it from their parent
	 // it can only exist in one place
	  if( newChild != null ) {
	   BKnoop<E> oldParent = newChild.getParent();
	   if( oldParent != null )
	     oldParent.remove( newChild );
	 }
	
	 newChild.parent = this;
	 if( childIndex == LEFT )
	   leftChild = newChild;
	 else
	   rightChild = newChild;
	}
	
	public boolean isChild( BKnoop<E> aNode ) {
	 return aNode == null?
	        false :
	        aNode.getParent() == this;
	}
	
	public boolean isAncestor( BKnoop<E> aNode ) {
	 BKnoop<E> ancestor = this;
	 while( ancestor != null && ancestor != aNode )
	   ancestor = ancestor.getParent();
	 return ancestor != null;
	}
	
	public void remove( BKnoop<E> aChild ) {
	 if( aChild == null )
	   throw new IllegalArgumentException(
	             "Argument is null" );
	
	 if( !isChild( aChild ) )
	   throw new IllegalArgumentException(
	             "Argument is geen kind" );
	
	 if( aChild == leftChild ) {
	   leftChild.parent = null;
	   leftChild = null;
	 }
	 else {
	   rightChild.parent = null;
	   rightChild = null;
	 }
	}
	
	public String toString() {
	 return userObject.toString();
	}
	
	public void printInOrder(BKnoop<E> aNode) {
		// print left, self, right
		
		if ( leftChild != null ) {
			leftChild.printInOrder(leftChild);
		}
		
		System.out.print(aNode.toString() + " ");
		
		if ( rightChild != null ) {
			rightChild.printInOrder(rightChild);
		}
	
	}
	
	public void printPostOrder(BKnoop<E> aNode) {
		// print left, right, self
		if ( leftChild != null ) {
			leftChild.printPostOrder(leftChild);
		}
		
		if ( rightChild != null ) {
			rightChild.printPostOrder(rightChild);
		}
		
		System.out.print(aNode.toString() + " ");
		
		
	}
	
	public void printPreOrder(BKnoop<E> aNode) {
		// print self, left, right
		
		System.out.print(aNode.toString() + " ");

		if ( leftChild != null ) {
			leftChild.printPreOrder(leftChild);
		}
		
		if ( aNode.getRightChild() != null ) {
			rightChild.printPreOrder(rightChild);
		}
		
			
	}
	
	public int aantalKnopen() {
		// return all kids + 1 (self)
		// considering LEAVES are also KNOPEN (type)
		
		int knopen = 0;
		
		if (leftChild != null) {
			knopen += leftChild.aantalKnopen();
		}
		
		if (rightChild != null) {
			knopen += rightChild.aantalKnopen();
		}
		
		return knopen + 1;
	}
	
	public int aantalLeafs() {
		
		if ( leftChild == null && rightChild == null ) {
			
			return 1;
			
		} else {
			
			int leafs = 0;
			
			if (leftChild != null) {
				leafs += leftChild.aantalLeafs();
			}
			
			if (rightChild != null) {
				leafs += rightChild.aantalLeafs();
			}
			
			return leafs;
			
		}
	}

	public int diepte() {
		
		// No path down
		if ( leftChild == null && rightChild == null ) {
			
			return 0;
			
		} else {
			
			int leftPath = 0;
			int rightPath = 0;
			
			// Get depth plus the step to child
			if (leftChild != null) {
				leftPath += leftChild.diepte() + 1;
			}
			if (rightChild != null) {
				rightPath += rightChild.diepte() + 1;
			} 
			
			// Choose the longest path
			if ( leftPath < rightPath)
				return rightPath;
			else 
				return leftPath;
		}

	}

	public int hoogte() {
		return diepte() + 1;
	}
}
