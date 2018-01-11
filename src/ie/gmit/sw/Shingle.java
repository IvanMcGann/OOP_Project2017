package ie.gmit.sw;

public class Shingle {

	private int docId1;
	// private int docId2;

	private int hashcode;

	public Shingle(int docId1, int hashcode) {
		super();
		this.docId1 = docId1;
		this.hashcode = hashcode;
	}

	public int getDocId1() {
		return docId1;
	}

	public void setDocId1(int docId1) {
		this.docId1 = docId1;
	}

	public int getHashcode() {
		return hashcode;
	}

	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}

}
