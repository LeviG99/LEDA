package adt.rbtree;

import java.util.ArrayList;
import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

   public RBTreeImpl() {
      this.root = new RBNode<T>();
   }

   protected int blackHeight() {
      int bHeight = 0;
      if (!root.isEmpty()) {
         RBNode<T> node = (RBNode<T>)root;
         while (!node.isEmpty()) {
            if (node.getColour() == Colour.BLACK) {
               bHeight++;
            }
            node = (RBNode<T>) node.getRight();
         }
      }
      return bHeight;

   }

   protected boolean verifyProperties() {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour() {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour() {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour() {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must be
    * BLACK.
    */
   private boolean verifyChildrenOfRedNodes() {
      return verifyChildrenOfRedNodes((RBNode<T>) root);
   }
   private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
      boolean check = true;
      if (!node.isEmpty()) {
         if (node.getColour() == Colour.RED) {
            RBNode<T> left = (RBNode<T>) node.getLeft();
            RBNode<T> right = (RBNode<T>) node.getRight();
            if (left.getColour() == Colour.RED || right.getColour() == Colour.RED)
               check = false;
         }
         check = verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) && verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
      }
      return check;
   }
   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight() {
      int left = verifyBlackHeight((RBNode<T>) root.getLeft(), 0);
      int right = verifyBlackHeight((RBNode<T>) root.getLeft(), 0);
      return left == right;
   }
   private int verifyBlackHeight(RBNode<T> node, int i) {
      if (node != null && node.isEmpty()) {
         if (node.getColour() == Colour.BLACK) {
            i++;
         }
         return Math.max(verifyBlackHeight((RBNode<T>) node.getLeft(), i),
               verifyBlackHeight((RBNode<T>) node.getRight(), i));
      }
      return i++;
   }
   @Override
   public void insert(T value) {
      RBNode<T> node = insert((RBNode<T>) root, value, new RBNode<T>());
      node.setColour(Colour.RED);
      fixUpCase1(node);
   }
   private RBNode<T> insert(RBNode<T> node, T element, RBNode<T> parent) {
      if (node.isEmpty()) {
         node.setData(element);
         node.setLeft(new RBNode<T>());
         node.setRight(new RBNode<T>());
         node.setParent(parent);
         return node;
      } else if (element.compareTo(node.getData()) < 0) 
         return insert((RBNode<T>) node.getLeft(), element, node);
       else if (element.compareTo(node.getData()) > 0) 
         return insert((RBNode<T>) node.getRight(), element, node); 
      return null;
   }
   @SuppressWarnings("unchecked")
@Override
   public RBNode<T>[] rbPreOrder() {
      ArrayList<RBNode<T>> arrayList = new ArrayList<RBNode<T>>();
      rbPreOrder((RBNode<T>)getRoot(),arrayList);
      RBNode<T>[] array = new RBNode[arrayList.size()];
      return arrayList.toArray(array);
   }
   private void rbPreOrder(RBNode<T> node, ArrayList<RBNode<T>> ArrayList) {
      if (!node.isEmpty()) {
         ArrayList.add(node);
         rbPreOrder((RBNode<T>) node.getLeft(), ArrayList);
         rbPreOrder((RBNode<T>) node.getRight(), ArrayList);
      }
   }
   // FIXUP methods
   protected void fixUpCase1(RBNode<T> node) {
	      if (root == node) 
	         node.setColour(Colour.BLACK);
	       else 
	         fixUpCase2(node);
	      
	   }

	   protected void fixUpCase2(RBNode<T> node) {
	      if (((RBNode<T>) node.getParent()).getColour() == Colour.RED)
		        fixUpCase3(node);
	   }

	   protected void fixUpCase3(RBNode<T> node) {
	      RBNode<T> grandParent = (RBNode<T>) node.getParent().getParent();
	      RBNode<T> uncle;
	      if (node.getParent() == grandParent.getRight())
	         uncle = (RBNode<T>) grandParent.getLeft();
	      else
	         uncle = (RBNode<T>) grandParent.getRight();
	      
	      if (uncle.getColour() == Colour.RED) {
	         ((RBNode<T>) grandParent.getLeft()).setColour(Colour.BLACK);
	         ((RBNode<T>) grandParent.getRight()).setColour(Colour.BLACK);
	         grandParent.setColour(Colour.RED);
	         fixUpCase1(grandParent);
	      } else
	         fixUpCase4(node);
	      
	   }

	   protected void fixUpCase4(RBNode<T> node) {
	      RBNode<T> grandParent = (RBNode<T>) node.getParent().getParent();
	      RBNode<T> next = node;
	      if ((node == node.getParent().getRight()) && (node.getParent() == grandParent.getLeft())) {
	         leftRotation((BSTNode<T>) node.getParent());
	         next = (RBNode<T>) node.getLeft();
	      } else if ((node == node.getParent().getLeft()) && (node.getParent() == grandParent.getRight())) {
	         rightRotation((BSTNode<T>) node.getParent());
	         next = (RBNode<T>) node.getRight();
	      }
	      fixUpCase5(next);
	   }

	   protected void fixUpCase5(RBNode<T> node) {
	      RBNode<T> grandParent = (RBNode<T>) node.getParent().getParent();
	      ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
	      grandParent.setColour(Colour.RED);
	      if (node == node.getParent().getLeft()) {
	         rightRotation(grandParent);
	      } else {
	         leftRotation(grandParent);
	      }
	   }
   protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		if (getRoot() == node)
			root = right;
		Util.leftRotation(node);
	}
	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		if (getRoot() == node)
			root = left;
		Util.rightRotation(node);
	}
}