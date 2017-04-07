// queue using linked list
package bankQueue;

class Node {
	public boolean t;
	public double money;
	public String curr;
	public int turn;
	public Node next;

	public Node(boolean t, double money, String curr, int turn) {
		this.t = t;
		this.money = money;
		this.curr = curr;
		this.turn = turn;
		next = null;
	}

	public String toString() {
		String ans = "";
		if (t) {
			ans += "BUY" + " ";
		} else {
			ans += "SELL" + " ";
		}
		ans += String.format("%.2f", money) + " ";
		ans += curr + " ";
		ans += "Try " + String.format("%d", turn);
		return ans;
	}

}

public class MyQueue {
	private Node first;
	private int size;

	public MyQueue() {
		first = null;
		size = 0;
	}

	public void push(Node now) {
		if (first == null) {
			first = now;
		} else {
			Node temp = first;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = now;
		}
		size++;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public int size() {
		return size;
	}

	public Node pop() {
		Node temp = first;
		if (first != null) {
			first = first.next;
			size--;
		}
		return temp;
	}

	public Node top() {
		return first;
	}

	public String toString() {
		String ans = "QUEUED:\n<Type> <Money> <Currency> <Number of try>\n";
		if (!isEmpty()) {
			Node temp = first;
			while (temp != null) {
				ans += temp.toString() + "\n";
				temp = temp.next;
			}
		}
		return ans;
	}
}