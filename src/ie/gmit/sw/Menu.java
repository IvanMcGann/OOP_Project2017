package ie.gmit.sw;

import java.util.Scanner;

public class Menu {

	Scanner Scanner = new Scanner(System.in);
	private String doc1;
	private String doc2;
	private int option;
	private int shingleSize;
	private int k;
	private int blockingQueueSize;
	private int threadPoolSize;

	public int getShingleSize() {
		return shingleSize;
	}

	public void setShingleSize(int shingleSize) {
		this.shingleSize = shingleSize;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getThreadPoolSize() {
		return threadPoolSize;
	}

	public void setThreadPoolSize(int threadPoolSize) {
		this.threadPoolSize = threadPoolSize;
	}

	public int getBlockingQueueSize() {
		return blockingQueueSize;
	}

	public void setBlockingQueueSize(int blockingQueueSize) {
		this.blockingQueueSize = blockingQueueSize;
	}

	public Menu() {
		doc1 = new String();
		doc2 = new String();
		shingleSize = 5;
		k = 200;
		blockingQueueSize = 100;
		threadPoolSize = 10;
		option = 0;

	}

	public void show() {

		System.out.println("Enter the first document name");
		doc1 = Scanner.next();

		System.out.println("Enter the second document name");
		doc2 = Scanner.next();

		try {
			System.out.println("please " + doc1);
			Launch.launch(doc1, doc2);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
