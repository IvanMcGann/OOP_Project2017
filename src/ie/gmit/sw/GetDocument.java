package ie.gmit.sw;

public class GetDocument {
	private String path;

	public GetDocument() {
		this.path = new String();
	}

	public String getPath() {
		return "./" + path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
