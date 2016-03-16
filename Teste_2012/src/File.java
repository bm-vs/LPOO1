
public class File extends Node {	
	public File (Node parent, String name) throws DuplicateNameException {
		super(parent, name);
	}
	
	public File (Node parent, String name, int size) throws DuplicateNameException {
		super(parent, name);
		this.size = size;
	}
}
