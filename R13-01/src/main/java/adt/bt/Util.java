package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		BSTNode<T> right = (BSTNode<T>) node.getRight();
		if(node.getParent()!=null) {
			if (isLeft(node,(BSTNode) node.getParent()))
				node.getParent().setLeft(right);
			else
				node.getParent().setRight(right);
		}
		right.setParent(node.getParent());
		node.setParent(right);
		node.setRight(right.getLeft());
		right.getLeft().setParent(node);
		right.setLeft(node);
		return right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> left = (BSTNode<T>) node.getLeft();
		if(node.getParent()!=null) {
			if (isLeft(node,(BSTNode) node.getParent()))
				node.getParent().setLeft(left);
			else
				node.getParent().setRight(left);
		}
		left.setParent(node.getParent());
		node.setParent(left);
		node.setLeft(left.getRight());
		left.getRight().setParent(node);
		left.setRight(node);
		return left;
	}
	private static boolean isLeft(BSTNode node,BSTNode parent) {
		return parent.getLeft().equals(node);
	}
	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
