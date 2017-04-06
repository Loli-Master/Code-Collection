package kalkulator;

class Node {
	public char data;
	public Node next;

	public Node(char data) {
		this.data = data;
		next = null;
	}

	public void displayLink() {
		System.out.print("{" + data + "}");
	}
}

public class ListChar {
	private Node first;
	private int size;

	public ListChar() {
		first = null;
		size = 0;
	}

	public Node getFirst() {
		return first;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertFirst(char data) {
		Node newNode = new Node(data);
		newNode.next = first;
		first = newNode;
		size++;
	}

	public void insertLast(char data) {
		if (first == null)
			insertFirst(data);
		else {
			Node temp = first;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node(data);
			size++;
		}
	}

	public Node deleteFirst() {
		Node temp = first;
		first = first.next;
		size--;
		return temp;
	}

	public Node deleteLast() {
		Node temp = first;
		while (temp.next.next != null) {
			temp = temp.next;
		}
		Node toReturn = temp.next;
		temp.next = null;
		size--;
		return toReturn;
	}

	public int size() {
		return size;
	}

	public String toString() {
		String ret = "";
		if (!isEmpty()) {
			Node temp = first;
			while (temp.next != null) {
				ret += temp.data;
				temp = temp.next;
			}
			ret += temp.data;
		}
		ret += '\n';
		return ret;
	}
}