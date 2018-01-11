package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class DocumentParser implements Runnable {

	private int shingleSize;
	private int k;
	private int docId;
	private String file;
	private Deque<String> buffer = new LinkedList<>();
	private BlockingQueue<Shingle> q;

	public DocumentParser(String file, BlockingQueue<Shingle> q, int shingleSize, int k) {
		super();
		this.q = q;
		this.file = file;
		this.shingleSize = shingleSize;
		this.k = k;
	}

	/*
	 * public int getShingleSize() { return shingleSize; }
	 * 
	 * public void setShingleSize(int shingleSize) { this.shingleSize =
	 * shingleSize; }
	 * 
	 * public int getK() { return k; }
	 * 
	 * public void setK(int k) { this.k = k; }
	 * 
	 * public int getDocId() { return docId; }
	 * 
	 * public void setDocId(int docId) { this.docId = docId; }
	 * 
	 * public String getFile() { return file; }
	 * 
	 * public void setFile(String file) { this.file = file; }
	 * 
	 * public Deque<String> getBuffer() { return Buffer; }
	 * 
	 * public void setBuffer(Deque<String> buffer) { Buffer = buffer; }
	 * 
	 * public BlockingQueue<Shingle> getQueue() { return queue; }
	 * 
	 * public void setQueue(BlockingQueue<Shingle> queue) { this.queue = queue;
	 * }
	 */

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;

			while ((line = br.readLine()) != null) {
				String uLine = line.toUpperCase();
				String[] words = uLine.split(" ");

				addWordsBuffer(words);

				Shingle s = getNextShingle();
				q.put(s);
			} // while
			flushBuffer();
			br.close();

		} // try

		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}// run

	private void addWordsBuffer(String[] words) {
		for (String s : words) {
			buffer.addLast(s);

		}

	}

	private Shingle getNextShingle() {
		StringBuilder sb = new StringBuilder();
		int counter = 0;
		while (counter < shingleSize) {
			if (buffer.peek() != null) {
				sb.append(buffer.poll());
				counter++;
			}
		}
		if (sb.length() > 0) {
			return (new Shingle(docId, sb.toString().hashCode()));
		} else {
			return null;
		}

	}

	private void flushBuffer() throws Exception {
		while (buffer.size() > 0) {
			Shingle s = getNextShingle();
			if (s != null) {
				q.put(s);
			} else {
				q.put(new Poison(docId, 0));
			}
		}
	}

}
