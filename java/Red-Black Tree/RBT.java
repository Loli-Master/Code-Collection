package rbt;

// TODO TRUE=RED; FALSE=BLACK
class Node {
	public int data;
	public boolean color;
	public Node left;
	public Node right;
	public Node parent;

	public Node(int data) {
		this.data = data;
		color = true;
	}

	public Node getGrandpa() {
		if (parent != null && parent.parent != null) {
			return parent.parent;
		} else {
			return null;
		}
	}

	public Node getSibling() {
		Node S = null;
		if (parent != null) {
			S = (this == parent.left) ? parent.right : parent.left;
		}
		return S;
	}

	public Node getUncle() {
		Node G = getGrandpa();
		if (G != null && parent == G.left) {
			return G.right;
		} else if (G != null && parent == G.right) {
			return G.left;
		} else {
			return null;
		}
	}
}

public class RBT {
	private Node root;

	public RBT() {
		root = null;
	}

	public boolean isEmpty() {
		if (root == null) {
			return true;
		} else {
			return false;
		}
	}

	public Node getRootNode() {
		return root;
	}

	public void insert(int data) {
		if (isEmpty()) {
			root = new Node(data);
			insert1(root);
		} else {
			insertTree(root, data);
		}
	}

	private void insertTree(Node pivot, int data) {
		if (data < pivot.data) {
			if (pivot.left == null) {
				pivot.left = new Node(data);
				pivot.left.parent = pivot;
				insert1(pivot.left);
			} else {
				insertTree(pivot.left, data);
			}
		} else if (data > pivot.data) {
			if (pivot.right == null) {
				pivot.right = new Node(data);
				pivot.right.parent = pivot;
				insert1(pivot.right);
			} else {
				insertTree(pivot.right, data);
			}
		}
	}

	private void rotateLeft(Node pivot) {
		Node oldRight = pivot.right;
		Node oldG = oldRight.left;
		pivot.right = oldG;
		oldG.parent = pivot;
		oldRight.left = pivot;
		if (pivot.parent != null) {
			Node G = pivot.parent;
			if (G.left == pivot) {
				G.left = oldRight;
			} else if (G.right == pivot) {
				G.right = oldRight;
			}
			oldRight.parent = G;
		}
		else{
			root=oldRight;
		}
		pivot.parent = oldRight;
		// return oldRight;
	}

	private void rotateRight(Node pivot) {
		Node oldLeft = pivot.left;
		Node oldG = oldLeft.right;
		pivot.left = oldG;
		oldG.parent = pivot;
		oldLeft.right = pivot;
		if (pivot.parent != null) {
			Node G = pivot.parent;
			if (G.left == pivot) {
				G.left = oldLeft;
			} else if (G.right == pivot) {
				G.right = oldLeft;
			}
			oldLeft.parent = G;
		}
		else{
			root=oldLeft;
		}
		pivot.parent = oldLeft;
		// return oldLeft;
	}

	// INSERT CASES
	// ADAPTED FROM WIKIPEDIA: https://en.wikipedia.org/wiki/Red-black_tree
	private void insert1(Node node) {
		if (node.parent == null) {
			node.color = false;
		} else {
			insert2(node);
		}
	}

	private void insert2(Node node) {
		if (!node.parent.color) {
			return;
		} else {
			insert3(node);
		}
	}

	private void insert3(Node node) {
		Node u = node.getUncle();
		if ((u != null) && (u.color)) {
			node.parent.color = false;
			u.color = false;
			Node g = node.getGrandpa();
			g.color = true;
			insert1(g);
		} else {
			insert4(node);
		}
	}

	private void insert4(Node node) {
		Node G = node.getGrandpa();
		Node next = null;
		if ((node == node.parent.right) && (node.parent == G.left)) {
			rotateLeft(node.parent);
			next = node.left;
		} else if ((node == node.parent.left) && (node.parent == G.right)) {
			rotateRight(node.parent);
			next = node.right;
		} else {
			next = node;
		}
		insert5(next);
	}

	private void insert5(Node node) {
		Node G = node.getGrandpa();
		node.parent.color = false;
		G.color = true;
		if (node == node.parent.left) {
			rotateRight(G);
		} else {
			rotateLeft(G);
		}
	}

	private Node min(Node node) {
		Node ans = node;
		if (ans.left != null) {
			ans = ans.left;
		}
		return ans;
	}

	public void delete(int key) {
		delete(root, key);
	}

	private void delete(Node node, int key) {
		if (key < node.data && node.left != null) {
			delete(node.left, key);
		} else if (key > node.data && node.right != null) {
			delete(node.right, key);
		} else if (key == node.data) {
			if (node.left == null && node.right == null) {
				if (node == root) {
					root = null;
				} else {
					if (node.parent.left == node) {
						if (!node.color) {
							doubleBlack(node);
						}
						node.parent.left = null;
					} else {
						if (!node.color) {
							doubleBlack(node);
						}
						node.parent.right = null;
					}
				}
			} else if (node.left == null || node.right == null) {
				Node now = (node.right == null) ? node.left : node.right;
				if (node == root) {
					root = now;
				} else {
					if (node.parent.left == node) {
						node.parent.left = now;
						if (!node.color && !now.color) {
							doubleBlack(now);
						}
						now.parent = node.parent;
					} else {
						node.parent.right = now;
						if (!node.color && !now.color) {
							doubleBlack(now);
						}
						now.parent = node.parent;
					}
				}
			} else {
				Node target = min(node.right);
				node.data = target.data;
				delete(node.right, node.data);
			}
		}
	}

	private void doubleBlack(Node n) {
		Node S = n.getSibling();
		Node P = n.parent;
		if(n==root){
			//nothing :v
		}
		//right
		else if(S!=null&&!S.color&&S==P.right){
			//rightright
			if(S.right!=null&&S.right.color){
				Node r=S.right;
				rotateLeft(P);
				r.color=false;
			}
			//rightleft
			else if(S.left!=null&&S.left.color){
				Node r=S.left;
				rotateRight(S);
				rotateLeft(P);
				r.color=false;
			}
		}
		//left
		else if(S!=null&&!S.color&&S==P.left){
			//leftleft
			if(S.left!=null&&S.left.color){
				Node r=S.left;
				rotateRight(P);
				r.color=false;
			}
			//leftright
			else if(S.right!=null&&S.right.color){
				Node r=S.right;
				rotateLeft(S);
				rotateRight(P);
				r.color=false;
			}
		}
		else if((S!=null&&!S.color)||S==null){
			if(S!=null){
				S.color=true;
			}
			P.color=false;
			if(!P.color){
				doubleBlack(P);
			}
		}
		else if(S!=null&&S.color){
			//right
			if(P.right==S){
				if(S.left!=null){
					S.left.color=true;
					//System.out.println("lol");
				}
				rotateLeft(P);
				S.color=false;
				//P.color=false;
			}
			//left
			else if(P.left==S){
				if(S.right!=null){
					S.right.color=true;
				}
				rotateRight(P);
				S.color=false;
				//P.color=false;
			}
		}
	}
}
