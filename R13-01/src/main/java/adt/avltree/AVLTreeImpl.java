package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	@Override
	public void insert(T element) {
			super.insert(element);
			BSTNode<T> node = search(element);
			rebalanceUp(node);
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);
		if (!node.isEmpty()) {
			if (!node.isLeaf())
				node = (BSTNode<T>) sucessor(element);
			super.remove(element);
			rebalanceUp(node);
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node.isEmpty()) 
			return -1;
		return height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (balance>1) {
			if (calculateBalance((BSTNode<T>) node.getRight())<0) {
				rightRotation((BSTNode<T>) node.getRight());
				leftRotation(node);
			} else {
				leftRotation(node);
			}
		} else if (balance<-1) {
			if (calculateBalance((BSTNode<T>) node.getLeft())>0) {
				leftRotation((BSTNode<T>) node.getLeft());
				rightRotation(node);
			} else {
				rightRotation(node);
			}
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			while (parent != null) {
				this.rebalance(parent);
				parent = (BSTNode<T>) parent.getParent();
			}
	}

	private void leftRotation(BSTNode<T> node) {
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		if (getRoot() == node)
			root = right;
		Util.leftRotation(node);
	}

	private void rightRotation(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		if (getRoot() == node)
			root = left;
		Util.rightRotation(node);
	}

}