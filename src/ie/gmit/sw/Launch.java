package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

//import javax.swing.text.html.parser.DocumentParser;

public class Launch {

	static Menu menu = new Menu();

	public static void launch(String F1, String F2) throws Exception {

		BlockingQueue<Shingle> bq = new LinkedBlockingQueue<>(menu.getBlockingQueueSize());
		Thread t1 = new Thread(new DocumentParser(F1, bq, menu.getShingleSize(), menu.getK()), "T1");
		Thread t2 = new Thread(new DocumentParser(F2, bq, menu.getShingleSize(), menu.getK()), "T2");
		Thread t3 = new Thread(new Consumer(bq, menu.getK(), menu.getThreadPoolSize()), "T3");

		t1.start();
		t2.start();
		t3.start();

		t1.join();
		t2.join();
		t3.join();
	}

}
