package kalkulator;

class Node2 {
	public double data;
	public Node2 next;

	public Node2(double data) {
		this.data = data;
		next = null;
	}

	public void displayLink() {
		System.out.print("{" + data + "}");
	}
}

public class ListDouble {
	private Node2 first;
	private int size;

	public ListDouble() {
		first = null;
		size = 0;
	}

	public Node2 getFirst() {
		return first;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public void insertFirst(double data) {
		Node2 newNode = new Node2(data);
		newNode.next = first;
		first = newNode;
		size++;
	}

	public void insertLast(double data) {
		if (first == null)
			insertFirst(data);
		else {
			Node2 temp = first;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = new Node2(data);
			size++;
		}
	}

	public Node2 deleteFirst() {
		Node2 temp = first;
		first = first.next;
		size--;
		return temp;
	}

	public Node2 deleteLast() {
		Node2 temp = first;
		while (temp.next.next != null) {
			temp = temp.next;
		}
		Node2 toReturn = temp.next;
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
			Node2 temp = first;
			while (temp.next != null) {
				ret += Double.toString(temp.data);
				temp = temp.next;
			}
			ret += Double.toString(temp.data);
		}
		ret += '\n';
		return ret;
	}
}