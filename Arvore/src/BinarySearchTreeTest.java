import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	@Test
	void test() {
		BinarySearchTree BST = new BinarySearchTree();
		assertEquals(BST.size(),0);
		assertEquals(BST.getFolhas(),0);
		BST.add(10);
		assertEquals(BST.getFolhas(),1);
		assertEquals(BST.size(),1);
		BST.add(5);
		assertEquals(BST.getFolhas(),1);
		assertEquals(BST.size(),2);
		BST.add(15);
		assertEquals(BST.getFolhas(),2);
		assertEquals(BST.size(),3);
		BST.add(20);
		assertEquals(BST.getFolhas(),2);
		assertEquals(BST.size(),4);
		assertTrue(BST.contains(20));
		assertFalse(BST.contains(100));
		BST.preOrder();
		BST.inOrder();
		BST.posOrder();
	}

}
