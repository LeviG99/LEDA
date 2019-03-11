
public class BinarySearchTree {
	Node raiz;
	public void add(int valor) {
		if(raiz == null)
			raiz = new Node(valor);
		else
			add(valor,raiz);
	}
	public boolean isEmpty() {
		return raiz == null;
	}
	private void add(int valor, Node node) {
		if(valor < node.value) {
			if(node.left == null) {
				node.left = new Node(valor);
			}else {
				add(valor,node.left);
			}
		}else {
			if(node.right == null) {
				node.right = new Node(valor);
			}else {
				add(valor,node.right);
			}
		}
	}
	public boolean contains(int value) {
		if(isEmpty())
			return false;
		else
			return contains(value,raiz);
	}

	private boolean contains(int value, Node node) {
		if(node == null)
			return false;
		if(node.value == value) {
			return true;
		}else {
			if(value < node.value) {
				return contains(value,node.left);
			}else {
				return contains(value,node.right);
				}
			}				
	}
	public int size() {
		return size(raiz);
	}

	private int size(Node node) {
		if(node == null)
			return 0;
		return 1+size(node.left)+size(node.right);
		
	}
	public int getFolhas() {
		if(isEmpty())
			return 0;
		return getFolhas(raiz);
	}

	private int getFolhas(Node node) {
		int x = 0;
		if(node.left == null && node.right == null)
			x = 1;
		if(node.left!= null)
			x += getFolhas(node.left);
		if(node.right!=null)
			x += getFolhas(node.right);
		return x;
	}
	public void preOrder() {
		if(!isEmpty())
		raiz.preOrder();
		System.out.println();
	}

	public class Node{
		public Node(int valor) {
			value = valor;
		}
		public void preOrder() {
			System.out.print(value+ " ");
			if(! (left == null))left.preOrder();
			if(!(right == null))right.preOrder();
		}
		Node left;
		Node right;
		Integer value;
		public void inOrder() {
			if(!(left==null))left.inOrder();
			System.out.print(value + " ");
			if(!(right==null))right.inOrder();
		}
		public void posOrder() {
			if(!(left==null))left.posOrder();
			if(!(right==null))right.posOrder();
			System.out.print(value+ " ");
			
		}
	}

	public void inOrder() {
		if(!isEmpty())
		raiz.inOrder();
		System.out.println();
		
	}
	public void posOrder() {
		if(!isEmpty())
			raiz.posOrder();
		System.out.println();
	}
}
