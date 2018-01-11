package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Consumer implements Runnable {

	private BlockingQueue<Shingle> queue;
	private int k;
	private int[] minhashes;
	private Map<Integer, List<Integer>> map = new HashMap<>();
	private ExecutorService pool;

	public void init() {
		Random random = new Random();
		minhashes = new int[k];
		for (int i = 0; i < minhashes.length; i++) {
			minhashes[i] = random.nextInt();
		}
	}

	public Consumer(BlockingQueue<Shingle> queue, int k, int poolSize) {
		super();
		this.queue = queue;
		this.k = k;
		pool = Executors.newFixedThreadPool(poolSize);
		;
		init();
	}

	@Override
	public void run() {
		try {
			int docCount = 2;
			while (docCount > 0) {
				Shingle s = queue.take();
				if (s instanceof Poison) {
					docCount--;
				} else {
					pool.execute(new Runnable() {
						public void run() {
							for (int i = 0; i < minhashes.length; i++) {
								int value = s.getHashcode() ^ minhashes[i]; // ^
																			// -
																			// xor(Random
																			// generated
																			// key)
								List<Integer> list = map.get(s.getDocId1());
								if (list == null) {
									list = new ArrayList<Integer>(k);
									for (int j = 0; j < list.size(); j++) {
										list.set(j, Integer.MAX_VALUE);
									}
									map.put(s.getDocId1(), list);
								} else {
									if (list.get(i) > value) {
										list.set(i, value);
									}
								}
							}
						}
					});

				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
