package rbt;

public class TreePrinter {
	public static String prefix(RBT key) {
		Node root = key.getRootNode();
		String ans = "";
		if (!key.isEmpty()) {
			ans = prefixNode(root, ans);
		}
		return ans;
	}

	private static String prefixNode(Node node, String ans) {
		ans += node.data;
		ans += "(" + (node.color ? "RED" : "BLACK") + ")";
		if (node.left != null) {
			ans += ", ";
			ans = prefixNode(node.left, ans);
		}
		if (node.right != null) {
			ans += ", ";
			ans = prefixNode(node.right, ans);
		}
		return ans;
	}

	public static String infix(RBT key) {
		Node root = key.getRootNode();
		String ans = "";
		if (!key.isEmpty()) {
			ans = infixNode(root, ans);
		}
		return ans;
	}

	private static String infixNode(Node node, String ans) {
		if (node.left != null) {
			ans = infixNode(node.left, ans);
			ans += ", ";
		}
		ans += node.data;
		ans += "(" + (node.color ? "RED" : "BLACK") + ")";
		if (node.right != null) {
			ans += ", ";
			ans = infixNode(node.right, ans);
		}
		return ans;
	}

	public static String postfix(RBT key) {
		Node root = key.getRootNode();
		String ans = "";
		if (!key.isEmpty()) {
			ans = postfixNode(root, ans);
		}
		return ans;
	}

	private static String postfixNode(Node node, String ans) {
		if (node.left != null) {
			ans = postfixNode(node.left, ans);
			ans += ", ";
		}
		if (node.right != null) {
			ans = postfixNode(node.right, ans);
			ans += ", ";
		}
		ans += node.data;
		ans += "(" + (node.color ? "RED" : "BLACK") + ")";
		return ans;
	}
}
