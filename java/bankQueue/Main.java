package bankQueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// set dot "." as decimal separator
		Locale.setDefault(Locale.US);

		// System input (cmd) for buy/sell rate and cash on bank
		Scanner scanin = new Scanner(System.in);

		MyRList rates = new MyRList();
		MyQueue queue = new MyQueue();
		String local = "IDR";
		int curr;

		// add default currencies (IDR)
		System.out.println("What is the code of local currency?");
		local = scanin.next();
		rates.add(new Data(local, 0, 0));

		// input buy/sell rate
		System.out.println("How many foreign currencies?");
		curr = scanin.nextInt();
		if (curr > 0) {
			System.out.println("Input the foreign currency name, sell, and buy rate with format:");
		}
		for (int i = 0; i < curr; i++) {
			String cur;
			double buyR;
			double sellR;
			System.out.println("<Currency name> <Sell rate> <Buy rate>");
			cur = scanin.next();
			sellR = scanin.nextDouble();
			buyR = scanin.nextDouble();
			rates.add(new Data(cur, buyR, sellR));
		}

		// input cash on bank
		System.out.println("How many currencies in stock?");
		curr = scanin.nextInt();
		if (curr > 0) {
			System.out.println("Input the currency name with cash on bank, format:");
		}
		for (int i = 0; i < curr; i++) {
			String cur;
			double stock;
			System.out.println("<Currency name> <Cash on bank>");
			cur = scanin.next();
			stock = scanin.nextDouble();
			rates.setStock(cur, stock);
		}
		scanin.close();
		System.out.println(rates);

		// read files Data.in
		System.out.println("##GETTING EXCHANGE DATA FROM FILE##");
		File file = new File("bankQueue/Data.in");
		try {
			Scanner data = new Scanner(file);
			while (data.hasNextLine()) {
				String Type;
				double money;
				String cur;
				boolean T;
				Type = data.next();
				money = data.nextDouble();
				cur = data.next();
				// sell==false buy==true
				if (Type.equalsIgnoreCase("BUY")) {
					T = true;
				} else {
					T = false;
				}
				queue.push(new Node(T, money, cur, 0));
			}
			data.close();
		} catch (FileNotFoundException e) {
			System.out.println("Data.in not found");
			System.out.println("Put Data.in at " + file.getAbsolutePath());

		}
		System.out.println(queue);

		// start transaction process
		System.out.println("##STRARTING QUEUE##");
		while (!queue.isEmpty()) {
			Node now = queue.pop();
			System.out.println("Now: " + now);
			double IDR = rates.getStock(local);
			double CUR = rates.getStock(now.curr);
			double BNOW = rates.getBuyRate(now.curr);
			double SNOW = rates.getSellRate(now.curr);
			if (now.t) {
				if (IDR >= BNOW * now.money) {
					System.out.println("Transaction Done");
					rates.setStock(local, IDR - BNOW * now.money);
					rates.setStock(now.curr, CUR + now.money);
				} else {
					System.out.println("Transaction Failed, Retrying");
					if (now.turn < 2) {
						queue.push(new Node(now.t, now.money, now.curr, now.turn + 1));
					} else {
						System.out.println("Already tried 2 times, failed to retry");
					}
				}
			} else {
				if (CUR >= now.money) {
					System.out.println("Transaction Done");
					rates.setStock(now.curr, CUR - now.money);
					rates.setStock(local, IDR + SNOW * now.money);
				} else {
					System.out.println("Transaction Failed, Retrying");
					if (now.turn < 2) {
						queue.push(new Node(now.t, now.money, now.curr, now.turn + 1));
					} else {
						System.out.println("Already tried 2 times, failed to retry");
					}
				}
			}
			System.out.println(rates.stored());
		}

		// All transaction finished
		System.out.println("##QUEUE FINISHED##");
		System.out.println(rates.stored());
	}
}