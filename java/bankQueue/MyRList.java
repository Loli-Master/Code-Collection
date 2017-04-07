// linked list with linear search

// TODO: using binary tree
package bankQueue;

class Data {
	public String key;
	public double buyRate;
	public double sellRate;
	public double stock;
	public Data next;

	public Data(String key, double buyRate, double sellRate) {
		this.key = key;
		this.buyRate = buyRate;
		this.sellRate = sellRate;
		stock = 0;
		next = null;
	}
}

public class MyRList {
	private Data first;
	private int size;

	public MyRList() {
		first = null;
		size = 0;
	}

	public boolean isEmpty() {
		return (first == null);
	}

	public int size() {
		return size;
	}

	public void add(Data data) {
		if (isEmpty()) {
			first = data;
		} else {
			data.next = first;
			first = data;
		}
		size++;
	}

	public Data search(String key) {
		Data temp = first;
		Data ans = null;
		while ((!temp.key.equalsIgnoreCase(key)) && temp.next != null) {
			temp = temp.next;
		}
		if (temp.key.equalsIgnoreCase(key)) {
			ans = temp;
		}
		return ans;
	}

	public void setStock(String key, double stock) {
		Data temp = search(key);
		if (temp != null) {
			temp.stock = stock;
		}
	}

	public double getSellRate(String key) {
		Data temp = search(key);
		if (temp != null) {
			return temp.sellRate;
		} else {
			return -1;
		}
	}

	public double getBuyRate(String key) {
		Data temp = search(key);
		if (temp != null) {
			return temp.buyRate;
		} else {
			return -1;
		}
	}

	public double getStock(String key) {
		Data temp = search(key);
		if (temp != null) {
			return temp.stock;
		} else {
			return -1;
		}
	}

	public String toString() {
		String ans = "STORED:\n<Currency> <Buy> <Sell> <Cash on Bank>\n";
		if (!isEmpty()) {
			Data temp = first;
			while (temp != null) {
				ans += temp.key + " ";
				ans += String.format("%.2f", temp.buyRate) + " ";
				ans += String.format("%.2f", temp.sellRate) + " ";
				ans += String.format("%.2f", temp.stock) + "\n";
				temp = temp.next;
			}
		}
		return ans;
	}

	public String stored() {
		String ans = "STORED:\n<Currency> <Cash on Bank>\n";
		if (!isEmpty()) {
			Data temp = first;
			while (temp != null) {
				ans += temp.key + " ";
				ans += String.format("%.2f", temp.stock) + "\n";
				temp = temp.next;
			}
		}
		return ans;
	}
}