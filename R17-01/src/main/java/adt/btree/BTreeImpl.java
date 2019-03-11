package adt.btree;

import java.util.ArrayList;
import java.util.LinkedList;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {
	protected BNode<T> root;
	protected int order;
	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}
	@Override
	public BNode<T> getRoot() {
		return this.root;
	}
	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}
	@Override
	public int height() {
		return height(getRoot());
	}
	private int height(BNode<T> node) {
		if (node.isEmpty()) {
			return -1;
		} else {
			int height = 0;
			if (!node.isLeaf()) {
				height = 1 + height(node.getChildren().get(0));
			}
			return height;
		}
	}
	@Override
	public BNode<T>[] depthLeftOrder() {
		ArrayList<BNode<T>> lista = new ArrayList<BNode<T>>();
		lista = depthLeftOrder(getRoot(), lista);
		@SuppressWarnings("unchecked")
		BNode<T>[] array = new BNode[lista.size()];
		return lista.toArray(array);
	}
	public ArrayList<BNode<T>> depthLeftOrder(BNode<T> node, ArrayList<BNode<T>> lista) {
		if (!node.isEmpty()) {
			lista.add(node);
			for (BNode<T> element : node.getChildren()) {
				depthLeftOrder(element, lista);
			}
		}
		return lista;
	}
	@Override
	public int size() {
		return size(getRoot());
	}
	private int size(BNode<T> node) {
		if (node.isEmpty()) {
			return 0;	
		} else {	
			int size = node.size();	
			if (!node.isLeaf()) {
				for (BNode<T> nodeChildren : node.getChildren()) {
					size += size(nodeChildren);
				}
			}	
			return size;
		}
	}
	@Override
	public BNodePosition<T> search(T element) {
		return search(getRoot(), element);
	}
	private BNodePosition<T> search(BNode<T> node, T element) {
		int index = 1;
		while (index <= node.size() && element.compareTo(node.getElementAt(index)) > 0) {
			index += 1;
		}
		if (index <= node.size() && element == node.getElementAt(index)) {
			return new BNodePosition<T>(node, index);
		}
		if (node.isLeaf()) {
			return new BNodePosition<T>();
		}
		return search(node.getChildren().get(index), element);
	}
	private int position(LinkedList<T> elements, T meio) {
		int i = 0;
		while (i < elements.size()) {
			if (elements.get(i).compareTo(meio) > 0) {
				return i;
			}
			i++;
		}
		return elements.size();
	}
	@Override
	public void insert(T element) {
		if (element != null) {
			insert(getRoot(), element);
		}
	}
	private void insert(BNode<T> node, T element) {
		if (node.isLeaf()) {
			node.addElement(element);
			if (node.getElements().size() > node.getMaxKeys()) {
				split(node);
			}
		} else {
			int aux = position(node.getElements(), element);
			insert(node.getChildren().get(aux), element);
		}
	}
	private void split(BNode<T> node) {
		node.split();
	}
	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}
	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}
	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}
}